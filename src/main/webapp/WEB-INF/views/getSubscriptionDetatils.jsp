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

			<input type="hidden" name="pageId" value="GET_SCUBSRIPTION_DETAILS"
				id="PAGE_ID"> <input type="hidden" name="memberId" value=""
				id="MEMBER_ID">
			<div class="form-wrap">
				<jsp:include page="searchBar.jsp" />
				<jsp:include page="topPanel.jsp" />


				<fieldset>
					<legend>
						<h4>Subscripton Details</h4>
					</legend>

					<div id="searchDetails"></div>


				</fieldset>








			</div>


		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
		</div>
</body>
</html>