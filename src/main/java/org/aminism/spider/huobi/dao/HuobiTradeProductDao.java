package org.aminism.spider.huobi.dao;

import org.aminism.spider.huobi.entity.HuobiTradeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xieyigang on 2018/12/8.
 */
@Repository
public interface HuobiTradeProductDao extends JpaRepository<HuobiTradeProduct,Long> {
    HuobiTradeProduct findFirstByOrderByDatachange_lasttimeDesc();
}
