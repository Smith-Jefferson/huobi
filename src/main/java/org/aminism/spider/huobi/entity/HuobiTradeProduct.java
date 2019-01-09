package org.aminism.spider.huobi.entity;

import org.jsoup.helper.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by xieyigang on 2018/12/8.
 */
@Entity
@Table(name = "Huobi_TradeProduct")
public class HuobiTradeProduct {
    @Id
    private Long id;
    @Column(length = 32)
    private String uname;
    @Column(length = 32)
    private Double price;
    @Column(length = 10)
    private String unit;
    @Column
    private Double avgPrice;
    @Column
    private Calendar datachange_lasttime;

    public HuobiTradeProduct(){}

    public HuobiTradeProduct(String uname, String price) {
        this.uname = uname;
        if(StringUtil.isBlank(price)){
            return;
        }
        String[] try1=price.trim().split(" ");
        if(try1.length>1 && StringUtil.isNumeric(try1[0])){
            this.price = Double.valueOf(try1[0]);
            this.unit = try1[1];
        }
        datachange_lasttime = Calendar.getInstance();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
