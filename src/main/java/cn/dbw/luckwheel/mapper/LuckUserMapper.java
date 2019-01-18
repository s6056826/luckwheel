package cn.dbw.luckwheel.mapper;

import cn.dbw.luckwheel.po.LuckUser;
import cn.dbw.luckwheel.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LuckUserMapper extends MyMapper<LuckUser> {
}