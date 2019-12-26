package manager;

import dao.QuestionDaoImpl;
import dao.ReplyDaoImpl;
import obj.Question;
import obj.Reply;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="doSubmitReply", urlPatterns={"/teacher/doSubmitReply"})
public class doSubmitReply extends HttpServlet
{
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();
    private static ReplyDaoImpl ReplyDao=new ReplyDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=(User)request.getSession().getAttribute("user");
        String mode=request.getParameter("mode");
        Reply reply=new Reply();
        int rid=0;
        if(request.getParameter("rid")!=null)
            rid=Integer.parseInt(request.getParameter("rid"));
        reply.setQid(Integer.parseInt(request.getParameter("qid")));
        reply.setContext(request.getParameter("context"));
        reply.setNowTime2TimeStamp();
        reply.setUsername(user.getUsername());
        String picPath=new String();
        if(request.getParameter("picPath")!=null)
            picPath=request.getParameter("picPath");
        picPath=picPath.replaceAll("\\\\","/");
        reply.setPicPath(picPath);
        boolean ret=false;
        if(mode.equals("add"))
        {
            ret=ReplyDao.addReply(reply);
            if(ret)
            {
                Question question=QDao.findById(reply.getQid());
                question.setSolved(1);
                QDao.updateQuestion(question);
                int cnt=QDao.findByTeacherCnt(user.getUsername());
                request.getSession().setAttribute("viewNum",cnt);
            }
        }
        else if(mode.equals("update"))
            ReplyDao.updateReply(reply);
        request.getRequestDispatcher("/teacher/goTeacherIndex").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
