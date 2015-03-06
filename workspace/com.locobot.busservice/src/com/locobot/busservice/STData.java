package com.locobot.busservice;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class STData {
String source,destination,time;
int id;
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public STData() {
}
public ResultSet getAll() {
    
	Connection con;
    PreparedStatement pst;
    ResultSet rs = null;
 String temp1= this.getSource();
 String temp2=this.getDestination();
 
 
 System.out.print(temp1);
 System.out.print(temp2);
    	try
    	{
    	
    	Class.forName("com.mysql.jdbc.Driver");
    	con=DriverManager.getConnection("jdbc:mysql://localhost/msrctc?user=root&password=root");
    	pst =con.prepareStatement("SELECT departure_time FROM amravti_st_schedule  where source=? and destination=? ; ");
    	
    	pst.setString(1, temp1);
    	pst.setString(2, temp2);
    	
    	rs =pst.executeQuery();
    } 
    catch (Exception e){
      e.printStackTrace() ;
    } 
    return rs;
}

}