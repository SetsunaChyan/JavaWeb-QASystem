package manager;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name="uploadIMG", urlPatterns={"/index/uploadIMG"})
public class uploadIMG extends HttpServlet
{
    private static final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("text/html;charset=UTF-8");
        String picPath="../img/upload/"+df.format(new Date())+".jpg";
        String realPath=getServletContext().getRealPath("")+"img\\upload\\"+df.format(new Date())+".jpg";
        try(ServletInputStream sis=request.getInputStream())
        {
            OutputStream os=new FileOutputStream(realPath);
            BufferedOutputStream bos=new BufferedOutputStream(os);
            byte[] buf=new byte[1024];
            int length;
            sis.readLine(buf,0,buf.length);
            sis.readLine(buf,0,buf.length);
            sis.readLine(buf,0,buf.length);
            sis.readLine(buf,0,buf.length);
            length=sis.readLine(buf,0,buf.length);
            while(length!=-1)
            {
                bos.write(buf,0,length);
                length=sis.read(buf);
            }
            sis.close();
            bos.close();
            os.close();
        }
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("src",picPath);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
