package pri.tangjiang.graduationdesign.dao;

import java.util.List;
import pri.tangjiang.graduationdesign.bean.Notic;

public interface NoticMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notic record);

    int insertSelective(Notic record);

    int updateByPrimaryKeySelective(Notic record);

    int updateByPrimaryKeyWithBLOBs(Notic record);

    int updateByPrimaryKey(Notic record);
}