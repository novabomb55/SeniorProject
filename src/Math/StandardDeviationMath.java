package Math;

import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import WebScraping.ScrapAndRead;

public class StandardDeviationMath {
	
	BigDecimal[] x = new BigDecimal[25];
	String info;
	int years = 0;
	
	double SD = 0;
	
	public StandardDeviationMath() throws IOException
	{
		System.out.println();
		System.out.println("Finding StandardDeviationMath.................");
	}
	
	double SDMath(Document doc)
	{
		years = ScrapAndRead.FindYears(doc);
		
		Element[] Points = new Element[25];
		for (int count = 0; count < years; count++)
		{
			Points[count] = doc.select("td[data-stat=pts_per_g]").get(count);
		}

		
		double results = 0;
		double average = 0;
		for (int count = 0; count < years; count++)
		{
			average += Double.valueOf(Points[count].text());
		}
		
		average = average/(years);
		//System.out.println("Average: " + average + "    X Length: " + (years+1));
		
		for (int count = 0; count < years; count++)
		{
			results += Math.pow((Double.valueOf(Points[count].text()) - average),2);
		}
		
		results = results/(years);
		
		results = Math.sqrt(results);
		
		return results;
		
	}
	
}
