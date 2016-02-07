/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

public class Name extends Obj
{
    public Name(String value)
    {
        this.value = value;
    }

    public Name(String label, String value)
    {
        super(label);
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    
    private String value;
}
