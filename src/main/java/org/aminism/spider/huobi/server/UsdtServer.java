package org.aminism.spider.huobi.server;

import org.aminism.spider.huobi.dao.HuobiTradeProductDao;
import org.aminism.spider.huobi.entity.HuobiTradeProduct;
import org.aminism.spider.huobi.usdt.UsdtSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieyigang on 2018/12/8.
 */
@Service
public class UsdtServer {
    @Autowired
    UsdtSpider spider;
    @Autowired
    HuobiTradeProductDao dao;

    public void collectInfo(){
        List<HuobiTradeProduct> list = spider.task();
        if(list !=null && !list.isEmpty()){
            dao.save(list);
            dao.flush();
        }
    }
}
