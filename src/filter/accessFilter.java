package filter;

import obj.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName="accessFilter", urlPatterns={"*"})
public class accessFilter implements Filter
{
    private static final Set<String> ALLOWED_PATHS=Set.of("/index","/login","/style","/layui","/img");
    private static final Set<String> ADMIN_PATHS=Set.of("/admin");

    private boolean needLogin(String path)
    {
        boolean ret=false;
        for(String s: ALLOWED_PATHS)
            if(path.length() >= s.length()&&path.substring(0,s.length()).equals(s))
            {
                ret=true;
                break;
            }
        return !ret;
    }

    private boolean needAdminAuthority(String path)
    {
        boolean ret=false;
        for(String s: ADMIN_PATHS)
            if(path.length() >= s.length()&&path.substring(0,s.length()).equals(s))
            {
                ret=true;
                break;
            }
        return ret;
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest req,ServletResponse resp,FilterChain chain) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        User user=(User)request.getSession().getAttribute("user");
        String path=request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$","");
        if(needLogin(path)&&user==null)
        {
            response.sendRedirect(request.getContextPath()+"/login/loginPage.jsp");
            chain.doFilter(req,resp);
            return;
        }
        else if(user!=null&&needAdminAuthority(path))
        {
            if(!user.getUsertype().equals("admin"))
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            chain.doFilter(req,resp);
            return;
        }
        chain.doFilter(req,resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
