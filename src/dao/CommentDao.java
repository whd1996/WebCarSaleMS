package dao;

import entity.Comment;
import entity.CommentDto;

import java.util.ArrayList;

public interface CommentDao {

    ArrayList<CommentDto> selectAllComments();

    void addComment(Comment comment);

    void deleteCommentById(int id);

    CommentDto selectCommentNameById(int id);
}
