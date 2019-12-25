package dao;

import obj.Question;
import obj.Teach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDaoImpl implements QuestionDao
{
    public boolean addQuestion(Question question)
    {
        int num=findMxId()+1;
        String sql="insert into question values(?,?,?,?,?,?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,num);
            pstmt.setString(2,question.getCur_name());
            pstmt.setString(3,question.getUsername());
            pstmt.setString(4,question.getTimestamp());
            pstmt.setString(5,question.getContext());
            pstmt.setString(6,question.getPicPath());
            pstmt.setInt(7,question.getSolved());
            pstmt.setString(8,question.getTitle());
            int cnt=pstmt.executeUpdate();
            conn.close();
            return cnt!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Question> findByContext(String context)
    {

        return null;
    }

    public ArrayList<Question> findByCur(String cur_name)
    {
        ArrayList<Question> arr=new ArrayList<>();
        Question tmp;
        String sql="select * from question inner join curriculum on question.cur_name = curriculum.cur_name where question.cur_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,cur_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Question();
                    tmp.setQid(ret.getInt("qid"));
                    tmp.setCur_name(ret.getString("cur_name"));
                    tmp.setUsername(ret.getString("username"));
                    tmp.setTimestamp(ret.getString("timestamp"));
                    tmp.setContext(ret.getString("context"));
                    tmp.setPicPath(ret.getString("picPath"));
                    tmp.setSolved(ret.getInt("solved"));
                    tmp.setTitle(ret.getString("title"));
                    arr.add(tmp);
                }
            }
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Question> findByTeacher(String te_name)
    {
        ArrayList<Question> arr=new ArrayList<>();
        Question tmp;
        String sql="with T as ( select * from question inner join curriculum c on question.cur_name = c.cur_name ) "+"select * from T inner join teach on T.cur_name=teach.cur_name where te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,te_name);
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Question();
                    tmp.setQid(ret.getInt("qid"));
                    tmp.setCur_name(ret.getString("cur_name"));
                    tmp.setUsername(ret.getString("username"));
                    tmp.setTimestamp(ret.getString("timestamp"));
                    tmp.setContext(ret.getString("context"));
                    tmp.setPicPath(ret.getString("picPath"));
                    tmp.setSolved(ret.getInt("solved"));
                    tmp.setTitle(ret.getString("title"));
                    arr.add(tmp);
                }
            }
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Question> findAll()
    {
        ArrayList<Question> arr=new ArrayList<>();
        Question tmp;
        String sql="select * from question;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            try(ResultSet ret=pstmt.executeQuery())
            {
                while(ret.next())
                {
                    tmp=new Question();
                    tmp.setQid(ret.getInt("qid"));
                    tmp.setCur_name(ret.getString("cur_name"));
                    tmp.setUsername(ret.getString("username"));
                    tmp.setTimestamp(ret.getString("timestamp"));
                    tmp.setContext(ret.getString("context"));
                    tmp.setPicPath(ret.getString("picPath"));
                    tmp.setSolved(ret.getInt("solved"));
                    tmp.setTitle(ret.getString("title"));
                    arr.add(tmp);
                }
            }
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delQuestion(int qid)
    {
        String sql="delete from question where qid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,qid);
            int cnt=pstmt.executeUpdate();
            conn.close();
            return cnt!=0;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuestion(Question question)
    {
        return false;
    }

    public int findMxId()
    {
        String sql="select max(qid) as mx from question;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            ResultSet ret=pstmt.executeQuery();
            int mx=0;
            if(ret.next())
                mx=ret.getInt("mx");
            conn.close();
            return mx;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
