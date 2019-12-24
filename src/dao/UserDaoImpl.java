package dao;

import obj.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao
{
    public boolean addUser(User user)
    {
        String sql="insert into users values(?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getUsertype());
            int cnt=pstmt.executeUpdate();
            conn.close();
            return cnt!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public User findByName(String username)
    {
        String sql="select * from users where u_name=?;";
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
                    user.setUsertype(ret.getString("u_type"));
                }
                else
                    return null;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public ArrayList<User> findByCurriculum(String cur_name)
    {
        ArrayList<User> arr=new ArrayList<>();
        String sql="select * from users,teach where u_name=te_name and cur_name=?;";
        User tmp=new User();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,cur_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp.setUsername(ret.getString("u_name"));
                    tmp.setUsertype(ret.getString("u_type"));
                    tmp.setPassword(ret.getString("u_password"));
                    arr.add(tmp);
                }
            }
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> findByUsertype(String usertype)
    {
        ArrayList<User> arr=new ArrayList<>();
        String sql="select * from users where u_type=?;";
        User tmp=new User();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,usertype);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new User();
                    tmp.setUsername(ret.getString("u_name"));
                    tmp.setUsertype(ret.getString("u_type"));
                    tmp.setPassword(ret.getString("u_password"));
                    arr.add(tmp);
                }
            }
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
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
            conn.close();
            return cnt!=0;
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
            conn.close();
            return cnt!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
