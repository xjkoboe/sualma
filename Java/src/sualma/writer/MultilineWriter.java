/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.writer;

import sualma.model.Call;
import sualma.model.List;
import sualma.model.Obj;

/**
 * MultilineWriter converts a sualma object to a string-representation.
 * Tries to put every object on a separate line.
 */
public final class MultilineWriter
{
    public static String toString(Obj obj)
    {
        MultilineWriter writer = new MultilineWriter();
        writer.writeObj(obj, 0);
        return writer.sb.toString();
    }
    
    private MultilineWriter()
    {
        sb = new StringBuilder();
    }
    
    private void writeObj(Obj obj, int indent)
    {
        if (obj instanceof List)
        {
            writeList((List)obj, indent);
        }
        else if (obj instanceof Call)
        {
            writeCall((Call)obj, indent);
        }
        else
        {
            add(obj.toString(), indent);
            //sb.append("\n");
        }
    }
    
    private void writeList(List lst, int indent)
    {
        int n = lst.getCount();
        if (n <= 1)
            add(lst.toString(), indent);
        else 
        {
            for (Obj el: lst.getElements())
            {
                if (el instanceof List)
                {
                    add("(", indent);
                    writeObj(el, indent + 1);
                    add(")", indent);
                }
                else
                    writeObj(el, indent);
            }
        }
    }
    
    private void writeCall(Call call, int indent)
    {
        writeObj(call.getHead(), indent);
        writeObj(call.getBody(), indent+1);
    }
    
    private void add(String s, int indent)
    {
        for (int i = 0; i < indent; ++i)
            sb.append(indentString);
        sb.append(s + '\n');
    }
    
    private final String indentString = "    ";
    private final StringBuilder sb;
}