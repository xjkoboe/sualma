/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import java.util.Objects;

/**
 * Object holding a head (Obj) and a body (Obj).
 * 
 * This can be used to represent a function call, 
 * where the head denotes a function and the body the argument, as in
 *     new Call( new Name("sin"), new Num("0") );
 * 
 * The argument can of course be a list:
 *     args = new List();
 *     args.addElement(new Num("10"));
 *     args.addElement(new Num("20"));
 *     new Call( new Name("sum"), args );
 * 
 * A Call can also represent a typed object. In this case the
 * head denotes a (unique) type and the body the content:
 *     new Call( new Name("Message"), new Str("hello") );
 * Equivalently, this can be regarded as a constructor call.
 */
public final class Call extends Obj
{
    public Call(Obj head, Obj body)
    {
        this.head = head;
        this.body = body;
    }

    public Obj getHead()
    {
        return head;
    }

    public Obj getBody()
    {
        return body;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.head);
        hash = 29 * hash + Objects.hashCode(this.body);
        return hash;
    }

    @Override
    public boolean equals(Object other)
    {   
        return other instanceof Call 
            && ((Call) other).head.equals(head) 
            && ((Call) other).body.equals(body);
    }

    @Override
    public String toString()
    {
        return "(" + head + " " + body + ")";
    }
    
    private final Obj head;
    private final Obj body;
}
