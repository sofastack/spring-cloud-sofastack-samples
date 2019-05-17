package io.sofastack.cloud.accounting.dao;

import io.sofastack.cloud.accounting.entity.AccountingEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/11 9:25 PM
 * @since:
 **/
@Mapper
public interface AccountingMapper {
    /**
     * 根据账户Id查询账户信息
     * @param userId
     * @return
     */
    @Select("SELECT * FROM sofastack_cloud_accounting WHERE user_id = #{userId}")
    @Results({ @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "totalAssets", column = "total_assets"),
            @Result(property = "frozenAssets", column = "frozen_assets"),
            @Result(property = "availableAssets", column = "available_assets"),
            @Result(property = "state", column = "state"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "type", column = "type"),
            @Result(property = "level", column = "level"),
            @Result(property = "points", column = "points"),
            @Result(property = "payPassword", column = "pay_password"),
            @Result(property = "reservedField", column = "reserved_field") })
    AccountingEntity queryById(int userId);

    /**
     * 根据 userId 和 payPassword 检验是否存在当前账户
     * @param userId
     * @param payPassword
     * @return
     */
    @Select("SELECT count(1) FROM sofastack_cloud_accounting WHERE user_id = #{userId} and pay_password = #{payPassword}")
    int queryByIdAndPayPassword(@Param("userId") int userId,
                                @Param("payPassword") String payPassword);

    /**
     * 冻结资产
     * @param userId            当前用户账户ID
     * @param currentFrozen     当前冻结之后的金额
     * @param availableAssets   当前冻结之后的可用金额
     * @return
     */
    @Update("UPDATE sofastack_cloud_accounting SET frozen_assets=#{currentFrozen},available_assets=#{availableAssets} where user_id = #{userId}")
    int frozonAssets(@Param("userId") int userId, @Param("currentFrozen") BigDecimal currentFrozen,
                     @Param("availableAssets") BigDecimal availableAssets);

    /**
     * 更新账户可用资产
     * @param targetId
     * @param currentAvailable
     * @param totalAssets
     * @return
     */
    @Update("UPDATE sofastack_cloud_accounting SET available_assets=#{currentAvailable},total_assets=#{totalAssets} where user_id = #{targetId}")
    int updateAvailableAssets(@Param("targetId") int targetId,
                              @Param("currentAvailable") BigDecimal currentAvailable,
                              @Param("totalAssets") BigDecimal totalAssets);

    /**
     * 扣减资产
     * @param userId
     * @param currentFrozen
     * @param totalAssets
     * @return
     */
    @Update("UPDATE sofastack_cloud_accounting SET frozen_assets=#{currentFrozen},total_assets=#{totalAssets} where user_id = #{userId}")
    int deductAssets(@Param("userId") int userId, @Param("currentFrozen") BigDecimal currentFrozen,
                     @Param("totalAssets") BigDecimal totalAssets);

    /**
     * 获取所有账id
     * @return
     */
    @Select("select user_id from sofastack_cloud_accounting")
    List<Integer> queryAllAccounts();

    /**
     * 新增 账户信息
     * @param entity
     * @return
     */
    @Insert("insert into sofastack_cloud_accounting(user_id,total_assets,frozen_assets,available_assets,state,create_time,type,level,points,pay_password,reserved_field)"
            + "values (#{userId},#{totalAssets},#{frozenAssets},#{availableAssets},#{state},#{createTime},#{type},#{level},#{points},#{payPassword},#{reservedField})")
    AccountingEntity insert(AccountingEntity entity);

}
