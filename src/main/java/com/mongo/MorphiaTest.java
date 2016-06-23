package com.mongo;

import com.yidian.commons.morphia.MorphiaInstance;
import com.yidian.commons.morphia.utils.MongoInstance;
import com.yidian.commons.morphia.utils.MongoUtils;

import java.net.UnknownHostException;

/**
 * Created by heifrank on 16/4/18.
 */
public class MorphiaTest {
    public static void main(String[] args) throws UnknownHostException {
        ComponentDAO componentDAO = new ComponentDAO(MyComponent.class,
                MongoUtils.getMongo("rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com"), MorphiaInstance.getMorphia(), "cpp");
        MyComponent ret = componentDAO.findOne("rss_feature");
//        System.out.println(ret.getComponent_id() + ".songyang");
    }
}
