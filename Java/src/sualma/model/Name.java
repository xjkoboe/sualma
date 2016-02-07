/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a name value.
 * 
 * A name can be used to refer to a list element (with label==name).
 */
public class Name extends Obj
{
    public Name(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    private final String value;
}
