package dao;

import obj.Department;

public interface DepartmentDao extends Dao
{
    public boolean addDept(Department dept);

    public Department findByName(String dept_name);

    public boolean modifyDept(Department dept);

    public boolean delDept(String dept_name);
}
