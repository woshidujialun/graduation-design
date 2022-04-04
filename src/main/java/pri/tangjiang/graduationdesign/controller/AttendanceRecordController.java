package pri.tangjiang.graduationdesign.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.AttendanceRecord;
import pri.tangjiang.graduationdesign.service.admin.AdminService;
import pri.tangjiang.graduationdesign.service.user.UserService;
import pri.tangjiang.graduationdesign.util.DateUtil;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 考勤控制类
 */
@RestController
@RequestMapping("/tj/attendance")
public class AttendanceRecordController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 员工签到
     *
     * @param param
     * @return
     */
    @RequestMapping("/signIn")
    public Result signIn(@RequestBody Map<String, Object> param, @RequestHeader("token") String token) {
        Date date = DateUtil.format(param.get("time").toString());
        if (date == null) {
            return Result.fail("日期格式化失败,请按照yyyy-MM-dd mm:HH:ss传入");
        }
        return userService.signIn(Long.valueOf(param.get("id").toString()), date, token);
    }

    /**
     * 员工签退
     *
     * @param param
     * @return
     */
    @RequestMapping("/signOut")
    public Result signOut(@RequestBody Map<String, Object> param) {
        Date date = DateUtil.format(param.get("time").toString());
        if (date == null) {
            return Result.fail("日期格式化失败,请按照yyyy-MM-dd mm:HH:ss传入");
        }
        return userService.signOut(Long.valueOf(param.get("id").toString()), date);
    }

    /**
     * 员工获取当天的考勤记录
     *
     * @param token
     * @return
     */
    @RequestMapping("/get")
    public Result<AttendanceRecord> getAttendanceRecord(@RequestHeader("token") String token) {
        return userService.getAttendanceRecord(token);
    }

    /**
     * 统计员工当月考勤
     *
     * @param token
     * @return
     */
    @RequestMapping("/statistics/myself")
    public Result statisticsMyself(@RequestHeader("token") String token) {
        return userService.signStistices(token);
    }

    /**
     * 管理员统计全部员工当月考勤
     *
     * @return
     */
    @RequestMapping("/statistics/admin")
    public Result statisticsAdmin() {
        return adminService.allRecord();
    }
}
