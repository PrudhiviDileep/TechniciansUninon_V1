
<div id="commonSearchBar">
	<fieldset>
		<legend>
			<h4>Search Member</h4>
		</legend>
		<table>
			<tr>
				<td align="right" style="width: 45% !important"><select class='select_style' id='commonSeachDeptSelectId'
					title="Select Department"
					onchange="commonSearchGetCardNumbersByDeptId()"
					 name='cmnSearchBarDeptId' style="width: 100% !important">${DEPARTMENTS}</select>
				</td>
				<td align="right"  style="width:45% !important"><input type="text" class="searchCardNo"
					name='cmnSearchBarcardNo' id='commonSearchCardNo'
				
					placeholder="Enter Card No.."
					title="Type in a name" disabled='true' style="width: 100% !important">	<!-- onkeydown="commonSearchCardNoAutocomplete()" --></td>
				<td align="left"  style="width:10%"><button class="w3-button w3-white w3-border w3-border-blue"
						style="margin: 5px;background:#4fcdff !important;border-radius: 5px;color:#FFF !important;display: block;
	

	
	padding: 10px 18px;
	margin: 20px auto;
	color: #FFF;
	background-color: #555;
	background: -webkit-linear-gradient(#888, #555);
	background: linear-gradient(#888, #555);
	border: 0 none;
	border-radius: 3px;
	font-weight:bold;

	cursor: pointer;" id="commonSearchBtn" onclick ="getSearchDetails()" >Search</button></td>
			</tr>
		</table>




	</fieldset>
</div>
