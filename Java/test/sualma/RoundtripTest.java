package sualma;

import org.junit.Test;
import static org.junit.Assert.*;
import sualma.model.*;
import sualma.parser.Parser;
import sualma.util.ObjFactory;
import static sualma.util.ObjFactory.True;
import static sualma.util.ObjFactory.call;
import static sualma.util.ObjFactory.list;
import static sualma.util.ObjFactory.name;
import static sualma.util.ObjFactory.num;
import static sualma.util.ObjFactory.str;
import sualma.writer.Writer;

/**
 * @author wijnand.schepens@gmail.com
 */
public class RoundtripTest extends ObjFactory
{
    Parser parser = new Parser();
    
    @Test
    public void TestNum()
    {
        Obj obj = num(123);
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }

    @Test
    public void TestName()
    {
        Obj obj = name("abc");
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }

    @Test
    public void TestBool()
    {
        Obj obj = True;
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }
   
    @Test
    public void TestStr()
    {
        Obj obj = str("abc");
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }
   
    @Test
    public void TestEmptyList()
    {
        Obj obj = list();
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }

    @Test
    public void TestOneElementList()
    {
        Obj obj = list(num(123));
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }

    @Test
    public void TestTwoElementList()
    {
        Obj obj = list(num(123), name("abc"));
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }

    @Test
    public void TestCall()
    {
        Obj obj = call(name("f"), num(123));
        
        String text = Writer.write(obj);
        Obj obj2 = parser.parse(text);
        assertEquals(obj, obj2);
    }
    
}