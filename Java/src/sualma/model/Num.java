/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

public class Num extends Obj
{
    public Num(String value)
    {
        this.value = value;
    }

    public Num(String label, String value)
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
