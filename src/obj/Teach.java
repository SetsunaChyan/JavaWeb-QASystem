package obj;

import java.io.Serializable;

public class Teach implements Serializable
{
    private String te_name, cur_name;

    public String getCur_name()
    {
        return cur_name;
    }

    public String getTe_name()
    {
        return te_name;
    }

    public void setCur_name(String cur_name)
    {
        this.cur_name=cur_name;
    }

    public void setTe_name(String te_name)
    {
        this.te_name=te_name;
    }
}
