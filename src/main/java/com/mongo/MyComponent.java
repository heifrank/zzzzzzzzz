package com.mongo;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by heifrank on 16/4/18.
 */
@Entity(value = "component_test", noClassnameStored = false)
public class MyComponent {
    @Id
    @Property("_id")
    private String component_id;

//    public String getComponent_id(){
//        return component_id;
//    }
}
