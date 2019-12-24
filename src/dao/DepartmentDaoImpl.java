package dao;

import obj.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDaoImpl implements DepartmentDao
{
    public boolean addDept(Department dept)
    {
        String sql="insert into department values(?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,dept.getDept());
            pstmt.setString(2,dept.getInf());
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

    public ArrayList<Department> findByName(String dept_name)
    {
        ArrayList<Department> arr=new ArrayList<>();
        String sql;
        Department dept;
        try
        {
            Connection conn=getConnection();
            PreparedStatement pstmt;
            if(dept_name==null||dept_name.equals("*"))
            {
                sql="select * from department;";
                pstmt=conn.prepareStatement(sql);
            }
            else
            {
                sql="select * from department where dept_name=?;";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,dept_name);
            }
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
            {
                dept=new Department();
                dept.setDept(ret.getString("dept_name"));
                dept.setInf(ret.getString("inf"));
                arr.add(dept);
            }
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return arr;
    }

    public boolean modifyDept(Department dept)
    {
        String sql="update Department set inf=? where dept_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,dept.getInf());
            pstmt.setString(2,dept.getDept());
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

    public boolean delDept(String dept_name)
    {
        String sql="delete from department where dept_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,dept_name);
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
