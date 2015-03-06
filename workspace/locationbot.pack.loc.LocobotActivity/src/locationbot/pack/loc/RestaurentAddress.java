package locationbot.pack.loc;

public class RestaurentAddress {
	private String restaurentName;
	private String restaurentAddress;

	public RestaurentAddress()
	{
		setRestaurentName("not found");
		setRestaurentAddress("not found");
	}
	public RestaurentAddress(String name,String add)
	{
		setRestaurentName(name);
		setRestaurentAddress(add);
	}
	public String getRestaurentName() {
		return restaurentName;
	}
	public void setRestaurentName(String restaurentName) {
		this.restaurentName = restaurentName;
	}
	public String getRestaurentAddress() {
		return restaurentAddress;
	}
	public void setRestaurentAddress(String restaurentAddress) {
		this.restaurentAddress = restaurentAddress;
	}
	
	
}
