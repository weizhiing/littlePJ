/**
 * 
 */

	    function checkRegisterUser(){
	    	var regName = /(^([a-zA-Z]+)$)|(^[0-9]*$)/;
	    	var user = document.getElementById("userName").value.trim();
	    	var alertU = document.getElementById("registerUserAlert");
	    	
	    	if(user == "" || (user.trim()) == ""){
	    		alertU.innerHTML = "用户名不能为空！";
	    		return false;
	    	} else if(user.length < 6 || (regName.test(user))){
	    		alertU.innerHTML = "用户名格式错误！";
	    		return false;
	    	} else{
	    		alertU.innerHTML = "";
	    		return true;
	    	}
	    }
	    
	    function checkRegisterPsd(){
	    	var regPsd = /^[0-9]*$/;
	    	var psd = document.getElementById("password").value;
	    	var alertP = document.getElementById("registerPsdAlert");
	    	var rePsdInput = document.getElementById("repassword");
	    	rePsdInput.setAttribute("disabled", "true");
	    	
	    	if(psd == "" || (psd.trim()) == ""){
	    		alertP.innerHTML = "密码不能为空！";
	    		return false;
	    	} else if(psd.length < 6 || regPsd.test(psd)){
	    		alertP.innerHTML = "密码格式错误！";
	    		return false;
	    	} else if(psd == document.getElementById("userName").value){
	    		alertP.innerHTML = "密码不能与用户名相同！";
	    		return false;
	    	} else{
	    		alertP.innerHTML = "";
	    		rePsdInput.removeAttribute("disabled");
	    		return true;
	    	}
	    }
	    
	    function checkRegisterRePsd(){
	    	var rePsd = document.getElementById("repassword").value;
	    	var alertR = document.getElementById("registerRePsdAlert");
	    	
	    	if(rePsd == "" || (rePsd.trim()) == ""){
	    		alertR.innerHTML = "密码不能为空！";
	    		return false;
	    	} else if(rePsd !== document.getElementById("password").value){
	    		alertR.innerHTML = "两次密码不一致！";
	    		return false;
	    	} else{
	    		alertR.innerHTML = "";
	    		return true;
	    	}
	    } 
	    
	    function checkRegisterEmail(){
	    	var regEmail = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
	    	var email = document.getElementById("email").value;
	    	var alertE = document.getElementById("registerEmailAlert");
	    	
	    	if(email == "" || (email.trim()) == ""){
	    		alertE.innerHTML = "邮箱不能为空！";
	    		return false;
	    	} else if(!regEmail.test(email)){
	    		alertE.innerHTML = "邮箱格式错误！";
	    		return false;
	    	} else {
	    		alertE.innerHTML = "";
	    		return true;
	    	}
	    }
	    
	    function changeValue(obj){
			obj.value = "after";
			//obj.innerHTML = "已发送";
		}
		
//		function changeSubmit(){
//			if(document.getElementById("getCode") == "before"){
//				return (checkRegisterUser() && checkRegisterPsd() && checkRegisterRePsd() && checkRegisterEmail());
//			} else if(document.getElementById("getCode") == "after"){
//				return (checkRegisterUser() && checkRegisterPsd() && checkRegisterRePsd() && checkRegisterEmail() && checkRegisterCode());
//			}
//		}
//		
		function checkRegisterCode(){
			var code = document.getElementById("emailCode").value;
			var alertC = document.getElementById("registerCodeAlert");
			var rightCode = document.getElementById("rightCode").value;
			
			if(code == "" || (code.trim()) == ""){
				alertC.innerHTML = "验证码不能为空！";
				return false;
			} else if(code !== rightCode.trim()){
				alertC.innerHTML = "验证码错误！";
				return false;
			} else{
				alertC.innerHTML = "";
				return true;
			}
		}
	    
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}