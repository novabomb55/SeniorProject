package WebScraping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.webfolder.ui4j.api.browser.BrowserEngine;
import io.webfolder.ui4j.api.browser.BrowserFactory;
import io.webfolder.ui4j.api.browser.Page;

public class ScrapAndRead {
	
	private static String info = "";
	static BigDecimal[] list = new BigDecimal[13];
	
	static GetData call = new GetData();
	
	static String temp = "";
    
    public static Document getData(String url, String Name) throws IOException
    {
    	String html = Jsoup.connect(url).execute().body();
        html = html.replaceAll("<!--", "");
        html = html.replaceAll("-->", "");
        Document doc = Jsoup.parse(html);
        
        return doc;
        
    }
    
    public static BigDecimal[] ReturnStats(Document doc, String PlayerName) throws Exception
    {
    	Element name = doc.select("h1[itemprop=\"name\"]").first();
    	//System.out.println(name.text().toLowerCase() + " | " + PlayerName);
    	boolean IsIt = (name.text().toLowerCase().contains(PlayerName));
    	//System.out.println(!IsIt);
    	//IsIt must be false to start again
        if (!IsIt)
        {
        	doc = call.UpdatePlayerTables(PlayerName, 2);
        }
    	
        int Years = -1;
        Element temp1 = doc.select("td[data-stat=age]").first();
        
        for (int count = 0; count < 25; count++)
        {
        	if (!doc.select("td[data-stat=age]").get(count).text().equals(""))
        	{
        		Years++;
        	}
        	else
        	{
        		count = 25;
        	}
        }
        
        //System.out.println("Years: " + Years+1);
        //Points
        Element Points = doc.select("td[data-stat=pts_per_g]").get(Years);
        Element ThreePA = doc.select("td[data-stat=fg3a_per_g]").get(Years);
        Element ThreeChance = doc.select("td[data-stat=fg3_pct]").get(Years);
        Element TwoPA = doc.select("td[data-stat=fg2a_per_g]").get(Years);
        Element TwoChance = doc.select("td[data-stat=fg2_pct]").get(Years);
        Element FreePA = doc.select("td[data-stat=fta_per_g]").get(Years);
        Element FreeChance = doc.select("td[data-stat=ft_pct]").get(Years);
        

        
        System.out.println(Points.text() + " | " + ThreePA.text() + " | " + ThreeChance.text() + " | " + TwoPA.text() + " | "
        + TwoChance.text() + " | " + FreePA.text() + " | " + FreeChance.text());
        
        System.out.println();
        
        BigDecimal[] list = new BigDecimal[7];
        
        list[0] = BigDecimal.valueOf(Double.valueOf(Points.text()));
        list[1] = BigDecimal.valueOf(Double.valueOf(ThreePA.text()));
        list[2] = BigDecimal.valueOf(Double.valueOf(TwoPA.text()));
        list[3] = BigDecimal.valueOf(Double.valueOf(FreePA.text()));
        list[4] = BigDecimal.valueOf(Double.valueOf(ThreeChance.text()));
        list[5] = BigDecimal.valueOf(Double.valueOf(TwoChance.text()));
        list[6] = BigDecimal.valueOf(Double.valueOf(FreeChance.text()));
        
        return list;
        
    }
    
