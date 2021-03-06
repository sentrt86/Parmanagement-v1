<!-- <!DOCTYPE html> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>Candidate</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/candidate.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="static/js/popper.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/candidate.js"></script>
</head>
<body>
		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
		<div class="main">
			<div >
				<h1 class="screen-title">Candidate</h1>
			</div>

		    <div class="tablediv">
		    	<table id="candidateTable" class="table table-striped table-bordered" style="width:160%">
				     <thead>
				         <tr>
				             <th>Candidate Id</th>
				             <th>Candidate Name</th>
				             <th>Candidate Phone</th>
				             <th>Candidate Email</th>
				             <th>Candidate Received Date</th>
				             <th>Skill Name</th>
				             <th>Active</th>
				             <th>Action</th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="candidate" items="${allCandidatesList}">
				     		<tr>
				     			<td>${candidate.candidateId}</td>
				     			<td>${candidate.candidateName}</td>
				     			<td>${candidate.candidatePhoneNum}</td>
				     			<td>${candidate.candidateEmailTxt}</td>
				     			<td>${candidate.candidateReceivedDate}</td>
				     			<td>${candidate.skill.skillName}</td>
				     			<td>${candidate.candidateActive}</td>
				     			<td>
				     				<button type="button" class="btn btnCandidateEdit btn-link" id="candidateTableEdit-btn">Edit</button>/
				     				<button type="button" class="btn btnCandidateDelete btn-link" id="candidateTableDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		   	<div>
				<button type="button" class="btn btn-primary btnCandidateAdd" id="candidateAdd-btn">Add Candidate</button>
			</div>
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete ExternalStaff Message Modal -->
		<div class="modal fade" id="candidateDeleteconfirmModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Delete
							Confirmation</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="candidateDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="candidateModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="candidateModalDeleteCandidateId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Edit ExternalStaff Message Modal -->
		<div class="modal fade" id="candidateEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update Candidate</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form class="container" novalidate=""  id="candidateForm">
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess1">Candidate Id</label>
							        <input type="text" class="form-control" name="candidateIdModal" id="inputSuccess1" required>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the candidate id</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess2">Candidate Name</label>
							        <input type="text" class="form-control"  name="candidateNameModal" required id="inputSuccess2">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the candidate name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess3">Candidate Phone</label>
							        <input type="text" class="form-control"  name="candidatePhoneNumModal" required id="inputSuccess3">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the candidate phone</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess4">Candidate Email</label>
							        <input type="text" class="form-control"  name="candidateEmailTxtModal" required id="inputSuccess4">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the candidate Email</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess5">Candidate Received Date</label>
							        <input type="date" class="form-control"  name="candidateReceivedDateModal" required id="inputSuccess5">
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please enter the candidate received date</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess6">Skill Name</label>
							        <select class="form-control" name="candidateSkillNameModal" required id="inputSuccess6">
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the Skill Name</div>
							    </div>
							    <div class="form-group">
							        <label class="form-control-label" for="inputSuccess7">Candidate Active</label>
							        <select class="form-control" name="candidateActiveModal" required id="inputSuccess7">
								      <option value="Yes">Yes</option>
								      <option value="No">No</option>
								    </select>
							        <div class="valid-feedback">Success!</div>
							        <div class="invalid-feedback">Please select the area active yes or no</div>
							    </div>
								 <div class="modal-footer">
									<button type="button" class="btn btn-primary" id="candidateModalEdit-btn">Save</button>
									<button type="button" class="btn btn-primary cancel"
											data-dismiss="modal">Cancel</button>
									<input type="hidden" id="candidateModalProcess"/>
								</div>
							</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Message Modal -->
		<jsp:include page="Message.jsp" />
</body>
</html>