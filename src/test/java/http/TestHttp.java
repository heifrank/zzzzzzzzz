package http;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidian.cpp.common.util.http.ExtendedHttpInvoker;
import com.yidian.cpp.common.util.json.JsonUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/7/26.
 */
public class TestHttp {
    static final Logger logger = LoggerFactory.getLogger(TestHttp.class);

    public static void main(String[] args) throws IOException {
        if(true) return ;
        film_create_test();
        film_read_test();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("");
        httpClient.execute(httpGet);
        return ;
    }

    static final ExtendedHttpInvoker invoker = new ExtendedHttpInvoker(3);

    static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public static void film_create_test() throws IOException {
        String url = "http://a4.go2yd.com/Website/mysql/default-channel";
        Map<String, Object> entity = Maps.newHashMap();
        Map<String, Object> channel_map = Maps.newHashMap();
        channel_map.put("id", "btest");
        channel_map.put("createTime", "2016-08-12");
        channel_map.put("type", "film");
        channel_map.put("topicName", "test7");
//        channel_map.put("edit", 0);

        List< Map<String, Object> > createdChannels = Lists.newArrayList();
        createdChannels.add(channel_map);

        entity.put("key", "2aa27aa594a76fad69ef9ba39ccb173f");
        entity.put("on_userid", 6);
        entity.put("opt", "write");
        entity.put("created_channels", createdChannels);

        String entityStr = mapper.writeValueAsString(entity);
        logger.debug(entityStr);

        Map<String, Object> resp = invoker.httpPost(url, entity, JsonUtil.MAP_REFERENCE);
        resp.entrySet().forEach(entry -> System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue())));
    }

    @Test
    public static void film_read_test() throws IOException {
        String url = "http://a4.go2yd.com/Website/mysql/default-channel";
        Map<String, Object> entity = Maps.newHashMap();

        entity.put("id", "btest7");

//        entity.put("topicName", "test2");
        entity.put("fields", "*");
//        entity.put("type", "film");

        Map<String, Object> resp = invoker.httpGet(url, entity, JsonUtil.MAP_REFERENCE);
        resp.entrySet().forEach(entry -> System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue())));
    }

    @Test
    public static void meta_post_test() throws IOException {

    }

    @Test
    public static void meta_read_test() throws IOException {
        String url = "http://a4.go2yd.com/Website/mysql/default-channel";
        Map<String, Object> entity = Maps.newHashMap();
        entity.put("id", "btest7");
        Map<String, Object> resp = invoker.httpGet(url, entity, JsonUtil.MAP_REFERENCE);
        resp.entrySet().forEach(entry -> System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue())));
    }
}
