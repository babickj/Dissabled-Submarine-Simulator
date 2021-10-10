import java.util.Scanner;


public class DISSUB {

	//DISSUB data
	String nation;
	String type;
	String additionalInformation;
	double depth;
	double internalPressure;
	double decompressionTime;
	int numSurvivors;
	int numStretcherCases;
	
	Scanner scanner = new Scanner(System.in);
	
	
	public DISSUB() 
	{
		super();
		
		//Each block of code does the following:
		//1. Prompts user for input
		//2. Checks input in proper type
		//3. Records input to new DISSUB object
		System.out.print("\nNation: ");
		this.nation = scanner.nextLine();
		System.out.print("\nType: ");
		this.type = scanner.nextLine();
		
		System.out.print("\nDepth(m): ");
		//Ensures depth is a valid number
		while(!scanner.hasNextDouble())
		{	
			System.out.println("\nPlease enter a depth(m): ");
			scanner.next();
		}
		this.depth = scanner.nextDouble();
		
		System.out.print("\nInternal Pressure(ata): ");
		//Ensures internal pressure is a valid number
		while(!scanner.hasNextDouble())
		{	
			System.out.println("\nPlease enter a pressure (ata): ");
			scanner.next();
		}
		this.internalPressure = scanner.nextDouble();
		
		System.out.print("\nDecomplession time(hr): ");
		//Ensures internal pressure is a valid number
		while(!scanner.hasNextDouble())
		{	
		System.out.println("\nPlease enter a decompression time(hr): ");
		scanner.next();
		}
		this.decompressionTime = scanner.nextDouble();
		
		System.out.print("\nNumber of Survivors: ");
		//Ensures a valid number
		while(!scanner.hasNextInt())
		{	
			System.out.println("\nPlease enter a number of survivors: ");
			scanner.next();
		}
		this.numSurvivors = scanner.nextInt();
		
		System.out.print("\nNumber of Stretchers: ");
		//Ensures a valid number
		while(!scanner.hasNextInt())
		{	
			System.out.println("\nPlease enter number of stretchers: ");
			scanner.next();
		}
		this.numStretcherCases = scanner.nextInt();
		
		System.out.print("\nEnter additional information: ");
		this.additionalInformation = scanner.next();
		System.out.println();
		
	}
	
	//This method prints DISSUB charactertics:
	public void Print()
	{
		System.out.print("\nNation: "+this.nation);
		System.out.print("\nType: "+ this.type);
		System.out.print("\nDepth: " + this.depth);
		System.out.print("\nInternal Pressure: " + this.internalPressure);
		System.out.print("\nDecomplession time: " + this.decompressionTime);
		System.out.print("\nNumber of Survivors: " + this.numSurvivors);
		System.out.print("\nNumber of Stretchers: " + this.numStretcherCases);
		System.out.print("\nEnter additional information: " + this.additionalInformation);
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getAdditionalInformation() {
		return additionalInformation;
	}


	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}


	public double getDepth() {
		return depth;
	}


	public void setDepth(double depth) {
		this.depth = depth;
	}


	public double getInternalPressure() {
		return internalPressure;
	}


	public void setInternalPressure(double internalPressure) {
		this.internalPressure = internalPressure;
	}


	public double getDecompressionTime() {
		return decompressionTime;
	}


	public void setDecompressionTime(double decompressionTime) {
		this.decompressionTime = decompressionTime;
	}


	public int getNumSurvivors() {
		return numSurvivors;
	}


	public void setNumSurvivors(int numSurvivors) {
		this.numSurvivors = numSurvivors;
	}


	public int getNumStretcherCases() {
		return numStretcherCases;
	}


	public void setNumStretcherCases(int numStretcherCases) {
		this.numStretcherCases = numStretcherCases;
	}

}
