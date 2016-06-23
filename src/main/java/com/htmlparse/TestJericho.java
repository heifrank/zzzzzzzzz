package com.htmlparse;

import net.htmlparser.jericho.Source;

/**
 * Created by heifrank on 16/4/21.
 */
public class TestJericho {
    public static void main(String[] args){
        String html = "<html><body>songyang <a href=\"http://www.baidu.com\">haha</a></body></html>";
        Source source = new Source(html);
        System.out.println(source.getTextExtractor().toString());
    }
}
