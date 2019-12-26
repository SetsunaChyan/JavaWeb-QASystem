package dao;

import obj.Ban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanDaoImpl implements BanDao
{

    public boolean addBan(Ban ban)
    {
        String sql="insert into ban values(?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,ban.getCur_name());
            pstmt.setString(2,ban.getU_name());
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

    public Ban findOne(Ban ban)
    {
        Ban tmp=null;
        String sql="select * from ban where cur_name=? and username=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,ban.getCur_name());
            pstmt.setString(2,ban.getU_name());
            try(ResultSet ret=pstmt.executeQuery())
            {
                if(ret.next())
                {
                    tmp=new Ban();
                    tmp.setCur_name(ret.getString("cur_name"));
                    tmp.setU_name(ret.getString("username"));
                }
            }
            conn.close();
            return tmp;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ban> findByStudent(String u_name)
    {
        Ban tmp;
        ArrayList<Ban> arr=new ArrayList<>();
        String sql="select * from ban where username=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,u_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Ban();
                    tmp.setU_name(ret.getString("username"));
                    tmp.setCur_name(ret.getString("cur_name"));
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

    public ArrayList<Ban> findByTeacher(String te_name)
    {
        Ban tmp;
        ArrayList<Ban> arr=new ArrayList<>();
        String sql="select * from ban inner join teach on ban.cur_name = teach.cur_name where teach.te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,te_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Ban();
                    tmp.setU_name(ret.getString("username"));
                    tmp.setCur_name(ret.getString("cur_name"));
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

    public ArrayList<Ban> findAll()
    {
        Ban tmp;
        ArrayList<Ban> arr=new ArrayList<>();
        String sql="select * from ban;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Ban();
                    tmp.setU_name(ret.getString("username"));
                    tmp.setCur_name(ret.getString("cur_name"));
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

    public boolean delBan(Ban ban)
    {
        String sql="delete from ban where username=? and cur_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,ban.getU_name());
            pstmt.setString(2,ban.getCur_name());
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
