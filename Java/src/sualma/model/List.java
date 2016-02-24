/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Object holding a list of elements (type Obj).
 * A list can not contain two elements with the same (non null) name.
 * 
 * Element lookup via index or via label (if not null).
 */
public final class List extends Obj
{
    public List(Obj... elements)
    {
        for (Obj el: elements)
            addElement(el);
    }
 
    public int getCount()
    {
        return elements.size();
    }
    
    public List addElement(Obj el)
    {
        assert(el != null);
        String label = el.getLabel();
        assert(label == null || getElement(label) == null); // label must be unique within this list 
        elements.add(el);
        return this;
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

    public Collection<Obj> getElements()
    {
        return Collections.unmodifiableList(elements);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.elements);
        return hash;
    }

    @Override
    public boolean equals(Object other)
    {   
        if (!(other instanceof List))
            return false;
        List otherList = (List)other;
        if (getCount() != otherList.getCount())
            return false;
        for (int i = 0; i < getCount(); ++i)
        {
            if (!getElement(i).equals(otherList.getElement(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        if (elements.isEmpty())
            return super.toString() + "()";
        
        if (elements.size() == 1)
            return super.toString() + "( " + elements.get(0) + ", )";
        
        String res = "( " + elements.get(0);
        for (int i = 1; i < elements.size(); ++i)
            res += ", " + elements.get(i);
        return super.toString() + res + " )";
    }

    
    private final ArrayList<Obj> elements = new ArrayList<>(); 
    // TODO: consider other container(s) for more efficient label lookup
}