    public static BigDecimal[] ReturnTeamStats(Document doc, boolean which)
    {
        if (which)
        {
        	
        	Element Points = doc.select("td[data-stat=pts_per_g]").first();
        
        	//System.out.println(Points.text());
        	//System.out.println();
        
        	BigDecimal[] PointList = new BigDecimal[1];
        
        	PointList[0] = BigDecimal.valueOf(Double.valueOf(Points.text()));

        	return PointList;
        }
        
        else
        {
        	BigDecimal[] ReboundList = new BigDecimal[5];

        	
        	//Total rebound
        	Element Rebound = doc.select("td[data-stat=trb_per_g]").first();
        	
        	ReboundList[0] = BigDecimal.valueOf(Double.valueOf(Rebound.text()));
        	
        	//Offensive Rebound
        	Rebound = doc.select("td[data-stat=orb_per_g]").first();
        	
        	ReboundList[1] = BigDecimal.valueOf(Double.valueOf(Rebound.text()));
        	
        	//Defense Rebound
        	Rebound = doc.select("td[data-stat=drb_per_g]").first();
        	
        	ReboundList[2] = BigDecimal.valueOf(Double.valueOf(Rebound.text()));
        	
        	
        	//Shot Average
        	Rebound = doc.select("td[data-stat=fg2a_per_g]").first();
        	
        	ReboundList[3] = BigDecimal.valueOf(Double.valueOf(Rebound.text()));
        	
        	//Shot %
        	Rebound = doc.select("td[data-stat=fg2_pct]").first();
        	
        	ReboundList[4] = BigDecimal.valueOf(Double.valueOf(Rebound.text()));
        	
        	return ReboundList;
        }
    }
    
    public static BigDecimal[] ReturnOppontentTeamStats(Document doc)
    {
        
        Element Points = doc.select("td[data-stat=opp_pts_per_g]").first();
        
        //System.out.println(Points.text());
        //System.out.println();
        
        BigDecimal[] list = new BigDecimal[1];
        
        list[0] = BigDecimal.valueOf(Double.valueOf(Points.text()));

        return list;
    }
    
    public static void Returnui4jStats(String Localinfo)
    {
    	System.out.println();
    	System.out.println("Filling List");
    	
    	int dots = 0;
    	
    	boolean hasRun = false;
		boolean hasRun2 = false;
		
		//System.out.println("info: " + Localinfo);
    	//Defense 3%
		for (int count = 0; count < Localinfo.length(); count++)
		{
			if (dots == 7 && hasRun == false)
			{
				hasRun = true;
				for (int count2 = count; count2 < Localinfo.length(); count2++)
				{
					if (Localinfo.charAt(count2) == '.')
					{
						temp = Localinfo.substring(count+1, count2+2);
						temp = temp.replace("|", "0");
						
						//System.out.println("Temp1 = " + temp);
						list[3] = BigDecimal.valueOf(Double.valueOf(temp));
						count2 = Localinfo.length();
					}
				}
			}
		//Defense 2%	
    	if (dots == 13 && hasRun2 == false)	
    	{
    		hasRun2 = true;
    		for (int count2 = count; count2 < Localinfo.length(); count2++)
			{
				if (Localinfo.charAt(count2) == '.')
				{
					temp = Localinfo.substring(count+1, count2+2);
					temp = temp.replace("|", "0");
					
					//System.out.println("Temp2 = " + temp);
					list[4] = BigDecimal.valueOf(Double.valueOf(temp));
					count2 = Localinfo.length();
				}
			}
    	}
    		
    		if (Localinfo.charAt(count) == '.')
    		{
    			dots++;
    		}
    		
    		//System.out.println(count + " | " + line);
    		
    	}
    	
    	//System.out.println(list[3]);
    	//System.out.println(list[4]);
    }
    
