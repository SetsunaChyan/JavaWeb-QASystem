package manager;

import dao.*;
import obj.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="doBanUpdate", urlPatterns={"/teacher/doBanUpdate"})
public class doBanUpdate extends HttpServlet
{
    private static BanDaoImpl BanDao=new BanDaoImpl();

    private boolean delBan(String cur_name,String u_name)
    {
        Ban ban=new Ban();
        ban.setCur_name(cur_name);
        ban.setU_name(u_name);
        return BanDao.delBan(ban);
    }

    private boolean addBan(String cur_name,String u_name)
    {
        Ban ban=new Ban();
        ban.setCur_name(cur_name);
        ban.setU_name(u_name);
        return BanDao.addBan(ban);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        String op=request.getParameter("op");
        boolean ret=false;
        if(op.equals("del"))
            ret=delBan(request.getParameter("cur_name"),request.getParameter("u_name"));
        else if(op.equals("add"))
            ret=addBan(request.getParameter("cur_name"),request.getParameter("u_name"));
        if(ret)
            response.getWriter().write("{\"success\":true}");
        else
            response.getWriter().write("{\"success\":false}");
    }
}
