
public class rescueVehicle {

	String nation = "";
	String nameOfRV = ""; 
	String airPortable = "";
	String dedicatedMOSHIP = "";
	String notes = "";
	double maxDepth = 0;
	double numRescuees = 0;
	double maxPressure = 0;

	//Constructor for a rescue Vehicle
	public rescueVehicle(String nation, String nameOfRV, String airPortable,
			String dedicatedMOSHIP, double maxDepth, double numRescuees,
			double maxPressure, String notes) {
		super();
		this.nation = nation;
		this.nameOfRV = nameOfRV;
		this.airPortable = airPortable;
		this.dedicatedMOSHIP = dedicatedMOSHIP;
		this.maxDepth = maxDepth;
		this.numRescuees = numRescuees;
		this.maxPressure = maxPressure;
		this.notes = notes;
	}


	//Prints data on RV
	public void Print() {
		//TODO Auto-generated method stub
		System.out.println("Nation: " + this.nation);
		System.out.println("Rescue Assett: " + this.nameOfRV);
		System.out.println("Air Portable: " + this.airPortable);
		System.out.println("Dedicated MOSHIP: " + this.dedicatedMOSHIP);
		System.out.println("Max Depth(ft): " + this.getMaxDepth());
		System.out.println("Sorie size: " + this.getNumRescuees());
		System.out.println("Max Pressure(ata): " + this.getMaxPressure());
		System.out.println("Notes: " + this.notes);
	}

	public String getNation() {
		return nation;
	}



	public void setNation(String nation) {
		this.nation = nation;
	}



	public String getNameOfRV() {
		return nameOfRV;
	}



	public void setNameOfRV(String nameOfRV) {
		this.nameOfRV = nameOfRV;
	}



	public String getAirPortable() {
		return airPortable;
	}



	public void setAirPortable(String airPortable) {
		this.airPortable = airPortable;
	}



	public String getDedicatedMOSHIP() {
		return dedicatedMOSHIP;
	}



	public void setDedicatedMOSHIP(String dedicatedMOSHIP) {
		this.dedicatedMOSHIP = dedicatedMOSHIP;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public double getMaxDepth() {
		return maxDepth;
	}



	public void setMaxDepth(double maxDepth) {
		this.maxDepth = maxDepth;
	}



	public double getNumRescuees() {
		return numRescuees;
	}



	public void setNumRescuees(double numRescuees) {
		this.numRescuees = numRescuees;
	}



	public double getMaxPressure() {
		return maxPressure;
	}



	public void setMaxPressure(double maxPressure) {
		this.maxPressure = maxPressure;
	}	
	
}
