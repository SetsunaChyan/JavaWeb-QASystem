package manager;

import dao.CurriculumDaoImpl;
import dao.DepartmentDao;
import dao.DepartmentDaoImpl;
import dao.TeacherDaoImpl;
import obj.Curriculum;
import obj.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="goIndex", urlPatterns={"/index/goIndex"})
public class goIndex extends HttpServlet
{
    private static TeacherDaoImpl teacherDao=new TeacherDaoImpl();
    private static DepartmentDaoImpl deptDao=new DepartmentDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("teachers",teacherDao.findAll());
        request.setAttribute("depts",deptDao.findByName("*"));
        request.getRequestDispatcher("/index/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
