/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import java.util.ArrayList;

public class List extends Obj
{
    public List()
    {
    }
    
    public List(String label)
    {
        super(label);
    }

    public int getCount()
    {
        return elements.size();
    }
    
    public void addElement(Obj el)
    {
        assert(el != null);
        String label = el.getLabel();
        assert(label == null || getElement(label) == null); // label must be unique within this list 
        elements.add(el);
    }
    
    public Obj getElement(int index)
    {
        return elements.get(index); // TODO: better exception handling
    }
    
    public Obj getElement(String label)
    {
        assert(label != null);  // TODO: better exception handling
        for (Obj el : elements)  
        {
            if (label.equals(el.getLabel()))
                return el;
        }
        return null; // TODO: better exception handling
    }

    private ArrayList<Obj> elements = new ArrayList<>(); 
    // TODO: consider other container(s) for more efficient label lookup
}
