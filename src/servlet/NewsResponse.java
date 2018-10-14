package servlet;

import dao.DataBase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NewsResponse extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DataBase db = new DataBase();
        String messages = db.getNewsIf();

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(messages);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
