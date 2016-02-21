package sualma.writer;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sualma.model.Obj;
import sualma.parser.Parser;
import sualma.util.ObjFactory;
import static sualma.util.ObjFactory.True;
import static sualma.util.ObjFactory.call;
import static sualma.util.ObjFactory.list;
import static sualma.util.ObjFactory.name;
import static sualma.util.ObjFactory.num;
import static sualma.util.ObjFactory.str;

/**
 * @author wijnand.schepens@gmail.com
 */
public class MultilineWriterTest extends ObjFactory
{
    Parser parser = new Parser();
 
    @Test
    public void TestNum()
    {
        Obj obj = num(123);
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("123\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestName()
    {
        Obj obj = name("abc");
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("abc\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

   
    @Test
    public void TestBool()
    {
        Obj obj = True;
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("true\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
   
    @Test
    public void TestStr()
    {
        Obj obj = str("abc");
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("\"abc\"\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
   
    @Test
    public void TestEmptyList()
    {
        Obj obj = list();
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("()\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestOneElementList()
    {
        Obj obj = list(num(123));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("( 123, )\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestTwoElementList()
    {
        Obj obj = list(num(123), name("abc"));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("123\nabc\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestComposedList()
    {
        Obj obj = list(list(num(1), name("a")),
                       name("x"),
                       list(num(2), name("b")));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("(\n    1\n    a\n)\nx\n(\n    2\n    b\n)\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }

    @Test
    public void TestCall()
    {
        Obj obj = call(name("f"), num(123));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("f\n    123\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
    
    @Test
    public void TestCall2()
    {
        Obj obj = call(name("f"), call(name("g"), num(123)));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("f\n    g\n        123\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
    
    @Test
    public void TestCall3()
    {
        Obj obj = call(name("f"), list(num(1), num(2)));
        
        String text = MultilineWriter.toString(obj);
        
        assertEquals("f\n    1\n    2\n", text);
        
        assertEquals(obj, parser.parse(text)); // roundtrip
    }
    
}