<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
</head>
<body>
	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
		<input type="hidden" name="pageId" value="SEARCH_CONTACT_DETAILS" id="PAGE_ID">
		<input type="hidden" name="memberId"  value=""              id="MEMBER_ID">
<!-- getEmployeeDetails -->
			<div class="form-wrap">
		<div id="commonSearchBar">
	<fieldset>
		<legend>
			<h4>Get Details</h4>
		</legend>	
		<table>
			<tr>			
				<td>
						<select class='' id='getDetailsDeptSelectId' title="Select Department" onchange="getDetails()">
							<!-- <option value="ALL_DEPARTMENTS">All Departments</option> -->
							<option value="CAMERA">Camera</option>
							<option value="ELECTRICIAN">Electrician</option>
							<option value="SOUND">Sound</option>
							<option value="UNIT_DRIVER">Unit Driver</option>
						</select> 
				</td>
				<td>
						<select class='' id='selectActionId' title="Select Action" onchange="getDetails()">
							
							<option value="CONTACT_DETAILS">Contact Details</option>
							<option value="CARD_BALANCE_DETAILS">Card Balance Report</option>
							<option value="LOAN_BALANCE_DETAILS">Loan Balance Report</option>
							<option value="BANK_DETAILS">Bank Details Report</option>
							<option value=SUBSCRIPTION_BALANCE>Subscription Report</option>								
						</select> 		
				</td>
				<td>Order By</td>
				
				<td id="TD_ORDER_BY">
				<!-- Card No	Department	First Name	Perminent Address	Present Address	Adhar	Phone -->
						<select id="ORDER_BY_CONTACT_DETAILS" style="display: " onchange="getDetails()">
						<option value="CARD_NO">Card No</option>
						<option value="FIRST_NAME">First Name</option>
					<!--<option value="PERMINENT_ADDRESS">Perminent Address</option>
						<option value="PRESENT_ADDRESS">Present Address</option>	
						<option value="AADHAR_CARD_NO">Aadhaar</option>
						<option value="PHONE_NO">Phone</option>			-->																	
						</select>
						<select id="ORDER_BY_CARD_BALANCE_DETAILS"  style="display: none" onchange="getDetails()">	
						<option value="CARD_NO">Card No</option>
						<option value="FIRST_NAME">First Name</option>
						<option value="CARD_AMOUNT">Card Amount</option>
						<option value="PAID_AMOUNT">Paid Amount</option>
						<option value="CARD_BALANCE">Card Balance</option>	
						<!-- <option value="PHONE_NO">Phone</option> -->																				
						</select>
						<select id="ORDER_BY_LOAN_BALANCE_DETAILS"  style="display: none" onchange="getDetails()">
						<option value="CARD_NO">Card No</option>
						<option value="FIRST_NAME">First Name</option>
						<option value="LOAN_AMOUNT">Loan Amount</option>
						<option value="LOAN_PAID">Loan RePaid</option>
						<option value="LOAN_BALANCE">Loan Balance</option>	
						<!-- <option value="PHONE_NO">Phone</option>	 -->																				
						</select>						
						<select id="ORDER_BY_BANK_DETAILS"  style="display: none" onchange="getDetails()">	
						<option value="CARD_NO">Card No</option>
						<option value="FIRST_NAME">First Name</option>
						<!--<option value="BANK_NAME">Bank Name</option>
						<option value="BANK_BRANCH">Branch</option>
						<option value="BANK_IFSC_CODE">IFSC</option>	
						<option value="PHONE_NO">Phone</option>	-->																				
						</select>						
						<select id="ORDER_BY_SUBSCRIPTION_BALANCE"  style="display: none" onchange="getDetails()">
						<option value="CARD_NO">Card No</option>
						<option value="FIRST_NAME">First Name</option>
						<!-- <option value="APPLIED_DATAE">Join Date</option>	-->
						<!-- <option value="PHONE_NO">Phone</option>		 -->																			
						</select>						
				</td>
				<td id="TD_SORT_TYPE">Sort</td>
				<td>
						<select id="SORT_TYPE" onchange="getDetails()">
							<option value="ASC">Ascending</option>
							<option value="DESC">Descending</option>
						</select>
		
				</td>		
			
			</tr>
		</table>	

		
	</fieldset>
</div>
		
				<fieldset>
					<legend>
						<h4> Results</h4>
					</legend>
					
					<div id="searchDetails" style="">			
					
					
					
					</div>
					
					
				</fieldset>

			</div>

		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>
</html>