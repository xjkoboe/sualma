package sualma;

/**
 * @author wijnand.schepens@gmail.com
 */
public class BaseException extends RuntimeException
{
    public BaseException(String message)
    {
        super(message);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }
}
