/**
 * 
 */

function addCheckName(){
	var name = document.getElementById("addCourseName").value;
	var alertN = document.getElementById("addNameAlert");
	
	if(name !== "" && (name.trim()) !== ""){
		alertN.innerHTML == "";
		return true;
	} else {
		alertN.innerHTML = "课程名不能为空！";
		return false;
	}
}

function addCheckDesc(){
	var desc = document.getElementById("addCourseDescription").value;
	var alertD = document.getElementById("addDescAlert");
	
	if(desc !== "" && (desc.trim()) !== ""){
		alertD.innerHTML == "";
		return true;
	} else {
		alertD.innerHTML = "课程简介不能为空！";
		return false;
	}
}

function addCheckImage(){
	var img = document.getElementById("addCourseImage").value;
	var alertI = document.getElementById("addImageAlert");
	
	if(img == ""){
		alertI.innerHTML = "请选择一张课程封面！";
		return false;
	} else {
		alertI.innerHTML == "";
		return true;
	}
}

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}