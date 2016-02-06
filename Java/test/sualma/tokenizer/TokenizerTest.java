package sualma.tokenizer;

import org.junit.Test;
import static org.junit.Assert.*;

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
        
        assertEquals(2, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("",                       tokens[0].getText());
        assertEquals(Token.Type.EndOfText,     tokens[1].getType());
    }
    
    @Test
    public void testEmptyLine()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "\n";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(3, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("",                       tokens[0].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[1].getType());
        assertEquals("",                       tokens[1].getText());
        assertEquals(Token.Type.EndOfText,     tokens[2].getType());
    }
    
    @Test
    public void testBasic()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "  a = \"abc\"\n";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(6, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("  ",                     tokens[0].getText());
        assertEquals(Token.Type.Name,          tokens[1].getType());
        assertEquals("a",                      tokens[1].getText());
        assertEquals(Token.Type.Operator,      tokens[2].getType());
        assertEquals("=",                      tokens[2].getText());
        assertEquals(Token.Type.String,        tokens[3].getType());
        assertEquals("\"abc\"",                tokens[3].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[4].getType());
        assertEquals("",                       tokens[4].getText());
        assertEquals(Token.Type.EndOfText,     tokens[5].getType());
    }
    
    @Test
    public void testBracketsAndOperators()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "  ( )\n  , ; . =  +  -  *  /  ^  <  >  <=  >=  !=";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(19, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("  ",                     tokens[0].getText());
        assertEquals(Token.Type.Bracket,       tokens[1].getType());
        assertEquals("(",                      tokens[1].getText());
        assertEquals(Token.Type.Bracket,       tokens[2].getType());
        assertEquals(")",                      tokens[2].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[3].getType());
        assertEquals("  ",                     tokens[3].getText());
        assertEquals(Token.Type.Operator,      tokens[4].getType());
        assertEquals(",",                      tokens[4].getText());
        assertEquals(Token.Type.Operator,      tokens[5].getType());
        assertEquals(";",                      tokens[5].getText());
        assertEquals(Token.Type.Operator,      tokens[6].getType());
        assertEquals(".",                      tokens[6].getText());
        assertEquals(Token.Type.Operator,      tokens[7].getType());
        assertEquals("=",                      tokens[7].getText());
        assertEquals(Token.Type.Operator,      tokens[8].getType());
        assertEquals("+",                      tokens[8].getText());
        assertEquals(Token.Type.Operator,      tokens[9].getType());
        assertEquals("-",                      tokens[9].getText());
        assertEquals(Token.Type.Operator,      tokens[10].getType());
        assertEquals("*",                      tokens[10].getText());
        assertEquals(Token.Type.Operator,      tokens[11].getType());
        assertEquals("/",                      tokens[11].getText());
        assertEquals(Token.Type.Operator,      tokens[12].getType());
        assertEquals("^",                      tokens[12].getText());
        assertEquals(Token.Type.Operator,      tokens[13].getType());
        assertEquals("<",                      tokens[13].getText());
        assertEquals(Token.Type.Operator,      tokens[14].getType());
        assertEquals(">",                      tokens[14].getText());
        assertEquals(Token.Type.Operator,      tokens[15].getType());
        assertEquals("<=",                     tokens[15].getText());
        assertEquals(Token.Type.Operator,      tokens[16].getType());
        assertEquals(">=",                     tokens[16].getText());
        assertEquals(Token.Type.Operator,      tokens[17].getType());
        assertEquals("!=",                     tokens[17].getText());
        assertEquals(Token.Type.EndOfText,     tokens[18].getType());
    }
    
    @Test
    public void testNumbers()
    {
        Tokenizer tokenizer = new Tokenizer();
  
        String text = "  0 10 1.2 12.34";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(6, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("  ",                     tokens[0].getText());
        assertEquals(Token.Type.Number,        tokens[1].getType());
        assertEquals("0",                      tokens[1].getText());
        assertEquals(Token.Type.Number,        tokens[2].getType());
        assertEquals("10",                     tokens[2].getText());
        assertEquals(Token.Type.Number,        tokens[3].getType());
        assertEquals("1.2",                    tokens[3].getText());
        assertEquals(Token.Type.Number,        tokens[4].getType());
        assertEquals("12.34",                  tokens[4].getText());
        assertEquals(Token.Type.EndOfText,     tokens[5].getType());
    }
    
    @Test
    public void testEmptyLines()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "\n 123 \n  \n";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(6, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("",                       tokens[0].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[1].getType());
        assertEquals(" ",                      tokens[1].getText());
        assertEquals(Token.Type.Number,        tokens[2].getType());
        assertEquals("123",                    tokens[2].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[3].getType());
        assertEquals("  ",                     tokens[3].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[4].getType());
        assertEquals("",                       tokens[4].getText());
        assertEquals(Token.Type.EndOfText,     tokens[5].getType());
    }
    
    @Test
    public void testCommentAndEmptyLine()
    {
        Tokenizer tokenizer = new Tokenizer();
        
        String text = "// comment1\n 123//comment2\n";
        Token[] tokens = tokenizer.scan(text);
        
        assertEquals(5, tokens.length);
        assertEquals(Token.Type.LeadingSpaces, tokens[0].getType());
        assertEquals("",                       tokens[0].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[1].getType());
        assertEquals(" ",                      tokens[1].getText());
        assertEquals(Token.Type.Number,        tokens[2].getType());
        assertEquals("123",                    tokens[2].getText());
        assertEquals(Token.Type.LeadingSpaces, tokens[3].getType());
        assertEquals("",                       tokens[3].getText());
        assertEquals(Token.Type.EndOfText,     tokens[4].getType());
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
        catch (TokenizerError e)
        {
            assertEquals(0, e.getLocation().getLine());
            assertEquals(2, e.getLocation().getColumn());
            assertEquals("  $", e.getLocation().getLineText());
            assertEquals("line 0:\n  $\n  ^", e.getLocation().toString());
        }
    }
}
