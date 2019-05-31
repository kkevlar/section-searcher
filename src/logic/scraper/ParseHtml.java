package logic.scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseHtml
{
    private ParseHtml() 
    {
        throw new IllegalStateException("Utility class");
    }
    
    public static List<ArrayList<String>> parselines(String arg)
    {
        BufferedReader br;
        String fLine;
        ArrayList<String> lines1;
        ArrayList<String> line;
        ArrayList<ArrayList<String>> lines;
        int i;
        
        lines = new ArrayList<>();
        try 
        {
            br = new BufferedReader(new StringReader(arg));
            lines1 = new ArrayList<>();

            // read lines into array list
            while ((fLine = br.readLine()) != null)
            {
                lines1.add(fLine);
            }

            br.close();

            // remove empty lines
            i = 0;
            while (i < lines1.size())
            {
                if (lines1.get(i).length() == 0)
                {
                    lines1.remove(i);
                }
                else
                {
                    i ++;
                }
            }

            // make each line into an list of lines
            i = 0;
            while (i < lines1.size())
            {
                line = new ArrayList<>(Arrays.asList(lines1.get(i).split(",")));
                // 9 and 10 need to be together so that professor names are one element
                line.set(9, line.get(9) + line.get(10));
                line.remove(10);
                lines.add(line);
                i ++;
            }
            
            i = 0;
            while (i < lines.size())
            {
                if (lines.get(i).get(10).length() == 1)
                {
                    lines.remove(i);
                }
                else
                {
                    i ++;
                }
            }

            return lines;
        }
        catch (IOException ioe) 
        {
        	lines.clear();
        	return lines;
        }
    }
}
