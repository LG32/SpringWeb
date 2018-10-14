package servlet;

import dao.DataBase;
import util.WebTool;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String master_id = request.getParameter("master_id");
        String master_password = request.getParameter("master_password");

        if(master_id != null && master_password != null){
            if (judgment(master_id, master_password)){
                WebTool webTool = new WebTool();
                DataBase db = new DataBase();
                webTool.jsonFW(db.printDataBase_textArea(master_id));
                request.getRequestDispatcher("html/TestHtml.html").forward(request,response);
            }
            else {
                request.getRequestDispatcher("html/error.html").forward(request,response);
            }
        }

    }

    private boolean judgment(String master_id, String master_password) {
        DataBase db = new DataBase();
        ArrayList<String> list =  db.printDataBase_userif(master_id);
        return master_password.equals(list.get(2));
    }

    /**
     * java中的js引擎加载js语句，功能不完整，废弃！
     * @throws ScriptException
     */
    private void testScriptInterFace() throws ScriptException{
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");
        String script = "var obj = new Object(); obj.run = function(){print('buttontest')}";
        engine.eval(script);
        Object obj=engine.get("obj");//获取js中对象
        Invocable inv=(Invocable)engine;
        Runnable r=inv.getInterface(obj,Runnable.class);
        Thread t=new Thread(r);
        t.start();
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
