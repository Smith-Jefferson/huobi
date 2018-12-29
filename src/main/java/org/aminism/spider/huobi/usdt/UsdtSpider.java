package org.aminism.spider.huobi.usdt;

import org.aminism.spider.huobi.common.SpiderTool;
import org.aminism.spider.huobi.entity.HuobiTradeProduct;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xieyigang on 2018/12/8.
 */
@Component
public class UsdtSpider {
    private static Logger logger = LogManager.getLogger(UsdtSpider.class);
    public static final String URL = "https://otc.huobi.co/zh-cn/trade/buy-usdt/";
    @Autowired
    SpiderTool tool;

    public List<HuobiTradeProduct> task() {
        try {
            return analysis(tool.getWebPage(URL));
        } catch (Exception e) {
            logger.error(e);
        }
        return Collections.emptyList();
    }

    private List<HuobiTradeProduct> analysis(Document doc) {
        Elements elements = doc.body().getElementsByClass("trade-list");
        if (elements == null || elements.isEmpty()) {
            return Collections.emptyList();
        }
        return elements.stream()
                .map(e -> new HuobiTradeProduct(getUser(e), getPrice(e)))
                .filter(i -> !StringUtil.isBlank(i.getPrice()))
                .collect(Collectors.toList());
    }

    private String getPrice(Element e) {
        return e.getElementsByClass("price average").text();
    }

    private String getUser(Element e) {
        return e.getElementsByClass("name").text();
    }

}
