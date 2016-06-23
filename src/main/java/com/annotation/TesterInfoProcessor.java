package com.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by heifrank on 16/4/7.
 */

@SupportedAnnotationTypes({"com.annotation.TesterInfo"})
public class TesterInfoProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        HashMap<String, String> map = new HashMap<>();
        for(TypeElement te : annotations){
            for(Element ele : roundEnv.getElementsAnnotatedWith(te)){
                TesterInfo info = ele.getAnnotation(TesterInfo.class);
                map.put(ele.getEnclosingElement().toString(), info.createBy());
            }
        }
        System.out.println("songyang...........");
        return false;
    }
}
