package manager;

import dao.CurriculumDaoImpl;
import dao.QuestionDaoImpl;
import obj.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="doModifyQuestion", urlPatterns={"/teacher/doModifyQuestion"})
public class doModifyQuestion extends HttpServlet
{
    private static CurriculumDaoImpl currDao=new CurriculumDaoImpl();
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String mode=request.getParameter("mode");
        request.setAttribute("currs",currDao.findAll());
        if(mode.equals("del"))
        {
            QDao.delQuestion(Integer.parseInt(request.getParameter("qid")));
            response.sendRedirect(request.getHeader("Referer"));
            User user=(User)request.getSession().getAttribute("user");
            int cnt=QDao.findByTeacherCnt(user.getUsername());
            request.getSession().setAttribute("viewNum",cnt);
            return;
        }
        if(mode.equals("update"))
            request.setAttribute("question",QDao.findById(Integer.parseInt(request.getParameter("qid"))));
        request.setAttribute("mode",mode);
        request.getRequestDispatcher("/teacher/modifyQuestion.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
