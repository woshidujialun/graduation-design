package pri.tangjiang.graduationdesign.service.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import pri.tangjiang.graduationdesign.bean.AttendanceRecord;
import pri.tangjiang.graduationdesign.bean.Leave;
import pri.tangjiang.graduationdesign.bean.Notic;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.LeaveVO;
import pri.tangjiang.graduationdesign.bean.vo.RecordVO;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.dao.AttendanceRecordMapper;
import pri.tangjiang.graduationdesign.dao.LeaveMapper;
import pri.tangjiang.graduationdesign.dao.NoticMapper;
import pri.tangjiang.graduationdesign.dao.UserMapper;
import pri.tangjiang.graduationdesign.util.DateUtil;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private NoticMapper noticMapper;

    @Resource
    private AttendanceRecordMapper attendanceRecordMapper;

    @Override
    public Result operationLeave(Long leaveId, Integer type) {
        Leave leave = leaveMapper.selectByPrimaryKey(leaveId);
        leave.setStatus(type.byteValue());
        int i = leaveMapper.updateByPrimaryKeySelective(leave);
        if (i == 1) {
            return Result.ok();
        }
        return Result.fail("操作失败");
    }

    @Override
    public Result<PageInfo<LeaveVO>> listNotHandleLeave(PageParm param) {
        return Result.ok(PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> leaveMapper.notHandleLeave()));
    }

    @Override
    public Result issueNotic(Notic notic) {
        int i = noticMapper.insertSelective(notic);
        if (i != 1) {
            return Result.fail("发布失败");
        }
        return Result.ok();
    }

    @Override
    public Result<PageInfo<UserVO>> listUserInfo(PageParm param) {
        return Result.ok(PageHelper.startPage(param.getPageNum(), param.getPageSize())
                .doSelectPageInfo(() -> userMapper.listAllUser()));
    }

    @Override
    public Result allRecord() {
        Date date = new Date();
        List<RecordVO> recordVOS = attendanceRecordMapper.listRecod(date);
        // 构造返回数据结构
        HashMap<String, Object> map = new HashMap<>();
        List<String> dateList = build(date);
        List<Integer> integers = new ArrayList<>();
        for (String s : dateList) {
            int i = 0;
            l1: for (RecordVO recordVO : recordVOS) {
                if (s.equals(recordVO.getDate())){
                    integers.add(Integer.valueOf(recordVO.getCount()));
                    i = 1;
                    break l1;
                }
            }
            if (i == 0){
                integers.add(0);
            }
        }
        map.put("date",dateList);
        map.put("count",integers);
        return Result.ok(map);
    }

    private List<String> build(Date date) {
        List<String> dates = new ArrayList<>();
        String stringDate = DateUtil.getStringDate(date);
        String substring = stringDate.substring(0, 8);
        Integer days = Integer.valueOf(stringDate.split("-")[2]);
        for (int i = 1; i <= days; i++) {
            if (i < 10) {
                dates.add(substring + "0" + i);
            } else {
                dates.add(substring + i);
            }
        }
        return dates;
    }
}
