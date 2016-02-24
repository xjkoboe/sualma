/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.util;

import sualma.model.*;

public class ObjFactory
{
    public static final Bool True = new Bool(true);
    public static final Bool False = new Bool(false);

    public static Name name(String value) { return new Name(value); }
    public static Name name(String label, String value) { Name n = new Name(value); n.setLabel(label); return n; }
    
    public static Num  num (String value) { return new Num(value); }
    public static Num  num (int value) { return new Num(Integer.toString(value)); }
    public static Num  num (String label, int value) { Num n = num(value); n.setLabel(label); return n; }
    public static Num  num (double value) { return new Num(Double.toString(value)); }
    
    public static Str  str (String value) { return new Str(value); }
    
    public static Call call(Obj head, Obj body) { return new Call(head, body); }
    
    public static List list(Obj... elements) { return new List(elements); }
    public static List list(String label, Obj... elements) { List l = new List(elements); l.setLabel(label); return l; }
    
}