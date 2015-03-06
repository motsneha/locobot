package locobotb;
import java.io.StringWriter;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



import java.sql.ResultSet;
import com.google.gson.stream.JsonWriter;


@Path("/city") 
public class CTDataResource {
	
	
	 
	 	
	@GET
	@Path("/{parameter}/{source}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getResult(@PathParam("source") String source,@PathParam("parameter") String parameter) {
		
		
		
		  	StringWriter sw = new StringWriter();
		  	JsonWriter writer = new JsonWriter(sw);
		    
		  try {
				  writer.beginObject();
				  writer.name("schedule"); 
				  writer.beginArray();  

				 CTData ct = new CTData();
				 ct.setSource(source);
				 System.out.println(source);
				 System.out.println(parameter);
				 ResultSet rst = ct.getAll(parameter);
				 System.out.println(rst);
		     if (rst!= null && rst.first()) {
		    	 							do { 
		    	 								writer.beginObject();
		    	 								writer.name("name").value(rst.getString("BUS_NAME"));
		    	 								if(parameter.equalsIgnoreCase("up"))
		    	 								writer.name("time").value(rst.getString("UP_TIME"));
		    	 								if(parameter.equalsIgnoreCase("down"))
		    	 								writer.name("time").value(rst.getString("DOWN_TIME"));
		    	 								writer.endObject();
		    	 								
		         
		    	 							} while (rst.next());
		    
		     								} 
		     else{
		    	 return "fail";
		     }  
		    writer.endArray();
		    writer.endObject();
		    writer.close();
		   
		  }  
		  catch (Exception e) {
		    e.printStackTrace();
		         }	   
		  return sw.toString();

		      }
	
	
		 }

