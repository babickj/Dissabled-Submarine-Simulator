import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Rescue_Event
{
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	//the purpose of this method is to calculate TTFR
	//with a given set of parameters
	public static void Rescue_Event(PrintWriter writer, boolean stochastic_on, rescueVehicle selRV, DISSUB newDISSUB)
	{

		double time = 0;

		//TIME TO COMPLETE VARIABLES:

		double time_to_send_TUP = 1;
		double var_to_send_TUP = 0.5;
		double time_to_decompress = newDISSUB.decompressionTime;
		double var_to_decompress = (newDISSUB.decompressionTime * 0.20);
		double sortie_time = 6;
		double var_sortie_time = 2;
		int sortie_number = 0;
		int number_of_survivors = newDISSUB.numSurvivors;
		int numHold = 0;//number_of_survivors;
		int number_of_people_sortie = (int) selRV.getNumRescuees();
		int var_people_sortie = 1;
		int temp_sortie = 0;
		int PRM_max = (int)selRV.getNumRescuees();

		//CONTROL VARIABLES
		//create a new chamber
		TUP_Chamber chamber_one = new TUP_Chamber();
		TUP_Chamber chamber_two = new TUP_Chamber();
		boolean isDelayed = false;
		double delay_time = 0;

		//SIMULATION VARIABLES
		double total_delay = 0;
		double number_of_IA = 0;

		//Create a scanner to take system inputs
		Scanner in = new Scanner(System.in);
	
		
		//This code displays the assumptions for the user
		if(!stochastic_on)
		{
			System.out.println("Assumptions:");
			System.out.print("Mean Sortie Time: "+sortie_time+" +/- "+var_sortie_time);
			System.out.print("\nTime to transfer into TUP: "+time_to_send_TUP+" +/- "+var_to_send_TUP);
			System.out.print("\nNumber of survivors: "+number_of_survivors+" +/- ");
			System.out.print("\nNumber of rescuees per sortie: "+number_of_people_sortie+" +/- "+var_people_sortie);
			System.out.print("\nTime to decompress: "+time_to_decompress+" +/- "+var_to_decompress);
	
			System.out.println("\n\n Sortie Number, Event, time, number of survivors, ch-1, ch-2");
		}
		
		//THIS OUTTER LOOP IS USED FOR SORTIES
		//This outter loop runs the rescue until all survivors
		//are onboard the VOO
	
		while(number_of_survivors > 0)
		{
			sortie_number++;


			//check to see if chamber one can be freed
			if(chamber_one.is_empty == false && time >= chamber_one.time_to_decompress)
			{
				Rescue_Print(sortie_number, "Ch-1 emptied", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
				chamber_one.num_of_people = 0;
				chamber_one.is_empty = true;
				chamber_one.time_to_decompress = 0;
				chamber_one.time_comex_decompress = 0;
			}//end if releasing chamber one

			//check to see if chamber two can be freed
			if(chamber_two.is_empty == false && time >= chamber_two.time_to_decompress)
			{
				Rescue_Print(sortie_number, "Ch-2 emptied", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
				chamber_two.num_of_people = 0;
				chamber_two.is_empty = true;
				chamber_two.time_to_decompress = 0;
				chamber_two.time_comex_decompress = 0;
			}//end if releasing chamber one
			
			//Determine if delay required for chamber free
			if(chamber_one.is_empty == false && chamber_two.is_empty == false)
			{
				if(chamber_one.time_to_decompress <= chamber_two.time_to_decompress)
				{
					isDelayed = true;
					delay_time = (chamber_one.time_to_decompress - time);
					total_delay += delay_time;
					time = chamber_one.time_to_decompress;
					Rescue_Print(sortie_number, "Delay for Ch-1", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
				}
				else
				{
					isDelayed = true;
					delay_time = (chamber_two.time_to_decompress - time);
					total_delay += delay_time;
					time = chamber_two.time_to_decompress;	
					Rescue_Print(sortie_number, "Delay for Ch-2", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people,stochastic_on);
				}
			}//end if delaying rescue to free a chamber


			//If rescue was delayed due to full chamber
			//this if statement resets delay times
			//delay time incorporated in chamber free time
			if(isDelayed)
			{
				isDelayed = false;
				delay_time = 0;
				//sortie_number--;  
			}

			time += sortie_time;
			Rescue_Print(sortie_number, "DTL Shut to DTL open", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);

			//people into chamber
			Rescue_Print(sortie_number, "People transfered to TUP", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
			time += ReturnNormal(time_to_send_TUP, var_to_send_TUP);

			//if loop checks to see if chamber one is empty
			//if chamber one is empty, chamber one is loaded
			if(chamber_one.is_empty == true)
			{
				temp_sortie = (int)Math.min(ReturnNormal(number_of_people_sortie, var_people_sortie), PRM_max);
				chamber_one.num_of_people += temp_sortie;
				number_of_survivors -=  temp_sortie;
				Rescue_Print(sortie_number, "People to Ch-1", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
			}
			//If chamber one if full, use chamber two
			else if(chamber_two.is_empty == true)
			{
				temp_sortie = (int)Math.min(ReturnNormal(number_of_people_sortie, var_people_sortie), PRM_max);
				chamber_two.num_of_people += temp_sortie;
				number_of_survivors -=  temp_sortie;
				Rescue_Print(sortie_number, "People to Ch-2", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
			}

			//Checks to see if a chamber is now full
			if((chamber_one.num_of_people+number_of_people_sortie) >= chamber_one.max_capacity && chamber_one.is_empty == true)
			{
				Rescue_Print(sortie_number, "Ch-1 filled", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
				chamber_one.is_empty = false;
				chamber_one.time_comex_decompress = time;
				chamber_one.time_to_decompress = time + time_to_decompress;
				chamber_one.was_used++;
			}//end if


			//Checks to see if a chamber is now full
			if((chamber_two.num_of_people +number_of_people_sortie)>= chamber_two.max_capacity && chamber_two.is_empty == true)
			{
				Rescue_Print(sortie_number, "Ch-2 filled", time, number_of_survivors, chamber_one.num_of_people, chamber_two.num_of_people, stochastic_on);
				chamber_two.is_empty = false;
				chamber_two.time_comex_decompress = time;
				chamber_two.time_to_decompress = time + time_to_decompress;
				chamber_two.was_used++;
			}//end if
		}//end rescue for loop

				/*File yourFile = new File("output.csv");
yourFile.createNewFile(); // if file already exists will do nothing 
FileOutputStream oFile = new FileOutputStream(yourFile, false); 
((PrintStream) oFile).println(fileContent);
oFile.close();*/
		//PrintWriter writer = new PrintWriter("output.csv", "UTF-8");
		//writer.println("Sortie#,Survivors,chamber1,chamber2,TTLR,delayTime");
		writer.println(sortie_number+","+numHold+","+chamber_one.was_used +","+chamber_two.was_used +","+df2.format(time)+","+df2.format(total_delay));
		//writer.close();	                        
	}//End rescue event method

	//Special method to format output
	public static void Rescue_Print(int n, String s, double time, int number_of_survivors, int c1, int c2, boolean stochastic_on)
	{   	

		if(!stochastic_on)
		{
			System.out.println(n+", "+s+", "+df2.format(time)+", "+number_of_survivors+", "+c1+", "+c2);
		}
	}//end print method

	//Returns a NormalDist number between min and max
	public static double ReturnNormal(double mean, double var)
	{
		Random gaussian = new Random();
		return Math.abs(mean + gaussian.nextGaussian()*var);
		//return (min + Math.random() * max);
	}
}//end rescue event
