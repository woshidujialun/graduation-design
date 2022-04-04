package pri.tangjiang.graduationdesign.dao;

import org.apache.ibatis.annotations.Param;
import pri.tangjiang.graduationdesign.bean.AttendanceRecord;
import pri.tangjiang.graduationdesign.bean.vo.RecordVO;

import java.util.Date;
import java.util.List;

public interface AttendanceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttendanceRecord record);

    int insertSelective(AttendanceRecord record);

    AttendanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttendanceRecord record);

    int updateByPrimaryKey(AttendanceRecord record);

    AttendanceRecord selectByUseridAndDaye(@Param("userId") Long userId, @Param("time") Date time);

    List<AttendanceRecord> currentMonthByUserId(@Param("userId") Integer userId, @Param("currentTime") Date currentTime);

    List<RecordVO> listRecod(@Param("currentTime") Date currentTime);
}