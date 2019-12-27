package dao;

import obj.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

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

    private void add2arr(Connection conn,String sql,String context,ArrayList<Question> set) throws SQLException
    {
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,sql);
        pstmt.setString(1,context);
        ResultSet ret=pstmt.executeQuery();
        Question tmp;
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
            set.add(tmp);
        }
    }

    public ArrayList<Question> findByContext(String context)
    {
        ArrayList<Question> arr=new ArrayList<>();
        ArrayList<Question> ret=new ArrayList<>();
        String sql_cur="select * from question where question.cur_name like ?;";
        String sql_dept="select * from question inner join curriculum on question.cur_name = curriculum.cur_name where curriculum.dept_name like ?;";
        String sql_title="select * from question where title like ?;";
        String sql_con="select * from question where context like ?;";
        String sql_te="select * from question inner join teach on question.cur_name = teach.cur_name where teach.te_name=?;";
        String sql_ue="select * from question where username like ?;";
        context="%"+context+"%";
        try
        {
            Connection conn=getConnection();
            add2arr(conn,sql_cur,context,arr);
            add2arr(conn,sql_dept,context,arr);
            add2arr(conn,sql_title,context,arr);
            add2arr(conn,sql_con,context,arr);
            add2arr(conn,sql_te,context,arr);
            add2arr(conn,sql_ue,context,arr);
            Collections.sort(arr);
            for(Question question: arr)
            {
                if(ret.isEmpty()||ret.get(ret.size()-1).getQid()!=question.getQid())
                    ret.add(question);
            }
            conn.close();
            return ret;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
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
        String sql="select * from question inner join teach on question.cur_name=teach.cur_name where te_name=?;";
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

    public ArrayList<Question> findByUsername(String username)
    {
        ArrayList<Question> arr=new ArrayList<>();
        Question tmp;
        String sql="select * from question where username=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,username);
            ResultSet ret=pstmt.executeQuery();
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
            conn.close();
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Question findById(int qid)
    {
        Question tmp=new Question();
        String sql="select * from question where qid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,qid);
            ResultSet ret=pstmt.executeQuery();
            if(ret.next())
            {
                tmp.setQid(ret.getInt("qid"));
                tmp.setCur_name(ret.getString("cur_name"));
                tmp.setUsername(ret.getString("username"));
                tmp.setTimestamp(ret.getString("timestamp"));
                tmp.setContext(ret.getString("context"));
                tmp.setPicPath(ret.getString("picPath"));
                tmp.setSolved(ret.getInt("solved"));
                tmp.setTitle(ret.getString("title"));
            }
            conn.close();
            return tmp;
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
        String sql="update question set cur_name=?,timestamp=?,context=?,picPath=?,solved=?,title=? where qid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,question.getCur_name());
            pstmt.setString(2,question.getTimestamp());
            pstmt.setString(3,question.getContext());
            pstmt.setString(4,question.getPicPath());
            pstmt.setInt(5,question.getSolved());
            pstmt.setString(6,question.getTitle());
            pstmt.setInt(7,question.getQid());
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

    public int findByStudentCnt(String stu_name)
    {
        int cnt=0;
        String sql="select solved from question where username=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,stu_name);
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
                cnt+=ret.getInt("solved");
            conn.close();
            return cnt;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int findByTeacherCnt(String te_name)
    {
        int cnt=0;
        String sql="select solved from question inner join teach on question.cur_name=teach.cur_name where te_name=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,te_name);
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
                cnt+=1-ret.getInt("solved");
            conn.close();
            return cnt;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
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
