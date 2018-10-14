//js 读取文件

function createXMLHttpRequest() {       //万能模板

    var XMLHttpReq;

    if (window.XMLHttpRequest) {   // 是Mozilla浏览器

        XMLHttpReq = new XMLHttpRequest();
    } else if (window.ActiveXObject()) { // IE浏览器
        try {
            XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP"); //因为ie也会出现不兼容所以抛异常
        } catch (e) {
            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");  //微软的，记住就好
        }
    }
    return XMLHttpReq;
}

function create() {
    $(function jsReadFiles() {
        var xmlReq = createXMLHttpRequest();
        //2.打开和服务器端的连接
        xmlReq.open("GET", "NewsResponse", true);//有GTE和POST方法，中间代表连接路径，true代表异步
        //3.发送数据
        xmlReq.send(null); //因为采用的是get方法，所以方法为null
        //4.接收服务器的响应
        xmlReq.onreadystatechange = function () {
            if (xmlReq.readyState == 4) {//判断对象状态是否完成
                if (xmlReq.status == 200) {     //信息已经成功返回
                    var xmltext = xmlReq.responseText;  //得到AjaxServlet往外输出的数据
                    alert("data-" + xmltext);  //输出结果：data-connection ok
                    var obj = JSON.parse(xmltext);
                    for (var i = 0; i < obj.length; i++) {
                        var str = "<div>" + obj[i].date + "</div>" + "<div>" + obj[i].title + "</div>"
                            + "<div>" + obj[i].news_information + "</div>";
                        $("#ok").append(str);
                    }
                }
            }
        }
    })
}

window.onload = create;