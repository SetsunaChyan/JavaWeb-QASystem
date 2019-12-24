package dao;

import obj.Teach;

import java.util.ArrayList;

public interface TeachDao extends Dao
{
    boolean addTeach(Teach teach);

    ArrayList<Teach> findByTeacherName(String te_name);

    ArrayList<Teach> findByCurrName(String cur_name);

    ArrayList<Teach> findAll();

    boolean delTeach(Teach teach);
}
