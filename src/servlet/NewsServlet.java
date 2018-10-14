package servlet;

import dao.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        JSONArray ja = new JSONArray();

        String news_tit = new String(request.getParameter("news_tit").getBytes("iso-8859-1"), "utf-8");
        String news_text = new String(request.getParameter("news_text").getBytes("iso-8859-1"), "utf-8");

        System.out.println(news_tit);
        System.out.println(news_text);

//        JSONObject jo = new JSONObject();
//        jo.put("news_tit", news_tit);
//        jo.put("news_text", news_text);
//        ja.add(jo);

//            WebTool webTool = new WebTool();
//            webTool.jsonNF(ja.toString());
        DataBase db = new DataBase();
        db.setNewsIf(news_tit, news_text);
        request.getRequestDispatcher("TempNews.html").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
