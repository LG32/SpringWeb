$(document).ready(function () {

	var $BLACKCOVER = $(".BLACKCOVER");
	var $bigShow = $(".bigShow");
	var $bigImg = $bigShow.children("img");
	var nowimg = 0;

	$(".img_list>.img_li>.img_container>.imgstyle").click(function () {
		console.log("点击大图");
		$BLACKCOVER.fadeIn();
		$bigShow.show();

		var URL = $(this).attr("src");
		nowimg = $(this).index();

		console.log(URL);

		$.get(URL, function () {
			$bigImg.attr("src", URL);
			$bigImg.fadeIn();
		});
	});

	$(".titletext>.titleli").click(function () {
		alert("别点了，没做！");
	});

	$(".close, .BLACKCOVER").click(function () {
		$BLACKCOVER.fadeOut();
		$bigShow.hide();
		$bigImg.hide();
	});

	$("#jquery").click(function () {
		$("#jqueryarea").html("<img class=\"imgstyle\" src=\"../images/kanojo9.png\" alt=\"kanojo kawaii\"></img>");
	});

	$(".sort>.navigation>.navili").click(function () {
		var html = "<li class=\"img_li\"><div class=\"img_container\"><img class=\"imgstyle\" src=\"../images/kanojo3.png\" alt=\"kanojo kawaii\"></div></li>";
		var strhtml1 = "<li class=\"img_li\"><div class=\"img_container\"><img class=\"imgstyle\" src=\"";
		var strhtml2 = "\" alt=\"kanojo kawaii\"></div></li>";
		var jsondata = ["JsonData/images_data.json", "JsonData/images_data2.json", "JsonData/images_data3.json"];
		var imagesdata = null;

		$(".navili").css({
			"border-bottom": "0px",
			"font-weight": "normal"
		});
		$(this).css({
			"border-bottom": "3px solid #F3080C",
			"font-weight": "bold"
		});
		switch (this.id) {
			case "li1":
				imagesdata = jsondata[0];
				break;
			case "li2":
				imagesdata = jsondata[1];
				break;
			case "li3":
				imagesdata = jsondata[2];
				break;
		}
		$.getJSON(imagesdata, function (data) {
			$(".img_list").empty();
			$.each(data, function (infoIndex, info) {
				$(".img_list").append(strhtml1 + info["src"] + strhtml2);
				$(".img_list").on("click", function () {
					$(".img_list>.img_li>.img_container>.imgstyle").click(function () {
						console.log("点击大图");
						$BLACKCOVER.fadeIn();
						$bigShow.show();

						var URL = $(this).attr("src");
						nowimg = $(this).index();

						console.log(URL);

						$.get(URL, function () {
							$bigImg.attr("src", URL);
							$bigImg.fadeIn();
						});
					});
				});
			});
		});
		//		$(".img_list").empty();
		//		for(var i = 0; i < 5; i++){
		//			$(".img_list").append(html);
		//			console.log("img_list" + i);
		//		}
	});


});
