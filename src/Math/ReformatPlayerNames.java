package Math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ReformatPlayerNames {

	public static void main(String[] args) {
		
		String fileName = "C:\\Users\\noman\\OneDrive\\Documents\\Data.txt";
        String separator = ", ";

        String line;
        
        int playercount = 0;
        
        String[] list = new String[600];

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) 
        {

        	int count = 0;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] elements = line.split(separator);

                if (elements.length == 2) 
                {
                	elements[1] = elements[1].replaceAll(" ", "");
                	elements[0] = elements[0].replaceAll(" ", "");
                    list[count] = elements[1] + " " + elements[0];
                    count++;
                    playercount++;
                } 
                else 
                {
                    System.out.println("Wrong line: " + line);
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int count = 0; count < playercount; count++)
        {
        	System.out.print(" , \"");
        	System.out.print(list[count]);
        	System.out.print("\"");
        	if (count%7 == 0)
        	{
        		System.out.println();
        	}
        }
	}
}