package com.mongo;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by heifrank on 16/4/18.
 */
public class ComponentDAO extends BasicDAO<MyComponent, String> {

    public ComponentDAO(Class<MyComponent> entityClass, MongoClient mongoClient, Morphia morphia, String dbName) {
        super(entityClass, mongoClient, morphia, dbName);
    }

    MyComponent findOne(String id){
        Query<MyComponent> query = createQuery().field("component_id").equal(id);
        return query.get();
    }
}
