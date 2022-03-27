package pri.tangjiang.graduationdesign.service.login;

import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.JwtManage;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<Map<String, String>> login(String username, String password) {
        if (username == null || password == null){
            return Result.fail("账户名或密码不能为空");
        }

        String token = null;
        List<User> users = userMapper.getByUsername(username);
        for (User user : users) {
            if (password.equals(user.getPassword())){
                token = JwtManage.create(user.getId());
            }
            break;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }
}
