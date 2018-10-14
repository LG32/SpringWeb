package servlet;

import dao.DataBase;
import service.Userif;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet{

    public SignUpServlet(){
        super();
        System.out.println("signup");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nickname = request.getParameter("name");
        String password = request.getParameter("password");
        String interest = request.getParameter("interest");
        String isMan = request.getParameter("isMan");
        String isGirl = request.getParameter("isGirl");
        String sex ;

        //sex 0,1,2. 为男，女，
        if(isMan == "1"){
            sex = "0";
        }else if (isGirl == "1"){
            sex = "1";
        }else {
            sex = "2";
            System.out.println("sex选项异常");
        }

        Userif userif = new Userif();
        userif.setNickname(nickname);
        userif.setPassword(password);
        userif.setInterest(interest);
        userif.setSex(sex);

        System.out.println("sex is " + sex);

        DataBase db = new DataBase();
        db.signUp_user(userif);

        request.getRequestDispatcher("TestHtml.html").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("doPost");
        doGet(request, response);
    }
}
