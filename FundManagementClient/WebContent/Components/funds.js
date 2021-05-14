
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	//var status = validateFundForm();
	//if (status != true)
	//{
	//$("#alertError").text(status);
	//$("#alertError").show();
	//return;
	//}
	// If valid------------------------
	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "FundsAPI",
		type : type,
		data : $("#formFund").serialize(),
		dataType : "text",
		complete : function onFundSaveComplete(response, status)
		{
			if (status == "success")
			{
				var resultSet = JSON.parse(response.responseText);
				//var resultSet = JSON.parse(response);
				if (resultSet.status.trim() == "success")
				{
					$("#alertSuccess").text("Successfully saved.");
					$("#alertSuccess").show();
					$("#divFundsGrid").html(resultSet.data);
				}
				else if (resultSet.status.trim() == "error")
				{
					 $("#alertError").text(resultSet.data);
					 $("#alertError").show();
				}
			}
			else if (status == "error")
			{
				$("#alertError").text("Error while saving.");
				$("#alertError").show();
			}
			else
			{
				$("#alertError").text("Unknown error while saving..");
				$("#alertError").show();
			} 
			$("#hidFundIDSave").val("");
			$("#formFund")[0].reset();
		}
	}); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidFundIDSave").val($(this).data("fundid"));
	$("#userid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#projectid").val($(this).closest("tr").find('td:eq(1)').text());
	$("#fundamount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#remark").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
			 url : "FundsAPI",
			 type : "DELETE",
			 data : "fundID=" + $(this).data("fundid"),
			 dataType : "text",
			 complete : function onFundDeleteComplete(response, status)
		 {
			 
			 {
			 if (status == "success")
			 	{
				 	var resultSet = JSON.parse(response.responseText);
				 	if (resultSet.status.trim() == "success")
				 	{
				 		$("#alertSuccess").text("Successfully deleted.");
				 		$("#alertSuccess").show();
				 		//
				 		setTimeout(function() {
				 			$("#alertSuccess").hide();
				 	    }, 5000);
				 		//
				 		$("#divFundsGrid").html(resultSet.data);
				 	} 
				 	else if (resultSet.status.trim() == "error")
				 	{
				 		$("#alertError").text(resultSet.data);
				 		$("#alertError").show();
				 	}
			 	} 
			  else if (status == "error")
			  {
				  $("#alertError").text("Error while deleting.");
				  $("#alertError").show();
			  } 
			  else
			  {
				  $("#alertError").text("Unknown error while deleting..");
				  $("#alertError").show();
			  }
			 }
		 }
		 });
		});

// CLIENT-MODEL================================================================
function validateFundForm()
{
// userID
	if ($("#userID").val() == "")
	{
		return "Insert User ID.";
	}
// projectID
	if ($("#projectID").val() == "")
	  {
		return "Insert Project ID.";
	  } 

// famount-------------------------------
	if ($("#famount").val() == "")
	  {
		return "Insert famount.";
	  }
// is numerical value
	var tmpAmount = $("#famount").val();
	if (!$.isNumeric(tmpAmount))
	  {
		return "Insert a numerical value for Fund Amount.";
	  }
// convert to decimal price
	$("#famount").val(parseFloat(tmpAmount).toFixed(2));
// remark---------------------------------
	if ($("#remark").val().trim() == "")
      {
		return "Insert remark.";
      }
return true;
}

