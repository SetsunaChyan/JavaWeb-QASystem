package dao;

import obj.User;

import java.util.ArrayList;

public interface UserDao extends Dao
{
    boolean addUser(User user);

    User findByName(String username);

    ArrayList<User> findByCurriculum(String cur_name);

    ArrayList<User> findByUsertype(String usertype);

    boolean modifyUser(User user);

    boolean delUser(String username);
}