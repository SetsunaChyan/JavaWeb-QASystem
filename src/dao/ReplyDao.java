package dao;

import obj.Question;
import obj.Reply;

import java.util.ArrayList;

public interface ReplyDao extends Dao
{
    boolean addReply(Reply reply);

    ArrayList<Reply> findByQuestion(int qid);

    ArrayList<Reply> findByUsername(String username);

    Reply findById(int rid);

    ArrayList<Reply> findAll();

    boolean delReply(int rid);

    boolean updateReply(Reply reply);

    int findMxId();
}
