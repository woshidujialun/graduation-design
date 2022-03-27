package pri.tangjiang.graduationdesign.service.admin;

import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.util.Result;

import java.util.List;

public interface AdminService {
    /**
     * 请假申请操作，同意或拒绝
     */
    void operationLeave();

    /**
     * 查看员工信息
     * @return
     */
    Result<List<UserVO>> listUserInfo();
}
