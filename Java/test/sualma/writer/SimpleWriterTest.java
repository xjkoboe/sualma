package sualma.writer;

import org.junit.Test;
import static org.junit.Assert.*;
import sualma.model.*;
import sualma.parser.Parser;
import sualma.util.ObjFactory;

/**
 * @author wijnand.schepens@gmail.com
 */
public class SimpleWriterTest extends ObjFactory
{
    Parser parser = new Parser();
 
    @Test
    public void TestNum()
    {
        Obj obj = num(123);
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("123", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestName()
    {
        Obj obj = name("abc");
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("abc", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

   
    @Test
    public void TestBool()
    {
        Obj obj = True;
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("true", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
   
    @Test
    public void TestStr()
    {
        Obj obj = str("abc");
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("\"abc\"", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
   
    @Test
    public void TestEmptyList()
    {
        Obj obj = list();
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("()", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestOneElementList()
    {
        Obj obj = list(num(123));
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("( 123, )", text);
    }

    @Test
    public void TestTwoElementList()
    {
        Obj obj = list(num(123), name("abc"));
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("( 123, abc )", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestCall()
    {
        Obj obj = call(name("f"), num(123));
        
        String text = SimpleWriter.toString(obj);
        
        assertEquals("( f 123 )", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
    
}