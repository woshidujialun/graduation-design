package pri.tangjiang.graduationdesign.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.Leave;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.LeaveVO;
import pri.tangjiang.graduationdesign.service.admin.AdminService;
import pri.tangjiang.graduationdesign.service.user.UserService;
import pri.tangjiang.graduationdesign.util.DateUtil;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/tj/leave")
public class LeaveController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 提交请假申请
     *
     * @param param
     * @return
     */
    @RequestMapping("/submit")
    public Result submitLeaveApplication(@RequestBody Map<String,Object> param) {
        Date start_time = DateUtil.format(param.get("start_time").toString());
        Date end_time = DateUtil.format(param.get("end_time").toString());
        if (start_time == null || end_time == null){
            return Result.fail("日期格式化失败,请按照yyyy-MM-dd mm:HH:ss传入");
        }
        Leave leave = new Leave();
        leave.setStart_time(start_time);
        leave.setEnd_time(end_time);
        leave.setDescription(param.get("description").toString());
        leave.setUser_id((Integer)param.get("user_id"));
        return userService.submitLeaveApplication(leave);
    }

    /**
     * 员工本人请假记录
     *
     * @param pageParm
     * @param token
     * @return
     */
    @RequestMapping("/myself/record")
    public Result<PageInfo<Leave>> leaveRecord(@RequestBody PageParm pageParm,
                                               @RequestHeader("token") String token) {
        return userService.leaveRecord(pageParm, token);
    }

    /**
     * 管理员审批请假申请
     *
     * @param param
     * @return
     */
    @RequestMapping("/peration")
    public Result operationLeave(@RequestBody Map<String, String> param) {
        Long leaveId = Long.valueOf(param.get("leaveId"));
        Integer type = Integer.valueOf(param.get("type"));
        if (leaveId == null || type == null){
            return Result.fail("参数异常");
        }
        return adminService.operationLeave(leaveId,type);
    }

    /**
     * 展示未处理的请假申请列表
     *
     * @param pageParm
     * @return
     */
    @RequestMapping("/nothandle/list")
    public Result<PageInfo<LeaveVO>> listNotHandleLeave(@RequestBody PageParm pageParm){
        return adminService.listNotHandleLeave(pageParm);
    }

}
