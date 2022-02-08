package Math;

import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.nodes.Document;

import WebScraping.GetData;
import WebScraping.ScrapAndRead;

/*links:
 * https://www.teamrankings.com/nba/team/atlanta-hawks (hawks team stats)
 * https://www.basketball-reference.com/teams/ATL/2019.html (hawks team stats)
 * https://www.foxsports.com/nba/atlanta-hawks-team-stats?season=2018&category=REBOUNDING&time=0 (hawks team stats)
 * https://www.teamrankings.com/nba/team/phoenix-suns (Suns team stats)
 * https://www.basketball-reference.com/teams/PHO/2019.html (Suns team stats)
 * 
 * https://www.basketball-reference.com/players/c/collijo01/gamelog/2019 (John Collins game by game stats)
 * https://www.basketball-reference.com/players/c/collijo01.html (John Collins Averages)
 * 
*/
public class Testing {

	public static void main(String[] args) throws Exception {
		
		GetData call = new GetData();
		
		System.out.println();
		System.out.println("Printing: " + call.UpdateRebound("John Collins"));
		System.out.println();
		ScrapAndRead.Returnui4jReboundStats(ScrapAndRead.ReturnData());
		BigDecimal[] list = ScrapAndRead.ReturnList();
		//for (int count = 0; count < list.length; count++)
		//{
		//	System.out.print(list[count] + "|");
		//}
		
		BigDecimal[] temp = ScrapAndRead.ReturnTeamStats(call.UpdateTeamRebound("ATL"), false);
		
		System.out.println("list[0] = " + temp[0]);
		
		list[0] = temp[0];
		list[2] = temp[1];
		list[3] = temp[2];
		
		for (int count = 0; count < list.length; count++)
		{
			System.out.print(list[count] + "|");
		}
		
		System.out.println();
		
		
		System.out.println("Done");
		
		
		
		//ScoreFormula(double TeamScore, double OTeamDefense, double DefenderPercentAllow, double PlayerScore, 
		//double PlayerThreeShot, double PlayerTwoShot, double PlayerFreeShot, double PlayerThreeChance,
		//double PlayerTwoChance, double PlayerFreeChance)
		
		//Lakers Vs Hawks data using John Collins as player (Atlanta Hawks) 2/12/2019
		//run.ScoreFormula(110.9, 113.4, 0.459, 19.3, 2.475, 10.775, 4.15, 0.354, 0.631, 0.740, 0.578);
		//System.out.println("John Collin Scored: 22");
		//System.out.println("-------------------------------------------------------------------------------------------------------");
		
		//ReboundFormula(double TeamRebound, double OTeamRebound, double OffensiveRebound, double DefensiveRebound,
		//double ShotsTotalAverage, double OShotsTotalAverage, double TeamShotAveragePercent, double OTeamShotAveragePercent,
		//double PlayerORebound, double PlayerDRebound, double PlayerOPercentRebound, double PlayerDPercentRebound
		
		//Suns Vs Hawks data using John Collins as player (Atlanta Hawks) 2/2/2019 (prediction worked)
		/*run.ReboundFormula(54, 48.2, 13.662, 40.338, 89.86, 87.06, 45.2, 45.7, 3.7, 6.2, 12.9, 22.8);
		System.out.println("John Collin Rebounded: 16");
		
		//Lakers Vs Hawks data using John Collins as player (Atlanta Hawks) 2/12/2019 (prediction worked)
		run.ReboundFormula(53.7, 56.9, 13.4787, 40.2213, 89.5, 90.4, 45.5, 47.4, 3.7, 6.1, 13.1, 22.2  );
		System.out.println("John Collin Rebounded: 8");
		*/
	}

}
