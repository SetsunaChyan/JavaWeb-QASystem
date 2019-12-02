package dao;

import obj.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{
    public boolean addUser(User user)
    {
        String sql="insert into user values(?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getUsertype());
            int cnt=pstmt.executeUpdate();
            if(cnt!=0) return true;
            return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public User findByName(String username)
    {
        String sql="select * from user where u_name=?;";
        User user=new User();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,username);
            try(ResultSet ret=pstmt.executeQuery())
            {
                if(ret.next())
                {
                    user.setUsername(ret.getString("u_name"));
                    user.setPassword(ret.getString("u_password"));
                    user.setUsertype(ret.getString("u_usertype"));
                }else return null;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public boolean modifyUser(User user)
    {
        String sql="update users set u_password=?,u_type=? where u_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,user.getPassword());
            pstmt.setString(2,user.getUsertype());
            pstmt.setString(3,user.getUsername());
            int cnt=pstmt.executeUpdate();
            if(cnt!=0) return true;
            else return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delUser(String username)
    {
        String sql="delete from users where u_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,username);
            int cnt=pstmt.executeUpdate();
            if(cnt!=0) return true;
            else return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
