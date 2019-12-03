package dao;

import obj.Department;

public interface DepartmentDao extends Dao
{
    boolean addDept(Department dept);

    Department findByName(String dept_name);

    boolean modifyDept(Department dept);

    boolean delDept(String dept_name);
}
