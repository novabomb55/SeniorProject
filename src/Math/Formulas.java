package Math;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jsoup.nodes.Document;

public class Formulas {
	
	double predictedTeamScore;
	double predictedPlayerScore;
	double predictedThreePlayerPercent, predictedTwoPlayerPercent, predictedTotalPlayerPercent;
	double temp, temp2, temp3;
	double totalShots;
	
	//BigDecimal two = new BigDecimal("2");
			

	public void ScoreFormula(BigDecimal TeamScore, BigDecimal OTeamDefense, BigDecimal DefenderThreePercentAllow, 
			BigDecimal DefenderTwoPercentAllow, BigDecimal PlayerScore, 
			BigDecimal ThreeShot, BigDecimal TwoShot, BigDecimal FreeShot, BigDecimal ThreeChance,
			BigDecimal TwoChance, BigDecimal FreeChance, String PlayerName, String TeamName, Document doc) throws IOException
	{
		//System.out.println("TeamScore: " + TeamScore + " | " + "OTeamScore: " + OTeamDefense + " | " + 
				//"Defender3Allow: " + DefenderThreePercentAllow + " | " + "Defender2Allow: " + DefenderTwoPercentAllow + 
				//" | " + "PlayerScore: " + PlayerScore + " | " + "Threeshot: " + ThreeShot + " | " +
			    //"TwoShot: " + TwoShot + " | " + "FreeShot: " +  FreeShot + " | " + "ThreeChance: " +  ThreeChance + " | " 
			    //+ "Twochance: " +  TwoChance + " | " + "FreeChance: " +  FreeChance + " | " + PlayerName);
		
		//PredictedTeamScore is created by taking teamscore + opposingteamsore divided to find the average
		predictedTeamScore = (TeamScore.doubleValue() + OTeamDefense.doubleValue())/2;
		//temp holds a value which is Player's Score / Team score
		temp = PlayerScore.doubleValue()/TeamScore.doubleValue();
		//temp3 holds temp * predictedTeamScore minus TeamScore
		temp3 = temp*(predictedTeamScore - TeamScore.doubleValue());
		
		double[] points = new double[3];
		
		points[0] = ThreeShot.doubleValue()*ThreeChance.doubleValue();
		points[1] = TwoShot.doubleValue()*TwoChance.doubleValue();
		points[2] = FreeShot.doubleValue()*FreeChance.doubleValue();
		
		//ThreeShot / (ThreeShot + TwoShot) How many threes are shot in his total shot output
		temp = ThreeShot.doubleValue()/(ThreeShot.doubleValue()+TwoShot.doubleValue());
		//Twoshot / (Threeshot + TwoShot) How many twos are shot in his total shot output
		temp2 = TwoShot.doubleValue()/(ThreeShot.doubleValue()+TwoShot.doubleValue());
		//Gets total shots
		totalShots = TwoShot.doubleValue()+ThreeShot.doubleValue();
		
		//Three % added to defensive three % then find the average of them both
		predictedThreePlayerPercent = (DefenderThreePercentAllow.doubleValue()/100) + ThreeChance.doubleValue();
		predictedThreePlayerPercent = predictedThreePlayerPercent/2;
		//Two % added to defense two % then find the average of them both
		predictedTwoPlayerPercent = (DefenderTwoPercentAllow.doubleValue()/100) + TwoChance.doubleValue();
		predictedTwoPlayerPercent = predictedTwoPlayerPercent/2;
		
		//total shots * How many threes are normally shot * 3 predicted % * 3 points
		predictedPlayerScore = totalShots*temp*predictedThreePlayerPercent*3;
		//total shots * How many twos are normally shot * 2 predicted % * 2 points
		predictedPlayerScore = predictedPlayerScore + (totalShots*temp2*predictedTwoPlayerPercent*2);
		//Free throw shot amount * Free throw %
		predictedPlayerScore = predictedPlayerScore + (FreeShot.doubleValue()*FreeChance.doubleValue());
		//Ending amount added with the leftover points using a player's % of team scoring
		predictedPlayerScore = predictedPlayerScore + temp3;
		
		//Standard deviation of john = 3.83
		StandardDeviationMath num = new StandardDeviationMath();
		double StandardDeviation = num.SDMath(doc);
		double[] deviation = new double[7];
		
		deviation[0] = predictedPlayerScore - (3*StandardDeviation);
		deviation[1] = predictedPlayerScore - (2*StandardDeviation);
		deviation[2] = predictedPlayerScore - StandardDeviation;
		deviation[3] = predictedPlayerScore;
		deviation[4] = predictedPlayerScore + StandardDeviation;
		deviation[5] = predictedPlayerScore + (2*StandardDeviation);
		deviation[6] = predictedPlayerScore + (3*StandardDeviation);
		
		String yesno = "No";
		
		if (predictedPlayerScore > PlayerScore.doubleValue())
		{
			yesno = "Yes";
		}
		
		//System.out.println(predictedThreePlayerPercent + " | " + predictedTwoPlayerPercent);
		
		System.out.println();
		
		//System.out.println("Total Shots that would go in: " + (points[0] + points[1] + points[2]));
		
		
		//System.out.println("Predicted PlayerScore: " + predictedPlayerScore + " | "
				//+ "Player Average Score: " + PlayerScore);
		
		System.out.println();
		
		System.out.println("Outcome statement \n Will " + PlayerName + " do better than average: " + yesno + 
				"\n Player X has a 68.2% of scoring between: " + deviation[4] + " - " + deviation[2] + 
				"\n Player X has a 34.1% of scoring between: " + deviation[4] + " - " + deviation[3] +
				"\n Player X has a 34.1% of scoring between: " + deviation[3] + " - " + deviation[2]);
	}
	
