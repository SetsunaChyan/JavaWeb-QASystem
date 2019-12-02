package dao;

import obj.User;

public interface UserDao extends Dao
{
    public boolean addUser(User user);

    public User findByName(String username);

    public boolean modifyUser(User user);

    public boolean delUser(String username);
}