package locationbot.pack.loc;

public class PetrolStationAddress {

	private String petrolStationName;
	private String petrolStationAddress;

	public PetrolStationAddress()
	{
		setPetrolStationName("not found");
		setPetrolStationAddress("not found");
	}
	public PetrolStationAddress(String name,String add)
	{
		petrolStationName=name;
		petrolStationAddress=add;
	}

	public String getPetrolStationName() {
		return petrolStationName;
	}

	public void setPetrolStationName(String petrolStationName) {
		this.petrolStationName = petrolStationName;
	}

	public String getPetrolStationAddress() {
		return petrolStationAddress;
	}

	public void setPetrolStationAddress(String petrolStationAddress) {
		this.petrolStationAddress = petrolStationAddress;
	}



}
