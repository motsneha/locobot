package locationbot.pack.loc;

public class MedicalsAddress {
	private String medicals_name;
	  private String medicals_address;
	  
	  public MedicalsAddress(){
		  medicals_name="not found";
		  medicals_address="not found";
	  }
	  public MedicalsAddress(String name,String address){
		  medicals_name=name;
		  medicals_address=address;
	  }
		  
	public String getMedicals_name() {
		return medicals_name;
	}
	public void setMedicals_name(String medicals_name) {
		this.medicals_name = medicals_name;
	}
	public String getMedicals_address() {
		return medicals_address;
	}
	public void setMedicals_address(String medicals_address) {
		this.medicals_address = medicals_address;
	}
}
