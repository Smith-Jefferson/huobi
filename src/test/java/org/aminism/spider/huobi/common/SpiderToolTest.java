package org.aminism.spider.huobi.common;

import org.aminism.spider.huobi.framework.AbstractSpringContextTest;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by xieyigang on 2018/12/8.
 */
public class SpiderToolTest extends AbstractSpringContextTest{
    @Autowired
    SpiderTool tool;
    @Test
    public void test1() throws IOException {
        Document d= tool.getWebPage("https://otc.huobi.co/zh-cn/trade/buy-usdt/");
        System.out.print(d.title());
    }

}