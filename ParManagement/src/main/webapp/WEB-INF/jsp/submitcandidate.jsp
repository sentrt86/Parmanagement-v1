	<!-- <!DOCTYPE html> -->
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<%@ page session="false" %>
	<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="_csrf" content="${_csrf.token}"/>
	    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
		
		<title>Submit Candidate</title>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<!--  Jquery CSS -->
		<link rel="stylesheet" href="static/css/jquery-ui.min.css">
		<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
		<link rel="stylesheet" href="static/css/bootstrap.css">
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
		<!-- Par Management CSS -->
		<link rel="stylesheet" href="static/css/common.css">
		<link rel="stylesheet" href="static/css/parfullfillmentcommon.css">	
		<link rel="stylesheet" href="static/css/submitcandidate.css">	
		
		<!-- JQuery -->
		<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="static/js/popper.min.js"></script>
	
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
		<script type="text/javascript" src="static/js/bootstrap.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
		<!-- Par Management Java Script -->
		<script type="text/javascript" src="static/js/common.js"></script>
		<script type="text/javascript" src="static/js/submitcandidate.js"></script>
	</head>
	<body>
			<!-- Page Header and Menu jsp -->
			<jsp:include page="header-menu.jsp" />		
			<div class="main">
				<div>
			    	<h1 class="screen-title">Submit Candidate</h1>
			    </div>
				<form class="form-top">
				  <div class="row">
				    <div class="col col-sm-4">
				      <div class="form-inline">
				      	<label class="form-control-label" for="inputSuccess1">Par No :  </label> &nbsp;&nbsp;
				        <input type="text" class="form-control mb-6 mr-sm-2" name="parNo" id="inputSuccess1" required style="width:205px;" placeholder=" Enter Par No">
				        <div class="valid-feedback">Success!</div>
				        <div class="invalid-feedback">Please enter the par no</div>
				      </div>
				    </div>
				  </div>
				  <hr class="linebreak"></hr>
				  <div class="row mt-3 marginRow">
				    <div class="col">
				      <div class="form-group">
				      	<label class="form-control-label mr-sm-2" for="inputSuccess2">Par Received Date</label>
				      	<input type="date" class="form-control mb-6 mr-sm-2" name="parDateReceived" id="inputSuccess2" required placeholder="Date Received" readonly>
				      </div>
				    </div>
				    <div class="col">
				      <div class="form-group">
				       	 	<label class="form-control-label mr-sm-2" for="inputSuccess3">External Staff</label>
					        <input type="text" class="form-control mb-6 mr-sm-2" name="extStaffName" id="inputSuccess3"  placeholder="External Staff" readonly>
					  </div>
				    </div>
				  </div>
				  <div class="row mt-3 marginRow">
				     <div class="col">
				      <div class="form-group">
				      		<label class="form-control-label" for="inputSuccess4">Role</label>
				      		<input type="text" class="form-control mb-6 mr-sm-2" name="parRole" id="inputSuccess4"  placeholder="Role" readonly>
					  </div>
				    </div>
				    <div class="col">
				       <div class="form-group">
				       		<label class="form-control-label" for="inputSuccess5">Skill</label>
				       		<input type="text" class="form-control mb-6 mr-sm-2" name="skill" id="inputSuccess5"  placeholder="Skill" readonly>
					   </div>
				    </div>
				  </div>
				</form>
				
				<div class="tablediv">
			    	<table id="submitCandidateTable" class="table table-striped table-bordered" style="width:100%">
					     <thead>
					         <tr>
					             <th>Candidate Name</th>
					             <th>Submission Date</th>
					             <th>Action</th>
					         </tr>
					     </thead>
					     <tbody>
					     	<%-- <c:forEach var="candidate" items="${allParCandidatesList}">
					     		<tr>
					     			<td>${candidate.candidateName}</td>
					     			<td>${candidate.dateReceived}</td>
					     			<td>
					     				<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     			</td>
					     		</tr>        		
					     	</c:forEach> --%>
					     	<tr>
					     		<td> Anu </td>
					     		<td> 01/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Mike </td>
					     		<td> 02/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Raju </td>
					     		<td> 03/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Deepika </td>
					     		<td> 04/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Amanda </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Amanda </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Amanda </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Karthi </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     			<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Magesh </td>

					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     			<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Menon </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     			<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Balraj </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     			<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	<tr>
					     		<td> Ram </td>
					     		<td> 05/20/2020 </td>
					     		<td>
					     			<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
					     			<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
					     		</td>
					     	</tr>
					     	
					     </tbody>
					</table>
			   </div>
			</div>
			<footer class="footer">
				<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
			</footer>
			
		
			
			<!-- Message Modal -->
			<jsp:include page="Message.jsp" />
	</body>
	</html>