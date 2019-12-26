package manager;

import dao.CurriculumDaoImpl;
import dao.QuestionDaoImpl;
import obj.Curriculum;
import obj.Question;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="goTeacherQuestions", urlPatterns={"/teacher/goQuestions"})
public class goTeacherQuestions extends HttpServlet
{
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();
    private static CurriculumDaoImpl curDao=new CurriculumDaoImpl();
    private static final int QperPage=3;

    private int min(int x,int y)
    {
        if(x<y)
            return x;
        return y;
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String suffix="?";
        String mode=request.getParameter("mode");
        suffix+="mode="+mode;
        int page=Integer.parseInt(request.getParameter("page"));
        int mxPage=1;
        ArrayList<Question> arr=null;
        ArrayList<Question> ret=new ArrayList<>();
        if(mode.equals("cur"))
        {
            String cur_name=request.getParameter("cur_name");
            suffix+="&cur_name="+cur_name;
            Curriculum cur=curDao.findByName(cur_name);
            arr=QDao.findByCur(cur_name);
            request.setAttribute("cur_name",cur_name);
            request.setAttribute("inf",cur.getInf());
        }
        else if(mode.equals("teacher"))
        {
            User user=(User)request.getSession().getAttribute("user");
            String myName=user.getUsername();
            if(user.getUsertype().equals("teacher"))
                arr=QDao.findByTeacher(myName);
            else if(user.getUsertype().equals("student"))
                arr=QDao.findByUsername(myName);
        }
        if(arr==null)
            arr=new ArrayList<>();
        mxPage=arr.size()/QperPage;
        if(arr.size()%QperPage!=0)
            mxPage++;
        if(page>mxPage)
            page=mxPage;
        if(page<=0)
            page=1;
        for(int i=QperPage*(page-1);i<min(QperPage*page,arr.size());i++)
            ret.add(arr.get(i));
        request.setAttribute("mxPage",mxPage);
        request.setAttribute("page",page);
        request.setAttribute("questions",ret);
        request.setAttribute("suffix",suffix);
        if(mode.equals("cur"))
            request.getRequestDispatcher("/teacher/teacherCurQuestions.jsp?page="+page).forward(request,response);
        else if(mode.equals("teacher"))
            request.getRequestDispatcher("/teacher/teacherQuestions.jsp?page="+page).forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
