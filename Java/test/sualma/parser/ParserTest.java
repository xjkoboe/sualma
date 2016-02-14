package sualma.parser;

import org.junit.Test;
import static org.junit.Assert.*;
import sualma.model.*;
import sualma.util.ObjFactory;

/**
 * @author wijnand.schepens@gmail.com
 */
public class ParserTest extends ObjFactory
{
    Parser p = new Parser();

//    @Test(expected = ParserException.class)
//    public void testEmpty()
//    {
//        p.parse("");
//    }
    
    @Test
    public void testNum()
    {
        Obj res = p.parse("123");
        
        assertEquals(num(123), res);
    }
    
    @Test
    public void testNum2()
    {
        Obj res = p.parse("// A number\n\n  123"); // TODO: trailing \n fails!
        
        assertEquals(num(123), res);
    }

    @Test
    public void testName()
    {
        Obj res = p.parse("abc\n");
        
        assertEquals(name("abc"), res);
    }
    
    @Test
    public void testCall1()
    {
        Obj res = p.parse("abc 123\n");
        
        assertEquals(call(name("abc"), num(123)), res);
    }

    @Test
    public void testCall2()
    {
        Obj res = p.parse("a b c\n");
        
        assertEquals(call(name("a"), 
                          call(name("b"),
                               name("c"))), res);
    }

    @Test
    public void testBinOp1()
    {
        Obj res = p.parse("abc + 123\n");
        
        assertEquals(call(name("+"), list(name("abc"), num(123))), res);
    }

    @Test
    public void testBinOp2()
    {
        Obj res = p.parse("a + b * c\n");
        
        assertEquals(call(name("+"), 
                          list(name("a"),
                               call(name("*"),
                                    list(name("b"), name("c"))))), 
                     res);
    }

    @Test
    public void testBrackets1()
    {
        Obj res = p.parse("(abc)");
        
        assertEquals(name("abc"), res);
    }

    @Test
    public void testBrackets2()
    {
        Obj res = p.parse("a * (b + c)");
        
        assertEquals(call(name("*"),
                          list(name("a"),
                               call(name("+"),
                                    list(name("b"), name("c"))))), res);
    }

    @Test
    public void testList0()
    {
        Obj res = p.parse("()");
        
        assertEquals(list(), res);
    }

    @Test
    public void testList1()
    {
        Obj res = p.parse("(a,)");
        
        assertEquals(list(name("a")), res);
    }

    @Test
    public void testList2()
    {
        Obj res = p.parse("a, b");
        
        assertEquals(list(name("a"), name("b")), res);
    }

    @Test
    public void testList3()
    {
        Obj res = p.parse("a, b, c");
        
        assertEquals(list(name("a"), name("b"), name("c")), res);
    }

    @Test
    public void testList4()
    {
        Obj res = p.parse("a, b; c; d, e");
        
        assertEquals(list(list(name("a"), name("b")),
                          name("c"), 
                          list(name("d"), name("e"))), res);
    }

    @Test
    public void testIndent1()
    {
        Obj res = p.parse("a\nb");
        
        assertEquals(list(name("a"), name("b")), res);
    }

    @Test
    public void testIndent2()
    {
        Obj res = p.parse("a\nb\nc");
        
        assertEquals(list(name("a"), name("b"), name("c")), res);
    }

    @Test
    public void testIndent3()
    {
        Obj res = p.parse("a\n  b");
        
        assertEquals(call(name("a"), name("b")), res);
    }

    @Test
    public void testIndent4()
    {
        Obj res = p.parse("a\n  b\n  c");
        
        assertEquals(call(name("a"), 
                          list(name("b"), 
                               name("c"))), res);
    }

    @Test
    public void testIndent5()
    {
        Obj res = p.parse("a\n  b\n    c");
        
        assertEquals(call(name("a"), 
                          call(name("b"), 
                               name("c"))), res);
    }

    @Test
    public void testIndent6()
    {
        Obj res = p.parse("a\n  b\nc");
        
        assertEquals(list(call(name("a"), 
                               name("b")),
                          name("c")), res);
    }

    @Test
    public void testIndent7()
    {
        Obj res = p.parse("a\n  b\n  c\nd");
        
        assertEquals(list(call(name("a"), 
                               list(name("b"),
                                    name("c"))),
                          name("d")), res);
    }
    
    @Test
    public void testMixed1()
    {
        Obj res = p.parse("a, b\nc, d");
        
        assertEquals(list(list(name("a"), 
                               name("b")),
                          list(name("c"),
                               name("d"))), res);
    }
    
    @Test
    public void testMixed2()
    {
        Obj res = p.parse("a + \nb");
        
        assertEquals(call(name("+"),
                          list(name("a"),
                               name("b"))), res);
    }
    
    @Test
    public void testMixed3()
    {
        Obj res = p.parse("a + \n  b");
        
        assertEquals(call(name("+"),
                          list(name("a"),
                               name("b"))), res);
    }
    
    @Test
    public void testMixed4()
    {
        Obj res = p.parse("  a + \n  b");
        
        assertEquals(call(name("+"),
                          list(name("a"),
                               name("b"))), res);
    }
   
    // desired??
    @Test
    public void testMixed5()
    {
        Obj res = p.parse("  a + \nb");
        
        assertEquals(call(name("+"),
                          list(name("a"),
                               name("b"))), res);
    }
}
        