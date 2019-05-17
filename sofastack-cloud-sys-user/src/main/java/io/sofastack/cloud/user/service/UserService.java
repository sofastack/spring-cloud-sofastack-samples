package io.sofastack.cloud.user.service;

import io.sofastack.cloud.common.model.UserDetails;
import io.sofastack.cloud.user.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/5 6:15 PM
 * @since:
 **/
@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository              userRepository;

    /**
     * 走fallback
     *
     * @param userId
     * @return
     */
    public UserDetails queryUserDetails(int userId) {
        return userRepository.findById(userId).get();
    }

    /**
     * 用户注册
     *
     * @param userDetails
     * @return
     */
    public UserDetails register(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    /**
     * 用户注册
     *
     * @param userDetails
     * @return
     */
    public boolean isAlreadyExist(UserDetails userDetails) {
        boolean isAlreadyExist = false;
        try {
            long count = userRepository.countByUserName(userDetails.getUserName());
            if (count > 0) {
                isAlreadyExist = true;
            }
        } catch (Exception e) {
            // 失败则默认为已经存在
            isAlreadyExist = true;
            LOGGER.error("Error to check registry", e);
        }
        return isAlreadyExist;
    }

    /**
     * 根据userName 和 password 查询用户账户信息
     *
     * @param userName
     * @param password
     * @return
     */
    public UserDetails validate(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
}
