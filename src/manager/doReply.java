package manager;

import dao.CurriculumDaoImpl;
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

@WebServlet(name="doReply", urlPatterns={"/teacher/doReply"})
public class doReply extends HttpServlet
{
    private static ReplyDaoImpl ReplyDao=new ReplyDaoImpl();
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String mode=request.getParameter("mode");
        request.setAttribute("mode",mode);
        if(mode.equals("add"))
        {
            Question question=QDao.findById(Integer.parseInt(request.getParameter("qid")));
            request.setAttribute("qid",question.getQid());
            request.setAttribute("title",question.getTitle());
            request.getRequestDispatcher("/teacher/teacherAddReply.jsp").forward(request,response);
            return;
        }
        Reply reply=ReplyDao.findById(Integer.parseInt(request.getParameter("rid")));
        Question question=QDao.findById(reply.getQid());
        if(mode.equals("del"))
        {
            ReplyDao.delReply(reply.getRid());
            if(ReplyDao.findByQuestion(question.getQid()).size()==0)
            {
                question.setSolved(0);
                QDao.updateQuestion(question);
                User user=(User)request.getSession().getAttribute("user");
                int cnt=QDao.findByTeacherCnt(user.getUsername());
                request.getSession().setAttribute("viewNum",cnt);
            }
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }
        if(mode.equals("update"))
        {
            request.setAttribute("qid",question.getQid());
            request.setAttribute("title",question.getTitle());
            request.setAttribute("reply",reply);
        }
        request.getRequestDispatcher("/teacher/teacherAddReply.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
