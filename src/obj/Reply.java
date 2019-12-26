package obj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply implements Serializable
{
    private static final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int qid, rid;
    private String picPath, username, context, timestamp;

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

    public int getRid()
    {
        return rid;
    }

    public void setRid(int rid)
    {
        this.rid=rid;
    }

    public String getPicPath()
    {
        return picPath;
    }

    public void setPicPath(String picPath)
    {
        this.picPath=picPath;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context=context;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp=timestamp;
    }
}
