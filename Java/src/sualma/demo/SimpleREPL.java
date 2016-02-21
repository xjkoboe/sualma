package sualma.demo;

import java.util.Scanner;
import sualma.model.Obj;
import sualma.parser.Parser;
import sualma.writer.SimpleWriter;

public class SimpleREPL 
{
    public static void main(String[] args)
    {
        Parser parser = new Parser();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true)
        {
            System.out.println("?");
            String text = "";
            String line = scanner.nextLine();
            while (!line.isEmpty())
            {
                if ("quit".equals(line))
                    return;
                text += line + "\n";
                line = scanner.nextLine();
            }

            System.out.println("->");
            try
            {
                Obj obj = parser.parse(text);

                System.out.println(SimpleWriter.toString(obj));
            }
            catch(Throwable e)
            {
                System.out.println(e);
            }
        }
    }
}
