package org.aminism.spider.huobi.server;

import org.aminism.spider.huobi.common.MailCaller;
import org.aminism.spider.huobi.dao.HuobiTradeProductDao;
import org.aminism.spider.huobi.entity.HuobiTradeProduct;
import org.aminism.spider.huobi.usdt.UsdtSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    @Autowired
    MailCaller mailCaller;



    public void collectInfo(){
        List<HuobiTradeProduct> list = spider.task();
        if(list ==null || list.isEmpty()){
            return;
        }
        HuobiTradeProduct p = list.stream().min(Comparator.comparing(HuobiTradeProduct::getPrice)).get();
        Double avg = list.stream().mapToDouble(HuobiTradeProduct::getPrice).average().getAsDouble();
        p.setAvgPrice(avg);
        dao.saveAndFlush(p);
    }

    public void sendMail(HuobiTradeProduct p){
        HuobiTradeProduct v = dao.findFirstByOrderByDatachange_lasttimeDesc();
        if(v==null || !v.getPrice().equals(p.getPrice()) || !v.getAvgPrice().equals(p.getAvgPrice())){
            StringBuilder builder = new StringBuilder();
            //todo
            try {
                mailCaller.sendMail(builder.toString(),"w952577382@126.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
