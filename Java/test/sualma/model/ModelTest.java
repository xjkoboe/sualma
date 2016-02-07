/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest
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
        Spec s = new Spec(name, arg);
        assertEquals(name, s.getHead());
        assertEquals(arg, s.getBody());
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
        assertEquals(el1, l.getElement(0));
        
        Obj el2 = new Num("123");
        l.addElement(el2);
        assertEquals(2, l.getCount());
        assertEquals(el2, l.getElement(1));
    }
    
    @Test
    public void TestListWithLabels()
    {
        List l = new List();
        
        Obj el1 = new Str("John");
        el1.setLabel("name");
        l.addElement(el1);
        assertEquals(1, l.getCount());
        assertEquals(el1, l.getElement(0));
        assertEquals(el1, l.getElement("name"));
        
        Obj el2 = new Num("123");
        el2.setLabel("age");
        l.addElement(el2);
        assertEquals(2, l.getCount());
        assertEquals(el2, l.getElement(1));
        assertEquals(el2, l.getElement("age"));
        
        assertNull(l.getElement("foo"));
    }
}
