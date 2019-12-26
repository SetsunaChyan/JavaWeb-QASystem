package manager;

import dao.BanDaoImpl;
import dao.CurriculumDaoImpl;
import dao.QuestionDaoImpl;
import obj.Ban;
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

@WebServlet(name="doAddQuestion", urlPatterns={"/student/doAddQuestion"})
public class doAddQuestion extends HttpServlet
{
    private static CurriculumDaoImpl currDao=new CurriculumDaoImpl();
    private static QuestionDaoImpl QDao=new QuestionDaoImpl();
    private static BanDaoImpl BanDao=new BanDaoImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        User user=(User)request.getSession().getAttribute("user");
        String mode=request.getParameter("mode");
        ArrayList<Curriculum> currs=currDao.findAll();
        ArrayList<Curriculum> ret=new ArrayList<>();
        ArrayList<Ban> BanArr=BanDao.findByStudent(user.getUsername());
        for(Curriculum cur: currs)
        {
            boolean flag=true;
            for(Ban ban: BanArr)
                if(ban.getCur_name().equals(cur.getName()))
                {
                    flag=false;
                    break;
                }
            if(flag)
                ret.add(cur);
        }
        request.setAttribute("currs",ret);
        if(mode.equals("del"))
        {
            QDao.delQuestion(Integer.parseInt(request.getParameter("qid")));
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }
        if(mode.equals("update"))
            request.setAttribute("question",QDao.findById(Integer.parseInt(request.getParameter("qid"))));
        request.setAttribute("mode",mode);
        request.getRequestDispatcher("/student/addQuestion.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
