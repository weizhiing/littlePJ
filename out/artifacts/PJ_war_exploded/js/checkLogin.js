/**
 * 
 */
function checkLoginUser(){
			var username = document.getElementById("username").value;
			
			var alertU = document.getElementById("loginUsernameAlert");		
			
			if(username !== "" && (username.trim()) !== ""){	
				//alertU.style.display = "none";
				//if(checkLoginPsd()){
					//return true;
				//} else{
					//return false;
				//}
				return true;
			} else{
				alertU.style.display = "block";
				return false;
			}
		}
		
		function checkLoginPsd(){
			var password = document.getElementById("password").value;
			var alertP = document.getElementById("loginPsdAlert");
			
			if(password !== "" && (password.trim()) !== ""){
				alertP.style.display = "none";
				return true;
			} else{
				alertP.style.display = "block";
				return false;
			}			
		}
		
		function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}