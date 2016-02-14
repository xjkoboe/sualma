package sualma.parser;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static sualma.parser.Token.Type.*;

/**
 * @author wijnand.schepens@gmail.com
 */
public class TokenizerTest
{
    @Test
    public void testEmpty()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(EndOfText), types(tokens));
    }
    
    @Test
    public void testEmptyLines()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "\n\n";

        Token[] tokens = tokenizer.scan(text);
        
        assertArrayEquals(types(EndOfText), types(tokens));
    }
    
    @Test
    public void testEmptyLinesAndComments()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "// Hello\n    \n\n    // World!\n";

        Token[] tokens = tokenizer.scan(text);
        
        assertArrayEquals(types(EndOfText), types(tokens));
    }
    
    @Test
    public void testSimple()
    {
        Tokenizer tokenizer = new Tokenizer("true");
        
        String text = "abc 123 \"abc\"";

        Token[] tokens = tokenizer.scan(text);
        
        assertArrayEquals(types(Indent, Name , Number, String    , EndOfText), types(tokens));
        assertArrayEquals(texts(""    , "abc", "123" , "\"abc\"" , ""       ), texts(tokens));
    }
    
    @Test
    public void testNumbers()
    {
        Tokenizer tokenizer = new Tokenizer();
  
        String text = "  0 10 1.2 12.34 ";
        
        Token[] tokens = tokenizer.scan(text);
        
        assertArrayEquals(types(Indent, Number, Number, Number, Number , EndOfText), types(tokens));
        assertArrayEquals(texts("  "  , "0"   , "10"  , "1.2" , "12.34", ""       ), texts(tokens));
    }
    
    @Test
    public void testReserved()
    {
        Tokenizer tokenizer = new Tokenizer("true");
        
        String text = " abc true ";

        Token[] tokens = tokenizer.scan(text);
        
        assertArrayEquals(types(Indent, Name , Reserved, EndOfText), types(tokens));
        assertArrayEquals(texts(" "   , "abc", "true"  , ""       ), texts(tokens));
    }
    
    
    @Test
    public void testBrackets()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "( ) ";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(Indent, Bracket, Bracket, EndOfText), types(tokens));
        assertArrayEquals(texts(""    , "("    , ")"    , ""       ), texts(tokens));
    }
    
    @Test
    public void testOperators1()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = " , ; . = ";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(Indent, Operator, Operator, Operator, Operator, EndOfText), types(tokens));
        assertArrayEquals(texts(" "   , ","     , ";"     , "."     , "="     , ""       ), texts(tokens));
    }
    
    @Test
    public void testOperators2()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "<  >  <=  >=  == !=";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(Indent, Operator, Operator, Operator, Operator, Operator, Operator, EndOfText), types(tokens));
        assertArrayEquals(texts(""    , "<"     , ">"     , "<="    , ">="    , "=="    , "!="    , ""       ), texts(tokens));
    }
    
    @Test
    public void testOperators3()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "+  -  *  / ^";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(Indent, Operator, Operator, Operator, Operator, Operator, EndOfText), types(tokens));
        assertArrayEquals(texts(""    , "+"     , "-"     , "*"     , "/"     , "^"     , ""       ), texts(tokens));
    }

    @Test
    public void testIndentation()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "0\n  1\n    2\n\n    2\n      3\n  1";

        Token[] tokens = tokenizer.scan(text);

        assertArrayEquals(types(Indent, Number, Indent, Number, Indent, Number, Indent, Number, Indent  , Number, Indent, Number, EndOfText), types(tokens));
        assertArrayEquals(texts(""    , "0"   , "  "  , "1"   , "    ", "2"   , "    ", "2"   , "      ", "3"   , "  "  , "1"   , ""       ), texts(tokens));
    }


    @Test //(expected = TokenizerError.class)
    public void testIllegal3()
    {
        try
        {
            Tokenizer tokenizer = new Tokenizer();
        
            String text = "  $";
            
            tokenizer.scan(text);
            
            fail();
        }
        catch (TokenizerException e)
        {
            assertEquals(0, e.getLocation().getLine());
            assertEquals(2, e.getLocation().getColumn());
            assertEquals("  $", e.getLocation().getLineText());
            assertEquals("line 0:\n  $\n  ^", e.getLocation().toString());
        }
    }

    
    
    private Token.Type[] types(Token... tokens)
    {
        List<Token.Type> types = new ArrayList<>();
        for (Token t: tokens)
            types.add(t.getType());
        return types.toArray(new Token.Type[0]);
    }

    private Token.Type[] types(Token.Type... t)
    {
        return t;
    }
    
    private String[] texts(Token... tokens)
    {
        List<String> texts = new ArrayList<>();
        for (Token t: tokens)
            texts.add(t.getText());
        return texts.toArray(new String[0]);
    }
    
    private String[] texts(String... t)
    {
        return t;
    }
}