    public static void Returnui4jReboundStats(String Localinfo)
    {
    	System.out.println();
    	System.out.println("Filling List");
    	
    	int line = 0;
    	
    	boolean hasRun = false;
		boolean hasRun2 = false;
		boolean hasRun3 = false;
		boolean hasRun4 = false;
    	
		for (int count = 0; count < Localinfo.length(); count++)
		{
			//Offensive Rebound %
			if (line == 14 && hasRun == false)
			{
				hasRun = true;
				for (int count2 = count; count2 < Localinfo.length(); count2++)
				{
					if (Localinfo.charAt(count2) == '.')
					{
						temp = Localinfo.substring(count, count2+2);
						temp = temp.replace("|", "0");
						
						list[11] = BigDecimal.valueOf(Double.valueOf(temp));
						count2 = Localinfo.length();
					}
				}
			}
			//Defensive Rebound %
			if (line == 17 && hasRun2 == false)
			{
				hasRun2 = true;
				for (int count2 = count; count2 < Localinfo.length(); count2++)
				{
					if (Localinfo.charAt(count2) == '.')
					{
						temp = Localinfo.substring(count, count2+2);
						temp = temp.replace("|", "0");
						
						list[12] = BigDecimal.valueOf(Double.valueOf(temp));
						count2 = Localinfo.length();
					}
				}
			}
			//Offensive rebound
			if (line == 6 && hasRun3 == false)
			{
				hasRun3 = true;
				for (int count2 = count; count2 < Localinfo.length(); count2++)
				{
					if (Localinfo.charAt(count2) == '.')
					{
						temp = Localinfo.substring(count, count2+2);
						temp = temp.replace("|", "0");
						
						list[9] = BigDecimal.valueOf(Double.valueOf(temp));
						count2 = Localinfo.length();
					}
				}
			}
			//Defensive Rebound
			if (line == 8 && hasRun4 == false)
			{
				hasRun4 = true;
				for (int count2 = count; count2 < Localinfo.length(); count2++)
				{
					if (Localinfo.charAt(count2) == '.')
					{
						temp = Localinfo.substring(count, count2+2);
						temp = temp.replace("|", "0");
						
						list[10] = BigDecimal.valueOf(Double.valueOf(temp));
						count2 = Localinfo.length();
					}
				}
			}
			
			if (Localinfo.charAt(count) == '|')
			{
				line++;
			}
		}
		
    }
    
    public static String getHTML(String url, String Name, boolean Rebound) throws Exception 
    {
    	if (Rebound)
    	{
    		BrowserEngine browser = BrowserFactory.getWebKit();
    		Page page = browser.navigate(url);
    		io.webfolder.ui4j.api.dom.Document doc = page.getDocument();
    		List<io.webfolder.ui4j.api.dom.Element> aTagLinks = doc.queryAll("tr[class=\"wisbb_fvStand \"]");

    		int count = 1;
    		for(io.webfolder.ui4j.api.dom.Element elm : aTagLinks)
    		{
    			if (count == 14)
    			{
    				info += elm.getText();
    			}
    			count++;
    		}
    	
    		TimeUnit.SECONDS.sleep(3);
    		
    		info = info.replaceAll("\n", "");
    		
    		for (int count2 = 5; count2 < info.length(); count2++)
    		{
    			if (info.charAt(count2) == ' ' && count2%10 == 0)
    			{
    				info = info.substring(0, count2-1) + '|' + info.substring(count2+1);
    			}
    		}
    		
    		info = info.replaceAll(" ", "");
    		
    		info = info.substring(12);
    	
    		return info;
    	}
    	
    	else 
    	{
    		BrowserEngine browser = BrowserFactory.getWebKit();
    		Page page = browser.navigate(url);
    		io.webfolder.ui4j.api.dom.Document doc = page.getDocument();
    		List<io.webfolder.ui4j.api.dom.Element> aTagLinks = doc.queryAll("td");

    	
    		for(io.webfolder.ui4j.api.dom.Element elm : aTagLinks)
    		{
    			info += elm.getText();
    		}
    	
    		TimeUnit.SECONDS.sleep(3);
    	
    		return info;
    	}
    }
    
    public static String ReturnData()
    {
    	return info;
    }
    
    public static BigDecimal[] ReturnList()
    {
    	return list;
    }
    
    public static void WipeData()
    {
    	info = "";
    }
    
    public static int FindYears(Document doc)
    {
    	int Years = 0;
        Element temp1 = doc.select("td[data-stat=age]").first();
        Element temp2 = null;
        
        for (int count = 0; count < 25; count++)
        {
        	if (!doc.select("td[data-stat=age]").get(count).text().equals(""))
        	{
        		temp2 = doc.select("td[data-stat=age]").get(count);
        	}
        	else
        	{
        		count = 25;
        	}
        }
        
        Years = Integer.valueOf(temp2.text()) - Integer.valueOf(temp1.text()) + 1; 
        
        return Years;
    }
} 

