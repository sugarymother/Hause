package fun.moyujian.hause.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fun.moyujian.hause.entity.User;
import fun.moyujian.hause.entity.form.UserInfoForm;
import fun.moyujian.hause.entity.form.UserLoginForm;
import fun.moyujian.hause.entity.form.UserRegisterForm;
import fun.moyujian.hause.entity.view.UserInfoView;
import fun.moyujian.hause.mapper.UserMapper;
import fun.moyujian.hause.service.UserService;
import fun.moyujian.hause.util.EncryptUtil;
import fun.moyujian.hause.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 * @author Tian
 * @date 2023/2/15
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public String register(UserRegisterForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);

        // 检查用户名是否已存在
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.lambda().eq(User::getUsername, user.getUsername());
        if(userMapper.exists(wrapper)) {
            // 用户名已存在
            log.info("用户注册失败，用户名：" + user.getUsername() + " 已存在。");
            return null;
        }

        // 加密密码
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));

        // 插入用户信息
        userMapper.insert(user);

        // 生成token
        return JwtUtil.signToken(user.getId());
    }

    @Override
    public String login(UserLoginForm form) {
        // 加密密码
        String password = EncryptUtil.encrypt(form.getPassword());

        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.lambda().eq(User::getUsername, form.getUsername());
        if (!userMapper.exists(wrapper)) {
            // 用户名不存在
            log.info("用户登录失败，用户名：" + form.getUsername() + " 不存在。");
            return null;
        }
        User user = userMapper.selectOne(wrapper);

        // 比较密码
        if (!user.getPassword().equals(password)) {
            log.info("用户登陆失败，密码错误。用户名：" + form.getUsername() + " 。");
            return null;
        }

        // 生成token
        return JwtUtil.signToken(user.getId());
    }

    @Override
    public void modifyInfo(String token, UserInfoForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setId(JwtUtil.getChaimUserId(token));
        userMapper.updateById(user);
    }

    @Override
    public UserInfoView getUserInfo(String token) {
        User user = userMapper.selectById(JwtUtil.getChaimUserId(token));
        UserInfoView userInfoView = new UserInfoView();
        BeanUtils.copyProperties(user, userInfoView);
        return userInfoView;
    }
}
