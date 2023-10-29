<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Search</title>
</head>
<body>
	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
				
		<input type="hidden" name="pageId" value="GET_LOAN_DETAILS" id="PAGE_ID">
		<input type="hidden" name="memberId"  value=""              id="MEMBER_ID">

			<div class="form-wrap">
				<fieldset>
					<legend>
						<h4>Search Member</h4>
					</legend>
					<select class='select_style'>${DEPARTMENTS}</select> <input
						type="text" id="myInput" onkeyup="myFunction()"
						placeholder="Enter Card No.." title="Type in a name">

				</fieldset>
				<jsp:include page="topPanel.jsp" />





				<div calss="" style="width: 100%; height: auto; margin: 0 auto;">


					<fieldset>
						<legend>
							<h4>Loan Sanction Details</h4>
						</legend>
						<table border="0">


							<tr>
								<td style="width: 12%;">Loan Sanction Date</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Sanctioned Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Loan Sanctioned By</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Recovered Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Remarks</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Due Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>


						</table>



					</fieldset>
					<fieldset>
						<legend>
							<h4>Loan Payment History</h4>
						</legend>


<div id="searchDetails"></div>



					</fieldset>




				</div>
				<!-- -->





			</div>

			
		</div>
		<jsp:include page="footer.jsp" />
</body>
</html>