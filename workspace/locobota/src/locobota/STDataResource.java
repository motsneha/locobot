package locobota;
import java.io.StringWriter;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



import java.sql.ResultSet;
import com.google.gson.stream.JsonWriter;


@Path("/st") 
public class STDataResource {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	 
	 	
	@GET
	@Path("/{source}/{destination}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getResult(@PathParam("source") String source,@PathParam("destination") String destination) {
		
		
		
		  	StringWriter sw = new StringWriter();
		  	JsonWriter writer = new JsonWriter(sw);
		    
		  try {
			  writer.beginObject();
			  writer.name("schedule"); 
			  writer.beginArray();  

			  STData dao1 = new STData();
			  dao1.setSource(source);
			  dao1.setDestination(destination); 
			  System.out.println(source);
			  System.out.println(destination);
			  ResultSet rst = dao1.getAll();
		   System.out.println(rst);
		     if (rst!= null && rst.first()) {
		    	 							do { 
		    	 								writer.beginObject();
		    	 								
		    	 								writer.name("time").value(rst.getString("DEPARTURE_TIME"));
		    	 								writer.endObject();;
		         
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

