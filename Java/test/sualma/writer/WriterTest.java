package sualma.writer;

import org.junit.Test;
import static org.junit.Assert.*;
import sualma.model.*;
import sualma.util.ObjFactory;

/**
 * @author wijnand.schepens@gmail.com
 */
public class WriterTest extends ObjFactory
{
    @Test
    public void TestNum()
    {
        Obj obj = num(123);
        
        String text = Writer.write(obj);
        
        assertEquals("123", text);
    }

    @Test
    public void TestName()
    {
        Obj obj = name("abc");
        
        String text = Writer.write(obj);
        
        assertEquals("abc", text);
    }

   
    @Test
    public void TestBool()
    {
        Obj obj = True;
        
        String text = Writer.write(obj);
        
        assertEquals("true", text);
    }
   
    @Test
    public void TestStr()
    {
        Obj obj = str("abc");
        
        String text = Writer.write(obj);
        
        assertEquals("\"abc\"", text);
    }
   
    @Test
    public void TestEmptyList()
    {
        Obj obj = list();
        
        String text = Writer.write(obj);
        
        assertEquals("()", text);
    }

    @Test
    public void TestOneElementList()
    {
        Obj obj = list(num(123));
        
        String text = Writer.write(obj);
        
        assertEquals("( 123, )", text);
    }

    @Test
    public void TestTwoElementList()
    {
        Obj obj = list(num(123), name("abc"));
        
        String text = Writer.write(obj);
        
        assertEquals("( 123, abc )", text);
    }

    @Test
    public void TestCall()
    {
        Obj obj = call(name("f"), num(123));
        
        String text = Writer.write(obj);
        
        assertEquals("( f 123 )", text);
    }
    
}