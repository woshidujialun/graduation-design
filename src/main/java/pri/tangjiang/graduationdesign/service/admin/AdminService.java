package pri.tangjiang.graduationdesign.service.admin;

import com.github.pagehelper.PageInfo;
import pri.tangjiang.graduationdesign.bean.Notic;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.LeaveVO;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.util.Result;


public interface AdminService {
    /**
     * 请假申请操作，同意或拒绝
     */
    Result operationLeave(Long leaveId, Integer type);

    /**
     * 展示未处理的请假申请列表
     */
    Result<PageInfo<LeaveVO>> listNotHandleLeave(PageParm param);

    /**
     * 发布公告
     */
    Result issueNotic(Notic notic);

    /**
     * 查看员工信息
     *
     * @return
     */
    Result<PageInfo<UserVO>> listUserInfo(PageParm param);

    /**
     * 统计全部员工考勤
     */
    Result allRecord();
}
