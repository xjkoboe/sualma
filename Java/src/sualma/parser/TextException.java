/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

import sualma.BaseException;
import sualma.BaseException;
import sualma.parser.TextLocation;

public class TextError extends BaseException
{
    public TextError(String msg, TextLocation location)
    {
        super(msg);
        this.location = location;
    }

    public TextError(Throwable cause, TextLocation location)
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

    private TextLocation location;
}