	double predictedPossibleRebounds, predictedTeamRebound, predictedOTeamRebound;
	double Oratio, Dratio;
	double OOratio, ODratio;
	double Temp, Temp2;
	
	public void ReboundFormula(BigDecimal TeamRebound, BigDecimal OTeamRebound, BigDecimal OffensiveRebound, BigDecimal DefensiveRebound,
			BigDecimal ShotsTotalAverage, BigDecimal OShotsTotalAverage, BigDecimal TeamShotAveragePercent, BigDecimal OTeamShotAveragePercent,
			BigDecimal PlayerORebound, BigDecimal PlayerDRebound, BigDecimal PlayerOPercentRebound, BigDecimal PlayerDPercentRebound, String PlayerName)
	{
		//Possible rebounds using amount of shots that "miss" which result in a rebound chance. Note fouls, ball going out of bounds
		//, or other event that causes a rebound to not occur on a missed shot
		predictedPossibleRebounds = (ShotsTotalAverage.doubleValue()*(1 - TeamShotAveragePercent.doubleValue()/100)) + OShotsTotalAverage.doubleValue()*(1 - TeamShotAveragePercent.doubleValue()/100);
		Temp = TeamRebound.doubleValue()/(TeamRebound.doubleValue()+OTeamRebound.doubleValue());
		Temp2 = OTeamRebound.doubleValue()/(TeamRebound.doubleValue()+OTeamRebound.doubleValue());
		//Predicted rebounds a team will grab of the amount of rebounds they will likely get
		predictedTeamRebound = (double) predictedPossibleRebounds*Temp;
		predictedOTeamRebound = (double) predictedPossibleRebounds*Temp2;
		System.out.println("Amount of Rebounds to Team: " + predictedTeamRebound + " | Amount of Rebounds to OTeam: " + predictedOTeamRebound);
		System.out.println();
		//Rebounds predicted to be grabbed by teams
		Oratio = (OffensiveRebound.doubleValue()/TeamRebound.doubleValue())*predictedTeamRebound;
		Dratio = (DefensiveRebound.doubleValue()/TeamRebound.doubleValue())*predictedTeamRebound;
		
		//Player data time
		Temp = (Oratio*(PlayerOPercentRebound.doubleValue()/100)) + (Dratio*(PlayerDPercentRebound.doubleValue()/100));
		
		System.out.println();
		System.out.println("Player Predicted Rebound Total: " + Temp + " | Player Rebound Total Average: " + (PlayerORebound.doubleValue()+PlayerDRebound.doubleValue()) );
		System.out.println();
		
		/*System.out.println( "  " + (int) Math.round((Temp) - (Temp*0.75)) + "     " + (int) Math.round((Temp) - (Temp*0.5)) + "     " + (int) Math.round((Temp) - (Temp*0.25)) + "     " + (int) Math.round((Temp) - (Temp*0.1)) + "    " + (Temp) 
				+ "  " + (int) Math.round((Temp) + (Temp*0.1)) + "    " + (int) Math.round((Temp) + (Temp*0.25)) + "     " +  (int) Math.round((Temp) + (Temp*0.5)) + "     " + (int) Math.round((Temp) + (Temp*0.75)));
		System.out.println("-75%  -50%  -25%  -10%   Predicted Average  +10%  +25%   +50%   +75%");
		System.out.println(); */
		
		
		
		//Standard deviation of john = 3.83
		double StandardDeviation = 3.83;
		double[] deviation = new double[7];
		
		deviation[0] = Temp-(3*StandardDeviation);
		deviation[1] = Temp-(2*StandardDeviation);
		deviation[2] = Temp-StandardDeviation;
		deviation[3] = Temp;
		deviation[4] = Temp+StandardDeviation;
		deviation[5] = Temp+(2*StandardDeviation);
		deviation[6] = Temp+(3*StandardDeviation);
		
		/*for (int count = 0; count < deviation.length; count++)
		{
			System.out.println(deviation[count]);
		}*/
		
		System.out.println("99.8 percentile: " + deviation[0] + " < x < " + deviation[6]);
		System.out.println("95.4 percentile: " + deviation[1] + " < x < " + deviation[5]);
		System.out.println("68.2 percentile: " + deviation[2] + " < x < " + deviation[4]);
		System.out.println();
		
		boolean yesno = false;
		
		if (Temp > (PlayerORebound.doubleValue()+PlayerDRebound.doubleValue()))
		{
			yesno = true;
		}
		
		System.out.println("Outcome statement \n Will Player X do better than average: " + yesno + 
				"\n Player X has a 68.2% of rebounding between: " + deviation[4] + " - " + deviation[2] + 
				"\n Player X has a 34.1% of rebounding between: " + deviation[4] + " - " + deviation[3] +
				"\n Player X has a 34.1% of rebounding between: " + deviation[3] + " - " + deviation[2]);
		
		
	}
	
	public void AssistFormula()
	{
		
	}
	
	public void PERFormula()
	{
		
	}
	
	
}
