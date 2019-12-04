package dao;

import obj.Curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurriculumDaoImpl implements CurriculumDao
{
    public boolean addCurriculum(Curriculum cur)
    {
        String sql="insert into curriculum values(?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,cur.getName());
            pstmt.setString(2,cur.getInf());
            pstmt.setString(3,cur.getDept());
            int cnt=pstmt.executeUpdate();
            return cnt!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Curriculum> findByTeacher(String teacher)
    {
        ArrayList<Curriculum> arr=new ArrayList<>();
        String sql="select * from curriculum,teach where curriculum.cur_name=teach.cur_name and te_name=?;";
        Curriculum tmp=new Curriculum();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp.setName(ret.getString("cur_name"));
                    tmp.setInf(ret.getString("inf"));
                    tmp.setDept(ret.getString("dept_name"));
                    arr.add(tmp);
                }
            }
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Curriculum> findByDept(String dept_name)
    {
        ArrayList<Curriculum> arr=new ArrayList<>();
        String sql="select * from curriculum where dept_name=?;";
        Curriculum tmp=new Curriculum();
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,dept_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp.setName(ret.getString("cur_name"));
                    tmp.setInf(ret.getString("inf"));
                    tmp.setDept(ret.getString("dept_name"));
                    arr.add(tmp);
                }
            }
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delCurriculum(String cur_name)
    {
        String sql1="delete from teach where cur_name=?;";
        String sql2="delete from curriculum where cur_name=?;";
        try(Connection conn=getConnection())
        {
            PreparedStatement pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1,cur_name);
            pstmt.executeUpdate();
            pstmt=conn.prepareStatement(sql2);
            pstmt.setString(1,cur_name);
            return pstmt.executeUpdate()!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifyCurriculum(Curriculum cur)
    {
        String sql="update curriculum set inf=?,dept_name=? where cur_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,cur.getInf());
            pstmt.setString(2,cur.getDept());
            pstmt.setString(3,cur.getName());
            return pstmt.executeUpdate()!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}