package cn.dbw.luckwheel.mapper;

import cn.dbw.luckwheel.po.LuckUserProduct;
import cn.dbw.luckwheel.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Mapper
public interface LuckUserProductMapper extends MyMapper<LuckUserProduct> {


    public List<Map<String,Object>> getAllLuckPerson();

}