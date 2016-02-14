/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.writer;

import sualma.model.Obj;

/**
 * A Writer can be used to convert a sualma object (and dependencies)
 * to a string representation.
 */
public final class Writer
{
    public static String write(Obj obj)
    {
        return obj.toString();
    }
}