package dao;

import obj.Curriculum;

public interface CurriculumDao extends Dao
{
    boolean addCurriculum(Curriculum cur);

    Curriculum findByTeacher(String teacher);

    Curriculum findByDept(String dept_name);

    boolean delCurriculum(String cur_name);

    boolean modifyCurriculum(Curriculum cur);
}
