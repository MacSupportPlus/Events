package com.macsupport.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.macsupport.Models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment>findAll();
    Comment findByComment(String comment);
}