$(document).ready(function() {

		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$('#parEditForm :input').attr('disabled', true);
		$('#parEditForm select').attr('disabled', 'disabled');
		
		
		
		$('[name="parNo"]').change(function(){
			var isValid = $('#getParDetailsForm')[0].checkValidity();

			if (!isValid) 
			{
				event.preventDefault();
				event.stopPropagation();
			}
	
			$('#getParDetailsForm').addClass('was-validated');
			
			if(isValid)
			{
				var parNo = $('[name="parNo"]').val();
				
				var url = "./getPardetails/"+parNo;
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
								
							    $('input, select').attr('disabled', false);
							    $('#parEditForm :input').attr('disabled', false);
								
								
								$('[name="parRole"] ').val(dataout.parRole.roleId).prop('selected',true);
								
								
								$('[name="skillName"] ').val(dataout.skill.skillId).prop('selected',true);
								
								$('[name="locationName"] ').val(dataout.location.locationId).prop('selected',true);
								
								
								$('[name="parDescriptionText"]').val(dataout.parDescriptionText);
								$('[name="areaName"] ').val(dataout.area.areaId).prop('selected',true);
								
								$('[name="extStaffName"] ').val(dataout.externalStaff.extStaffId).prop('selected',true);
								
								
								console.log(dataout.parReceivedDate);	
								console.log((dataout.parReceivedDate).substring(0,2));
								console.log((dataout.parReceivedDate).substring(3,5));
								console.log((dataout.parReceivedDate).substring(6));
								
								
								parRecieveddate = (dataout.parReceivedDate).substring(6)+'-'+(dataout.parReceivedDate).substring(0,2)+'-'+(dataout.parReceivedDate).substring(3,5);
								$('[name="parDateReceived"]') .val(parRecieveddate);
								$('[name="activePar"]').val(dataout.parStatus).prop('selected',true);
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
		
		$('#parEntryUpdate-btn').click(function(){
			var isValid = $('#getParDetailsForm')[0].checkValidity();

			if (!isValid) 
			{
				event.preventDefault();
				event.stopPropagation();
			}
	
			$('#getParDetailsForm').addClass('was-validated');
			
			if(isValid)
			{ 
				var parNumber = $('[name="parNo"]').val();
				var parDescriptionText = $('[name="parDescriptionText"').val();
				var parDateReceieved = $('[name="parDateReceived"').val();
				var parStatus =$('[name="activePar"').val();
				var roleId = $('[name="parRole"]').val();
				var skillId = $('[name="skillName"]').val();
				var locationId =$('[name="locationName"]').val();
				var extStaffId =$('[name="extStaffName"]').val();
				var areaId=$('[name="areaName"]').val();
				
				var parId=0;
				
				var roleActive,areaActive,locationActive,skillActive=false;
				var intentToFill,intentSentDate,emailSent,parComment="";
				
				var data = '{"parId":"'+parId+'","parNumber":"'+parNumber+'","roleId":"'+roleId+'","skillId":"'+skillId+'","locationId":"'+locationId+'","parDescriptionText":"'+parDescriptionText+'","areaId":"'+areaId+'","extStaffId":"'+extStaffId+'","parReceivedDate":"'+parDateReceieved+'","intentToFill":"'+intentToFill+'","intentSentDate":"'+intentSentDate+'","emailSent":"'+emailSent+'","parComment":"'+parComment+'","parStatus":"'+parStatus+'"}';
				
				var url = "./updatePardetails";
				$.ajax({
					type:"POST",
					dataType:"text",
					contentType: "application/json",
					url:url,
					data:data,
					success:function(data){	
						console.log(data);
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
		
		$('#parEntryDelete-btn').click(function(){
			var parNo= $('[name="parNo"]').val();
			var url = "./deletePardetails/"+parNo;
			
					$.ajax({
						type:"POST",
						dataType:"text",
						contentType: "text",
						url:url,
						success:function(data){	
							console.log(data);
							$('#messageModalBody').html(data);
							$('#messageModal').modal('show'); 
						},
						error:function(req, status, error)
						{
							
							console.log(req.responseText);
							console.log(status,error);
						}	
					});	
				
				
		})
		
		$('#messageClose-btn').click(function(){
			location.reload(); 
		});
		
		
});
