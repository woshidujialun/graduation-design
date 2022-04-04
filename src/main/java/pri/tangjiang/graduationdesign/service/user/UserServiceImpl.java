package pri.tangjiang.graduationdesign.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.AttendanceRecord;
import pri.tangjiang.graduationdesign.bean.Leave;
import pri.tangjiang.graduationdesign.bean.Notic;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.dao.AttendanceRecordMapper;
import pri.tangjiang.graduationdesign.dao.LeaveMapper;
import pri.tangjiang.graduationdesign.dao.NoticMapper;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.JwtManage;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private NoticMapper noticMapper;

    @Resource
    private AttendanceRecordMapper attendanceRecordMapper;

    @Override
    public Result submitLeaveApplication(Leave leave) {
        leave.setStatus((byte) 0);
        int i = leaveMapper.insertSelective(leave);
        if (i == 1) {
            return Result.ok();
        }
        return Result.fail("提交失败");
    }

    @Override
    public Result<PageInfo<Leave>> leaveRecord(PageParm param, String token) {
        Long userId;
        try {
            userId = JwtManage.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token invalid");
        }
        return Result.ok(PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> leaveMapper.selectByPrimaryKey(userId)));
    }

    @Override
    public Result signIn(Long id, Date time, String token) {
        Long userId;
        try {
            userId = JwtManage.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token invalid");
        }
        AttendanceRecord oldData = attendanceRecordMapper.selectByPrimaryKey(id);
        if (oldData != null) {
            return Result.fail("请勿重复打卡");
        }
        AttendanceRecord record = new AttendanceRecord();
        record.setWork_start_time(time);
        record.setUser_id(userId.intValue());
        int i = attendanceRecordMapper.insertSelective(record);
        if (i != 1) {
            return Result.fail("打卡失败");
        }
        return Result.ok();
    }

    @Override
    public Result signOut(Long id, Date time) {
        AttendanceRecord oldData = attendanceRecordMapper.selectByPrimaryKey(id);
        int i;
        if (oldData != null) {
            oldData.setWork_end_time(time);
            oldData.setWork_time(hoursBetweenWhitTowDate(time, oldData.getWork_start_time()));
            i = attendanceRecordMapper.insertSelective(oldData);
            if (i != 1) {
                return Result.fail("更新打卡失败");
            }
            return Result.ok();
        }
        AttendanceRecord record = new AttendanceRecord();
        record.setWork_end_time(time);
        record.setWork_time(hoursBetweenWhitTowDate(time, oldData.getWork_start_time()));
        i = attendanceRecordMapper.insertSelective(record);
        if (i != 1) {
            return Result.fail("打卡失败");
        }
        return Result.ok();
    }

    @Override
    public Result signStistices(String token) {
        Long userId;
        try {
            userId = JwtManage.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token invalid");
        }
        Date date = new Date();
        List<AttendanceRecord> attendanceRecords = attendanceRecordMapper.currentMonthByUserId(userId.intValue(), date);
        // 计算当月到现在的天数
        Integer day = Integer.valueOf(String.format("%td", date));
        int work = 0;
        int rest = 0;
        int eorr = 0;
        // 判断单月工作、休息、考勤异常的天数
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getWork_start_time() == null || record.getWork_end_time() == null) {
                ++eorr;
            } else {
                ++work;
            }
        }
        rest = day - work - eorr;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("上班天数",work);
        map.put("休息天数",rest);
        map.put("打卡异常天数",eorr);
        return Result.ok(map);
    }

    @Override
    public Result<AttendanceRecord> getAttendanceRecord(String token) {
        Long userId;
        try {
            userId = JwtManage.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("token invalid");
        }
        AttendanceRecord oldData = attendanceRecordMapper.selectByUseridAndDaye(userId, new Date());
        if (oldData == null) {
            AttendanceRecord record = new AttendanceRecord();
            record.setUser_id(userId.intValue());
            attendanceRecordMapper.insertSelective(record);
            return Result.ok(record);
        }
        return Result.ok(oldData);
    }

    @Override
    public Result<PageInfo<Notic>> listNotices(PageParm pageParm) {
        return Result.ok(PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize())
                .doSelectPageInfo(() -> noticMapper.listNotic()));
    }

    @Override
    public Result<UserVO> getMyselfInfo(String token) {
        Long userId;
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
        if (i != 1) {
            return Result.fail("保存失败");
        }
        return Result.ok();
    }

    @Override
    public Result insertMyselfInfo(User user) {
        int i = userMapper.insertSelective(user);
        if (i != 1) {
            return Result.fail("保存失败");
        }
        return Result.ok();
    }

    @Override
    public Result deleteMyselfInfo(List<Integer> userIds) {
        if (userIds.size() <= 0) {
            return Result.fail("请选择删除员工");
        }
        userIds.stream().forEach(e -> userMapper.deleteByPrimaryKey(Long.valueOf(e)));
        return Result.ok();
    }

    private String hoursBetweenWhitTowDate(Date start, Date end) {
        Double hours = (end.getTime() - start.getTime()) / Double.valueOf(1000 * 60 * 60);
        return new DecimalFormat("0.00").format(hours);
    }
}
