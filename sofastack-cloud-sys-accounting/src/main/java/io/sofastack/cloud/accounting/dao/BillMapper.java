package io.sofastack.cloud.accounting.dao;

import io.sofastack.cloud.common.model.BillDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 6:20 PM
 * @since:
 **/
@Mapper
public interface BillMapper {

    /**
     * 账单入库
     *
     * @param billDetails
     * @return
     */
    @Insert("insert into sofastack_cloud_bill(pay,income,pay_amount,income_amount,user_id,create_time) values "
            + "(#{pay},#{income},#{payAmount},#{incomeAmount},#{userId},#{createTime})")
    int save(BillDetails billDetails);
}
