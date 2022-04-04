package pri.tangjiang.graduationdesign.dao;

import pri.tangjiang.graduationdesign.bean.Leave;
import pri.tangjiang.graduationdesign.bean.vo.LeaveVO;

import java.util.List;

public interface LeaveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    List<LeaveVO> notHandleLeave();
}