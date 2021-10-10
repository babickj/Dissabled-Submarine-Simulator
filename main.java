import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class main
{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		boolean stochastic_on = false;
		int num_stochastic_runs = 10000;
		long numOfRV;
		
		//Create a Scanner for keyboard input
		Scanner scanner = new Scanner(System.in);
		
		//Create new DISSUB
		DISSUB newDISSUB = new DISSUB();
		newDISSUB.Print();
		//Create a new Mobilization event
		Mobilization_Event new_Mobilization = new Mobilization_Event();
		
		//Create a new rescue event / alert
		Rescue_Event new_Rescue = new Rescue_Event();

		//Opens CSV file with RV data
		String fileToParse = "RescueSystems.csv";
		BufferedReader fileReader = null;
		fileReader = new BufferedReader(new FileReader(fileToParse));
        numOfRV = fileReader.lines().count();
        
        //closes file after number of RVs is recorded
        try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create rescueVehicles
		rescueVehicle[] rvArray = new rescueVehicle[(int)numOfRV-1];
		
	
		//Loads RVs in rvArray
		ReadCSV("RescueSystems.csv", rvArray);
		
		//Ask Use what RV he wants to use
		System.out.println("Select the RV you want to use:");
		for (int i = 0; i < numOfRV-1; ++i)
		{
			System.out.println((i+1)+" "+rvArray[i].getNameOfRV());
		}
		System.out.print("\nEnter number or RV: ");
		
		int selection = 0;
		selection = scanner.nextInt();
		
		PrintWriter mobWriter = new PrintWriter("mobilization_Stochastic_Data.csv", "UTF-8");
		mobWriter.println("Alert,Recall,Aircraft_Arrive,Load_Aircraft,Air_Transport,VOO_arrive,Mobilized,Transit,Moor/Mate,Total");
		if(stochastic_on)
		{
			for(int i = 0; i <= num_stochastic_runs;i++)
			{
				new_Mobilization.Mobilization_Event(mobWriter, stochastic_on);
			}
		}
		mobWriter.close();
		
		PrintWriter writer = new PrintWriter("output.csv", "UTF-8");
		writer.println("Sortie#,Survivors,chamber1,chamber2,TTLR,delayTime");

		//This block of code is used for stochastic modeling
		if(stochastic_on)
		{
			num_stochastic_runs = 10000;
		
		
			for (int i = 0; i <= num_stochastic_runs; i++)
			{
				//Commences the rescue event
				new_Rescue.Rescue_Event(writer, stochastic_on, rvArray[selection -1], newDISSUB);
			}
		}//end if
		else
		{
			new_Rescue.Rescue_Event(writer, stochastic_on, rvArray[selection -1], newDISSUB);
		}//end else
		
		
		writer.close();
	
		System.out.println("Simulation Complete, output in 'output.csv'");
	}//end main method



	//This method reads a CSV file
	public static void ReadCSV(String fileName, rescueVehicle [] rvArray) 
	{ 
		long numOfRV = 0;
		
        //Input file which needs to be parsed
        String fileToParse = fileName;
        BufferedReader fileReader = null;
  
        
        //Delimiter used in CSV file
        final String DELIMITER = ",";
        try
        {
            String line = "";
            //Create the file reader
         
            //fileReader = new BufferedReader(new FileReader(fileToParse));
            //numOfRV = fileReader.lines().count();
            //fileReader.close();          
            //get rid of header line
            fileReader = new BufferedReader(new FileReader(fileToParse));
            fileReader.readLine();
          
          
            //Prepare Array for Rvs
            //rescueVehicle[] rvArray = new rescueVehicle[(int) numOfRV];
            int count = 0;
            
            //System.out.println("TEST");
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) 
            {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
               
                //(String nation, String nameOfRV, boolean airPortable,
    			//boolean dedicatedMOSHIP, double maxDepth, int numRescuees,
    			//double maxPressure) 
                rvArray[count] = new rescueVehicle(tokens[0],tokens[1],tokens[2],tokens[3],Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]),Double.parseDouble(tokens[6]),tokens[7]);
                
                //rvArray[count].Print();
                //System.out.println();
                count++;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }	     
      
	}//End Read CSV method
	
}//end main class
