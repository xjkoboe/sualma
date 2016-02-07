/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a boolean value.
 */
public class Bool extends Obj
{
    public Bool(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }
    
    private final boolean value;
}
