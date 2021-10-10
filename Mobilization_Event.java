import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 
 */

/**
 * @author johnbabick
 *
 */
public class Mobilization_Event {

	/**
	 * 
	 */
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	public Mobilization_Event() {
		// TODO Auto-generated constructor stub
	}

	public void Mobilization_Event(PrintWriter mobWriter, boolean stochastic_on) {
		// TODO Auto-generated method stub
		double alertTime = 0.5;
		double varAlert = 0.5;
		double recallTime = 3;
		double varRecall = 2;
		double Aircraft_Arrive_time = 24;
		double varAirArrive = 4;
		double LoadTime = 2;
		double varLoad = 1;
		double TransportTime = 6;
		double varTransport = 1;
		double minFlight = TransportTime - (2 * varTransport);
		double VOO_arrive_time = 24;
		double var_VOO_Arrive = 6;
		double Mobilize_time = 36;
		double varMobilize = 6;
		double minMobilize = 30;
		double Transit_time = 9;
		double varTransit = 1;
		double minTransit = Transit_time - 2;
		double mate = 4;
		double varMate = 2;
		
		double Time_to_on_scene = 0;
		double tempVar = 0;
		
		
		//adds alert time
		tempVar = ReturnNormal(alertTime, varAlert);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds recall time
		tempVar = ReturnNormal(recallTime, varRecall);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds aircraft arrival time
		tempVar = ReturnNormal(Aircraft_Arrive_time, varAirArrive);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds load time
		tempVar = ReturnNormal(LoadTime, varLoad);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds transport time
		tempVar = ReturnNormal(TransportTime, varTransport);
		tempVar = Math.max(tempVar, minFlight);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds VOO arrive time
		tempVar = ReturnNormal(VOO_arrive_time, var_VOO_Arrive);
		mobWriter.print(df2.format(tempVar)+",");
		
		//Delays rescue for VOO arrival
		tempVar = Math.max(tempVar - Time_to_on_scene, 0);
		Time_to_on_scene += tempVar;
		
		//adds Mobilize time
		tempVar = ReturnNormal(Mobilize_time, varMobilize);
		//Ensures time to mobilize is > min mobilization
		tempVar = Math.max(tempVar, minMobilize);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds Transit time
		tempVar = ReturnNormal(Transit_time, varTransit);
		tempVar = Math.max(tempVar, minTransit);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//adds mate time
		tempVar = ReturnNormal(mate, varMate);
		Time_to_on_scene += tempVar;
		mobWriter.print(df2.format(tempVar)+",");
		
		//records total time
		mobWriter.println(df2.format(Time_to_on_scene));
		
		
	}
	
	//Returns a NormalDist number between min and max
		public static double ReturnNormal(double mean, double var)
		{
			Random gaussian = new Random();
			return Math.abs(mean + gaussian.nextGaussian()*var);
			//return (min + Math.random() * max);
		}

}
