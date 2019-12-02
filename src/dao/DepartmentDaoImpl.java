package dao;

import obj.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDaoImpl implements DepartmentDao
{
    public boolean addDept(Department dept)
    {
        String sql = "insert into department value(?,?);";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, dept.getDept());
            pstmt.setString(2, dept.getInf());
            int cnt = pstmt.executeUpdate();
            if (cnt != 0) return true;
            return false;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Department findByName(String dept_name)
    {
        String sql = "select * from department where dept_name=?;";
        Department dept = new Department();
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, dept_name);
            try (ResultSet ret = pstmt.executeQuery())
            {
                if (ret.next())
                {
                    dept.setDept(dept_name);
                    dept.setInf(ret.getString("inf"));
                } else
                    return null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return dept;
    }

    public boolean modifyDept(Department dept)
    {
        String sql = "update Department inf=? where dept_name=?;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, dept.getInf());
            pstmt.setString(2, dept.getDept());
            int cnt = pstmt.executeUpdate();
            if (cnt != 0) return true;
            return false;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delDept(String dept_name)
    {
        String sql = "delete from department where dept_name=?;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, dept_name);
            int cnt = pstmt.executeUpdate();
            if (cnt != 0) return true;
            return false;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
