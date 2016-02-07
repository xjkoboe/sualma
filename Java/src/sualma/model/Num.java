/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a numeric value.
 * 
 * Note: the value is stored as a string. 
 * This may change when the type system is expanded...
 */
public class Num extends Obj
{
    public Num(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    private final String value;
}
