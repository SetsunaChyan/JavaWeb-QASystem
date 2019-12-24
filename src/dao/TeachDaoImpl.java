package dao;

import obj.Teach;
import obj.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeachDaoImpl implements TeachDao
{

    public boolean addTeach(Teach teach)
    {
        String sql="insert into teacher values(?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teach.getTe_name());
            pstmt.setString(2,teach.getCur_name());
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

    public ArrayList<Teach> findByTeacherName(String te_name)
    {
        Teach tmp;
        ArrayList<Teach> arr=new ArrayList<>();
        String sql="select * from teach where te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,te_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Teach();
                    tmp.setTe_name(ret.getString("te_name"));
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

    public ArrayList<Teach> findByCurrName(String cur_name)
    {
        Teach tmp;
        ArrayList<Teach> arr=new ArrayList<>();
        String sql="select * from teach where cur_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,cur_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Teach();
                    tmp.setTe_name(ret.getString("te_name"));
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

    public ArrayList<Teach> findAll()
    {
        Teach tmp;
        ArrayList<Teach> arr=new ArrayList<>();
        String sql="select * from teach;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Teach();
                    tmp.setTe_name(ret.getString("te_name"));
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

    public boolean delTeach(Teach teach)
    {
        String sql="delete from teach where te_name=? and cur_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teach.getTe_name());
            pstmt.setString(2,teach.getCur_name());
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
