package dao;

import obj.Ban;
import obj.Teach;

import java.util.ArrayList;

public interface BanDao extends Dao
{
    boolean addBan(Ban ban);

    Ban findOne(Ban ban);

    ArrayList<Ban> findByStudent(String u_name);

    ArrayList<Ban> findByTeacher(String te_name);

    ArrayList<Ban> findAll();

    boolean delBan(Ban ban);
}
