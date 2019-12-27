package dao;

import obj.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReplyDaoImpl implements ReplyDao
{

    public boolean addReply(Reply reply)
    {
        int num=findMxId()+1;
        String sql="insert into reply values(?,?,?,?,?,?);";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,reply.getQid());
            pstmt.setInt(2,num);
            pstmt.setString(3,reply.getPicPath());
            pstmt.setString(4,reply.getUsername());
            pstmt.setString(5,reply.getContext());
            pstmt.setString(6,reply.getTimestamp());
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

    public ArrayList<Reply> findByQuestion(int qid)
    {
        ArrayList<Reply> arr=new ArrayList<>();
        Reply tmp;
        String sql="select * from reply where qid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,qid);
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
            {
                tmp=new Reply();
                tmp.setQid(ret.getInt("qid"));
                tmp.setRid(ret.getInt("rid"));
                tmp.setUsername(ret.getString("username"));
                tmp.setTimestamp(ret.getString("timestamp"));
                tmp.setContext(ret.getString("context"));
                tmp.setPicPath(ret.getString("picPath"));
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

    public ArrayList<Reply> findByUsername(String username)
    {
        ArrayList<Reply> arr=new ArrayList<>();
        Reply tmp;
        String sql="select * from reply where username=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,username);
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
            {
                tmp=new Reply();
                tmp.setQid(ret.getInt("qid"));
                tmp.setRid(ret.getInt("rid"));
                tmp.setUsername(ret.getString("username"));
                tmp.setTimestamp(ret.getString("timestamp"));
                tmp.setContext(ret.getString("context"));
                tmp.setPicPath(ret.getString("picPath"));
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

    public Reply findById(int rid)
    {
        Reply tmp=null;
        String sql="select * from reply where rid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,rid);
            ResultSet ret=pstmt.executeQuery();
            if(ret.next())
            {
                tmp=new Reply();
                tmp.setQid(ret.getInt("qid"));
                tmp.setRid(ret.getInt("rid"));
                tmp.setUsername(ret.getString("username"));
                tmp.setTimestamp(ret.getString("timestamp"));
                tmp.setContext(ret.getString("context"));
                tmp.setPicPath(ret.getString("picPath"));
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

    public ArrayList<Reply> findAll()
    {
        ArrayList<Reply> arr=new ArrayList<>();
        Reply tmp;
        String sql="select * from reply;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            ResultSet ret=pstmt.executeQuery();
            while(ret.next())
            {
                tmp=new Reply();
                tmp.setQid(ret.getInt("qid"));
                tmp.setRid(ret.getInt("rid"));
                tmp.setUsername(ret.getString("username"));
                tmp.setTimestamp(ret.getString("timestamp"));
                tmp.setContext(ret.getString("context"));
                tmp.setPicPath(ret.getString("picPath"));
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

    public boolean delReply(int rid)
    {
        String sql="delete from reply where rid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setInt(1,rid);
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

    public boolean updateReply(Reply reply)
    {
        String sql="update reply set picPath=?,context=? where rid=?;";
        try(Connection conn=getConnection();PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,reply.getPicPath());
            pstmt.setString(2,reply.getContext());
            pstmt.setInt(3,reply.getRid());
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

    public int findMxId()
    {
        String sql="select max(qid) as mx from reply;";
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
