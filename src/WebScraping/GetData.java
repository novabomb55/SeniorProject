package WebScraping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.nodes.Document;

public class GetData {

	String First;
	String Last;
	
	Document temp;
	
	public Document UpdatePlayerTables(String PlayerName, int index) throws Exception
	{
		System.out.println("Updating Tables.......");
		temp = ScrapAndRead.getData("https://www.basketball-reference.com/players/" + NameParsing(PlayerName, index).charAt(0) + "/" + NameParsing(PlayerName, index) + ".html", PlayerName.replaceAll("\\s",""));
		//System.out.println("https://www.basketball-reference.com/players/" + NameParsing(PlayerName, index).charAt(0) + "/" + NameParsing(PlayerName, index) + ".html");
		return temp;
		
	}
	
	public String UpdateDefenseTables(String PlayerName) throws Exception
	{
		return ScrapAndRead.getHTML("https://stats.nba.com/player/" + GetID(readID(PlayerName) , PlayerName) + "/defense-dash/", (PlayerName.replaceAll("\\s","")+"Defense"), false);
	}
	
	public Document UpdateTeamTables(String TeamName, int which) throws IOException
	{
		if (which == 0)
		{
			temp = ScrapAndRead.getData("https://www.basketball-reference.com/teams/" + TeamName + "/stats_per_game_totals.html", TeamName);
		}
		else
		{
			//System.out.println("running defense scan");
			temp = ScrapAndRead.getData("https://www.basketball-reference.com/teams/" + TeamName + "/opp_stats_per_game_totals.html", TeamName);
		}
		
		return temp;
	
	}
	
	public String UpdateRebound(String PlayerName) throws Exception
	{
		NameParsing(PlayerName);
		
		String temp = ScrapAndRead.getHTML("https://www.foxsports.com/nba/" + First + "-" + Last + "-player-stats", PlayerName, true);
		
		return temp;
	}
	
	public Document UpdateTeamRebound(String TeamName) throws IOException
	{
		Document temp = ScrapAndRead.getData("https://www.basketball-reference.com/teams/" + TeamName + "/stats_per_game_totals.html", TeamName);
		
		return temp;
	}
	
	private String NameParsing(String Name, int index)
	{
		String URLName = "";
		int Space = 0;
		
		
		for (int count = 0; count < Name.length(); count++)
		{
			if (Name.charAt(count) == ' ')
			{
				Space = count;
			}
		}
		
		First = Name.substring(0, Space).toLowerCase();
		Last = Name.substring(Space+1, Name.length()).toLowerCase();
		
		//System.out.println("First name: " + First);
		//System.out.println("Last name: " + Last);
		
		for (int count = 0; count < Last.length() && count < 5; count++)
		{
			URLName += Last.charAt(count);
		}
		
		for (int count = 0; count < 2; count++)
		{
			URLName += First.charAt(count);
		}
		
		URLName += "0" + index;
		
		return URLName;
	}
	
	private String NameParsing(String Name)
	{
		String URLName = "";
		int Space = 0;
		
		
		for (int count = 0; count < Name.length(); count++)
		{
			if (Name.charAt(count) == ' ')
			{
				Space = count;
			}
		}
		
		First = Name.substring(0, Space).toLowerCase();
		Last = Name.substring(Space+1, Name.length()).toLowerCase();
		
		//System.out.println("First name: " + First);
		//System.out.println("Last name: " + Last);
		
		for (int count = 0; count < Last.length() && count < 5; count++)
		{
			URLName += Last.charAt(count);
		}
		
		for (int count = 0; count < 2; count++)
		{
			URLName += First.charAt(count);
		}
		
		return URLName;
	}

	
	public String UpdatePlayerList() throws IOException
	{
		//System.out.println("Gathering Player ID............");
		//ScrapAndRead.getIDs();
		return ScrapAndRead.ReturnData();
	}
	
	public String GetID(String info, String PlayerName)
	{
		String PlayerData = "";
		
		NameParsing(PlayerName, 1);
		
		for (int count = 0; count < info.length(); count++)
		{
			if (info.substring(count, count + Last.length()).equalsIgnoreCase(Last))
			{
				for (int count2 = count; count2 < count+15; count2++)
				{
					if (info.substring(count2, count2 + First.length()).equalsIgnoreCase(First))
					{
						PlayerData = info.substring(count-10 ,count);
						//System.out.println(PlayerData);
						count = info.length();
						count2 = count+16;
					}
				}
			}
			
			if (count == info.length() - Last.length()-1)
			{
				count = info.length();
			}
		}
		
		if (PlayerData.equals(""))
		{
			System.out.println("Player data is missing!");
		}
		
		//System.out.println("Player data: " + PlayerData);
		
		PlayerData = PlayerData.replaceAll("[a-zA-Z]", "");
		PlayerData = PlayerData.replaceAll("/", "");
		PlayerData = PlayerData.substring(0, PlayerData.length()-2);
		
		//System.out.println("Player data: " + PlayerData);
		
		return PlayerData;
	}
	
	public String readID(String PlayerName) throws FileNotFoundException, IOException
	{
		NameParsing(PlayerName, 1);
		
		String NewLast = Last.substring(0, 1).toUpperCase() + Last.substring(1);
		NewLast = NewLast.replace(".", "");
		String NewFirst = First.substring(0, 1).toUpperCase() + First.substring(1);
		NewFirst = NewFirst.replace(".", "");
		
		//System.out.println(Last + ", " + First);
		
		String csvFile = "C:\\Users\\Dell 9020 i7\\eclipse-workspace\\SeniorProject\\src\\PlayerIDs\\NameList.csv";
		String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
        	
        	while ((line = br.readLine()) != null) 
        	{
        		line = TrimInfo(line);
        		if (line.contains(NewLast + ", " + NewFirst))
        		{
        			System.out.println();
        			return line.substring(line.length()-40, line.length());
        		}
        		
        		if (line.contains(NewFirst.substring(0, NewFirst.length()/2) + "." + NewFirst.substring(NewFirst.length()/2).toUpperCase() + "."))
        		{
        			System.out.println();
        			return line.substring(line.length()-40, line.length());
        		}
        	}
        }
		
        System.out.println();
        System.out.println("Player was not found!");
		return null;
	}
	
	public String TrimInfo(String IDInfo)
    {
    	for (int count = 0; count < IDInfo.length()-7; count++)
    	{
    		if (IDInfo.substring(count, count+7).equalsIgnoreCase("abrines"))
    		{
    			IDInfo = IDInfo.substring(count-25);
    		}
    	}
    	
    	return IDInfo;
    }
	
	public String ReturnFirst()
	{
		return First;
	}
	
	public String ReturnLast()
	{
		return Last;
	}
	
	public String ReturnName()
	{
		return First + " " + Last;
	}
}

