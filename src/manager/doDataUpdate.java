package manager;

import dao.*;
import obj.Curriculum;
import obj.Department;
import obj.Teacher;
import obj.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="doDataUpdate", urlPatterns={"/admin/doDataUpdate"})
public class doDataUpdate extends HttpServlet
{
    private static DepartmentDaoImpl DepartmentDao=new DepartmentDaoImpl();
    private static UserDaoImpl UserDao=new UserDaoImpl();
    private static CurriculumDaoImpl CurrDao=new CurriculumDaoImpl();
    private static TeacherDaoImpl TeacherDao=new TeacherDaoImpl();

    private boolean updateDepartment(String dept_name,String inf)
    {
        Department dept=new Department();
        dept.setDept(dept_name);
        dept.setInf(inf);
        return DepartmentDao.modifyDept(dept);
    }

    private boolean deleteDepartment(String dept_name)
    {
        return DepartmentDao.delDept(dept_name);
    }

    private boolean addDepartment(String dept_name,String inf)
    {
        Department dept=new Department();
        dept.setDept(dept_name);
        dept.setInf(inf);
        return DepartmentDao.addDept(dept);
    }

    private boolean updateCurriculum(String curr_name,String dept_name,String inf)
    {
        Curriculum curr=new Curriculum();
        curr.setDept(dept_name);
        curr.setName(curr_name);
        curr.setInf(inf);
        return CurrDao.modifyCurriculum(curr);
    }

    private boolean deleteCurriculum(String curr_name)
    {
        return CurrDao.delCurriculum(curr_name);
    }

    private boolean addCurriculum(String curr_name,String dept_name,String inf)
    {
        Curriculum curr=new Curriculum();
        curr.setDept(dept_name);
        curr.setName(curr_name);
        curr.setInf(inf);
        return CurrDao.addCurriculum(curr);
    }

    private boolean updateTeacher(String te_name,String title,String inf)
    {
        Teacher teacher=new Teacher();
        teacher.setName(te_name);
        teacher.setTitle(title);
        teacher.setInf(inf);
        return TeacherDao.modifyTeacher(teacher);
    }

    private boolean delTeacher(String te_name)
    {
        return TeacherDao.delTeacher(te_name)&&UserDao.delUser(te_name);
    }

    private boolean addTeacher(String te_name,String title,String inf)
    {
        Teacher teacher=new Teacher();
        User user=new User();
        user.setUsername(te_name);
        user.setUsertype("teacher");
        user.setPassword("123456");
        teacher.setName(te_name);
        teacher.setTitle(title);
        teacher.setInf(inf);
        if(UserDao.addUser(user))
        {
            if(TeacherDao.addTeacher(teacher))
                return true;
            UserDao.delUser(te_name);
            return false;
        }
        return false;
    }

    private boolean delStudent(String u_name)
    {
        return UserDao.delUser(u_name);
    }

    private boolean addStudent(String u_name,String password)
    {
        User user=new User();
        user.setUsername(u_name);
        user.setUsertype("student");
        user.setPassword("123456");
        return UserDao.addUser(user);
    }

    private boolean resetPassword(String u_name,String u_type)
    {
        User user=new User();
        user.setUsername(u_name);
        user.setUsertype(u_type);
        user.setPassword("123456");
        return UserDao.modifyUser(user);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        String op=request.getParameter("op");
        String type=request.getParameter("type");
        boolean ret=false;
        switch(type)
        {
            case "department":
                if(op.equals("update"))
                    ret=updateDepartment(request.getParameter("dept_name"),request.getParameter("inf"));
                else if(op.equals("del"))
                    ret=deleteDepartment(request.getParameter("dept_name"));
                else if(op.equals("add"))
                    ret=addDepartment(request.getParameter("dept_name"),request.getParameter("inf"));
                break;
            case "curr":
                if(op.equals("update"))
                    ret=updateCurriculum(request.getParameter("name"),request.getParameter("dept"),request.getParameter("inf"));
                else if(op.equals("del"))
                    ret=deleteCurriculum(request.getParameter("name"));
                else if(op.equals("add"))
                    ret=addCurriculum(request.getParameter("name"),request.getParameter("dept"),request.getParameter("inf"));
                break;
            case "teacher":
                if(op.equals("update"))
                    ret=updateTeacher(request.getParameter("name"),request.getParameter("title"),request.getParameter("inf"));
                else if(op.equals("del"))
                    ret=delTeacher(request.getParameter("name"));
                else if(op.equals("add"))
                    ret=addTeacher(request.getParameter("name"),request.getParameter("title"),request.getParameter("inf"));
                else if(op.equals("reset"))
                    ret=resetPassword(request.getParameter("name"),"teacher");
                break;
            case "student":
                if(op.equals("del"))
                    ret=delStudent(request.getParameter("name"));
                else if(op.equals("add"))
                    ret=addStudent(request.getParameter("name"),request.getParameter("password"));
                else if(op.equals("reset"))
                    ret=resetPassword(request.getParameter("name"),"student");
                break;
        }
        if(ret)
            response.getWriter().write("{\"success\":true}");
        else
            response.getWriter().write("{\"success\":false}");
    }
}
