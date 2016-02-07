 /**
 * @author wijnand.schepens@gmail.com
 */
package sualma.parser;

public class Token
{
    public enum Type
    {
        LeadingSpaces,
		Reserved,
        Number,
        String, 
        Name, 
        Bracket,
        Operator,
        EndOfText
    }

    public Token(String text, Type type)
    {
        this.text = text;
        this.type = type;
        this.location = null;
    }

    public Token(String text, Type type, TextLocation location)
    {
        this.text = text;
        this.type = type;
        this.location = location;
    }

    public String getText()
    {
        return text;
    }

    public Type getType()
    {
        return type;
    }

    public TextLocation getLocation()
    {
        return location;
    }

    public boolean is(String text)
    {
        return this.text.equals(text);
    }
    
    public boolean is(Token.Type type)
    {
        return this.type == type;
    }
    
    @Override
    public String toString()
    {
        return "token '" + getText() + "' (" + getType() + ")";
    }
    
    private final String       text;
    private final Type         type;
    private final TextLocation location;
}
