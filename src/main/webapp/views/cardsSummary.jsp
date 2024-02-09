<!DOCTYPE html>
<html>
<head>
<%String context=request.getContextPath(); %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Cards Yearly Summary</title>
    <link rel="stylesheet" href="<%=context %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=context %>/css/main.css">
 <link rel="stylesheet" type="text/css" href="<%=context %>/js/jsgrid/css/jsgrid.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=context %>/js/jsgrid/css/theme.css" />

    <script src="<%=context %>/js/jsgrid/external/jquery/jquery-1.8.3.js"></script>
 <script src="<%=context %>/js/jsgrid/db.js"></script> 

<script type="text/javascript"  src="<%=context %>/js/jquery-ui.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.core.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.load-indicator.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.load-strategies.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.sort-strategies.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.field.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.text.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.number.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.select.js"></script>
<script
	src="<%=context%>/js/jsgrid/src/fields/jsgrid.field.checkbox.js"></script>
<script
	src="<%=context%>/js/jsgrid/src/fields/jsgrid.field.control.js"></script>
<script src="<%=context%>/js/jsgrid/src/jsgrid.validation.js"></script>
<script src="<%=context%>/js/cheque.js"></script>
<style>
.saveButn {
	margin: 5px;
	background: #4fcdff !important;
	border-radius: 5px;
	color: #FFF !important;
	padding: 5px 9px;
	margin: 0px auto;
	color: #FFF;
	background-color: #555;
	background: -webkit-linear-gradient(#888, #555);
	background: linear-gradient(#888, #555);
	border: 0 none;
	border-radius: 3px;
	cursor: pointer !important;
	/*font-weight: bold;*/
}

.external-pager {
	margin: 10px 0;
}

.external-pager .jsgrid-pager-current-page {
	background: #c4e2ff;
	color: #fff;
}

.config-panel {
	padding: 10px;
	margin: 10px 0;
	background: #fcfcfc;
	border: 1px solid #e9e9e9;
	display: inline-block;
}

.config-panel label {
	margin-right: 10px;
}
</style>
<script> var context="<%=context %>"; </script>

</head>
<body>
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<div id="loader"></div>
<div class="headerWrapper">
<h1 style="horizontal-allign: middle; color: white; text-shadow: 2px 2px 4px #000000;">TELUGU CINE & T.V. OUTDOOR UNIT TECHNICIANS UNION</h1>
</div>
<script type="text/javascript">

function getCardYearlySummary(){
	
	var deptId = $("#DEPARTMENT option:selected" ).text();
	var year = $("#year option:selected" ).text();

	
	
	$.ajax({
		type : "POST",
		url : 'getSummary',
		data : {
			deptId : deptId,
			action : 'CARD_BALANCE_SUMMARY',			
			year : year			

		},
		traditional : true,
		cache : false,
		// async: false,
		success : function(response) {
			$('#CARD_BALANCE_SUMMARY').empty();
			$('#CARD_BALANCE_SUMMARY').append(response);

		},
		error : function(response) {
		}
	});
	
	
	
}


</script>
<jsp:include page="navigation.jsp" />
<table style="width: 30%;margin:0 auto;"><tr>
<td>Department</td><td>
<select onchange="getCardYearlySummary()" id="DEPARTMENT">
<!-- <option id='ALL'>ALL</option> -->
<option id='CAMERA'>CAMERA</option>
<option id='ELECTRICIAN'>ELECTRICIAN</option>
<option id='SOUND'>SOUND</option>
<option id='UNIT_DRIVER'>UNIT_DRIVER</option>
</select>
</td>
<td>Select Year</td><td><select id='year' onchange="getCardYearlySummary()">
<option>2015</option>
<option>2016</option>
<option>2017</option>
<option>2018</option>
<option>2019</option>
<option>2020</option>
<option>2021</option>
<option>2022</option>
<option>2023</option>
<option>2024</option>
</select></td></tr>
</table>
<div id="CARD_BALANCE_SUMMARY" style="width: 97%;margin:0 auto;"></div>
<jsp:include page="footer.jsp" />

<div id='logoutDailog'>
</div>
</body>
</html>