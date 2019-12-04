package dao;

import obj.Curriculum;

import java.util.ArrayList;

public interface CurriculumDao extends Dao
{
    boolean addCurriculum(Curriculum cur);

    ArrayList<Curriculum> findByTeacher(String teacher);

    ArrayList<Curriculum> findByDept(String dept_name);

    boolean delCurriculum(String cur_name);

    boolean modifyCurriculum(Curriculum cur);
}
