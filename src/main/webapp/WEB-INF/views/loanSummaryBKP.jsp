<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Summary</title>


</head>
<body>
	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<script type="text/javascript">
$(document).ready(function(){
  
	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : "getLoanSummary",		
		async:true,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 6000000,
		success : function(data) {
			
			console.log(data);
			$('#LoanDetailedSummary').empty().prepend(data);
			

		},
		
		error : function(e) {
			
			

		}
	});
});

</script>
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
		<input type="hidden" name="pageId" value="SEARCH_CONTACT_DETAILS" id="PAGE_ID">
		<input type="hidden" name="memberId"  value=""              id="MEMBER_ID">
<!-- getEmployeeDetails -->
			<div class="form-wrap">
				<fieldset>
					<legend> <h4> Department Wise Summary</h4> </legend>					
					<div id="DepartmentWiseSummary" style=""></div>
				</fieldset>
				<fieldset>
					<legend> <h4> Detailed Summary</h4> </legend>					
					<div id="LoanDetailedSummary" style=""></div>
				</fieldset>
		
				

			</div>

		</div>
		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>
</html>