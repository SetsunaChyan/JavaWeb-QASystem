package manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="goIMG", urlPatterns={"/index/goIMG"})
public class goIMG extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String picPath=request.getParameter("picPath");
        request.setAttribute("picPath",picPath);
        request.getRequestDispatcher("/index/showIMG.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
