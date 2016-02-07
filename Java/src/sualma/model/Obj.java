/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

/**
 * Base class for sualma objects.
 */
public class Obj
{
    public Obj()
    {
        this.label = null;
    }

    public Obj(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    
    private String label;
}
