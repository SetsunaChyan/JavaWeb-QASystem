package dao;

import obj.Curriculum;
import obj.Teacher;

import java.util.ArrayList;

public interface TeacherDao extends Dao
{
    boolean addTeacher(Teacher teacher);

    Teacher findByName(String name);

    ArrayList<Teacher> findAll();

    boolean delTeacher(String name);

    boolean modifyTeacher(Teacher teacher);
}
