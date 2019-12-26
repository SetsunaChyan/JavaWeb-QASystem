package manager;

import dao.CurriculumDaoImpl;
import dao.DepartmentDaoImpl;
import dao.TeacherDaoImpl;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="goTeacherIndex", urlPatterns={"/teacher/goTeacherIndex"})
public class goTeacherIndex extends HttpServlet
{
    private static CurriculumDaoImpl currDao=new CurriculumDaoImpl();
    private static TeacherDaoImpl teacherDao=new TeacherDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=(User)request.getSession().getAttribute("user");
        String name=user.getUsername();
        request.setAttribute("data",teacherDao.findByName(name));
        request.setAttribute("currs",currDao.findByTeacher(name));
        request.getRequestDispatcher("/teacher/teacherIndex.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
