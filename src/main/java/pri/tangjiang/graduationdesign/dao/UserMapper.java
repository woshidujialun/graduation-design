package pri.tangjiang.graduationdesign.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getByUsername(@Param("username") String username);

    UserVO getByUserId(@Param("userId") Long userId);

    List<UserVO> listAllUser();
}