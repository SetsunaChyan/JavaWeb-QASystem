package manager;

import dao.CurriculumDaoImpl;
import dao.DepartmentDaoImpl;
import dao.TeacherDaoImpl;
import obj.Department;

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

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String pageType=request.getParameter("pageType");
        String name=request.getParameter("name");
        request.setAttribute("pageType",pageType);
        if(pageType.equals("dept"))
        {
            ArrayList<Department> tmp=deptDao.findByName(name);
            if(!tmp.isEmpty())
                request.setAttribute("data",tmp.get(0));
            request.setAttribute("currs",currDao.findByDept(name));
        }
        else if(pageType.equals("teacher"))
        {
            request.setAttribute("data",teacherDao.findByName(name));
            request.setAttribute("currs",currDao.findByTeacher(name));
        }
        request.getRequestDispatcher("/index/showCurrs.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
