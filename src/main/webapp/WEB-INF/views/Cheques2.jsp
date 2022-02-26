<!DOCTYPE html>
<html lang="en">
<head>
<title>Cheques</title>
<style>

.saveButn{
	margin: 5px;
	background:#4fcdff !important;
	border-radius: 5px;color:#FFF !important;
	padding: 10px 18px;
	margin: 20px auto;
	color: #FFF;
	background-color: #555;
	background: -webkit-linear-gradient(#888, #555);
	background: linear-gradient(#888, #555);
	border: 0 none;
	border-radius: 3px;
	font-weight:bold;

}

</style>
</head>
<body>
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<jsp:include page="navigation.jsp" />
<div class='mainbody'>
<div class="form-wrap"> 
<div class="GRID_ONE">
<div id="commonTopPanel">
	<fieldset>
		<legend>
			<h4>Create Cheque</h4>
		</legend>
		
		<table border="0">
			<tr>
				<td style="width: 12%;">Type</td>
				<td style="width: 18%;" align="center">
				
				<select style="width: 80%">
				<option value="CHEQUE">CHEQUE</option>
				<option value="IMPS">IMPS</option></select>
				</td>
				<td style="width: 12%;">Cheque No</td>
				<td style="width: 18%;" align="center"><input type='text' name='CHEQUE_NO' id='CHEQUE_NO' class='texBoxCss'></td>
				
			
				<td style="width: 12%;">Recieved Date</td>
				<td style="width: 18%;" align="center">
				<input type='text' name='CHEQUE_DATE' id='CHEQUE_DATE' class='texBoxCss'>
				</td>
				</tr>
			<tr>

				<td style="width: 12%;">Amount</td>
				<td style="width: 18%;" align="center">
				<input type='text' name='AMOUNT' id='AMOUNT' class='texBoxCss' >
				</td>
				<td style="width: 12%;">Company Name</td>
				<td style="width: 18%;" align="center">
				<input type='text' name='COMPANY_NAME' id='COMPANY_NAME' class='texBoxCss'></td>
				<td style="width: 12%;">Recieved From</td>
				<td style="width: 18%;" align="center"><input type='text'
					name='CHEQUE_RECIEVED_FROM' id='CHEQUE_RECIEVED_FROM' class='texBoxCss'></td>
			</tr>
			<tr>

				<td style="width: 12%;">Bank Name</td>
				<td style="width: 18%;" align="center">
				<input type='text' name='BANKENAME' id='BANKENAME' class='texBoxCss'></td>
				<td style="width: 12%;">Remarks</td>
				<td style="width: 58%;" align="center" colspan="3">
				<textarea name="REMARKS" id="REMARKS" style="border: 1px solid #ccc;    box-shadow: inset 0 1px 3px #ddd;    border-radius: 4px;    -webkit-box-sizing: border-box;    -moz-box-sizing: border-box;    box-sizing: border-box;    padding: 5px;width: 90% !important;" ></textarea></td>
			</tr>
		 	<tr>
		 	<td colspan="6"><button type="button" class="saveButn">Save</button> <button class="saveButn" type="reset"> Reset</button></td>
		 	</tr>
		</table>
	</fieldset>
</div>

</div>
<div class="GRID_TWO">



</div>
<div class="GRID_THRE"></div>



</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>