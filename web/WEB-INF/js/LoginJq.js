// JavaScript Document
$(document).ready(function () {

	$(".title>.navigation>.titleli").click(function () {
		console.log("onclick!");
		console.log(this.id);
		$(".titleli").css({
			"border-bottom": "0px",
			"font-weight": "normal"
		});
		$(this).css({
			"border-bottom": "3px solid #FFFFFF",
			"font-weight": "bold"
		});

		switch (this.id) {
			case "page1":
				console.log("change 1");
				$('#login_page').load("html/LoginPage1.html");
				break;
			case "page2":
				console.log("change 2");
				$('#login_page').load("html/LoginPage2.html");
				break;
		}
	});

	$("#toSignUp").click(function () {
		console.log("toSignUp onClick!");
		alert("Sorry啊! 没救了！");
	});

	$("#sign_btn").click(function () {
		console.log("sign_btn onClick!");
		// $("#login_page").load("html/SignUp.html");
		window.location.href = "html/SignUp.html";
	});
})
