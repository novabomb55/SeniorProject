package WebScraping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import org.jsoup.nodes.Document;

import Math.Formulas;

public class Driver {
	
	
	public static void main(String[] args) throws Exception   {

		String Data;
		String answer;
		String PlayerAnswer, TeamAnswer;
		Scanner scan = new Scanner(System.in);
		
		GetData call = new GetData();
		
		BigDecimal[] result = new BigDecimal[12];
		
		BigDecimal[] Player = new BigDecimal[13];
		BigDecimal[] Team = new BigDecimal[5];
		BigDecimal[] OpponentTeam = new BigDecimal[5];
		BigDecimal[] OpponentPlayer = new BigDecimal[13];
		
		Document temp;
		Document PlayerTemp = null;
		
		ScrapAndRead.WipeData();
		
		System.out.println("Please Pick between these opitions:");
		System.out.println("1. Point Prediction");
		System.out.println("2. Rebound Prediction");
		Scanner scan2 = new Scanner(System.in);
		int reply = scan2.nextInt();
		
	if (reply != 1 && reply != 2)
	{
		System.out.println("Try again. Reply with 1 for points and 2 for rebounds");
		System.out.println("1. Point Prediction");
		System.out.println("2. Rebound Prediction");
	}
		
	if (reply == 1)
	{
		System.out.println();
		System.out.println("Please enter what team your player is on (Please use team acronym)");
		answer = scan.nextLine();
		TeamAnswer = answer;
		
		String[] TeamName = new String[]{"ATL", "BOS", "BKN", "CHA", "CHI", "CLE", "DAL", "DEN", "DET", "GSW", "HOU"
				, "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN", "NOP", "NYK", "OKC", "ORL", "PHI", "PHX", "POR"
				, "SAC", "SAS", "TOR" , "UTA", "WAS"};
		for (int count = 0; count < TeamName.length; count++)
		{
			if (TeamName[count].equalsIgnoreCase(answer))
			{
				temp = call.UpdateTeamTables(TeamName[count], 0);
    		
				Team = ScrapAndRead.ReturnTeamStats(temp, true);
			
				count = TeamName.length+1;
			}
			
			else if (count == TeamName.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				count = 0;
			}
		}
		
		result[7] = Team[0]; //Points Team Average
		
		System.out.println();
		System.out.println("Please enter what player you want to check");
		answer = scan.nextLine();
		PlayerAnswer = answer;
		
		String[] PlayerName = new String[]{"Alex Abrines", "Quincy Acy" , "Jaylen Adams" , "Steven Adams" , "Bam Adebayo" , "Deng Adel" , "DeVaughn Akoon-Purcell" , "LaMarcus Aldridge"
				 , "Rawle Alkins" , "Grayson Allen" , "Jarrett Allen" , "Kadeem Allen" , "Al-Farouq Aminu" , "Justin Anderson" , "Kyle Anderson"
				 , "Ryan Anderson" , "Ike Anigbogu" , "Giannis Antetokounmpo" , "Kostas Antetokounmpo" , "Carmelo Anthony" , "OG Anunoby" , "Ryan Arcidiacono"
				 , "Trevor Ariza" , "D.J. Augustin" , "Deandre Ayton" , "Dwayne Bacon" , "Marvin BagleyIII" , "Ron Baker" , "Wade BaldwinIV"
				 , "Lonzo Ball" , "Mo Bamba" , "J.J. Barea" , "Harrison Barnes" , "Will Barton" , "Keita Bates-Diop" , "Nicolas Batum"
				 , "Jerryd Bayless" , "Aron Baynes" , "Kent Bazemore" , "Bradley Beal" , "Malik Beasley" , "Michael Beasley" , "Marco Belinelli"
				 , "Jordan Bell" , "DeAndre' Bembry" , "Dragan Bender" , "Dairis Bertans" , "Davis Bertans" , "Patrick Beverley" , "Khem Birch"
				 , "Bismack Biyombo" , "Nemanja Bjelica" , "Antonio Blakeney" , "Eric Bledsoe" , "Jaron Blossomgame" , "Trevon Bluiett" , "Bogdan Bogdanovic"
				 , "Bojan Bogdanovic" , "Andrew Bogut" , "Jonah Bolden" , "Isaac Bonga" , "Devin Booker" , "Chris Boucher" , "Avery Bradley"
				 , "Tony Bradley" , "Corey Brewer" , "Mikal Bridges" , "Miles Bridges" , "Isaiah Briscoe" , "Ryan Broekhoff" , "Malcolm Brogdon"
				 , "Dillon Brooks" , "MarShon Brooks" , "Bruce Brown" , "Jaylen Brown" , "Lorenzo Brown" , "Sterling Brown" , "Troy BrownJr."
				 , "Jalen Brunson" , "Thomas Bryant" , "Reggie Bullock" , "Trey Burke" , "Alec Burks" , "Deonte Burton" , "Jimmy Butler"
				 , "Bruno Caboclo" , "Jose Calderon" , "Kentavious Caldwell-Pope" , "Isaiah Canaan" , "Clint Capela" , "DeMarre Carroll" , "Jevon Carter"
				 , "Vince Carter" , "Wendell CarterJr." , "Michael Carter-Williams" , "Alex Caruso" , "Omri Casspi" , "Willie Cauley-Stein" , "Troy Caupain"
				 , "Tyler Cavanaugh" , "Tyson Chandler" , "Wilson Chandler" , "Joe Chealey" , "Marquese Chriss" , "Gary Clark" , "Ian Clark"
				 , "Jordan Clarkson" , "John Collins" , "Zach Collins" , "Darren Collison" , "Bonzie Colson" , "Mike Conley" , "Pat Connaughton"
				 , "Quinn Cook" , "DeMarcus Cousins" , "Robert Covington" , "Allen Crabbe" , "Torrey Craig" , "Jamal Crawford" , "Mitchell Creek"
				 , "Jae Crowder" , "Dante Cunningham" , "Seth Curry" , "Stephen Curry" , "Troy Daniels" , "Anthony Davis" , "Ed Davis"
				 , "Tyler Davis" , "DeMar DeRozan" , "Dewayne Dedmon" , "Sam Dekker" , "Angel Delgado" , "Matthew Dellavedova" , "Luol Deng"
				 , "Marcus Derrickson" , "Donte DiVincenzo" , "Cheick Diallo" , "Hamidou Diallo" , "Gorgui Dieng" , "Spencer Dinwiddie" , "Luka Doncic"
				 , "Tyler Dorsey" , "Damyean Dotson" , "PJ Dozier" , "Goran Dragic" , "Andre Drummond" , "Jared Dudley" , "Kris Dunn"
				 , "Kevin Durant" , "Trevon Duval" , "Vincent Edwards" , "Henry Ellenson" , "Wayne Ellington" , "Joel Embiid" , "James EnnisIII"
				 , "Drew Eubanks" , "Jacob Evans" , "Jawun Evans" , "Tyreke Evans" , "Dante Exum" , "Kenneth Faried" , "Derrick Favors"
				 , "Cristiano Felicio" , "Raymond Felton" , "Terrance Ferguson" , "Yogi Ferrell" , "Dorian Finney-Smith" , "Bryn Forbes" , "Evan Fournier"
				 , "De'Aaron Fox" , "Tim Frazier" , "Melvin FrazierJr." , "Channing Frye" , "Markelle Fultz" , "Wenyen Gabriel" , "Danilo Gallinari"
				 , "Langston Galloway" , "Marc Gasol" , "Pau Gasol" , "Rudy Gay" , "Paul George" , "Taj Gibson" , "Harry GilesIII"
				 , "Shai Gilgeous-Alexander" , "Rudy Gobert" , "Brandon Goodwin" , "Aaron Gordon" , "Eric Gordon" , "Marcin Gortat" , "Devonte' Graham"
				 , "Treveon Graham" , "Jerami Grant" , "Jerian Grant" , "Donte Grantham" , "Danny Green" , "Draymond Green" , "Gerald Green"
				 , "JaMychal Green" , "Jeff Green" , "Blake Griffin" , "Daniel Hamilton" , "Tim HardawayJr." , "James Harden" , "Maurice Harkless"
				 , "Montrezl Harrell" , "Devin Harris" , "Gary Harris" , "Joe Harris" , "Tobias Harris" , "Andrew Harrison" , "Shaquille Harrison"
				 , "Josh Hart" , "Isaiah Hartenstein" , "Udonis Haslem" , "Gordon Hayward" , "John Henson" , "Juancho Hernangomez" , "Willy Hernangomez"
				 , "Mario Hezonja" , "Isaiah Hicks" , "Buddy Hield" , "Haywood Highsmith" , "George Hill" , "Solomon Hill" , "Aaron Holiday"
				 , "Jrue Holiday" , "Justin Holiday" , "John Holland" , "Rondae Hollis-Jefferson" , "Richaun Holmes" , "Rodney Hood" , "Scotty Hopson"
				 , "Al Horford" , "Danuel HouseJr." , "Dwight Howard" , "Kevin Huerter" , "RJ Hunter" , "Chandler Hutchison" , "Serge Ibaka"
				 , "Andre Iguodala" , "Ersan Ilyasova" , "Joe Ingles" , "Brandon Ingram" , "Kyrie Irving" , "Jonathan Isaac" , "Wes Iwundu"
				 , "Demetrius Jackson" , "Frank Jackson" , "Josh Jackson" , "Justin Jackson" , "Reggie Jackson" , "Jaren JacksonJr." , "LeBron James"
				 , "Amile Jefferson" , "John Jenkins" , "Jonas Jerebko" , "Alize Johnson" , "Amir Johnson" , "BJ Johnson" , "James Johnson"
				 , "Stanley Johnson" , "Tyler Johnson" , "Wesley Johnson" , "Nikola Jokic" , "Damian Jones" , "Jalen Jones" , "Terrence Jones"
				 , "Tyus Jones" , "Derrick JonesJr." , "DeAndre Jordan" , "Cory Joseph" , "Frank Kaminsky" , "Enes Kanter" , "Luke Kennard"
				 , "Michael Kidd-Gilchrist" , "George King" , "Maxi Kleber" , "Brandon Knight" , "Kevin Knox" , "Furkan Korkmaz" , "Luke Kornet"
				 , "Kyle Korver" , "Kosta Koufos" , "Rodions Kurucs" , "Kyle Kuzma" , "Zach LaVine" , "Skal Labissiere" , "Jeremy Lamb"
				 , "Jake Layman" , "Caris LeVert" , "TJ Leaf" , "Courtney Lee" , "Damion Lee" , "Alex Len" , "Kawhi Leonard"
				 , "Meyers Leonard" , "Jon Leuer" , "Damian Lillard" , "Jeremy Lin" , "Shaun Livingston" , "Zach Lofton" , "Kevon Looney"
				 , "Brook Lopez" , "Robin Lopez" , "Kevin Love" , "Kyle Lowry" , "Jordan Loyd" , "Kalin Lucas" , "Timothe Luwawu-Cabarrot"
				 , "Tyler Lydon" , "Trey Lyles" , "Shelvin Mack" , "Daryl Macon" , "J.P. Macura" , "Ian Mahinmi" , "Thon Maker"
				 , "Boban Marjanovic" , "Lauri Markkanen" , "Jarell Martin" , "Frank Mason" , "Yante Maten" , "Wesley Matthews" , "Luc MbahaMoute"
				 , "Tahjere McCall" , "Patrick McCaw" , "CJ McCollum" , "T.J. McConnell" , "Doug McDermott" , "JaVale McGee" , "Rodney McGruder"
				 , "Alfonzo McKinnie" , "Ben McLemore" , "Jordan McRae" , "Jodie Meeks" , "Salah Mejri" , "De'Anthony Melton" , "Chimezie Metu"
				 , "Khris Middleton" , "CJ Miles" , "Darius Miller" , "Malcolm Miller" , "Patty Mills" , "Paul Millsap" , "Shake Milton"
				 , "Nikola Mirotic" , "Donovan Mitchell" , "Naz Mitrou-Long" , "Malik Monk" , "Greg Monroe" , "Ben Moore" , "E'Twaun Moore"
				 , "Eric Moreland" , "Jaylen Morris" , "Marcus Morris" , "Markieff Morris" , "Monte Morris" , "Johnathan Motley" , "Timofey Mozgov"
				 , "Emmanuel Mudiay" , "Dejounte Murray" , "Jamal Murray" , "Dzanan Musa" , "Mike Muscala" , "Svi Mykhailiuk" , "Abdel Nader"
				 , "Larry NanceJr." , "Shabazz Napier" , "Raul Neto" , "Georges Niang" , "Joakim Noah" , "Nerlens Noel" , "Dirk Nowitzki"
				 , "Frank Ntilikina" , "James Nunnally" , "Jusuf Nurkic" , "David Nwaba" , "Royce O'Neale" , "Kyle O'Quinn" , "Semi Ojeleye"
				 , "Jahlil Okafor" , "Elie Okobo" , "Josh Okogie" , "Victor Oladipo" , "Kelly Olynyk" , "Cedi Osman" , "Kelly OubreJr."
				 , "Zaza Pachulia" , "Jabari Parker" , "Tony Parker" , "Chandler Parsons" , "Patrick Patterson" , "Justin Patton" , "Chris Paul"
				 , "Cameron Payne" , "Elfrid Payton" , "Gary PaytonII" , "Theo Pinson" , "Mason Plumlee" , "Miles Plumlee" , "Jakob Poeltl"
				 , "Quincy Pondexter" , "Michael PorterJr." , "Otto PorterJr." , "Bobby Portis" , "Kristaps Porzingis" , "Dwight Powell" , "Norman Powell"
				 , "Alex Poythress" , "Taurean Prince" , "Ivan Rabb" , "Chasson Randle" , "Julius Randle" , "JJ Redick" , "Davon Reed"
				 , "Cameron Reynolds" , "Josh Richardson" , "Malachi Richardson" , "Austin Rivers" , "Andre Roberson" , "Devin Robinson" , "Duncan Robinson"
				 , "Jerome Robinson" , "Mitchell Robinson" , "Glenn RobinsonIII" , "Rajon Rondo" , "Derrick Rose" , "Terrence Ross" , "Terry Rozier"
				 , "Ricky Rubio" , "D'Angelo Russell" , "Domantas Sabonis" , "Brandon Sampson" , "Dario Saric" , "Tomas Satoransky" , "Dennis Schroder"
				 , "Mike Scott" , "Thabo Sefolosha" , "Wayne Selden" , "Collin Sexton" , "Landry Shamet" , "Iman Shumpert" , "Pascal Siakam"
				 , "Jordan Sibert" , "Ben Simmons" , "Jonathon Simmons" , "Kobi Simmons" , "Anfernee Simons" , "Marcus Smart" , "Ish Smith"
				 , "JR Smith" , "Jason Smith" , "Zhaire Smith" , "Dennis SmithJr." , "Tony Snell" , "Richard Solomon" , "Ray Spalding"
				 , "Omari Spellman" , "Nik Stauskas" , "DJ Stephens" , "Lance Stephenson" , "Edmond Sumner" , "Caleb Swanigan" , "Jayson Tatum"
				 , "Jeff Teague" , "Garrett Temple" , "Milos Teodosic" , "Jared Terrell" , "Emanuel Terry" , "Daniel Theis" , "Isaiah Thomas"
				 , "Khyri Thomas" , "Lance Thomas" , "Klay Thompson" , "Tristan Thompson" , "Sindarius Thornwell" , "Anthony Tolliver" , "Karl-Anthony Towns"
				 , "Gary TrentJr." , "Allonzo Trier" , "PJ Tucker" , "Evan Turner" , "Myles Turner" , "Ekpe Udoh" , "Tyler Ulis"
				 , "Jonas Valanciunas" , "Denzel Valentine" , "Fred VanVleet" , "Jarred Vanderbilt" , "Noah Vonleh" , "Nikola Vucevic" , "Dwyane Wade"
				 , "Moritz Wagner" , "Dion Waiters" , "Kemba Walker" , "Lonnie WalkerIV" , "John Wall" , "Tyrone Wallace" , "Brad Wanamaker"
				 , "T.J. Warren" , "Julian Washburn" , "Yuta Watanabe" , "Thomas Welsh" , "Russell Westbrook" , "Derrick White" , "Okaro White"
				 , "Isaiah Whitehead" , "Hassan Whiteside" , "Andrew Wiggins" , "Alan Williams" , "C.J. Williams" , "Johnathan Williams" , "Kenrich Williams"
				 , "Lou Williams" , "Marvin Williams" , "Troy Williams" , "Robert WilliamsIII" , "D.J. Wilson" , "Justise Winslow" , "Christian Wood"
				 , "Delon Wright" , "Guerschon Yabusele" , "Nick Young" , "Thaddeus Young" , "Trae Young" , "Cody Zeller" , "Tyler Zeller"
				 , "Ante Zizic", "Nene", "ZhouQi"};

		
		for (int count = 0; count < PlayerName.length; count++)
		{
			if (PlayerName[count].equalsIgnoreCase(answer))
			{
				PlayerTemp = call.UpdatePlayerTables(PlayerName[count], 1);
				
				Player = ScrapAndRead.ReturnStats(PlayerTemp, call.ReturnName());
				
				ScrapAndRead.WipeData();
				
				count = PlayerName.length+1;
			}
			
			else if (count == PlayerName.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				PlayerAnswer = answer;
				count = 0;
			}
		}
		
		result[0] = Player[0]; //Points
		result[1] = Player[1]; //3PA
		result[2] = Player[2]; //2PA
		result[3] = Player[3]; //FTA
		result[4] = Player[4]; //3%
		result[5] = Player[5]; //2%
		result[6] = Player[6]; //FT%
	
		
		
		System.out.println();
		System.out.println("Please enter team your player is facing");
		answer = scan.nextLine();
		
		String[] OpponentTeamAllow = new String[]{"ATL", "BOS", "BKN", "CHA", "CHI", "CLE", "DAL", "DEN", "DET", "GSW", "HOU"
				, "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN", "NOP", "NYK", "OKC", "ORL", "PHI", "PHX", "POR"
				, "SAC", "SAS", "TOR" , "UTA", "WAS"};
		
		for (int count = 0; count < TeamName.length; count++)
		{
			if (OpponentTeamAllow[count].equalsIgnoreCase(answer))
			{
				temp = call.UpdateTeamTables(TeamName[count], 1);
				
			
				OpponentTeam = ScrapAndRead.ReturnOppontentTeamStats(temp);
				
				ScrapAndRead.WipeData();
				
				count = OpponentTeamAllow.length+1;
			}
			
			else if (count == OpponentTeamAllow.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				count = 0;
			}
		}
		
		result[8] = OpponentTeam[0]; //Opponent Team Allow Points
		
		System.out.println();
		System.out.println("Who is primarily defending this player");
		answer = scan.nextLine();
		
		String[] PlayerDefense = new String[]{"Alex Abrines", "Quincy Acy" , "Jaylen Adams" , "Steven Adams" , "Bam Adebayo" , "Deng Adel" , "DeVaughn Akoon-Purcell" , "LaMarcus Aldridge"
				 , "Rawle Alkins" , "Grayson Allen" , "Jarrett Allen" , "Kadeem Allen" , "Al-Farouq Aminu" , "Justin Anderson" , "Kyle Anderson"
				 , "Ryan Anderson" , "Ike Anigbogu" , "Giannis Antetokounmpo" , "Kostas Antetokounmpo" , "Carmelo Anthony" , "OG Anunoby" , "Ryan Arcidiacono"
				 , "Trevor Ariza" , "D.J. Augustin" , "Deandre Ayton" , "Dwayne Bacon" , "Marvin BagleyIII" , "Ron Baker" , "Wade BaldwinIV"
				 , "Lonzo Ball" , "Mo Bamba" , "J.J. Barea" , "Harrison Barnes" , "Will Barton" , "Keita Bates-Diop" , "Nicolas Batum"
				 , "Jerryd Bayless" , "Aron Baynes" , "Kent Bazemore" , "Bradley Beal" , "Malik Beasley" , "Michael Beasley" , "Marco Belinelli"
				 , "Jordan Bell" , "DeAndre' Bembry" , "Dragan Bender" , "Dairis Bertans" , "Davis Bertans" , "Patrick Beverley" , "Khem Birch"
				 , "Bismack Biyombo" , "Nemanja Bjelica" , "Antonio Blakeney" , "Eric Bledsoe" , "Jaron Blossomgame" , "Trevon Bluiett" , "Bogdan Bogdanovic"
				 , "Bojan Bogdanovic" , "Andrew Bogut" , "Jonah Bolden" , "Isaac Bonga" , "Devin Booker" , "Chris Boucher" , "Avery Bradley"
				 , "Tony Bradley" , "Corey Brewer" , "Mikal Bridges" , "Miles Bridges" , "Isaiah Briscoe" , "Ryan Broekhoff" , "Malcolm Brogdon"
				 , "Dillon Brooks" , "MarShon Brooks" , "Bruce Brown" , "Jaylen Brown" , "Lorenzo Brown" , "Sterling Brown" , "Troy BrownJr."
				 , "Jalen Brunson" , "Thomas Bryant" , "Reggie Bullock" , "Trey Burke" , "Alec Burks" , "Deonte Burton" , "Jimmy Butler"
				 , "Bruno Caboclo" , "Jose Calderon" , "Kentavious Caldwell-Pope" , "Isaiah Canaan" , "Clint Capela" , "DeMarre Carroll" , "Jevon Carter"
				 , "Vince Carter" , "Wendell CarterJr." , "Michael Carter-Williams" , "Alex Caruso" , "Omri Casspi" , "Willie Cauley-Stein" , "Troy Caupain"
				 , "Tyler Cavanaugh" , "Tyson Chandler" , "Wilson Chandler" , "Joe Chealey" , "Marquese Chriss" , "Gary Clark" , "Ian Clark"
				 , "Jordan Clarkson" , "John Collins" , "Zach Collins" , "Darren Collison" , "Bonzie Colson" , "Mike Conley" , "Pat Connaughton"
				 , "Quinn Cook" , "DeMarcus Cousins" , "Robert Covington" , "Allen Crabbe" , "Torrey Craig" , "Jamal Crawford" , "Mitchell Creek"
				 , "Jae Crowder" , "Dante Cunningham" , "Seth Curry" , "Stephen Curry" , "Troy Daniels" , "Anthony Davis" , "Ed Davis"
				 , "Tyler Davis" , "DeMar DeRozan" , "Dewayne Dedmon" , "Sam Dekker" , "Angel Delgado" , "Matthew Dellavedova" , "Luol Deng"
				 , "Marcus Derrickson" , "Donte DiVincenzo" , "Cheick Diallo" , "Hamidou Diallo" , "Gorgui Dieng" , "Spencer Dinwiddie" , "Luka Doncic"
				 , "Tyler Dorsey" , "Damyean Dotson" , "PJ Dozier" , "Goran Dragic" , "Andre Drummond" , "Jared Dudley" , "Kris Dunn"
				 , "Kevin Durant" , "Trevon Duval" , "Vincent Edwards" , "Henry Ellenson" , "Wayne Ellington" , "Joel Embiid" , "James EnnisIII"
				 , "Drew Eubanks" , "Jacob Evans" , "Jawun Evans" , "Tyreke Evans" , "Dante Exum" , "Kenneth Faried" , "Derrick Favors"
				 , "Cristiano Felicio" , "Raymond Felton" , "Terrance Ferguson" , "Yogi Ferrell" , "Dorian Finney-Smith" , "Bryn Forbes" , "Evan Fournier"
				 , "De'Aaron Fox" , "Tim Frazier" , "Melvin FrazierJr." , "Channing Frye" , "Markelle Fultz" , "Wenyen Gabriel" , "Danilo Gallinari"
				 , "Langston Galloway" , "Marc Gasol" , "Pau Gasol" , "Rudy Gay" , "Paul George" , "Taj Gibson" , "Harry GilesIII"
				 , "Shai Gilgeous-Alexander" , "Rudy Gobert" , "Brandon Goodwin" , "Aaron Gordon" , "Eric Gordon" , "Marcin Gortat" , "Devonte' Graham"
				 , "Treveon Graham" , "Jerami Grant" , "Jerian Grant" , "Donte Grantham" , "Danny Green" , "Draymond Green" , "Gerald Green"
				 , "JaMychal Green" , "Jeff Green" , "Blake Griffin" , "Daniel Hamilton" , "Tim HardawayJr." , "James Harden" , "Maurice Harkless"
				 , "Montrezl Harrell" , "Devin Harris" , "Gary Harris" , "Joe Harris" , "Tobias Harris" , "Andrew Harrison" , "Shaquille Harrison"
				 , "Josh Hart" , "Isaiah Hartenstein" , "Udonis Haslem" , "Gordon Hayward" , "John Henson" , "Juancho Hernangomez" , "Willy Hernangomez"
				 , "Mario Hezonja" , "Isaiah Hicks" , "Buddy Hield" , "Haywood Highsmith" , "George Hill" , "Solomon Hill" , "Aaron Holiday"
				 , "Jrue Holiday" , "Justin Holiday" , "John Holland" , "Rondae Hollis-Jefferson" , "Richaun Holmes" , "Rodney Hood" , "Scotty Hopson"
				 , "Al Horford" , "Danuel HouseJr." , "Dwight Howard" , "Kevin Huerter" , "RJ Hunter" , "Chandler Hutchison" , "Serge Ibaka"
				 , "Andre Iguodala" , "Ersan Ilyasova" , "Joe Ingles" , "Brandon Ingram" , "Kyrie Irving" , "Jonathan Isaac" , "Wes Iwundu"
				 , "Demetrius Jackson" , "Frank Jackson" , "Josh Jackson" , "Justin Jackson" , "Reggie Jackson" , "Jaren JacksonJr." , "LeBron James"
				 , "Amile Jefferson" , "John Jenkins" , "Jonas Jerebko" , "Alize Johnson" , "Amir Johnson" , "BJ Johnson" , "James Johnson"
				 , "Stanley Johnson" , "Tyler Johnson" , "Wesley Johnson" , "Nikola Jokic" , "Damian Jones" , "Jalen Jones" , "Terrence Jones"
				 , "Tyus Jones" , "Derrick JonesJr." , "DeAndre Jordan" , "Cory Joseph" , "Frank Kaminsky" , "Enes Kanter" , "Luke Kennard"
				 , "Michael Kidd-Gilchrist" , "George King" , "Maxi Kleber" , "Brandon Knight" , "Kevin Knox" , "Furkan Korkmaz" , "Luke Kornet"
				 , "Kyle Korver" , "Kosta Koufos" , "Rodions Kurucs" , "Kyle Kuzma" , "Zach LaVine" , "Skal Labissiere" , "Jeremy Lamb"
				 , "Jake Layman" , "Caris LeVert" , "TJ Leaf" , "Courtney Lee" , "Damion Lee" , "Alex Len" , "Kawhi Leonard"
				 , "Meyers Leonard" , "Jon Leuer" , "Damian Lillard" , "Jeremy Lin" , "Shaun Livingston" , "Zach Lofton" , "Kevon Looney"
				 , "Brook Lopez" , "Robin Lopez" , "Kevin Love" , "Kyle Lowry" , "Jordan Loyd" , "Kalin Lucas" , "Timothe Luwawu-Cabarrot"
				 , "Tyler Lydon" , "Trey Lyles" , "Shelvin Mack" , "Daryl Macon" , "J.P. Macura" , "Ian Mahinmi" , "Thon Maker"
				 , "Boban Marjanovic" , "Lauri Markkanen" , "Jarell Martin" , "Frank Mason" , "Yante Maten" , "Wesley Matthews" , "Luc MbahaMoute"
				 , "Tahjere McCall" , "Patrick McCaw" , "CJ McCollum" , "T.J. McConnell" , "Doug McDermott" , "JaVale McGee" , "Rodney McGruder"
				 , "Alfonzo McKinnie" , "Ben McLemore" , "Jordan McRae" , "Jodie Meeks" , "Salah Mejri" , "De'Anthony Melton" , "Chimezie Metu"
				 , "Khris Middleton" , "CJ Miles" , "Darius Miller" , "Malcolm Miller" , "Patty Mills" , "Paul Millsap" , "Shake Milton"
				 , "Nikola Mirotic" , "Donovan Mitchell" , "Naz Mitrou-Long" , "Malik Monk" , "Greg Monroe" , "Ben Moore" , "E'Twaun Moore"
				 , "Eric Moreland" , "Jaylen Morris" , "Marcus Morris" , "Markieff Morris" , "Monte Morris" , "Johnathan Motley" , "Timofey Mozgov"
				 , "Emmanuel Mudiay" , "Dejounte Murray" , "Jamal Murray" , "Dzanan Musa" , "Mike Muscala" , "Svi Mykhailiuk" , "Abdel Nader"
				 , "Larry NanceJr." , "Shabazz Napier" , "Raul Neto" , "Georges Niang" , "Joakim Noah" , "Nerlens Noel" , "Dirk Nowitzki"
				 , "Frank Ntilikina" , "James Nunnally" , "Jusuf Nurkic" , "David Nwaba" , "Royce O'Neale" , "Kyle O'Quinn" , "Semi Ojeleye"
				 , "Jahlil Okafor" , "Elie Okobo" , "Josh Okogie" , "Victor Oladipo" , "Kelly Olynyk" , "Cedi Osman" , "Kelly OubreJr."
				 , "Zaza Pachulia" , "Jabari Parker" , "Tony Parker" , "Chandler Parsons" , "Patrick Patterson" , "Justin Patton" , "Chris Paul"
				 , "Cameron Payne" , "Elfrid Payton" , "Gary PaytonII" , "Theo Pinson" , "Mason Plumlee" , "Miles Plumlee" , "Jakob Poeltl"
				 , "Quincy Pondexter" , "Michael PorterJr." , "Otto PorterJr." , "Bobby Portis" , "Kristaps Porzingis" , "Dwight Powell" , "Norman Powell"
				 , "Alex Poythress" , "Taurean Prince" , "Ivan Rabb" , "Chasson Randle" , "Julius Randle" , "JJ Redick" , "Davon Reed"
				 , "Cameron Reynolds" , "Josh Richardson" , "Malachi Richardson" , "Austin Rivers" , "Andre Roberson" , "Devin Robinson" , "Duncan Robinson"
				 , "Jerome Robinson" , "Mitchell Robinson" , "Glenn RobinsonIII" , "Rajon Rondo" , "Derrick Rose" , "Terrence Ross" , "Terry Rozier"
				 , "Ricky Rubio" , "D'Angelo Russell" , "Domantas Sabonis" , "Brandon Sampson" , "Dario Saric" , "Tomas Satoransky" , "Dennis Schroder"
				 , "Mike Scott" , "Thabo Sefolosha" , "Wayne Selden" , "Collin Sexton" , "Landry Shamet" , "Iman Shumpert" , "Pascal Siakam"
				 , "Jordan Sibert" , "Ben Simmons" , "Jonathon Simmons" , "Kobi Simmons" , "Anfernee Simons" , "Marcus Smart" , "Ish Smith"
				 , "JR Smith" , "Jason Smith" , "Zhaire Smith" , "Dennis SmithJr." , "Tony Snell" , "Richard Solomon" , "Ray Spalding"
				 , "Omari Spellman" , "Nik Stauskas" , "DJ Stephens" , "Lance Stephenson" , "Edmond Sumner" , "Caleb Swanigan" , "Jayson Tatum"
				 , "Jeff Teague" , "Garrett Temple" , "Milos Teodosic" , "Jared Terrell" , "Emanuel Terry" , "Daniel Theis" , "Isaiah Thomas"
				 , "Khyri Thomas" , "Lance Thomas" , "Klay Thompson" , "Tristan Thompson" , "Sindarius Thornwell" , "Anthony Tolliver" , "Karl-Anthony Towns"
				 , "Gary TrentJr." , "Allonzo Trier" , "PJ Tucker" , "Evan Turner" , "Myles Turner" , "Ekpe Udoh" , "Tyler Ulis"
				 , "Jonas Valanciunas" , "Denzel Valentine" , "Fred VanVleet" , "Jarred Vanderbilt" , "Noah Vonleh" , "Nikola Vucevic" , "Dwyane Wade"
				 , "Moritz Wagner" , "Dion Waiters" , "Kemba Walker" , "Lonnie WalkerIV" , "John Wall" , "Tyrone Wallace" , "Brad Wanamaker"
				 , "T.J. Warren" , "Julian Washburn" , "Yuta Watanabe" , "Thomas Welsh" , "Russell Westbrook" , "Derrick White" , "Okaro White"
				 , "Isaiah Whitehead" , "Hassan Whiteside" , "Andrew Wiggins" , "Alan Williams" , "C.J. Williams" , "Johnathan Williams" , "Kenrich Williams"
				 , "Lou Williams" , "Marvin Williams" , "Troy Williams" , "Robert WilliamsIII" , "D.J. Wilson" , "Justise Winslow" , "Christian Wood"
				 , "Delon Wright" , "Guerschon Yabusele" , "Nick Young" , "Thaddeus Young" , "Trae Young" , "Cody Zeller" , "Tyler Zeller"
				 , "Ante Zizic", "Nene", "ZhouQi"};
		
		for (int count = 0; count < PlayerDefense.length; count++)
		{
			if (PlayerDefense[count].equalsIgnoreCase(answer))
			{
				String info = call.UpdateDefenseTables(PlayerDefense[count]);
				
				ScrapAndRead.WipeData();
				
				ScrapAndRead.Returnui4jStats(info);
		
				OpponentPlayer = ScrapAndRead.ReturnList();
				
				ScrapAndRead.WipeData();
				
				count = PlayerDefense.length+1;
			}
			
			else if (count == PlayerDefense.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				count = 0;
			}
		
		}
	
		result[9] = OpponentPlayer[3]; //Opponent 3%
		result[10] = OpponentPlayer[4]; //Opponent 2%
		
		System.out.println();
		System.out.println("Point 3PA  2PA   FTA   3%     2%    FT%    Team   OTeam  O3%   O2%");
		for(int count = 0; count < result.length; count++)
		{
			System.out.print(result[count] + "  ");
		}
		
		System.out.println();
		
		Formulas run = new Formulas();
		//public void ScoreFormula(BigDecimal TeamScore, BigDecimal OTeamDefense, BigDecimal DefenderThreePercentAllow, 
		//		BigDecimal DefenderTwoPercentAllow, BigDecimal PlayerScore, 
		//		BigDecimal ThreeShot, BigDecimal TwoShot, BigDecimal FreeShot, BigDecimal ThreeChance,
		//		BigDecimal TwoChance, BigDecimal FreeChance, String PlayerName) throws IOException
		//result[8] = OpponentTeam[0];		result[7] = Team[0];
		//result[0] = Player[5]; //Points
		//result[1] = Player[6]; //3PA
		//result[2] = Player[7]; //2PA
		//result[3] = Player[8]; //FTA
		//result[4] = Player[9]; //3%
		//result[5] = Player[10]; //2%
		//result[6] = Player[11]; //FT%
		//result[9] = OpponentPlayer[3]; //Opponent 3%
		//result[10] = OpponentPlayer[4]; //Opponent 2%
		
		run.ScoreFormula(result[7], result[8], result[9], result[10], result[0], result[1], result[2], result[3], result[4], result[5], result[6], PlayerAnswer, TeamAnswer, PlayerTemp);
		//   3/6/2019 John Collin of Atlanta Hawks Vs Davis Bertans of San Antonio Spurs = 18.074 | 19.5 | False | Result = 18 points scored
		//   3/6/2019 Davis Bertans of San Antonio Spurs Vs John Collin of Atlanta Hawks = 7.6 | 8.0 | False | Result = 12 points scored
		
		
	}
	
	
	
	
	if (reply == 2)
	{
		System.out.println();
		System.out.println("Please enter what team your player is on (Please use team acronym)");
		answer = scan.nextLine();
		TeamAnswer = answer;
		
		String[] TeamName = new String[]{"ATL", "BOS", "BKN", "CHA", "CHI", "CLE", "DAL", "DEN", "DET", "GSW", "HOU"
				, "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN", "NOP", "NYK", "OKC", "ORL", "PHI", "PHX", "POR"
				, "SAC", "SAS", "TOR" , "UTA", "WAS"};
		for (int count = 0; count < TeamName.length; count++)
		{
			if (TeamName[count].equalsIgnoreCase(TeamAnswer))
			{
				Team = ScrapAndRead.ReturnTeamStats(call.UpdateTeamRebound(TeamName[count]), false);
			
				count = TeamName.length+1;
			}
			
			else if (count == TeamName.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				count = 0;
			}
		}
		
		result[0] = Team[0]; //Team Rebound
		result[2] = Team[1]; //Offensive Rebound
		result[3] = Team[2]; //Defensive Rebound
		result[4] = Team[3]; //Shot Average
		result[6] = Team[4]; //Shot %
		
		System.out.println();
		System.out.println("Please enter what player you want to check");
		answer = scan.nextLine();
		PlayerAnswer = answer;
		
		String[] PlayerName = new String[]{"Alex Abrines", "Quincy Acy" , "Jaylen Adams" , "Steven Adams" , "Bam Adebayo" , "Deng Adel" , "DeVaughn Akoon-Purcell" , "LaMarcus Aldridge"
				 , "Rawle Alkins" , "Grayson Allen" , "Jarrett Allen" , "Kadeem Allen" , "Al-Farouq Aminu" , "Justin Anderson" , "Kyle Anderson"
				 , "Ryan Anderson" , "Ike Anigbogu" , "Giannis Antetokounmpo" , "Kostas Antetokounmpo" , "Carmelo Anthony" , "OG Anunoby" , "Ryan Arcidiacono"
				 , "Trevor Ariza" , "D.J. Augustin" , "Deandre Ayton" , "Dwayne Bacon" , "Marvin BagleyIII" , "Ron Baker" , "Wade BaldwinIV"
				 , "Lonzo Ball" , "Mo Bamba" , "J.J. Barea" , "Harrison Barnes" , "Will Barton" , "Keita Bates-Diop" , "Nicolas Batum"
				 , "Jerryd Bayless" , "Aron Baynes" , "Kent Bazemore" , "Bradley Beal" , "Malik Beasley" , "Michael Beasley" , "Marco Belinelli"
				 , "Jordan Bell" , "DeAndre' Bembry" , "Dragan Bender" , "Dairis Bertans" , "Davis Bertans" , "Patrick Beverley" , "Khem Birch"
				 , "Bismack Biyombo" , "Nemanja Bjelica" , "Antonio Blakeney" , "Eric Bledsoe" , "Jaron Blossomgame" , "Trevon Bluiett" , "Bogdan Bogdanovic"
				 , "Bojan Bogdanovic" , "Andrew Bogut" , "Jonah Bolden" , "Isaac Bonga" , "Devin Booker" , "Chris Boucher" , "Avery Bradley"
				 , "Tony Bradley" , "Corey Brewer" , "Mikal Bridges" , "Miles Bridges" , "Isaiah Briscoe" , "Ryan Broekhoff" , "Malcolm Brogdon"
				 , "Dillon Brooks" , "MarShon Brooks" , "Bruce Brown" , "Jaylen Brown" , "Lorenzo Brown" , "Sterling Brown" , "Troy BrownJr."
				 , "Jalen Brunson" , "Thomas Bryant" , "Reggie Bullock" , "Trey Burke" , "Alec Burks" , "Deonte Burton" , "Jimmy Butler"
				 , "Bruno Caboclo" , "Jose Calderon" , "Kentavious Caldwell-Pope" , "Isaiah Canaan" , "Clint Capela" , "DeMarre Carroll" , "Jevon Carter"
				 , "Vince Carter" , "Wendell CarterJr." , "Michael Carter-Williams" , "Alex Caruso" , "Omri Casspi" , "Willie Cauley-Stein" , "Troy Caupain"
				 , "Tyler Cavanaugh" , "Tyson Chandler" , "Wilson Chandler" , "Joe Chealey" , "Marquese Chriss" , "Gary Clark" , "Ian Clark"
				 , "Jordan Clarkson" , "John Collins" , "Zach Collins" , "Darren Collison" , "Bonzie Colson" , "Mike Conley" , "Pat Connaughton"
				 , "Quinn Cook" , "DeMarcus Cousins" , "Robert Covington" , "Allen Crabbe" , "Torrey Craig" , "Jamal Crawford" , "Mitchell Creek"
				 , "Jae Crowder" , "Dante Cunningham" , "Seth Curry" , "Stephen Curry" , "Troy Daniels" , "Anthony Davis" , "Ed Davis"
				 , "Tyler Davis" , "DeMar DeRozan" , "Dewayne Dedmon" , "Sam Dekker" , "Angel Delgado" , "Matthew Dellavedova" , "Luol Deng"
				 , "Marcus Derrickson" , "Donte DiVincenzo" , "Cheick Diallo" , "Hamidou Diallo" , "Gorgui Dieng" , "Spencer Dinwiddie" , "Luka Doncic"
				 , "Tyler Dorsey" , "Damyean Dotson" , "PJ Dozier" , "Goran Dragic" , "Andre Drummond" , "Jared Dudley" , "Kris Dunn"
				 , "Kevin Durant" , "Trevon Duval" , "Vincent Edwards" , "Henry Ellenson" , "Wayne Ellington" , "Joel Embiid" , "James EnnisIII"
				 , "Drew Eubanks" , "Jacob Evans" , "Jawun Evans" , "Tyreke Evans" , "Dante Exum" , "Kenneth Faried" , "Derrick Favors"
				 , "Cristiano Felicio" , "Raymond Felton" , "Terrance Ferguson" , "Yogi Ferrell" , "Dorian Finney-Smith" , "Bryn Forbes" , "Evan Fournier"
				 , "De'Aaron Fox" , "Tim Frazier" , "Melvin FrazierJr." , "Channing Frye" , "Markelle Fultz" , "Wenyen Gabriel" , "Danilo Gallinari"
				 , "Langston Galloway" , "Marc Gasol" , "Pau Gasol" , "Rudy Gay" , "Paul George" , "Taj Gibson" , "Harry GilesIII"
				 , "Shai Gilgeous-Alexander" , "Rudy Gobert" , "Brandon Goodwin" , "Aaron Gordon" , "Eric Gordon" , "Marcin Gortat" , "Devonte' Graham"
				 , "Treveon Graham" , "Jerami Grant" , "Jerian Grant" , "Donte Grantham" , "Danny Green" , "Draymond Green" , "Gerald Green"
				 , "JaMychal Green" , "Jeff Green" , "Blake Griffin" , "Daniel Hamilton" , "Tim HardawayJr." , "James Harden" , "Maurice Harkless"
				 , "Montrezl Harrell" , "Devin Harris" , "Gary Harris" , "Joe Harris" , "Tobias Harris" , "Andrew Harrison" , "Shaquille Harrison"
				 , "Josh Hart" , "Isaiah Hartenstein" , "Udonis Haslem" , "Gordon Hayward" , "John Henson" , "Juancho Hernangomez" , "Willy Hernangomez"
				 , "Mario Hezonja" , "Isaiah Hicks" , "Buddy Hield" , "Haywood Highsmith" , "George Hill" , "Solomon Hill" , "Aaron Holiday"
				 , "Jrue Holiday" , "Justin Holiday" , "John Holland" , "Rondae Hollis-Jefferson" , "Richaun Holmes" , "Rodney Hood" , "Scotty Hopson"
				 , "Al Horford" , "Danuel HouseJr." , "Dwight Howard" , "Kevin Huerter" , "RJ Hunter" , "Chandler Hutchison" , "Serge Ibaka"
				 , "Andre Iguodala" , "Ersan Ilyasova" , "Joe Ingles" , "Brandon Ingram" , "Kyrie Irving" , "Jonathan Isaac" , "Wes Iwundu"
				 , "Demetrius Jackson" , "Frank Jackson" , "Josh Jackson" , "Justin Jackson" , "Reggie Jackson" , "Jaren JacksonJr." , "LeBron James"
				 , "Amile Jefferson" , "John Jenkins" , "Jonas Jerebko" , "Alize Johnson" , "Amir Johnson" , "BJ Johnson" , "James Johnson"
				 , "Stanley Johnson" , "Tyler Johnson" , "Wesley Johnson" , "Nikola Jokic" , "Damian Jones" , "Jalen Jones" , "Terrence Jones"
				 , "Tyus Jones" , "Derrick JonesJr." , "DeAndre Jordan" , "Cory Joseph" , "Frank Kaminsky" , "Enes Kanter" , "Luke Kennard"
				 , "Michael Kidd-Gilchrist" , "George King" , "Maxi Kleber" , "Brandon Knight" , "Kevin Knox" , "Furkan Korkmaz" , "Luke Kornet"
				 , "Kyle Korver" , "Kosta Koufos" , "Rodions Kurucs" , "Kyle Kuzma" , "Zach LaVine" , "Skal Labissiere" , "Jeremy Lamb"
				 , "Jake Layman" , "Caris LeVert" , "TJ Leaf" , "Courtney Lee" , "Damion Lee" , "Alex Len" , "Kawhi Leonard"
				 , "Meyers Leonard" , "Jon Leuer" , "Damian Lillard" , "Jeremy Lin" , "Shaun Livingston" , "Zach Lofton" , "Kevon Looney"
				 , "Brook Lopez" , "Robin Lopez" , "Kevin Love" , "Kyle Lowry" , "Jordan Loyd" , "Kalin Lucas" , "Timothe Luwawu-Cabarrot"
				 , "Tyler Lydon" , "Trey Lyles" , "Shelvin Mack" , "Daryl Macon" , "J.P. Macura" , "Ian Mahinmi" , "Thon Maker"
				 , "Boban Marjanovic" , "Lauri Markkanen" , "Jarell Martin" , "Frank Mason" , "Yante Maten" , "Wesley Matthews" , "Luc MbahaMoute"
				 , "Tahjere McCall" , "Patrick McCaw" , "CJ McCollum" , "T.J. McConnell" , "Doug McDermott" , "JaVale McGee" , "Rodney McGruder"
				 , "Alfonzo McKinnie" , "Ben McLemore" , "Jordan McRae" , "Jodie Meeks" , "Salah Mejri" , "De'Anthony Melton" , "Chimezie Metu"
				 , "Khris Middleton" , "CJ Miles" , "Darius Miller" , "Malcolm Miller" , "Patty Mills" , "Paul Millsap" , "Shake Milton"
				 , "Nikola Mirotic" , "Donovan Mitchell" , "Naz Mitrou-Long" , "Malik Monk" , "Greg Monroe" , "Ben Moore" , "E'Twaun Moore"
				 , "Eric Moreland" , "Jaylen Morris" , "Marcus Morris" , "Markieff Morris" , "Monte Morris" , "Johnathan Motley" , "Timofey Mozgov"
				 , "Emmanuel Mudiay" , "Dejounte Murray" , "Jamal Murray" , "Dzanan Musa" , "Mike Muscala" , "Svi Mykhailiuk" , "Abdel Nader"
				 , "Larry NanceJr." , "Shabazz Napier" , "Raul Neto" , "Georges Niang" , "Joakim Noah" , "Nerlens Noel" , "Dirk Nowitzki"
				 , "Frank Ntilikina" , "James Nunnally" , "Jusuf Nurkic" , "David Nwaba" , "Royce O'Neale" , "Kyle O'Quinn" , "Semi Ojeleye"
				 , "Jahlil Okafor" , "Elie Okobo" , "Josh Okogie" , "Victor Oladipo" , "Kelly Olynyk" , "Cedi Osman" , "Kelly OubreJr."
				 , "Zaza Pachulia" , "Jabari Parker" , "Tony Parker" , "Chandler Parsons" , "Patrick Patterson" , "Justin Patton" , "Chris Paul"
				 , "Cameron Payne" , "Elfrid Payton" , "Gary PaytonII" , "Theo Pinson" , "Mason Plumlee" , "Miles Plumlee" , "Jakob Poeltl"
				 , "Quincy Pondexter" , "Michael PorterJr." , "Otto PorterJr." , "Bobby Portis" , "Kristaps Porzingis" , "Dwight Powell" , "Norman Powell"
				 , "Alex Poythress" , "Taurean Prince" , "Ivan Rabb" , "Chasson Randle" , "Julius Randle" , "JJ Redick" , "Davon Reed"
				 , "Cameron Reynolds" , "Josh Richardson" , "Malachi Richardson" , "Austin Rivers" , "Andre Roberson" , "Devin Robinson" , "Duncan Robinson"
				 , "Jerome Robinson" , "Mitchell Robinson" , "Glenn RobinsonIII" , "Rajon Rondo" , "Derrick Rose" , "Terrence Ross" , "Terry Rozier"
				 , "Ricky Rubio" , "D'Angelo Russell" , "Domantas Sabonis" , "Brandon Sampson" , "Dario Saric" , "Tomas Satoransky" , "Dennis Schroder"
				 , "Mike Scott" , "Thabo Sefolosha" , "Wayne Selden" , "Collin Sexton" , "Landry Shamet" , "Iman Shumpert" , "Pascal Siakam"
				 , "Jordan Sibert" , "Ben Simmons" , "Jonathon Simmons" , "Kobi Simmons" , "Anfernee Simons" , "Marcus Smart" , "Ish Smith"
				 , "JR Smith" , "Jason Smith" , "Zhaire Smith" , "Dennis SmithJr." , "Tony Snell" , "Richard Solomon" , "Ray Spalding"
				 , "Omari Spellman" , "Nik Stauskas" , "DJ Stephens" , "Lance Stephenson" , "Edmond Sumner" , "Caleb Swanigan" , "Jayson Tatum"
				 , "Jeff Teague" , "Garrett Temple" , "Milos Teodosic" , "Jared Terrell" , "Emanuel Terry" , "Daniel Theis" , "Isaiah Thomas"
				 , "Khyri Thomas" , "Lance Thomas" , "Klay Thompson" , "Tristan Thompson" , "Sindarius Thornwell" , "Anthony Tolliver" , "Karl-Anthony Towns"
				 , "Gary TrentJr." , "Allonzo Trier" , "PJ Tucker" , "Evan Turner" , "Myles Turner" , "Ekpe Udoh" , "Tyler Ulis"
				 , "Jonas Valanciunas" , "Denzel Valentine" , "Fred VanVleet" , "Jarred Vanderbilt" , "Noah Vonleh" , "Nikola Vucevic" , "Dwyane Wade"
				 , "Moritz Wagner" , "Dion Waiters" , "Kemba Walker" , "Lonnie WalkerIV" , "John Wall" , "Tyrone Wallace" , "Brad Wanamaker"
				 , "T.J. Warren" , "Julian Washburn" , "Yuta Watanabe" , "Thomas Welsh" , "Russell Westbrook" , "Derrick White" , "Okaro White"
				 , "Isaiah Whitehead" , "Hassan Whiteside" , "Andrew Wiggins" , "Alan Williams" , "C.J. Williams" , "Johnathan Williams" , "Kenrich Williams"
				 , "Lou Williams" , "Marvin Williams" , "Troy Williams" , "Robert WilliamsIII" , "D.J. Wilson" , "Justise Winslow" , "Christian Wood"
				 , "Delon Wright" , "Guerschon Yabusele" , "Nick Young" , "Thaddeus Young" , "Trae Young" , "Cody Zeller" , "Tyler Zeller"
				 , "Ante Zizic", "Nene", "ZhouQi"};

		
		for (int count = 0; count < PlayerName.length; count++)
		{
			if (PlayerName[count].equalsIgnoreCase(answer))
			{
				ScrapAndRead.WipeData();
				
				call.UpdateRebound(PlayerName[count].toLowerCase());
				
				ScrapAndRead.Returnui4jReboundStats(ScrapAndRead.ReturnData());
				
				Player = ScrapAndRead.ReturnList();
				
				ScrapAndRead.WipeData();
				
				count = PlayerName.length+1;
			}
			
			else if (count == PlayerName.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				PlayerAnswer = answer;
				count = 0;
			}
		}
		
		result[8] = Player[9]; //Offensive Rebound
		result[9] = Player[10]; //Defensive Rebound
		result[10] = Player[11]; //Offensive %
		result[11] = Player[12]; //Defensive %
		
	
		
		
		System.out.println();
		System.out.println("Please enter team your player is facing");
		answer = scan.nextLine();
		
		String[] OpponentTeamAllow = new String[]{"ATL", "BOS", "BKN", "CHA", "CHI", "CLE", "DAL", "DEN", "DET", "GSW", "HOU"
				, "IND", "LAC", "LAL", "MEM", "MIA", "MIL", "MIN", "NOP", "NYK", "OKC", "ORL", "PHI", "PHX", "POR"
				, "SAC", "SAS", "TOR" , "UTA", "WAS"};
		
		for (int count = 0; count < TeamName.length; count++)
		{
			if (OpponentTeamAllow[count].equalsIgnoreCase(answer))
			{
				OpponentTeam = ScrapAndRead.ReturnTeamStats(call.UpdateTeamRebound(OpponentTeamAllow[count]), false);
				
				count = OpponentTeamAllow.length+1;
			}
			
			else if (count == OpponentTeamAllow.length-1)
			{
				System.out.println("We found no one by that name. Please try again");
				answer = scan.nextLine();
				count = 0;
			}
		}
		
		result[1] = OpponentTeam[0]; //Opponent Team Total Rebound
		result[5] = OpponentTeam[3]; //Opponent Team Shot Average
		result[7] = OpponentTeam[4]; //Opponent Team Shot %
		
		
		
		System.out.println();
		System.out.println("Rebound ORebound  Offensive   Defensive   ShotAvg     OShotAvg     Shot%      OShot%  PORebound PDRebound PlayerO%  PlayerD%");
		for(int count = 0; count < result.length; count++)
		{
			System.out.print(result[count] + "       ");
		}
		
		System.out.println();
		
		//ReboundFormula(double TeamRebound, double OTeamRebound, double OffensiveRebound, double DefensiveRebound,
		//double ShotsTotalAverage, double OShotsTotalAverage, double TeamShotAveragePercent, double OTeamShotAveragePercent,
		//double PlayerORebound, double PlayerDRebound, double PlayerOPercentRebound, double PlayerDPercentRebound
	}
	
	System.out.println("If done please press 0!");
	answer = scan.nextLine();
	
	if (answer.equals("0"))
	{
		System.exit(0);
	}
	
}

	
	
    	
}

