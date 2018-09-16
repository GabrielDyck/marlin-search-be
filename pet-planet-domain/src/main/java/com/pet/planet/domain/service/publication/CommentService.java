package com.pet.planet.domain.service.publication;

import com.google.common.collect.Lists;
import com.pet.planet.domain.configuration.MongoConfig;
import com.pet.planet.domain.dao.model.mongo.Comment;
import com.pet.planet.domain.dao.model.mongo.KVComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private MongoConfig mongoConfig;

    @Autowired
    public CommentService(MongoConfig mongoComment) {
        this.mongoConfig = mongoComment;
    }

    public List<Comment> findCommentsById(Long id){
        KVComment kvComment= findByPublicationId(id);
        if(kvComment !=null){
            return kvComment.getComments();

        }

        return Lists.newArrayList();
    }

    private KVComment findByPublicationId(Long id) {
        return mongoConfig.getDatastore().createQuery(KVComment.class).field("publicationId").equal(id).get();
    }

    public KVComment findKVCommentById(Long id){
        return this.findByPublicationId(id);
    }

    public void save(KVComment comment){
        mongoConfig.getDatastore().save(comment);
    }
}
