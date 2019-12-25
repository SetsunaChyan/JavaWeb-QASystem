package dao;

import obj.Curriculum;

import java.util.ArrayList;

public interface CurriculumDao extends Dao
{
    boolean addCurriculum(Curriculum cur);

    ArrayList<Curriculum> findByTeacher(String teacher);

    ArrayList<Curriculum> findByDept(String dept_name);

    Curriculum findByName(String cur_name);

    ArrayList<Curriculum> findAll();

    boolean delCurriculum(String cur_name);

    boolean modifyCurriculum(Curriculum cur);
}
