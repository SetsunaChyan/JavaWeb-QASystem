package manager;

import dao.QuestionDaoImpl;
import dao.ReplyDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="showTeacherQuestion", urlPatterns={"/teacher/showQuestion"})
public class showTeacherQuestion extends HttpServlet
{
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();
    private static ReplyDaoImpl ReplyDao=new ReplyDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        int qid=Integer.parseInt(request.getParameter("qid"));
        request.setAttribute("question",QDao.findById(qid));
        request.setAttribute("replies",ReplyDao.findByQuestion(qid));
        request.getRequestDispatcher("/teacher/teacherReply.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
