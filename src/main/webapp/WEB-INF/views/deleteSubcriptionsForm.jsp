<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Subscription Details</title>


</head>


<body>
	<div class="container">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
			<H1>Delete Subscription Details</H1>
			<div class="form-wrap">
			
				<div id="commonTopPanel">
					<fieldset>
						<legend>
							<h4>Member Details</h4>
						</legend>
						<table border="0">
							<tr>
								<td style="width: 12%;">Name</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${FIRST_NAME}" name='memberName' id='tpnl_memberName'
									class='texBoxCss' disabled="true"></td>
								<td style="width: 12%;">Card Balance</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${CARD_BALANCE}" name='memberName' id='tpnl_cardBalance'
									class='texBoxCss' disabled="true"></td>

								<td rowspan="4" align="center" style="width: 15%;"><div
										id="tpnl_profilePicDiv">
										<img id="tpnl_profilePic" alt="" src='${FILE_CONTENT}'>
									</div></td>
							</tr>
							<tr>
								<td style="width: 12%;">Department</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${DEPT_ID}" name='memberName' id='tpnl_deparmentName'
									class='texBoxCss' disabled="true"></td>
								<td style="width: 12%;">Loan Balance</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${CURRENT_LOAN_BALANCE}" name='memberName' id='tpnl_loanBalance'
									class='texBoxCss' disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Card No</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${CARD_NO}" name='memberName' id='tpnl_cardNo'
									class='texBoxCss' disabled="true"></td>
								<td style="width: 12%;">Phone No</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${PHONE_NO}" name='memberName' id='tpnl_phoneNo'
									class='texBoxCss' disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Joining Date</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${REGISTERED_DATE}" name='memberName'
									id='tpnl_dateOfJoining' class='texBoxCss' disabled="true"></td>
								<td style="width: 12%;">Address</td>
								<td style="width: 30%;" align="center"><input type='text'
									value="${PERMINENT_ADDRESS}" name='memberName'
									id='tpnl_address' class='texBoxCss' disabled="true"></td>
							</tr>
						</table>
					</fieldset>
				</div>
				<fieldset>
					<legend>
						<h4>Update Subscripton Details</h4>
					</legend>
					<div id="searchDetails"></div>

					<div class="" style="width: 80%; height: auto; margin: 0 auto;">
					
					<input type="hidden" name="pageId" value="DELETE_SUBSCRIPTION_PAYMENT" id="PAGE_ID">
		

						<table>
							<form  method="POST" id="subscriptionsDeletingForm">
							<input type='hidden' id='deleteSubscrib_cardNo'
									name='cardNo' value='${CARD_NO}'> 
									<input type='hidden' id='deleteSubscrib_deptId'
									name='deptId' value='${DEPT_ID}'> 
								<input type='hidden' id='deleteSubscrib_category'
									name='category' value='${CATEGORY}'> <input
									type='hidden' id='deleteSubscrib_memberId' name='memberId'
									value='${MEMBER_ID}'> <input type='hidden' id='delete_subscription_cardNo'
									name='cardNo' value='${CARD_NO}'>
									<input type='hidden'
									id='deleteSubscrib_ctransactionId' name='transactionId'
									value='${TRANSACTION_ID}'>
									<input type='hidden'
									id='deleteSubscrib_paymentConfId' name='paymentConfId'
									value='${PAYMENT_CONF_ID}'>
									<input type='hidden'
									id='deleteSubscrib_subscriptionAmount' name='subscriptionAmount'
									value='${SUBSCRIPTION_AMOUNT}'>
								<tr>
									<td>Subscribed Year</td>
									<td><div class="select-style ">
											<select id='deleteSubscrib_subscriptionYear' name='subscriptionYear' disabled="disabled">${SUBSCRIPTION_YEAR}</select>
										</div></td>

									<td>Paid Date</td>
									<td><input type='text' id='deleteSubscrib_paidDate'
										name='paidDate' value='${PAID_DATE}'
										class='texBoxCss form-control datepicker' disabled="disabled"></td>
								</tr>
								<tr>
									<td>Paid Amount</td>
									<td><input type='text' id='deleteSubscrib_paidAmount'
										name='paidAmount' value='${PAID_AMOUNT}' class='texBoxCss' disabled="disabled"></td>
									<td>Receipt No</td>
									<td><input type='text' id='deleteSubscrib_receiptNo'
										name='receiptNo' value='${RECEIPT_NO}' class='texBoxCss' disabled="disabled"></td>
								</tr>
								<tr>
									<td>Payment Status</td>
									<td><div class="select-style ">
											<select id='deleteSubscrib_paymentStatus' name='paymentStatus' disabled="disabled">${PAYMENT_STATUS}</select>
										</div></td>
									<td>Remarks</td>
									<td><input type='text' id='deleteSubscrib_remarks'
										name='remarks' value='${REMARKS}' class='texBoxCss' disabled="disabled"></td>
								</tr>

								<tr>
								<td colspan="4" align="center">
										<button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="subscriptionsdeleteBtn">Delete</button></td>
								</tr>
							</form>
						</table>
					</div>
				</fieldset>
			</div>
			<div id='logoutDailog'></div>
		</div>
		<jsp:include page="footer.jsp" />
		
	</div>
</body>
</html>