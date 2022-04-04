package pri.tangjiang.graduationdesign.service.login;

import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.JwtManage;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password, Integer isAdmin) {
        if (isAdmin != 0 && isAdmin != 1) {
            return Result.fail("非法参数");
        }
        if (username == null || password == null) {
            return Result.fail("账户名或密码不能为空");
        }

        String token = null;
        User user = userMapper.getByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        if (password.equals(user.getPassword()) && (user.getAdmin().intValue() == isAdmin)) {
            token = JwtManage.create(user.getId());
        }else {
            return Result.fail("非法参数");
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }
}
