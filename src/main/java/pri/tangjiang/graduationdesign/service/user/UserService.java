package pri.tangjiang.graduationdesign.service.user;

import com.github.pagehelper.PageInfo;
import pri.tangjiang.graduationdesign.bean.AttendanceRecord;
import pri.tangjiang.graduationdesign.bean.Leave;
import pri.tangjiang.graduationdesign.bean.Notic;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.util.Result;

import java.util.Date;
import java.util.List;

public interface UserService {
    /**
     * 提交请假申请
     */
    Result submitLeaveApplication(Leave leave);

    /**
     * 请假记录
     */
    Result<PageInfo<Leave>> leaveRecord(PageParm param, String token);

    /**
     * 签到
     */
    Result signIn(Long id, Date time, String token);

    /**
     * 签退
     */
    Result signOut(Long id, Date time);

    /**
     * 员工本人当月考勤统计
     */
    Result signStistices(String token);

    /**
     * 查看本人当天考勤记录
     */
    Result<AttendanceRecord> getAttendanceRecord(String token);


    /**
     * 公告列表
     */
    Result<PageInfo<Notic>> listNotices(PageParm pageParm);

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
    Result deleteMyselfInfo(List<Integer> userIds);
}
