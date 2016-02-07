/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

public class Bool extends Obj
{
    public Bool(boolean value)
    {
        this.value = value;
    }

    public Bool(String label, boolean value)
    {
        super(label);
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }
    
    private boolean value;
}
