/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

public class Str extends Obj
{
    public Str(String value)
    {
        this.value = value;
    }

    public Str(String label, String value)
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
