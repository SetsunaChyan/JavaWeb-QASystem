package manager;

import dao.BanDaoImpl;
import dao.QuestionDaoImpl;
import obj.Question;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="doSubmitQuestion", urlPatterns={"/student/doSubmitQuestion"})
public class doSubmitQuestion extends HttpServlet
{
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();
    private static BanDaoImpl BanDao=new BanDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String mode=request.getParameter("mode");
        Question question=new Question();
        int qid=0;
        if(request.getParameter("qid")!=null)
            qid=Integer.parseInt(request.getParameter("qid"));
        question.setTitle(request.getParameter("title"));
        question.setContext(request.getParameter("context"));
        question.setQid(qid);
        question.setNowTime2TimeStamp();
        question.setCur_name(request.getParameter("curr"));
        User user=(User)request.getSession().getAttribute("user");
        question.setUsername(user.getUsername());
        question.setSolved(0);
        String picPath=new String();
        if(request.getParameter("picPath")!=null)
            picPath=request.getParameter("picPath");
        picPath=picPath.replaceAll("\\\\","/");
        question.setPicPath(picPath);
        boolean ret=false;
        if(mode.equals("add"))
        {
            ret=QDao.addQuestion(question);
            qid=QDao.findMxId();
        }
        else if(mode.equals("update"))
            ret=QDao.updateQuestion(question);
        if(ret)
            request.getRequestDispatcher("/index/showQuestion?qid="+qid).forward(request,response);
        else
            request.getRequestDispatcher("/index/goIndex").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
