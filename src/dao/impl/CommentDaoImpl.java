package dao.impl;

import dao.CommentDao;
import entity.Comment;
import entity.CommentDto;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDaoImpl implements CommentDao {
    @Override
    public ArrayList<CommentDto> selectAllComments() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        ArrayList<CommentDto> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select comment.id,o_id,content,s_id,comment_date,`name` from comment left join staff s on comment.s_id = s.id");
            rs = ps.executeQuery();
            while (rs.next()){
                CommentDto commentDto = new CommentDto();
                commentDto.setId(rs.getInt("id"));
                commentDto.setoId(rs.getInt("o_id"));
                commentDto.setContent(rs.getString("content"));
                commentDto.setsId(rs.getInt("s_id"));
                commentDto.setCommentDate(rs.getString("comment_date"));
                commentDto.setName(rs.getString("name"));
                list.add(commentDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,ps, conn);
        }
        return list;
    }

    @Override
    public void addComment(Comment comment) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("insert into webcss.comment (o_id, content, s_id, comment_date)" +
                    "values (?,?,?,?);");
            ps.setInt(1,comment.getoId());
            ps.setString(2,comment.getContent());
            ps.setInt(3,comment.getsId());
            ps.setString(4,comment.getCommentDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
    }

    @Override
    public void deleteCommentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("delete from comment where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps, conn);
        }
    }

    @Override
    public CommentDto selectCommentNameById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        CommentDto commentDto = new CommentDto();
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select comment.id,o_id,content,s_id,comment_date,`name` from comment " +
                    "left join staff s on comment.s_id = s.id where comment.id=?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                commentDto.setId(rs.getInt("id"));
                commentDto.setoId(rs.getInt("o_id"));
                commentDto.setContent(rs.getString("content"));
                commentDto.setsId(rs.getInt("s_id"));
                commentDto.setCommentDate(rs.getString("comment_date"));
                commentDto.setName(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,ps, conn);
        }
        return commentDto;
    }
}
