package cn.dbw.luckwheel.controller;

import cn.dbw.luckwheel.po.LuckUser;
import cn.dbw.luckwheel.service.BaseService;
import cn.dbw.luckwheel.service.LuckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class LuckUserController extends BaseController<LuckUser> {

    @Autowired
    private LuckUserService luckUserService;

    @Override
    public BaseService<LuckUser> getBaseService() {
        return luckUserService;
    }


}
