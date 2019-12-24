package manager;

import dao.UserDaoImpl;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="doLogin", urlPatterns={"/login/doLogin"})
public class doLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String t_password=request.getParameter("password");
        String t_username=request.getParameter("username");
        UserDaoImpl dao=new UserDaoImpl();
        User user=dao.findByName(t_username);
        HttpSession session=request.getSession();
        session.setAttribute("user",user);
        if(user!=null&&user.getPassword().equals(t_password))
        {
            if(user.getUsertype().equals("admin"))
                response.sendRedirect(request.getContextPath()+"/admin/dashboard.jsp");
            else
                response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else
            response.sendRedirect(request.getContextPath()+"/login/loginPage.jsp");
    }
}
