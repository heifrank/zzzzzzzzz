package com.mongo;

import avro.shaded.com.google.common.collect.Lists;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidian.cpp.common.mongo.GenericMongoDAO;
import com.yidian.cpp.common.mongo.MapDocConverter;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/7/7.
 *
 * This class is used to check whether there is field used before it is generate in a specific chain.
 */
public class CheckDependency {
    GenericMongoDAO mongoDAO = new GenericMongoDAO("rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com", "cpp");

    String meta = "meta";

    String component = "component";

    String[] uppers = {"__action__",
            "__compt__",
            "b_rept",
            "parse_author",
            "parse_tag",
            "parse_category",
            "parse_source",
            "parse_content",
            "parse_pages",
            "inlink",
            "base",
            "fts",
            "html_offcut",
            "level",
            "parse_articlesign",
            "wm_autoimport",
            "b_nosubcat",
            "b_nocat",
            "original_content",
            "vertical",
            "app",
            "protect",
            "newslist",
            "c_comment",
            "c_ocmt",
            "xpath_seed",
            "b_audio",
            "c_adownload",
            "c_alisten",
            "related_audios",
            "related_audios",
            "audio_index",
            "duration",
            "audio_url",
            "program_name",
            "audio_src",
            "audio_id",
            "c_aclisten",
            "c_acdownload",
            "c_acsub",
            "b_top",
            "top_until",
            "audio_cat",
            "astyles",
            "ascenes",
            "actags",
            "attl",
            "akws",
            "b_native",
            "video_url",
            "rlevel",
            "vct",
            "vsct",
            "c_vup",
            "c_vdown",
            "c_vplay",
            "c_vcmt",
            "c_vtime",
            "c_vfav",
            "wm_id",
            "wm_tier",
            "summary",
            "wm_tags",
            "wm_cates",
            "upload_images",
            "b_review",
            "e_censor",
            "wm_docid",
            "wemedia",
            "hot_rank",
            "rank",
            "important",
            "parse_date",
            "portal_id",
            "hot_pid",
            "b_rank",
            "pop_rank",
            "ranks",
            "from", "date", "parse_title", "url"
            };

    boolean check(String chainName){
        Map<String, Object> metaConfigMap = mongoDAO.findOne(meta, new Document("_id", chainName), new MapDocConverter());
        List<String> components = (List<String>) metaConfigMap.get("components");
        List< List<String> > componentsOutput = Lists.newArrayList();
        List<String> upperList = Arrays.asList(uppers);

        for(int i = 0; i < components.size() ; i++){
            String componentID = components.get(i);
            Map<String, Object> cur = mongoDAO.findOne(component, new Document("_id", componentID), new MapDocConverter());
            List<String> input = (List<String>) cur.get("input");
            List<String> output = (List<String>) cur.get("output");

            if(output != null) {
                componentsOutput.add(output);
            }

            if(input == null){
                continue;
            }

            for(String field : input){
                boolean flag = false;
                for(List<String> outputs : componentsOutput){
                    if(outputs.contains(field)){
                        flag = true;
                        break;
                    }
                }
                if(flag == false && !upperList.contains(field)){
                    System.out.println(String.format("Check field %s, current component %s", field, componentID));
                }
            }


        }
        return true;
    }

    public static void main(String[] args){
        CheckDependency checkDependency = new CheckDependency();
        System.out.println(checkDependency.check("editor_wemedia"));
    }
}
