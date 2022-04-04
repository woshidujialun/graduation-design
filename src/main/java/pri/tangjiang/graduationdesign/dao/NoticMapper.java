package pri.tangjiang.graduationdesign.dao;

import pri.tangjiang.graduationdesign.bean.Notic;

import java.util.List;

public interface NoticMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notic record);

    int insertSelective(Notic record);

    int updateByPrimaryKeySelective(Notic record);

    int updateByPrimaryKeyWithBLOBs(Notic record);

    int updateByPrimaryKey(Notic record);

    List<Notic> listNotic();
}