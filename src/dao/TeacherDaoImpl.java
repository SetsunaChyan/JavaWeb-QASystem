package dao;

import obj.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDaoImpl implements TeacherDao
{
    public boolean addTeacher(Teacher teacher)
    {
        String sql="insert into teacher values(?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher.getName());
            pstmt.setString(2,teacher.getInf());
            pstmt.setString(3,teacher.getTitle());
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

    public Teacher findByName(String name)
    {
        String sql="select * from teacher where te_name=?;";
        Teacher teacher=new Teacher();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                if(ret.next())
                {
                    teacher.setName(name);
                    teacher.setTitle(ret.getString("title"));
                    teacher.setInf(ret.getString("inf"));
                }
            }
            conn.close();
            return teacher;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Teacher> findAll()
    {
        Teacher tmp;
        ArrayList<Teacher> arr=new ArrayList<>();
        String sql="select * from teacher;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Teacher();
                    tmp.setName(ret.getString("te_name"));
                    tmp.setTitle(ret.getString("title"));
                    tmp.setInf(ret.getString("inf"));
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

    public boolean delTeacher(String name)
    {
        String sql="delete from teacher where te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,name);
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

    public boolean modifyTeacher(Teacher teacher)
    {
        String sql="update teacher set title=?,inf=? where te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher.getTitle());
            pstmt.setString(2,teacher.getInf());
            pstmt.setString(3,teacher.getName());
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
