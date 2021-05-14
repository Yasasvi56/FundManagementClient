package com;
import com.Fund; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Funds") 
public class FundService 
{ 
	Fund fundObj = new Fund(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds() 
	{ 
		return fundObj.readFunds(); 
	}
	
	@GET
	@Path("/{userid}") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFundDetailsByUserId(@PathParam("userid") String puserID) 
	{ 
		return fundObj.readFunds(); 
	}
	
	@POST@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFund(
			@FormParam("userID") String userID, 
			@FormParam("projectID") String projectID, 
			@FormParam("famount") String famount, 
			@FormParam("remark") String remark,
			@FormParam("createdby") String createdby,
			@FormParam("creationdate") String creationdate,
			@FormParam("createdby") String modifiedby,
			@FormParam("creationdate") String modifieddate) 
	{ 
		String output = fundObj.insertFund(userID, projectID, famount, remark); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFund(String fundData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject(); 
		//Read the values from the JSON object
		String fundID = fundObject.get("fundID").getAsString(); 
		String userID = fundObject.get("userID").getAsString(); 
		String projectID = fundObject.get("projectID").getAsString(); 
		String famount = fundObject.get("famount").getAsString(); 
		String remark = fundObject.get("remark").getAsString();
		String output = fundObj.updateFund(fundID, userID, projectID, famount, remark); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFund(String fundData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
		//Read the value from the element <fundID>
		String fundID = doc.select("fundID").text(); 
		String output = fundObj.deleteFund(fundID); 
		return output; 
	}
		
}
