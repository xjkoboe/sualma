package sualma.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wijnand.schepens@gmail.com
 */
public class Tokenizer
{
    private final Pattern whitespacePattern;
    private final Map<Token.Type, Pattern> patternMap = new LinkedHashMap<>();

    private final Set<String> reserved;
    
    // aux:
    private String text;
    private int start;
    private int lineNumber;

    public Tokenizer(String... reserved)
    {
        this.reserved = new HashSet<>(Arrays.asList(reserved));
        
        whitespacePattern = Pattern.compile("\\s+");

        patternMap.put(Token.Type.String,   Pattern.compile("\\\".*?\\\""));
        patternMap.put(Token.Type.Number,   Pattern.compile("\\d+(\\.\\d+)?"));  // int/real, no unary minus, digits before and after decimal point obligatory
        patternMap.put(Token.Type.Name,     Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*"));
        patternMap.put(Token.Type.Bracket,  Pattern.compile("\\(|\\)"));
        patternMap.put(Token.Type.Operator, Pattern.compile("\\+|\\-|\\*|/|\\^|==|!=|<=|>=|<|>|=|,|;|\\."));
    }

    public Token[] scan(String text) throws TokenizerError
    {
        String[] lines = text.split("\n", Integer.MAX_VALUE);

        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < lines.length; i++)
        {
            tokens.addAll(scanLine(lines[i], i));
        }
        
        tokens.add(new Token("", Token.Type.EndOfText, new TextLocation("", lines.length, 0))); 
        
        return tokens.toArray(new Token[tokens.size()]);
    }

    private List<Token> scanLine(String line, int lineNumber) throws TokenizerError
    {
        text = line;
        start = 0;
        lineNumber = lineNumber;

        List<Token> tokens = new ArrayList<>();
        
        int commentStart = text.indexOf("//");
        if (commentStart >= 0)
            this.text = text.substring(0, commentStart);
        
        while (start < text.length() && text.charAt(start) == ' ')
            start++;
        tokens.add(new Token(text.substring(0, start), Token.Type.LeadingSpaces));
        
        while (start < text.length())
        {
            Token t = match();
            if (t != null)
            {
                if (reserved.contains(t.getText()))
                    t.setType(Token.Type.Reserved);
                tokens.add(t);
            } 
            else
            {
                throw new TokenizerError("Unknown token", new TextLocation(line, lineNumber, start));
            }
            skipWhitespace();
        }
        return tokens;
    }

    private int skipWhitespace()
    {
        CharSequence cs = text.subSequence(start, text.length());
        int len = matchLength(cs, whitespacePattern);
        start += len;
        return len;
    }

    private Token match()
    {
        CharSequence cs = text.subSequence(start, text.length());
        for (Token.Type tt : patternMap.keySet())
        {
            Pattern p = patternMap.get(tt);

            int len = matchLength(cs, p);
            if (len > 0)
            {
                Token t = new Token(text.substring(start, start + len), tt, new TextLocation(this.text, this.lineNumber, start));
                start += len;
                return t;
            }
        }
        return null;
    }

    private int matchLength(CharSequence cs, Pattern p)
    {
        Matcher m = p.matcher(cs);
        
        return m.lookingAt() ? m.end() : 0;
    }
}
