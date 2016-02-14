/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

import sualma.BaseException;

public class TextException extends BaseException
{
    public TextException(String msg, TextLocation location)
    {
        super(msg);
        this.location = location;
    }

    public TextException(Throwable cause, TextLocation location)
    {
        super(cause);
        this.location = location;
    }

    public TextLocation getLocation()
    {
        return location;
    }

    @Override
    public String getMessage()
    {
        return super.getMessage() + " at " + location;
    }

    private final TextLocation location;
}
