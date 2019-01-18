package cn.dbw.luckwheel.controller;

import cn.dbw.luckwheel.po.LuckUserProduct;
import cn.dbw.luckwheel.service.BaseService;
import cn.dbw.luckwheel.service.LuckUserProductService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lup")
public class LuckUserProductController extends BaseController<LuckUserProduct> {

    @Autowired
    private LuckUserProductService luckUserProductService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BaseService<LuckUserProduct> getBaseService() {
        return luckUserProductService;
    }

    @RequestMapping("lkp")
    public JSONObject getLuckPersonByUid(String uid){
        LuckUserProduct luckUserProduct = new LuckUserProduct();
        luckUserProduct.setUid(uid);
       return success(luckUserProductService.queryList(luckUserProduct),"");
    }

    @RequestMapping("lkall")
    public JSONObject getAllLuckPerson(){
        List<Map<String, Object>> allLuckPerson = luckUserProductService.getAllLuckPerson();
        return success(allLuckPerson,"");

    }

    @Override
    protected void afterUpdate(Map map) {
        if(map!=null){
            Integer exchange = (Integer) map.get("exchange");
            if(exchange!=null){
                String pname = (String) map.get("pname");
                if(pname.contains("元")){
                    String money = pname.substring(0, pname.indexOf("元"));
                    int anInt = Integer.parseInt(money);
                    String uid = (String) map.get("uid");
                    jdbcTemplate.update("update login_user set user_money=user_money+"+anInt+" where app_login_id='"+uid+"'");
                }
            }
        }
        super.afterUpdate(map);
    }

    public static void main(String[] args) {
        String pname="188元红包";
        System.out.println(pname.substring(0,pname.indexOf("元")));
    }
}
