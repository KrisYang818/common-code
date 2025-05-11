package com.arvin.it.service;

import com.arvin.it.entity.User;

public interface UserService {


    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 删除的条数
     */
    public int deleteUserById(Long userId);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean registerUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);
}
