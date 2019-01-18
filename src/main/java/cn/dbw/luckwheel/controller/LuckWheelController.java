package cn.dbw.luckwheel.controller;


import cn.dbw.luckwheel.po.LuckProduct;
import cn.dbw.luckwheel.po.LuckUser;
import cn.dbw.luckwheel.po.LuckUserProduct;
import cn.dbw.luckwheel.service.BaseService;
import cn.dbw.luckwheel.service.LuckProductService;
import cn.dbw.luckwheel.service.LuckUserProductService;
import cn.dbw.luckwheel.service.LuckUserService;
import cn.dbw.luckwheel.util.RandomUtil;
import cn.dbw.luckwheel.util.WeightMeta;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("wheel")
public class LuckWheelController extends BaseController<LuckProduct> {

    @Autowired
    private LuckProductService luckProductService;

    @Autowired
    private LuckUserProductService luckUserProductService;

    @Autowired
    private LuckUserService luckUserService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("lottery")
    public JSONObject wheelLottery(String uid){

        boolean flag=canDog(uid);
        if(!flag){
            return  fail("没有可抽奖次数");
        }
        LuckUser luckUser = new LuckUser();
        luckUser.setUid(uid);
        LuckUser luckUser1 = luckUserService.queryOne(luckUser);
        if(luckUser1==null){
            luckUserService.add(luckUser);
        }

        String dod = luckDog();
        if(dod.indexOf("谢谢参与")==-1){
            //中奖了
            LuckUserProduct luckUserProduct = new LuckUserProduct();
            luckUserProduct.setUid(uid);
            luckUserProduct.setPname(dod);
            luckUserProductService.add(luckUserProduct);
        }
        return success(dod,"");
    }

    private String luckDog(){
        LuckProduct luckProduct = new LuckProduct();
        luckProduct.setFlag(1);
        List<LuckProduct> luckProducts = luckProductService.queryList(luckProduct);
        Map<String,Integer> tt=new HashMap<>();
        if(luckProduct!=null&&luckProducts.size()>0){
           for(int i=0;i<luckProducts.size();i++){
               LuckProduct luckProduct1=luckProducts.get(i);
               tt.put(luckProduct1.getName(),luckProduct1.getWeight().intValue());
           }
            tt.forEach((k,v)->{
                System.out.println(k+"   "+v);
            });
            WeightMeta<String> md = RandomUtil.buildWeightMeta(tt);
            String dog = md.random();
            return dog;
        }
        return  null;
    }


    //可以计算可以抽奖
    @RequestMapping("test")
    public boolean canDog(String uid){
        System.out.println(uid);
       // List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from codepay_order where pay_id=? and pay_tag=0", new BeanPropertyRowMapper<>(Map.class), uid);
        //select * from codepay_order where pay_id='13826467262' and pay_tag>=0 and money>=200 and pay_tag <FLOOR(money/200) and up_time >='2019-1-15 0:0:0'
        List<Map<String, Object>> maps=jdbcTemplate.queryForList("select * from codepay_order where pay_id='"+uid+"' and pay_tag>=0 and money>=200 and pay_tag <FLOOR(money/200) and up_time >='2019-1-15 0:0:0'");
        if(maps!=null&&maps.size()>0){
            Map<String, Object> stringObjectMap = maps.get(0);
            String pay_no = (String) stringObjectMap.get("pay_no");
            //更新一次
            jdbcTemplate.update("update codepay_order set pay_tag=pay_tag+1 where pay_id='"+uid+"' and pay_no='"+pay_no+"'");
            System.out.println(uid+"  "+true);
            return true ;
        }
        return false;
    }



    @Override
    public BaseService<LuckProduct> getBaseService() {
        return null;
    }

}
