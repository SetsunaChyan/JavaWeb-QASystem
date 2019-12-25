package obj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question implements Serializable
{
    private static final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int qid, solved;
    private String cur_name, title, username, timestamp, context, picPath;

    public void setNowTime2TimeStamp()
    {
        timestamp=df.format(new Date());
    }

    public int getQid()
    {
        return qid;
    }

    public void setQid(int qid)
    {
        this.qid=qid;
    }

    public int getSolved()
    {
        return solved;
    }

    public void setSolved(int solved)
    {
        this.solved=solved;
    }

    public String getCur_name()
    {
        return cur_name;
    }

    public void setCur_name(String cur_name)
    {
        this.cur_name=cur_name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp=timestamp;
    }

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context=context;
    }

    public String getPicPath()
    {
        return picPath;
    }

    public void setPicPath(String picPath)
    {
        this.picPath=picPath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }
}
