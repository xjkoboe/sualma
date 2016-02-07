/**
 * @author wijnand.schepens@gmail.com
 */
package sualma.tokenizer;

public class TextLocation
{
    public TextLocation(String lineText, int line, int column)
    {
        this.lineText = lineText;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("line ").append(line).append(":\n");
        sb.append(lineText).append('\n');
        for (int i = 0; i < column; i++)
        {
            sb.append(' ');
        }
        sb.append("^");
        return sb.toString();
    }

    public String getLineText()
    {
        return lineText;
    }

    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }
    
    // TODO: filename, url, ...
    private String lineText;
    private int line;   // counting from 0
    private int column; // counting from 0
}
