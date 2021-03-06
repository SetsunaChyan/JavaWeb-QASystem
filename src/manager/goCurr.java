package manager;

import dao.BanDaoImpl;
import dao.CurriculumDaoImpl;
import dao.DepartmentDaoImpl;
import dao.TeacherDaoImpl;
import obj.Ban;
import obj.Curriculum;
import obj.Department;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="goCurr", urlPatterns={"/index/goCurr"})
public class goCurr extends HttpServlet
{
    private static CurriculumDaoImpl currDao=new CurriculumDaoImpl();
    private static TeacherDaoImpl teacherDao=new TeacherDaoImpl();
    private static DepartmentDaoImpl deptDao=new DepartmentDaoImpl();
    private static BanDaoImpl BanDao=new BanDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=(User)request.getSession().getAttribute("user");
        if(user==null)
            request.getRequestDispatcher("/login/loginPage.jsp").forward(request,response);
        String pageType=request.getParameter("pageType");
        String name=request.getParameter("name");
        request.setAttribute("pageType",pageType);
        ArrayList<Curriculum> currs=new ArrayList<Curriculum>();
        ArrayList<Curriculum> ret=new ArrayList<Curriculum>();
        if(pageType.equals("dept"))
        {
            ArrayList<Department> tmp=deptDao.findByName(name);
            if(!tmp.isEmpty())
                request.setAttribute("data",tmp.get(0));
            currs=currDao.findByDept(name);
        }
        else if(pageType.equals("teacher"))
        {
            request.setAttribute("data",teacherDao.findByName(name));
            currs=currDao.findByTeacher(name);
        }
        ArrayList<Ban> BanArr=BanDao.findByStudent(user.getUsername());
        for(Curriculum cur: currs)
        {
            boolean flag=true;
            for(Ban ban: BanArr)
                if(ban.getCur_name().equals(cur.getName()))
                {
                    flag=false;
                    break;
                }
            if(flag)
                ret.add(cur);
        }
        request.setAttribute("currs",ret);
        request.getRequestDispatcher("/index/showCurrs.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
