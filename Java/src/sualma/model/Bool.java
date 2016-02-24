/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a boolean value.
 */
public final class Bool extends Obj
{
    public Bool(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 31 * hash + (this.value ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other)
    {   
        return other instanceof Bool && ((Bool) other).value == value;
    }

    @Override
    public String toString()
    {
        return super.toString() + (value ? "true" : "false");
    }
    
    private final boolean value;
}
