<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Loan Sanction</title>
</head>
<body>
	<div class="container">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
			<input type="hidden" name="pageId" value="LOAN_SANCTION" id="PAGE_ID">
			<input type="hidden" name="memberId" value="" id="MEMBER_ID">
			<H1>Loan Sanction</H1>
			<div class="form-wrap">
				<jsp:include page="searchBar.jsp" />
				<jsp:include page="topPanel.jsp" />
				<div calss="" style="width: 100%; height: auto; margin: 0 auto;">

					<fieldset>
						<legend>
							<h4>Loan Form</h4>
						</legend>

						<div class="" style="width: 80%; height: auto; margin: 0 auto;">

							<table style="width: 80%; margin: 0 auto">
								<form action="" method="POST" onsubmit="">

									<tr>
										<td>Loan Amount</td>
										<td><input type='number' name='loanAmount'
											id='loanSanctionAmount' class='texBoxCss'></td>
									</tr>
									<tr>


										<td>Loan Issued Date</td>
										<td><input type='text' name='loanSanctioneddDate'
											id='loanSanctionLoanSanctioneddDate'
											class='texBoxCss currentDateDatepicker'></td>
									</tr>
									<tr>
										<td>Remarks</td>
										<td><input type='text' name='ramarks'
											id='loanSanctionRmarks' class='texBoxCss'></td>
									</tr>

								<tr>


									<td align="center">&nbsp;</td>
									<td ><center>
										<button class="w3-button w3-white w3-border w3-border-blue"
											style="margin: 5px;" id="sactionLoanBtn">Sanction
											Loan</button>
										<input type="reset" class="w3-button w3-white w3-border w3-border-blue"
											style="margin: 5px;" id=""></center>
									</td>
								</tr>
								</form>
							</table>

						</div>

					</fieldset>


				</div>
				<!-- 	<fieldset>
					<legend>
						<h4>Loan Details</h4>
					</legend>
					<div id="searchDetails"></div>
				</fieldset> -->

			</div>


		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>
</html>