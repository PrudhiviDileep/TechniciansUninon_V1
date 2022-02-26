<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Registerd Detials</title>


</head>

<body>

	<div class="container">
		<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
			<input type="hidden" name="membrId" value="${MEMBER_ID}"
				id="memberId"> <input type="hidden" name='memberdetl'
				value='${MEMBER_DETAILS}' id="memberDetailsData"> <input
				type="hidden" name="pageId" value="VIEW_MEMBER_DETAILS" id="PAGE_ID">

			<H1>REGISTERED MEMBER DETIALS</H1>

			<div class='form-wrap'>
				<form method="POST" enctype="multipart/form-data"
					id='registrationFormId'>

					<input type="hidden" id="pageId" value="MEMBER_REGISTRATION"
						id="PAGE_ID"> <input type="hidden" name="memberId"
						value="" id="MEMBER_ID"> <input type="hidden" value=""
						name="PAYMENT_CONF_ID" id="PAYMENT_CONF_ID_id"> <input
						type="hidden" value="" name="MEMBERSHIP_AMOUNT"
						id="MEMBERSHIP_AMOUNT_id"> <input type="hidden" value=""
						name="ADMIN_AMOUNT" id="ADMIN_AMOUNT_id"> <input
						type="hidden" value="" name="SUBSCRIPTION_AMOUNT"
						id="SUBSCRIPTION_AMOUNT_id"> <input type="hidden" value=""
						name="DONATION_AMOUNT" id="DONATION_AMOUNT_id"> <input
						type='hidden' value="" name='approvedDetails_deptId'
						id='approvedDetails_deptIdId' class='texBoxCss'>
					<!-- <form action="/registration" method="POST"  enctype="multipart/form-data" onsubmit="return registrationValidation()" id='registrationFormId'>
				 -->

					<div style="width: 50%; height: auto; float: left;">
						<div class='dtails-half-box'>
							<h2>Personal Details</h2>
							<table>

								<tr>
									<td><div class="required">
											<label>Name</label>
										</div></td>
									<td><input type='text' name='memberName' id='memberNameId'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>

								</tr>
								<tr>
									<td><div class="required">
											<label>Father's Name</label>
										</div></td>
									<td><input type='text' name='fatherName' id='fatherNameId'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>Date of Birth</label>
										</div></td>
									<td><input type='text' name='dateOfBirth'
										id='dateOfBirthId' class='texBoxCss form-control datepicker'
										readonly="readonly" disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>Department</label>
										</div></td>
									<td><div class="select-style ">
											<select name='deptName' id='deptNameId' class='texBoxCss'
												onchange='recommondGetCardNumbers()' disabled="disabled">${DEPARTMENTS}</select>
										</div></td>
								</tr>
							</table>
						</div>
						<div class='dtails-half-box'>
							<h2>Educational Details</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Educational Qualification</label>
										</div></td>
									<td><input type='text' name='eduQualification'
										id='eduQualificationId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Mother Tongue</label>
										</div></td>
									<td><input type='text' id='motherTongueId'
										name='motherTongue' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Languages Known</label>
										</div></td>
									<td><input type='text' name='knownLang' id='knownLangId'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Blood Group</label>
										</div></td>
									<td><input type='text' name='bloodGroup' id='bloodGroupId'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>
								</tr>

							</table>

						</div>
						<div class='dtails-half-box'>


							<h2>Recommondation1</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Department name</label>
										</div></td>
									<td><div class="select-style ">
											<select name='recommand1_deptName' id='recommand1_deptNameId'
												class='texBoxCss' disabled="disabled">${DEPARTMENTS}</select>
										</div> <input type='hidden' name='recommand1_deptId'
										id='recommand1_deptIdId' class='texBoxCss'></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Card No</label>
										</div></td>
									<td><input type='text' name='recommand1_cardNo'
										id='recommand1_cardNoId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>



								<tr>
									<td><div class="required">
											<label>Name</label>
										</div></td>
									<td><input type='text' name='recommand1_name'
										id='recommand1_nameId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>


								<tr>
									<td>Place of work(Unit)</td>
									<td><div class="select-style ">
											<select name='recommand1_placeOfWork'
												id='recommand1_placeOfWorkId' class='texBoxCss'
												disabled="disabled">${UNITS}</select>
										</div> <input type='hidden' name='recommand1_unitId'
										id='recommand1_unitIdId' class='texBoxCss'></td>
								</tr>
							</table>
						</div>
						<div class='dtails-half-box'>
							<h2>Approved Details</h2>

							<table>


								<!-- <tr>
								<td><div class="required"><label>Department name</label></div></td>
								<td><select  name='approvedDetails_deptName' id='approvedDetails_deptNameId' class='texBoxCss'>${DEPARTMENTS}</select>
								
								</td>
							</tr>
							 -->


								<tr>
									<td>Place of work(Unit)</td>
									<td><div class="select-style ">
											<select name='approvedDetails_placeOfWork'
												id='approvedDetails_placeOfWorkId' class='texBoxCss'
												disabled="disabled">${UNITS}</select>
										</div> <input type='hidden' name='approvedDetails_unitId'
										id='approvedDetails_unitIdId' class='texBoxCss' value="">

									</td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Card No</label>
										</div></td>
									<td><input type='text' name='approvedDetails_cardNo'
										id='approvedDetails_cardNoId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Card Amount</label>
										</div></td>
									<td><input type='text' name='approvedDetails_cardAmount'
										id='approvedDetails_cardAmountId' class='texBoxCss' value=""
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Amount</label>
										</div></td>
									<td><input type='text' name='approvedDetails_paidAmmount'
										id='approvedDetails_paidAmmountId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Receipt No</label>
										</div></td>
									<td><input type='text' name='approvedDetails_receiptNo'
										id='approvedDetails_receiptId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Applied Date</label>
										</div></td>
									<td><input type='text' name='approvedDetails_appliedDate'
										id='approvedDetails_appliedDateId'
										class='texBoxCss form-control datepicker' readonly="readonly"
										disabled="disabled"></td>
								</tr>
							</table>
						</div>

						<div class='dtails-half-box'>
							<h2>Bank Details</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Bank Name</label>
										</div></td>
									<td><input type='text' name='bankDetails_bankName'
										id='bankDetails_bankNameId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Acc Holder Name</label>
										</div></td>
									<td><input type='text' name='bankDetails_accHolderName'
										id='bankDetails_accHolderNameId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>Account No</label>
										</div></td>
									<td><input type='text' name='bankDetails_accNumber'
										id='bankDetails_accNumberId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>Branch</label>
										</div></td>
									<td><input type='text' name='bankDetails_branch'
										id='bankDetails_branchId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>IFSC</label>
										</div></td>
									<td><input type='text' name='bankDetails_ifsc'
										id='bankDetails_ifscId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>



							</table>
						</div>
					</div>
					<div style="width: 50%; height: auto; float: left;">

						<div class='dtails-half-box'>
							<h2>Profile Picture</h2>
							<div class='defaultProfilePic' id='profilePicImg'>
								<img alt="" src="/images/default-profile-pic.png"
									id='profilePicShowCase'>



							</div>

							<div style='width: 110px; height: 35px; margin: 0 auto;'>
								<div class='inputFileWrapDiv'>
									Choose File <input type="file" id="img" name="file"
										class='inputFile' />
								</div>
							</div>
						</div>






						<div class='dtails-half-box'>
							<h2>Work Details</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>FEFSI Member</label>
										</div></td>
									<td><input type='text' name='workDetails_fefsiNumber'
										id='workDetails_fefsiNumberId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Date of Joining</label>
										</div></td>
									<td><input type='text' name='workDetails_dateOfJoining'
										id='workDetails_dateOfJoiningId'
										class='texBoxCss form-control datepicker' readonly="readonly"
										disabled="disabled"></td>
								</tr>
								<tr>
								<td>Nature of Work</td>
								<td><div class="select-style "><input type='hidden' name='workDetails_deptName' id='workDetails_deptNameId' value=''>
								<select  name='workDetails_deptNameSelect' id='workDetails_deptNameIdSelect' class='texBoxCss'>${DEPARTMENTS}</select></div>
								</div>
								<!-- <input type='text' name='workDetails_natureOfWork' id='workDetails_natureOfWorkId' class='texBoxCss'> -->
								
								</td>
							</tr>
							<tr>
								<td><div class="required"><label>Name of Company</label></div></td>
								<td><div class="select-style "><input type='hidden' name='workDetails_placeOfWork' id='workDetails_placeOfWorkId' value=''>
								<select name='workDetails_placeOfWorkSelect' id='workDetails_placeOfWorkIdSelect' class='texBoxCss'>${UNITS}</select></td>
								<!-- <input type='text' name='workDetails_nameOfCompany' id='workDetails_nameOfCompanyId' class='texBoxCss'> -->
							</tr>
							</table>

						</div>








						<div class='dtails-half-box'>
							<h2>Recommondation2</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Department name</label>
										</div></td>
									<td><div class="select-style ">
											<select name='recommand2_deptName' id='recommand2_deptNameId'
												class='texBoxCss' disabled="disabled">${DEPARTMENTS}</select>
										</div> <input type='hidden' name='recommand2_deptId'
										id='recommand2_deptIdId'></td>
									</td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Card No</label>
										</div></td>
									<td><input type='text' name='recommand2_cardNo'
										id='recommand2_cardNoId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>

								<tr>
									<td><div class="required">
											<label>Name</label>
										</div></td>
									<td><input type='text' name='recommand2_name'
										id='recommand2_nameId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>

								<tr>
									<td>Place of work(Unit)</td>
									<td><div class="select-style ">
											<select name='recommand2_placeOfWork'
												id='recommand2_placeOfWorkId' class='texBoxCss'
												disabled="disabled">${UNITS}</select>
										</div> <input type='hidden' name='recommand2_unitId'
										id='recommand2_unitIdId'></td>
								</tr>
							</table>
						</div>






						<div class='dtails-half-box'>
							<h2>Contact Details</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Present Address</label>
										</div></td>
									<td><textarea rows="4" cols="35" name='presentAddress'
											id='presentAddressId' readonly="readonly"></textarea></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Phone No1</label>
										</div></td>
									<td><input type='text' name='phoneNo1' id='phoneNo1Id'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Perminent Address</label>
										</div></td>
									<td><textarea rows="4" cols="35" name='perminentAddress'
											id='perminentAddressId' readonly="readonly"></textarea></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Phone No2</label>
										</div></td>
									<td><input type='text' name='phoneNo2' id='phoneNo2Id'
										class='texBoxCss' readonly="readonly" disabled="disabled"></td>
								</tr>

							</table>
						</div>


						<div class='dtails-half-box'>
							<h2>Nominee Details</h2>
							<table>
								<tr>
									<td><div class="required">
											<label>Name</label>
										</div></td>
									<td><input type='text' name='nomineeDetails_name'
										id='nomineeDetails_nameId' class='texBoxCss'
										readonly="readonly" disabled="disabled"></td>
								</tr>
								<tr>
									<td><div class="required">
											<label>Relationship</label>
										</div></td>
									<td>

										<div class="select-style ">
											<select name='nomineeDetails_relation'
												id='nomineeDetails_relationId' disabled="disabled">

												<option>Father</option>
												<option>Mother</option>
												<option>Wife</option>
												<option>Husband</option>
												<option>Brother</option>
												<option>Sister</option>



											</select>
										</div>
									</td>
								</tr>

							</table>
						</div>



						<div class='dtails-half-box'>

							<h2>Aadhaar Details</h2>

							<table>
								<tr>
									<td><div class="required">
											<label>Adhar Card No:</label>
										</div></td>
									<td><input type='text' name='aadharCardNo'
										id='aadharCardNoId' class='texBoxCss' readonly="readonly"
										disabled="disabled"></td>
								</tr>
								<tr>
									<td>Upload Adhar Card:</td>
									<td><input type='file' name='file' id='' class='texBoxCss'></td>
								</tr>




							</table>

						</div>
					</div>


				</form>

			</div>


		</div>
		<jsp:include page="footer.jsp" />
	</div>
	<div id='logoutDailog'></div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		var dataObj = $("#memberDetailsData").val();

		obj = JSON.parse(dataObj);
		displayRegisteredMemberDetails(obj.DATA_DETAILS);
	});
</script>
</html>
