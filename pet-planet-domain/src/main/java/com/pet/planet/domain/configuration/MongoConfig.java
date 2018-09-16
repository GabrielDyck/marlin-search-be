package com.pet.planet.domain.configuration;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoConfig {

    private Morphia morphia;
    private Datastore datastore;

    private int port;

    private String host;


    @Autowired
    public MongoConfig(@Value("${pet.planet.mongo.port}") int port,@Value("${pet.planet.mongo.host}") String host) {
        this.host=host;
        this.port=port;
        morphia = new Morphia();
        morphia.mapPackage("com.pet.planet.domain.dao.model.mongo");
        datastore = morphia.createDatastore(new MongoClient(new ServerAddress(host, port)), getDatabaseName());
        datastore.ensureIndexes();
    }

    protected String getDatabaseName() {
        return "PET_PLANET";
    }


    public Datastore getDatastore() {
        return datastore;
    }
}
