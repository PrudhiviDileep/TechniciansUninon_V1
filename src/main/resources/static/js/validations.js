





function registrationValidation(){
	var result=true;
	console.log("registrationValidation called");
	
	var memberName=("#memberNameId").val();
	var fatherName=("#fatherNameId").val();
	var dateOfBirth=("#dateOfBirthId").val();
	var eduQualification=("#eduQualificationId").val();
	var motherTongue=("#motherTongueId").val();
	var knownLang=("#knownLangId").val();
	var bloodGroup=("#bloodGroupId").val();
	var fefsiNumber=("#workDetails_fefsiNumberId").val();
	var dateOfJoining=("#workDetails_dateOfJoiningId").val();
	var workDetails_natureOfWork=("#workDetails_natureOfWorkId").val();
	var workDetails_nameOfCompany=("#workDetails_nameOfCompanyId").val();
	var recommand1_name=("#recommand1_nameId").val();
	var recommand1_cardNo=("#recommand1_cardNoId").val();
	var recommand1_deptId=("#recommand1_deptIdId").val();
	var recommand1_deptNameId=("#recommand1_deptNameId").val();
//	var recommand1_placeOfWork=("#recommand1_placeOfWorkId").val();
	var recommand2_name=("#recommand2_nameId").val();
	var recommand2_cardNo=("#recommand2_cardNoId").val();
	var recommand2_deptId=("#recommand2_deptIdId").val();
	var recommand2_deptNameId=("#recommand2_deptNameId").val();
//	var recommand2_placeOfWork=("#recommand2_placeOfWorkId").val();
	var nomineeDetails_name=("#nomineeDetails_nameId").val();
	var nomineeDetails_relation=("#nomineeDetails_relationId").val();
	var presentAddress=("#presentAddressId").val();
	var perminentAddress=("#perminentAddressId").val();
	var approvedDetails_cardNo=("#approvedDetails_cardNoId").val();
	var approvedDetails_deptName=("#approvedDetails_deptNameId").val();
	var approvedDetails_cardAmount=("#approvedDetails_cardAmountId").val();
	var approvedDetails_paidAmmount=("#approvedDetails_paidAmmountId").val();
	
	if((memberName==='' || memberName===null)){
		result= false;
		
	}else if((fatherName==='' || fatherName===null)){
		result= false;
		
	}else if((dateOfBirth==='' || dateOfBirth===null)){
		result= false;
		
	}else if((eduQualification==='' || eduQualification===null)){
		result= false;
		
	}else if((motherTongue==='' || motherTongue===null)){
		result= false;
		
	}else if((knownLang==='' || knownLang===null)){
		result= false;
		
	}else if((bloodGroup==='' || bloodGroup===null)){
		result= false;
		
	}else if((fefsiNumber==='' || fefsiNumber===null)){
		result= false;
		
	}else if((dateOfJoining==='' || dateOfJoining===null)){
		result= false;
		
	}else if((workDetails_nameOfCompany==='' || workDetails_nameOfCompany===null)){
		result= false;
		
	}else if((recommand1_name==='' || recommand1_name===null)){
		result= false;
		
	}else if((recommand1_cardNo==='' || recommand1_cardNo===null)){
		result= false;
		
	}else if((recommand1_deptId==='' || recommand1_deptId===null)){
		//result= false;
		
	}else if((recommand1_deptName==='' || recommand1_deptName===null)){
		result= false;
		
	}else if((recommand2_name==='' || recommand2_name===null)){
		result= false;
		
	}else if((recommand2_cardNo==='' || recommand2_cardNo===null)){
		result= false;
		
	}else if((recommand2_deptId==='' || recommand2_deptId===null)){
		//result= false;
		
	}else if((recommand2_deptName==='' || recommand2_deptName===null)){
		result= false;
		
	}else if((nomineeDetails_name==='' || nomineeDetails_name===null)){
		result= false;
		
	}else if((nomineeDetails_relation==='' || nomineeDetails_relation===null)){
		result= false;
		
	}else if((presentAddress==='' || presentAddress===null)){
		result= false;
		
	}else if((perminentAddress==='' || perminentAddress===null)){
		result= false;
		
	}else if((approvedDetails_cardNo==='' || approvedDetails_cardNo===null)){
		result= false;
		
	}else if((approvedDetails_deptName==='' || approvedDetails_deptName===null)){
		result= false;
		
	}else if((approvedDetails_cardAmount==='' || approvedDetails_cardAmount===null)){
		result= false;
		
	}else if((approvedDetails_paidAmmount==='' || approvedDetails_paidAmmount===null)){
		result= false;
		
	}
	
	
	return result;
	
}

/*LOGIN VALIDATION*/

function loginFormValidation(){	
	
	var username=$("#userNameId").val();
	var password=$("#passwordId").val();	
	
	if((username==='' || username===null)){
		console.log("Please Enter UserNmae !");
		return false;
		
	}else if((password===''|| password===null)){
		console.log("Please Enter Password !");
		return false;
		
	}else  if((username!=='' && username!==null)&& (password!=='' &&password!==null)){		
		
		return true;
		
	}else {	return false;
		
	}


}