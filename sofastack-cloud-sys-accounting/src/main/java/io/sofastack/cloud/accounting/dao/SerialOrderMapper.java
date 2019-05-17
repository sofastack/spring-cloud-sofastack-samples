package io.sofastack.cloud.accounting.dao;

import io.sofastack.cloud.common.model.SerialOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/12 9:46 AM
 * @since:
 **/
@Mapper
public interface SerialOrderMapper {

    /**
     * 流水单入库
     *
     * @param serialOrder
     * @return
     */
    @Insert("insert into sofastack_cloud_serial (id,serial_type,create_time,target_type,target_id,serial_src,money,data_flag,remark,pay_terminal) "
            + "values(#{id},#{serialType},#{createTime},#{targetType},#{userId},#{serialSrc},#{money},#{dataFlag},#{remark},#{payTerminal})")
    int save(SerialOrder serialOrder);

    /**
     * 查询当天的所有流水单号
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    @Select("select * from sofastack_cloud_serial where target_id=#{userId} and create_time > #{start} and create_time < #{end}")
    List<SerialOrder> querySerialOrdersOneDay(@Param("userId") int userId,
                                              @Param("start") Date start, @Param("end") Date end);
}
