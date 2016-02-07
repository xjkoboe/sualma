/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a head (Obj) and a body (Obj).
 * 
 * This can be used to represent a function call, 
 * where the head denotes a function and the body the argument, as in
 *     new Spec( new Name("sin"), new Num("0") );
 * 
 * The argument can of course be a list:
 *     args = new List();
 *     args.addElement(new Num("10"));
 *     args.addElement(new Num("20"));
 *     new Spec( new Name("sum"), args );
 * 
 * A Spec can also represent a typed object. In this case the
 * head denotes a (unique) type and the body the content:
 *     new Spec( new Name("Message"), new Str("hello") );
 * Equivalently, this can be regarded as a constructor call.
 */
public class Spec extends Obj
{
    public Spec(Obj head, Obj body)
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
    
    private final Obj head;
    private final Obj body;
}
