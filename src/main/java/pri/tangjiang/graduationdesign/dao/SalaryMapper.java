package pri.tangjiang.graduationdesign.dao;

import pri.tangjiang.graduationdesign.bean.Salary;
import pri.tangjiang.graduationdesign.bean.vo.SalaryVO;

import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<SalaryVO> list();
}