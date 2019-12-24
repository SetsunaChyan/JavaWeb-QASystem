package obj;

import java.io.Serializable;

public class Teacher implements Serializable
{
    private String name, inf, title;

    public void setName(String name)
    {
        this.name=name;
    }

    public void setInf(String inf)
    {
        this.inf=inf;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getTitle()
    {
        return title;
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
