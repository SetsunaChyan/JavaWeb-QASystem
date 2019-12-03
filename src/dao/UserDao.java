package dao;

import obj.User;

public interface UserDao extends Dao
{
    boolean addUser(User user);

    User findByName(String username);

    boolean modifyUser(User user);

    boolean delUser(String username);
}