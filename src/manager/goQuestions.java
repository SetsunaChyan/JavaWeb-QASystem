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

@WebServlet(name="goQuestions", urlPatterns={"/index/goQuestions"})
public class goQuestions extends HttpServlet
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
        String keyword=request.getParameter("keyword");
        if(mode.equals("find"))
            if(keyword==null||keyword.equals(""))
                mode="all";
        suffix+="mode="+mode;
        int page=Integer.parseInt(request.getParameter("page"));
        int mxPage=1;
        ArrayList<Question> arr=null;
        ArrayList<Question> ret=new ArrayList<>();
        if(mode.equals("all"))
            arr=QDao.findAll();
        else if(mode.equals("find"))
        {
            suffix+="&keyword="+keyword;
            arr=QDao.findByContext(keyword);
        }
        else if(mode.equals("cur"))
        {
            String cur_name=request.getParameter("cur_name");
            suffix+="&cur_name="+cur_name;
            Curriculum cur=curDao.findByName(cur_name);
            arr=QDao.findByCur(cur_name);
            request.setAttribute("cur_name",cur_name);
            request.setAttribute("inf",cur.getInf());
        }
        else if(mode.equals("my"))
        {
            User user=(User)request.getSession().getAttribute("user");
            String myName=user.getUsername();
            if(user.getUsertype().equals("teacher"))
                arr=QDao.findByTeacher(myName);
            else if(user.getUsertype().equals("student"))
                arr=QDao.findByUsername(myName);
            System.out.println(user.getUsername());
            System.out.println(arr.size());
            System.out.println(QDao.findByStudentCnt(user.getUsername()));
            request.getSession().setAttribute("viewNum",0);
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
            request.getRequestDispatcher("/index/showQuestions.jsp?page="+page).forward(request,response);
        else if(mode.equals("my"))
            request.getRequestDispatcher("/student/showMyQuestions.jsp?page="+page).forward(request,response);
        else if(mode.equals("all")||mode.equals("find"))
            request.getRequestDispatcher("/index/showAllQuestions.jsp?page="+page).forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
