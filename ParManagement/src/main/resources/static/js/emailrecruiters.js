$(document).ready(function() {

		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		
		
		
		
		$('[name="parNo"]').change(function(){
			var isValid = $('#ParDetailsForm')[0].checkValidity();

			if (!isValid) 
			{
				event.preventDefault();
				event.stopPropagation();
			}
	
			$('#ParDetailsForm').addClass('was-validated');
			
			if(isValid)
			{
				var parNo = $('[name="parNo"]').val();
				
				var url = "./emailrecruiters/getPardetails/"+parNo;
				var parRecieveddate="";
	
				var dataout="";
				$.ajax({
					type:"GET",
					dataType:"text",
					contentType: "application/json",
					url:url,
					data: "",
					success:function(data){	
												
						
						
						if(data.length > 0)
							{
								dataout=$.parseJSON(data);
								console.log(dataout.parRole.roleName);
								
							    
								
								
								$('[name="parRole"] ').val(dataout.parRole.roleName);
								
								
								$('[name="skillName"] ').val(dataout.skill.skillName);
								
								$('[name="locationName"] ').val(dataout.location.locationName);
								
								
								$('[name="parDescriptionText"]').val(dataout.parDescriptionText);
								$('[name="areaName"] ').val(dataout.area.areaName);
								
								$('[name="extStaffName"] ').val(dataout.externalStaff.extStaffName);
								
								
								console.log(dataout.parReceivedDate);	
								console.log((dataout.parReceivedDate).substring(0,2));
								console.log((dataout.parReceivedDate).substring(3,5));
								console.log((dataout.parReceivedDate).substring(6));
								
								
								parRecieveddate = (dataout.parReceivedDate).substring(6)+'-'+(dataout.parReceivedDate).substring(0,2)+'-'+(dataout.parReceivedDate).substring(3,5);
								$('[name="parDateReceived"]') .val(parRecieveddate);
								$('[name="activePar"]').val(dataout.parStatus);
							}
						else
							{
							$('#messageModalBody').html('Data not found for Par no:'+parNo);
							$('#messageModal').modal('show');
							}
						
						
				
					},
					error:function(req, status, error)
					{
						
						console.log(req.responseText);
						console.log(status,error);
					}	
				});
			}

		})
		
		
		$('#sendEmail-btn').click(function(){
			var isValid = $('#ParDetailsForm')[0].checkValidity();

			if (!isValid) 
			{
				event.preventDefault();
				event.stopPropagation();
			}
	
			$('#ParDetailsForm').addClass('was-validated');
			
			if(isValid)
			{
				var parNumber = $('[name="parNo"]').val();
				var parComment= $('[name="parComment"]').val();
				var parDescriptionText = $('[name="parDescriptionText"').val();
				var parDateReceieved = $('[name="parDateReceived"').val();
				var parStatus =$('[name="activePar"').val();
				var roleName = $('[name="parRole"]').val();
				var skillName = $('[name="skillName"]').val();
				var locationName =$('[name="locationName"]').val();
				var extStaffName =$('[name="extStaffName"]').val();
				var areaName=$('[name="areaName"]').val();
				
				var parId=0;
				
				var roleActive,areaActive,locationActive,skillActive=false;
				var intentToFill,intentSentDate,emailSent="";
				
				
				var data = '{"parId":"'+parId+'","parNumber":"'+parNumber+'","roleName":"'+roleName+'","skillName":"'+skillName+'","locationName":"'+locationName+'","parDescriptionText":"'+parDescriptionText+'","areaName":"'+areaName+'","extStaffName":"'+extStaffName+'","parReceivedDate":"'+parDateReceieved+'","intentToFill":"'+intentToFill+'","intentSentDate":"'+intentSentDate+'","emailSent":"'+emailSent+'","parComment":"'+parComment+'","parStatus":"'+parStatus+'"}';
				
				var url = "./sendEmail";
				$.ajax({
					type:"POST",
					dataType:"text",
					contentType: "application/json",
					url:url,
					data:data,
					success:function(data){	
					
						$('#messageModalBody').html(data);
						$('#messageModal').modal('show'); 
					},
					error:function(req, status, error)
					{						
						console.log(req.responseText);
						console.log(status,error);
					}	
				});	
			}
		})
		
		$('#messageClose-btn').click(function(){
			location.reload(); 
		});

		
});