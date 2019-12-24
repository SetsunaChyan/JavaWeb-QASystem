package dao;

import obj.Department;

import java.util.ArrayList;

public interface DepartmentDao extends Dao
{
    boolean addDept(Department dept);

    ArrayList<Department> findByName(String dept_name);

    boolean modifyDept(Department dept);

    boolean delDept(String dept_name);
}
