// JavaScript Document

$(document).ready(function () {
	var password;
	var repeat;
	$("#password").bind("input propertychange", function () {
		password = $("#password").val();
	});
	$("#repeat").bind("input propertychange", function () {
		repeat = $("#repeat").val();
		if(repeat !== null && repeat !== ""){
			if(repeat !== password){
				$("#misPassword").text("*密码不相同");
			}else{
				$("#misPassword").text("");
			}
		}
	});
	
});

function checkMan() {
	document.getElementById("isMan").checked = true;
	document.getElementById("isGirl").checked = false;
	// $("#isMan").val("1");
	// $("#isGirl").val("0");
	$("#isMan").value("1");
	$("#isGirl").value("0");

}

function checkGirl() {
	document.getElementById("isMan").checked = false;
	document.getElementById("isGirl").checked = true;
    // $("#isMan").val("0");
    // $("#isGirl").val("1");
    $("#isMan").value("0");
    $("#isGirl").value("1");
}
