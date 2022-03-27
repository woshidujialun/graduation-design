package pri.tangjiang.graduationdesign.service.admin;

import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void operationLeave() {

    }

    @Override
    public Result<List<UserVO>> listUserInfo() {
        return Result.ok(userMapper.listAllUser());
    }
}
