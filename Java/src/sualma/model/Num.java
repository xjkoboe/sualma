/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import java.util.Objects;

/**
 * Object holding a numeric value.
 * 
 * Note: the value is stored as a string. 
 * This may change when the type system is expanded...
 */
public final class Num extends Obj
{
    public Num(String value)
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
        return other instanceof Num && ((Num) other).value.equals(value);
    }
    
    private final String value;
}
