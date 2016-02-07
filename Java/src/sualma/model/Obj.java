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

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    private String label; // TODO: consider making final (pass through ctor)
}
