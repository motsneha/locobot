package locobot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CTData {
String up_time,down_time,bus_name,source;

public String getUp_time() {
	return up_time;
}

public void setUp_time(String up_time) {
	this.up_time = up_time;
}

public String getDown_time() {
	return down_time;
}

public void setDown_time(String down_time) {
	this.down_time = down_time;
}

public String getBus_name() {
	return bus_name;
}

public void setBus_name(String bus_name) {
	this.bus_name = bus_name;
}

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public CTData() {
	
}

public ResultSet getAll(String param) {
    
	Connection con;
    PreparedStatement pst;
    ResultSet rs = null;
 String temp1= this.getSource();
 String temp2=param;
 
 
 System.out.print(temp1);
 System.out.print(temp2);
    	try
    	{
    	
    	Class.forName("com.mysql.jdbc.Driver");
    	con=DriverManager.getConnection("jdbc:mysql://mysql-env-5835744.jelastic.servint.net/msrctc?user=root&password=WZYDg5lmJ6");
    	if(temp2.equalsIgnoreCase("up"))
    	{
	    	pst =con.prepareStatement("SELECT up_time,bus_name FROM msrctc.amravati_ct_schedule  where source=?  ; ");
	    	pst.setString(1, temp1);
	    	rs =pst.executeQuery();
    	}
    	if(temp2.equalsIgnoreCase("down"))
    	{
    		pst =con.prepareStatement("SELECT down_time,bus_name FROM msrctc.amravati_ct_schedule  where source=?  ; ");
    		pst.setString(1, temp1);
        	rs =pst.executeQuery();
    	}
    	
    	
    	
    	} 
    	catch (Exception e){
    		e.printStackTrace() ;
    	} 
    return rs;
}

}
