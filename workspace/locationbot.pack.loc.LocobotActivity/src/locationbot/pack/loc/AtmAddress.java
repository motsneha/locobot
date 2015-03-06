package locationbot.pack.loc;

public class AtmAddress {
	private String atmName;
	private String atmAddress;

	public AtmAddress()
	{
		atmName="not found";
		atmAddress="not found";
	}
	public AtmAddress(String name,String add)
	{
		atmName=name;
		atmAddress=add;
	}
	public String getAtmName() {
		return atmName;
	}

	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}

	public String getAtmAddress() {
		return atmAddress;
	}

	public void setAtmAddress(String atmAddress) {
		this.atmAddress = atmAddress;
	}
}
