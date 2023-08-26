package com.example.warehouse.mapper;

import com.example.warehouse.entity.User;
import com.example.warehouse.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user_info的mapper接口
 */
public interface UserMapper {
    /*
     * 根据账号查询用户信息的方法
     */
    User selectUserByCode(String userCode);

    /**
     * 查询用户行数的方法
     */
    Integer selectUserRowCount(User user);

    /**
     * 分页查询用户
     */
    List<User> selectUserList(@Param("page") Page page, @Param("user") User user);

    /**
     * 添加用户
     */
    int insertUser(User user);

    /**
     * 根据用户id修改用户状态
     */
    int updateStateByUid(@Param("userId") Integer userId, @Param("userState") String userState);

    /**
     * 根据ids修改用户的删除状态
     */
    int updateIsDeleteByUidList(List<Integer> userIdList);
}
