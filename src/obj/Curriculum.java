package obj;

import java.io.Serializable;

public class Curriculum implements Serializable
{
    String name, inf, dept;

    public void setDept(String dept)
    {
        this.dept=dept;
    }

    public void setInf(String inf)
    {
        this.inf=inf;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getDept()
    {
        return dept;
    }

    public String getInf()
    {
        return inf;
    }

    public String getName()
    {
        return name;
    }
}
