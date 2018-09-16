package com.pet.planet.domain.transformer;

import com.pet.planet.api.publication.Comment;
import com.pet.planet.api.publication.request.CreateCommentRequest;

import java.util.List;
import java.util.stream.Collectors;

public class CommentTransformer {


    public static List<Comment> commentToDto(List<com.pet.planet.domain.dao.model.mongo.Comment> mongoComments){
        return mongoComments.stream().map(mongoComment -> {
            Comment comment= new Comment();
            comment.setContent(mongoComment.getComment());
            comment.setUserId(mongoComment.getUserId());
            comment.setCreationDate(mongoComment.getCreationDate());
            comment.setUserName(mongoComment.getFullname());
            return comment;
        }).collect(Collectors.toList());
    }

    public static com.pet.planet.domain.dao.model.mongo.Comment dtoToComment(CreateCommentRequest request){

        com.pet.planet.domain.dao.model.mongo.Comment mongoComment= new com.pet.planet.domain.dao.model.mongo.Comment();
        mongoComment.setCreationDate(request.getCreationDate());
        mongoComment.setFullname(request.getUserName());
        //mongoComment.setUserId(request.getUserId());
        mongoComment.setComment(request.getContent());
        return mongoComment;
    }
}
