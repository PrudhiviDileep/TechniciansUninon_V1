<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Loan Summary</title>

<script type="text/javascript">

function printThisDocument(){
	
	
	window.print();
}


</script>
</head>


<body>
<div class="container">
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<jsp:include page="header.jsp" />
<jsp:include page="navigation.jsp" />
<div class='mainbody'>
   <input type="hidden" name="pageId" value="LOAN_SUMMARY" id="PAGE_ID">
   <input type="hidden" name="memberId"  value=""              id="MEMBER_ID">
   <div class="form-wrap">
      <jsp:include page="searchBar.jsp" />
      <jsp:include page="topPanel.jsp" />
      <div calss="" style="width: 100%; height: auto; margin: 0 auto;">
         <fieldset>
            <legend>
               <h4>Loan Sanctioned Details</h4>
            </legend>
            <div id="searchDetails1"></div>
         </fieldset>
         <fieldset>
            <legend>
               <h4>Loan Recovery Details</h4>
            </legend>
            <div id="searchDetails2"></div>
         </fieldset>
         <table border="0">
            <tr>
               <td align="center"><input type="button" onclick="printThisDocument()" name="Print" value="Print"></td>
            </tr>
         </table>
      </div>
   </div>
</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>