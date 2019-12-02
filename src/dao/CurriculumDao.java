package dao;

import obj.Curriculum;

public interface CurriculumDao extends Dao
{
    public boolean addCurriculum(Curriculum cur);

    public Curriculum findByTeacher(String teacher);

    public Curriculum findByDept(String dept_name);

    public boolean delCurriculum(String cur_name);

    public boolean modifyCurriculum(Curriculum cur);
}
