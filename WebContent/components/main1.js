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
	var status = validateDoctorForm(); 
	
	
	if (status != true) 
	{ 
		$("#alertError").text(status); 
		$("#alertError").show(); 
    return; 
	} 
	

	var type = ($("#hiddIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "DoctorAPI",  
		type : type,  
		data : $("#formItem").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onDoctorSaveComplete(response.responseText, status);  
			
		} 
	
}); 

});

function onDoctorSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

		} else if (status == "error")  
		{   
			$("#alertError").text("Error while saving.");   
			$("#alertError").show();  
		} else  
		{   
			$("#alertError").text("Unknown error while saving..");   
			$("#alertError").show();  
		} 

		$("#hiddIDSave").val("");  
		$("#formItem")[0].reset(); 
		
}

$(document).on("click", ".btnRemove", function(event) 
		{  
			$.ajax(  
			{   
				url : "DoctorAPI",   
				type : "DELETE",   
				data : "dID=" + $(this).data("did"),   
				dataType : "text",   
				complete : function(response, status)   
				{    
					onDoctorDeleteComplete(response.responseText, status);   
				}  
			}); 
		});

function onDoctorDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 

			$("#divItemsGrid").html(resultSet.data);   
			} else if (resultSet.status.trim() == "error")   
			{    
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
			} 

			} else if (status == "error")  
			{   
				$("#alertError").text("Error while deleting.");   
				$("#alertError").show();  
			} else  
			{   
				$("#alertError").text("Unknown error while deleting..");   
				$("#alertError").show();  
			} 
	} 



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{     
	    $("#hiddIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	    $("#dName").val($(this).closest("tr").find('td:eq(0)').text());     
	    $("#address").val($(this).closest("tr").find('td:eq(1)').text());     
	    $("#email").val($(this).closest("tr").find('td:eq(2)').text());     
	    $("#phoneNo").val($(this).closest("tr").find('td:eq(3)').text()); 
	    $("#specialization").val($(this).closest("tr").find('td:eq(4)').text());
});

function validateDoctorForm() 
{ 
// Name
     if ($("#dName").val().trim() == "")
    { 
           return "Insert Doctor Name."; 
    } 
// Address
      if ($("#address").val().trim() == "")
     {  
           return "Insert Address."; 
     }     
// Email
      if ($("#email").val().trim() == "")
     {  
           return "Insert email."; 
     } 
     
    
      
// Phone no-------------------------------
       if ($("#phoneNo").val().trim() == "")
     { 
           return "Insert Phone Number."; 
      } 
// is numerical value

      var tmpPhone = $("#phoneNo").val().trim();

        if (!$.isNumeric(tmpPhone))
       {
             return "Insert a numerical value for Phone Number.";
       } 
       
        

// specialization------------------------
            if ($("#specialization").val().trim() == "") 
        { 
           return "Insert specialization.";
        } 
           return true; 
}


