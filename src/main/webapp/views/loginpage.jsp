<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String context=request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=context %>/js/jquery.min.js"></script>
<script src="<%=context %>/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=context %>/css/main.css">
<script type="text/javascript" src="<%=context %>/js/mainJs.js"></script>

</head>
<body>
	<div class=""
		style="width: 30%; padding: 0px; height: 100%; margin: 250px auto; background: #FFF; padding: 25px;">

<form action='<%=context %>/authenticateUser' method='post' onsubmit="return loginFormValidation() ">


		<table style="margin: 20px 25px">

			<tr>
				<td colspan="2" align="center">Login</td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type='text' id='userNameId' name='userName' value=''></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type='password' id='passwordId' name='password'  value=''></td>
				<span style="color:red;font-size: 12px;">${LOGIN_MESSAGE} </span>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center">

					<div
						style="width: auto; height: auto; display: inline-table; text-align: center; float: left;">
						<button class="w3-button w3-white w3-border w3-border-blue"
							style="margin-right: 10px; margin-left: 20px;"  >Submit</button>
						<button class="w3-button w3-white w3-border w3-border-blue"
							style="margin-right: 10px;">Cancel</button>
						<button class="w3-button w3-white w3-border w3-border-blue">Reset</button>
						

					</div>
				</td>

			</tr>
		</table>

</form>






	</div>
<div id='logoutDailog'></div>
</body>
</html>