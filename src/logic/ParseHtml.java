package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ParseHtml
{
	public static ArrayList<ArrayList<String>> parselines(String arg)
	{
		InputStream is = null;
		BufferedReader br;
		String f_line;
		ArrayList<String> lines_1, line;
		ArrayList<ArrayList<String>> lines;
		int i;

		try 
		{
			br = new BufferedReader(new StringReader(arg));
			lines_1 = new ArrayList<String>();
			lines = new ArrayList<ArrayList<String>>();

			// read lines into arraylist
			while ((f_line = br.readLine()) != null)
			{
				lines_1.add(f_line);
			}

			br.close();

			// remove empty lines
			i = 0;
			while (i < lines_1.size())
			{
				if (lines_1.get(i).length() == 0)
				{
					lines_1.remove(i);
				}
				else
				{
					i ++;
				}
			}

			// make each line into an arraylist of lines
			i = 0;
			while (i < lines_1.size())
			{
				line = new ArrayList<String>(Arrays.asList(lines_1.get(i).split(",")));
				// 9 and 10 need to be together so that professor names are one element
				line.set(9, line.get(9) + line.get(10));
				line.remove(10);
				lines.add(line);
				i ++;
			}

			return lines;
		}
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}

		return new ArrayList<ArrayList<String>>();
	}

	public static void print_class_list(ArrayList<ArrayList<String>> class_list)
	{
		int i;

		i = 0;
		while (i < class_list.size())
		{
			System.out.println(class_list.get(i));
			i ++;
		}
	}
}
