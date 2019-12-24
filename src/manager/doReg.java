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

@WebServlet(name="doReg", urlPatterns={"/login/doReg"})
public class doReg extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=new User();
        UserDaoImpl dao=new UserDaoImpl();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("pwd"));
        user.setUsertype("student");
        System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getUsertype());
        if(dao.addUser(user))
            response.sendRedirect("doLogin?username="+user.getUsername()+"&password="+user.getPassword());
        else
            response.sendRedirect(request.getContextPath()+"/login/regPage.jsp");
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
