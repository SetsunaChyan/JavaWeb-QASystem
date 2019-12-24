package manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="doLogout", urlPatterns={"/login/doLogout"})
public class doLogout extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        session.invalidate();
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
