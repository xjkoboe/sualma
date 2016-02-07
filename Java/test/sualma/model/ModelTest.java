/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import org.junit.Test;
import static org.junit.Assert.*;
import sualma.util.ObjFactory;

public class ModelTest extends ObjFactory
{
    @Test
    public void TestStr()
    {
        Str s1 = new Str("abc");
        assertEquals("abc", s1.getValue());
        assertNull(s1.getLabel());
    }

    @Test
    public void TestNum()
    {
        Num n1 = new Num("123");
        assertEquals("123", n1.getValue());
        assertNull(n1.getLabel());
    }
    
    @Test
    public void TestBool()
    {
        Bool b1 = new Bool(false);
        assertEquals(false, b1.getValue());
        assertNull(b1.getLabel());
    }

    @Test
    public void TestName()
    {
        Name n1 = new Name("abc");
        assertEquals("abc", n1.getValue());
        assertNull(n1.getLabel());
    }
    
    @Test
    public void TestSpec()
    {
        Obj name = new Name("sin");
        Obj arg = new Num("0");
        Call s = new Call(name, arg);
        assertSame(name, s.getHead());
        assertSame(arg, s.getBody());
    }
    
    @Test
    public void TestList()
    {
        List l = new List();
        assertNull(l.getLabel());
        assertEquals(0, l.getCount());
        
        Obj el1 = new Str("abc");
        l.addElement(el1);
        assertEquals(1, l.getCount());
        assertSame(el1, l.getElement(0));
        
        Obj el2 = new Num("123");
        l.addElement(el2);
        assertEquals(2, l.getCount());
        assertSame(el2, l.getElement(1));
    }
    
    @Test
    public void TestListWithLabels()
    {
        List l = new List();
        
        Obj el1 = new Str("John");
        el1.setLabel("name");
        l.addElement(el1);
        assertEquals(1, l.getCount());
        assertSame(el1, l.getElement(0));
        assertSame(el1, l.getElement("name"));
        
        Obj el2 = new Num("123");
        el2.setLabel("age");
        l.addElement(el2);
        assertEquals(2, l.getCount());
        assertSame(el2, l.getElement(1));
        assertSame(el2, l.getElement("age"));
        
        assertNull(l.getElement("foo"));
    }
    
    @Test
    public void testEquality()
    {
        assertNotSame(num(12), num(12));
        assertEquals(num(12), num(12));
        assertFalse(num(12).equals(num(34)));
        
        assertEquals(str("abc"), str("abc"));
        assertFalse(str("abc").equals(str("def")));
        
        assertEquals(name("abc"), name("abc"));
        assertFalse(name("abc").equals(name("def")));
        
        assertEquals(True, new Bool(true));
        assertEquals(False, new Bool(false));
        assertFalse(True.equals(False));
        
        assertEquals(call(name("foo"), name("bar")),
                     call(name("foo"), name("bar")));
        
        assertFalse(call(name("foo"), name("bar"))
            .equals(call(name("foofoo"), name("bar"))));
        
        assertFalse(call(name("foo"), name("bar"))
            .equals(call(name("foo"), name("barbar"))));
        
        assertEquals(list(name("foo"), num(123)),
                     list(name("foo"), num(123)));
        
        assertFalse(list(name("foo"), num(123))
            .equals(list(name("foo"))));
        
        assertFalse(list(name("foo"), num(123))
            .equals(list(name("foofoo"), num(123))));
        
        assertFalse(list(name("foo"), name("bar"))
            .equals(list(name("foo"), num(456))));
    }
}
