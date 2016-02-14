/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

public class ParserException extends TextException
{
    public ParserException(String msg, Token token)
    {
        super(msg, token.getLocation());
        this.token = token;
    }
    
    private final Token token;
}
