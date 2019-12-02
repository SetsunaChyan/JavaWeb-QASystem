package obj;

import java.io.Serializable;

public class Department implements Serializable
{
    String inf, dept;

    public String getInf()
    {
        return inf;
    }

    public String getDept()
    {
        return dept;
    }

    public void setInf(String inf)
    {
        this.inf = inf;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }
}
