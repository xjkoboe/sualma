/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import java.util.Objects;

/**
 * Object holding a name value.
 * 
 * A name can be used to refer to a list element (with label==name).
 */
public final class Name extends Obj
{
    public Name(String value)
    {
        assert(value != null);
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object other)
    {   
        return other instanceof Name && ((Name) other).value.equals(value);
    }

    @Override
    public String toString()
    {
        return value;
    }
    
    private final String value;
}
