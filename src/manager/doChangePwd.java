package manager;

import dao.UserDaoImpl;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="doChangePwd", urlPatterns={"/login/doChangePwd"})
public class doChangePwd extends HttpServlet
{
    private static UserDaoImpl UserDao=new UserDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=(User)request.getAttribute("user");
        if(user==null)
        {
            request.getRequestDispatcher("/login/loginPage.jsp").forward(request,response);
            return;
        }
        if(!user.getPassword().equals(request.getParameter("oldPsw")))
        {
            request.getRequestDispatcher("/login/changePwd.jsp").forward(request,response);
            return;
        }
        user.setPassword(request.getParameter("psw"));
        UserDao.modifyUser(user);
        if(user.getUsertype().equals("teacher"))
            request.getRequestDispatcher("/teacher/goTeacherIndex").forward(request,response);
        else if(user.getUsertype().equals("student"))
            request.getRequestDispatcher("/index/goIndex").forward(request,response);
        else
            request.getRequestDispatcher("/admin/dashboard.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
