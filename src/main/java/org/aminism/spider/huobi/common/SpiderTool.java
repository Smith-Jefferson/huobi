package org.aminism.spider.huobi.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by xieyigang on 2018/12/8.
 */
@Component
public class SpiderTool {
    public Document getWebPage(String url,String... otherparams) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
}
