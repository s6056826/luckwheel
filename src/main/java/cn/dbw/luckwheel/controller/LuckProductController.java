package cn.dbw.luckwheel.controller;

import cn.dbw.luckwheel.po.LuckProduct;
import cn.dbw.luckwheel.service.BaseService;
import cn.dbw.luckwheel.service.LuckProductService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class LuckProductController extends  BaseController<LuckProduct> {

    private List luckProductCach= new ArrayList<String>();

    @Autowired
    private LuckProductService luckProductService;

    @Override
    public BaseService<LuckProduct> getBaseService() {
        return luckProductService;
    }


    @RequestMapping("lists")
    public JSONObject getLuckProductList(){
        return  success(luckProductService.queryList(null),"");
    }

    @RequestMapping("showlists")
    public JSONObject getLuckProductByShowList(){
        LuckProduct luckProduct = new LuckProduct();
        luckProduct.setFlag(1);
        List<LuckProduct> luckProducts = luckProductService.queryList(luckProduct);
        if(luckProduct!=null&&luckProducts.size()>0){
            List<String> collect = luckProducts.stream().map((lp) -> {
                return lp.getName();
            }).collect(Collectors.toList());
            return success(collect,"");
        }
        return fail("");
    }


}
