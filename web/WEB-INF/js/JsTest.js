function buttontest() {
	alert("buttontest")
};

function g(id) {
	return document.getElementById(id);
}
window.onload = function () {
	var next = g("next");
	var list = g("list");
	var pre = g("pre");
	var buttons = g("buttons").getElementsByTagName("span");
	var len = 4;
	var index = 1;
	var interval = 3000;
	var animated = false;
	var timer; //全局变量

	/*按钮显示*/
	function showButton() {
		for (var i = 0; i < buttons.length; i++) {
			if (buttons[i].className == "on") {
				buttons[i].className = "";
				break;
			}
		}
		buttons[index - 1].className = "on";
	}

	/*图片切换动画*/
	function animate(offset) {
		if (offset == 0) {
			return;
		}
		animated = true;
		var newLeft = parseInt(list.style.left) + offset;
		var time = 300; //位移总时间
		var interval = 10; //位移间隔时间
		var speed = offset / (time / interval); //每次位移量
		function go() {
			if ((speed < 0 && parseInt(list.style.left) > newLeft) || (speed > 0 && parseInt(list.style.left) < newLeft)) {
				list.style.left = parseInt(list.style.left) + speed + 'px';
				setTimeout(go, interval);
			} else {
				list.style.left = newLeft + 'px';
				/*如果当前是第五张图，就跳到第一张图*/
				if (newLeft > -200) {
					list.style.left = -600 * len + 'px';
				}
				if (newLeft < (-600 * len)) {
					list.style.left = '-600px';
				}
				animated = false;
			}
		}
		go();
	}

	next.onclick = function () {
		if (index == 4) {
			index = 1;
		} else {
			index += 1;
		}
		showButton();
		if (!animated) {
			animate(-600);
		}
	};
	pre.onclick = function () {
		if (index == 1) {
			index = 4;
		} else {
			index -= 1;
		}
		showButton();
		if (!animated) {
			animate(600);
		}
	};

	/*按钮切换*/
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].onclick = function () {
			if (animated) {
				return false;
			}
			if (this.className == "on") {
				return false;
			}
			var myIndex = parseInt(this.getAttribute("index"));
			var offset = 600 * (myIndex - index);
			animate(offset);
			index = myIndex;
			showButton();
		};
	}

	/*自动切换*/
	function autoPlay() {
		timer = setInterval(function () {
			next.onclick();
		}, interval);
	}

	function stop() {
		clearInterval(timer);
	}

	g("container").onmouseover = stop;
	g("container").onmouseout = autoPlay;
	autoPlay();
};
