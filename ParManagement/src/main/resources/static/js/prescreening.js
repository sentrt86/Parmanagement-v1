$(document).ready(function() {
	
	/*  To display the area datatables in the area jsp page  */

	
	
	var dataout;
	var tmp="";
	var datatableData;
	var datatableobj=[];
	var prescreenerList;
	var candidateList;
	var parAllocationList;
	var parmaster;
	var parAllocId=0;
	
	const actionbutton='<button type="button" class="btn btnprescreeningTableEdit btn-link" id="precreeningTableEdit-btn">Edit</button><button type="button" class="btn precreeningTableDelete btn-link" id="precreeningTableDelete-btn">Delete</button>';
	
	
	$('#prescreeningTable').DataTable( {
		"scrollY":        "300px",
		"scrollCollapse": true,
	});
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	// get the parAllocation details for given parId
	
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
			
			var url = "./prescreening/"+parNo;
			var parRecieveddate="";
			var Requestmethod="GET";
			var dataType="text";
			var contentType="application/json";
			var data="";
			AJAXcall(Requestmethod,dataType,contentType,url,data);
		}
	});
	
	
	
	// To display the prescreening candidate row

	$("#prescreeningTable tbody").on('click', '.btnprescreeningTableEdit', function () {
		
		var table = $("#prescreeningTable").DataTable();
		
	    var prescreeningcurrentRow = table.row($(this).closest('tr')).data();	
		$('#prescreeningModalProcess').val("prescreeningTableEdit");
		
		$('[name="candidateNameModal"]').val(prescreeningcurrentRow.candidateName);
		$('[name="candidateNameModal"]').prop("readonly", true);
		
			
		$.each(prescreenerList, function (key, value) {
		    $('[name="preScreenersNameModal"]').append('<option value="'+ value.preScreenerId+'"> '+value.preScreenerName+'</option>');
		    if(prescreeningcurrentRow.prescreenerName!=null)
			{
		    	if(prescreeningcurrentRow.prescreenerName==value.preScreenerName){
		    	$('[name="preScreenersNameModal"]').removeAttr('selected');
				$('[name="preScreenersNameModal"]').val(value.preScreenerId).prop("selected",true);
		    	}
			}
		});
		
		
		//$('[name="preScreenersNameModal"]').val(prescreeningcurrentRow.prescreenerName);
		$('[name="preScreeningDateModal"]').val(prescreeningcurrentRow.prescreeningDate);
		$('[name="preScreeningCommentModal"]').val(prescreeningcurrentRow.prescreeningComment);
		$('#prescreeningEditModal').modal('show');		

	});
	
	$("#prescreenerModalEdit-btn").click(function(){
		
		var url="";
		var parId=0;
		
		var recruiterId=0;
		var prescrId=0;
		var candidateId=0;
		var submitInd=" ";
		var submitDate=" ";
		var offerRecieved ="";
		var exptdStartDt="";
		var actualStartDt="";
		var isValid = $('#prescreeningForm')[0].checkValidity();

		if (!isValid) 
		{
			event.preventDefault();
			event.stopPropagation();
		}

		$('#prescreeningForm').addClass('was-validated');

		if (isValid)
		{
			var candidateName = $('[name="candidateNameModal"]').val();
			
			$.each(candidateList,function(key,value){
				if(candidateName==value.candidateName)
					{
					candidateId = value.candidateId;
					}
			});
			
			$.each(parAllocationList,function(key,value){
				if(candidateId==value.candidateId)
					{
					 parId=value.parId;
					 parAllocId=value.parAllocId;
					 recruiterId=value.recruiterId;
					}
				
			});
			
			var prescrId = $('[name="preScreenersNameModal"] :selected').val();
			var preScrdate  = $('[name="preScreeningDateModal"]').val();
			var preScrCommnt = $('[name="preScreeningCommentModal"]').val();
			url = "./updatePrescreening";
			var data = '{"parAllocId":"'+parAllocId+'","parId":"'+parId+'","recruiterId":"'+recruiterId+'","prescrId":"'+prescrId+'","candidateId":"'+candidateId+'","preScrdate":"'+preScrdate+'","preScrCommnt":"'+preScrCommnt+'","submitInd":"'+submitInd+'","submitDate":"'+submitDate+'","offerRecieved":"'+offerRecieved+'","exptdStartDt":"'+exptdStartDt+'","actualStartDt":"'+actualStartDt+'"}';
			var Requestmethod="POST";
			var contentType="application/json";
			var dataType="text";
			AJAXcall(Requestmethod,dataType,contentType,url,data);
		}
		
	})
	
	function AJAXcall(Requestmethod,dataType,contentType,url,data)
	{
		$.ajax({
			type:Requestmethod,
			dataType:dataType,
			contentType:contentType,
			url:url,
			data:data,
			success:function(data){	
			console.log(data);
			if(data.length > 0)
			{
				dataout=$.parseJSON(data);
				console.log(dataout.parmaster.parRole.roleName);
				console.log(dataout.parmaster.parReceivedDate);	
				console.log((dataout.parmaster.parReceivedDate).substring(0,2));
				console.log((dataout.parmaster.parReceivedDate).substring(3,5));
				console.log((dataout.parmaster.parReceivedDate).substring(6));
				
				
				$('[name="parRole"] ').val(dataout.parmaster.parRole.roleName).prop('selected',true);
				
				
				$('[name="skill"] ').val(dataout.parmaster.skill.skillName).prop('selected',true);
				
				
				$('[name="extStaffName"] ').val(dataout.parmaster.externalStaff.extStaffName).prop('selected',true);
				
				parRecieveddate = (dataout.parmaster.parReceivedDate).substring(6)+'-'+(dataout.parmaster.parReceivedDate).substring(0,2)+'-'+(dataout.parmaster.parReceivedDate).substring(3,5);
				$('[name="parDateReceived"]') .val(parRecieveddate);
				

				candidateList = dataout.candidateList;
				prescreenerList = dataout.preScreenerList;
				parmaster = dataout.parmaster;
				parAllocationList = dataout.parAllocationList;
				datatableobj=[];
				
				$.each(parAllocationList,function(key,value){
					var tablerowobj=convertIDtoName(value,tablerowobj);
					tablerowobj.prescreeningDate = value.preScrdate;
					tablerowobj.prescreeningComment = value.preScrCommnt;
					tablerowobj.action=actionbutton;
					datatableobj.push(tablerowobj);	
				});
					
				
				datatableData= JSON.parse(JSON.stringify(datatableobj));
				
				console.log(datatableData);
				console.log(typeof datatableData);
				console.log(typeof datatableobj);
				console.log(typeof JSON.stringify(datatableobj));
				$('#prescreeningTable').DataTable().destroy();
				$('#prescreeningTable').DataTable( {
					"scrollY":        "300px",
					"scrollCollapse": true,
					data: datatableData,
					columns: [
			            { 'data': 'candidateName' },
			            { 'data': 'prescreenerName' },
			            { 'data': 'prescreeningDate' },
			            { 'data': 'prescreeningComment' },
			            { 'data': 'action' }
			        ]
				});
				
				if(Requestmethod=="POST")
					{
						$('#prescreeningEditModal').modal('hide');
						$('#messageModalBody').html('Data Updated successfully for ParAllocId:'+parAllocId);
						$('#messageModal').modal('show');
					}
							
			}
			else
				{	
					if(Requestmethod=="GET")
					 {
						$('#messageModalBody').html('Data not found for Par no:'+parNo);
						$('#messageModal').modal('show');
					 }
					else
						{
						$('#messageModalBody').html('Data not updated for:'+parAllocId);
						$('#messageModal').modal('show');
						}
					
				}
			
			
			},
			error:function(req, status, error)
			{
				
				console.log(req.responseText);
				console.log(status,error);
			}	
		});
	}

	function convertIDtoName(data)
		{
		  var candidateName="";
		  var outputdata={};
		  var prescreenerName="";
		  $.each(candidateList,function(key,value){
			if(data.candidateId==value.candidateId)
				{
				candidateName =value.candidateName;
				}
		  });
		  var prescreenerName=""
		  $.each(prescreenerList,function(key,value){
			 if(data.prescrId==value.preScreenerId)
				{
				  prescreenerName =value.preScreenerName;
				}
		   });	
		  
		  outputdata.candidateName=candidateName;
		  outputdata.prescreenerName=prescreenerName;
		  return outputdata;
		  
		}
	
	/* Performs the functionality of deleting the selected canidate */

	$('#areaModalDelete-btn').click(function(){
		$('#areaDeleteconfirmModal').modal('hide');
		var areaId= $('#areaModalDeleteAreaId').val();

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteArea/"+areaId,
			data: "",
			success:function(data){
				$('#areaDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(req.responseText);
				console.log(status,error);
			}	
		});
	});
	
	
});

					
				

