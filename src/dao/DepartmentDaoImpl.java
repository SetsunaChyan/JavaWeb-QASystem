package dao;

import obj.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDaoImpl implements DepartmentDao
{
    public boolean addDept(Department dept)
    {
        String sql = "";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            int cnt = pstmt.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Department findByName(String dept_name)
    {
        return null;
    }

    public boolean modifyDept(Department dept)
    {
        return false;
    }

    public boolean delDept(String dept_name)
    {
        return false;
    }
}
