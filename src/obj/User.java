package obj;

import java.io.Serializable;

public class User implements Serializable
{
    private String username, password, usertype;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setUsertype(String usertype)
    {
        this.usertype = usertype;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getUsertype()
    {
        return usertype;
    }

    public String getPassword()
    {
        return password;
    }
}
