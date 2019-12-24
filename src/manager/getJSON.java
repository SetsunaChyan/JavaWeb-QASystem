package manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CurriculumDaoImpl;
import dao.DepartmentDaoImpl;
import dao.TeacherDaoImpl;
import dao.UserDaoImpl;
import obj.Curriculum;
import obj.Department;
import obj.Teacher;
import obj.User;
import org.json.JSONObject;

@WebServlet(name="getJSON", urlPatterns={"/getJSON"})
public class getJSON extends HttpServlet
{
    private static HashMap<String,String> hm;
    private static DepartmentDaoImpl DepartmentDao=new DepartmentDaoImpl();
    private static UserDaoImpl UserDao=new UserDaoImpl();
    private static TeacherDaoImpl TeacherDao=new TeacherDaoImpl();
    private static CurriculumDaoImpl CurrDao=new CurriculumDaoImpl();

    private void getDepartmentJSON(JSONObject json)
    {
        ArrayList<Department> arr=DepartmentDao.findByName("*");
        json.put("count",arr.size());
        for(Department dept: arr)
        {
            hm=new HashMap<>();
            hm.put("dept_name",dept.getDept());
            hm.put("inf",dept.getInf());
            json.append("data",new JSONObject(hm));
        }
    }

    private void getCurriculumJSON(JSONObject json)
    {
        ArrayList<Curriculum> arr=CurrDao.findAll();
        json.put("count",arr.size());
        for(Curriculum curr: arr)
        {
            hm=new HashMap<>();
            hm.put("name",curr.getName());
            hm.put("inf",curr.getInf());
            hm.put("dept",curr.getDept());
            json.append("data",new JSONObject(hm));
        }
    }

    private void getTeacherJSON(JSONObject json)
    {
        ArrayList<Teacher> arr=TeacherDao.findAll();
        json.put("count",arr.size());
        for(Teacher teacher: arr)
        {
            hm=new HashMap<>();
            hm.put("name",teacher.getName());
            hm.put("title",teacher.getTitle());
            hm.put("inf",teacher.getInf());
            json.append("data",new JSONObject(hm));
        }
    }

    private void getStudentJSON(JSONObject json)
    {
        ArrayList<User> arr=UserDao.findByUsertype("student");
        json.put("count",arr.size());
        for(User user: arr)
        {
            hm=new HashMap<>();
            hm.put("name",user.getUsername());
            json.append("data",new JSONObject(hm));
        }
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String datatype=request.getParameter("datatype");
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        if(datatype.equals("department"))
            getDepartmentJSON(json);
        else if(datatype.equals("curr"))
            getCurriculumJSON(json);
        else if(datatype.equals("teacher"))
            getTeacherJSON(json);
        else if(datatype.equals("student"))
            getStudentJSON(json);
        //System.out.println(json);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}