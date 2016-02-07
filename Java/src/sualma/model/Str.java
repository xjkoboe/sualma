/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Object holding a string value.
 */
public class Str extends Obj
{
    public Str(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    private final String value;
}
