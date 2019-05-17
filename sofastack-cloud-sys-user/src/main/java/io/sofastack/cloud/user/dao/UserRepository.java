package io.sofastack.cloud.user.dao;

import io.sofastack.cloud.common.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 6:10 PM
 * @since:
 **/
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    /**
     * findById
     *
     * @param userId
     * @return
     */
    @Override
    Optional<UserDetails> findById(Integer userId);

    /**
     * 用户信息注册入库
     *
     * @param userDetails
     * @return
     */
    @Override
    UserDetails save(UserDetails userDetails);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param userName
     * @param password
     * @return
     */
    UserDetails findByUserNameAndPassword(String userName, String password);

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    Long countByUserName(String userName);
}
