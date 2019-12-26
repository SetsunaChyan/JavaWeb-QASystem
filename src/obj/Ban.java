package obj;

import java.io.Serializable;

public class Ban implements Serializable
{
    private String cur_name, u_name;

    public String getCur_name()
    {
        return cur_name;
    }

    public void setCur_name(String cur_name)
    {
        this.cur_name=cur_name;
    }

    public String getU_name()
    {
        return u_name;
    }

    public void setU_name(String u_name)
    {
        this.u_name=u_name;
    }
}
