<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Technicians Union Reports</title>
</head>
<body>
	<div class="container">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
			<input type="hidden" name="pageId" value="SEARCH_CONTACT_DETAILS"
				id="PAGE_ID"> <input type="hidden" name="memberId" value=""
				id="MEMBER_ID">
			<div class="form-wrap">
				<div id="commonSearchBar">
					<fieldset>
						<legend>
							<h4>Report</h4>
						</legend>
						<select class='select_style' id='getDetailsDeptSelectId'
							title="Select Department" onchange="getDetails()">
							<option value="ALL_DEPARTMENTS">All Departments</option>
							<option value="CAMERA">Camera</option>
							<option value="ELECTRICIAN">Electrician</option>
							<option value="SOUND">Sound</option>
							<option value="UNIT_DRIVER">Unit Driver</option>
						</select> 
						<select class='select_style' id='selectActionId'
							title="Select Action" onchange="getDetails()">
							<option value="CONTACT_DETAILS">Contact Details</option>
							<option value="CARD_BALANCE_DETAILS">Card Balance
								Details</option>
							<option value="LOAN_BALANCE_DETAILS">Loan Balance
								Details</option>
							<option value="BANK_DETAILS">Bank Details</option>
						</select>
					</fieldset>
				</div>
				<fieldset>
					<legend>
						<h4>Results</h4>
					</legend>
					<div id="searchDetails" style=""></div>
				</fieldset>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
	</div>
</body>
</html>