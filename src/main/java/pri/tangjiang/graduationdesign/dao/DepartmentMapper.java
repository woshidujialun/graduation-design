package pri.tangjiang.graduationdesign.dao;

import java.util.List;
import pri.tangjiang.graduationdesign.bean.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectNameAndId();
}