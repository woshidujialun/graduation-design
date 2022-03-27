package pri.tangjiang.graduationdesign.service.user;

import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.JwtManage;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void submitLeaveApplication() {

    }

    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public Result<UserVO> getMyselfInfo(String token) {
        Integer userId = null;
        try {
            userId = JwtManage.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token invalid");
        }
        return Result.ok(userMapper.getByUserId(userId));
    }

    @Override
    public Result updateMyselfInfo(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i != 1){
            return Result.fail("保存失败");
        }
        return Result.ok();
    }

    @Override
    public Result insertMyselfInfo(User user) {
        int i = userMapper.insertSelective(user);
        if (i != 1){
            return Result.fail("保存失败");
        }
        return Result.ok();
    }

    @Override
    public Result deleteMyselfInfo(Long userId) {
        int i = userMapper.deleteByPrimaryKey(userId);
        if (i != 1){
            return Result.fail("保存失败");
        }
        return Result.ok();
    }
}
