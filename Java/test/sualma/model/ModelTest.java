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
        
        Str s2 = new Str("s", "abc");
        assertEquals("abc", s2.getValue());
        assertEquals("s", s2.getLabel());
    }

    @Test
    public void TestNum()
    {
        Num n1 = new Num("123");
        assertEquals("123", n1.getValue());
        assertNull(n1.getLabel());
        
        Num n2 = new Num("n", "123");
        assertEquals("123", n2.getValue());
        assertEquals("n", n2.getLabel());
    }
    
    @Test
    public void TestBool()
    {
        Bool b1 = new Bool(false);
        assertEquals(false, b1.getValue());
        assertNull(b1.getLabel());
        
        Bool b2 = new Bool("b", false);
        assertEquals(false, b2.getValue());
        assertEquals("b", b2.getLabel());
    }

    @Test
    public void TestName()
    {
        Name n1 = new Name("abc");
        assertEquals("abc", n1.getValue());
        assertNull(n1.getLabel());
        
        Name n2 = new Name("n", "abc");
        assertEquals("abc", n2.getValue());
        assertEquals("n", n2.getLabel());
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
        
        Obj el1 = new Str("name", "John");
        l.addElement(el1);
        assertEquals(1, l.getCount());
        assertEquals(el1, l.getElement(0));
        assertEquals(el1, l.getElement("name"));
        
        Obj el2 = new Num("age", "123");
        l.addElement(el2);
        assertEquals(2, l.getCount());
        assertEquals(el2, l.getElement(1));
        assertEquals(el2, l.getElement("age"));
    }
}
