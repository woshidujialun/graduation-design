package pri.tangjiang.graduationdesign.service.user;

import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.util.Result;

public interface UserService {
    /**
     * 提交请假申请
     */
    void submitLeaveApplication();

    /**
     * 签到
     */
    void signIn();

    /**
     * 签退
     */
    void signOut();

    /**
     * 获取个人信息
     */
    Result<UserVO> getMyselfInfo(String token);

    /**
     * 修改个人信息
     */
    Result updateMyselfInfo(User user);

    /**
     * 添加个人信息
     */
    Result insertMyselfInfo(User user);

    /**
     * 删除个人信息
     */
    Result deleteMyselfInfo(Long userId);
}
