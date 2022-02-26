<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% String context=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>


</head>
<body>


	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
		<H1>Delete Membership Payment Details</H1>

				
		<input type="hidden" name="pageId" value="DELETE_CARD_BALANCE_PAYMENT" id="PAGE_ID">
		<input type="hidden" name="memberId"  value='${MEMBER_ID}' id="MEMBER_ID">

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

			<div class="" id="membershipDeatils">


					<fieldset>
						<legend>
							<h4>Membership Details</h4>
						</legend>


						<table>
							<form action="" method="get" onsubmit="" id="deleteMembershipAmountDetailsForm">
								<input type="hidden" name="transactionId"  value='${TRANSACTION_ID}' id="deletePaymentTransactionId">
								<input type="hidden" name="cardNo"  value='${CARD_NO}' id="deletePaymentCardNo">
								<input type="hidden" name="deptId"  value='${DEPT_ID}' id="deletePaymentDeptId">
								<tr><td>Payment Type</td>
									<td><div class="select-style ">
											<select id='deletePaymentConfIdId' name='paymentConfId' onchange="setSelectValueToTarget('deletePaymentConfIdId','deleteMembershipAmountId')" disabled="disabled" >${PAYMENT_CONF_ID}</select>
										</div></td>
										

									<td>Membership Amount</td>
									<td><input type='text' name='membershipAmount' id='deleteMembershipAmountId' class='texBoxCss' disabled="disabled" value='${MEMBERSHIP_AMOUNT}'></td>
								</tr>
								<tr>
									<td>Paid Amount</td>
									<td><input type='text' name='paidAmount' id='deleteMembershipPaidAmountId' class='texBoxCss' disabled="disabled" value='${PAID_AMOUNT}'></td>
								
									
								
									<td>Receipt No</td>
									<td><input type='text' name='receiptNo' id='deleteMembershipReceiptNoId' class='texBoxCss' value='${RECEIPT_NO}' disabled="disabled"></td>
								</tr>
							
								
								<tr>
									<td>Paid Date</td>
									<td><input type='text' id='deleteMembershipPaidDate'
										name='paidDate' value='${PAID_DATE}'
										class='texBoxCss form-control ' disabled="disabled"></td>
								
									<td>Remarks</td>
									<td><input type='text' name='remarks' id='deleteMembershipRemarks' class='texBoxCss' value='${REMARKS}' disabled="disabled"></td>
								</tr>




								<tr>
									<td colspan="4" align="center"><button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="deleteMembershipAmountBtn">Delete</button>
										<button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="">Back</button>
								</td>
								</tr>
							</form>
						</table>



					</fieldset>


				</div>
	
	




			</div>



		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>

</html>
