package cn.dbw.luckwheel.controller;

import cn.dbw.luckwheel.service.BaseService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 基本Controller
 *
 * @auther ny by:dbw
 * @create 2018-06-09
 */
public abstract class BaseController<T> {

    protected Logger LOGGER= LoggerFactory.getLogger(this.getClass());


    protected void beforeAdd(Map map){

    }

    protected  void afterAdd(Map map){

    }

    protected void afterUpdate(Map map){

    }

    public abstract BaseService<T> getBaseService();
    @RequestMapping("add")
    public Object add(@RequestBody Map map){
        beforeAdd(map);
        T newinstance = getBaseService().newinstance(map);
        T t = getBaseService().add(newinstance);
        afterAdd(map);
        return success("");
    }

    @RequestMapping("delete")
    public Object delete(@RequestBody Map map){
        T newinstance = getBaseService().newinstance(map);
        getBaseService().delete(newinstance);
        return success("");
    }

    /**
     * 通过主键id批量删除 参数必须为数组，名为ids
     * @param ids
     * @return
     */
    @RequestMapping("deletes")
    public Object batchDelete(@RequestParam("ids[]") Integer ids[]){
        int i = getBaseService().deleteByIds(Arrays.asList(ids));
        if(i>0)
            return success();
        else
            return fail("");
    }



    @RequestMapping("update")
    public Object update(@RequestBody Map map){
        T t = getBaseService().newinstance(map);
        int rs= getBaseService().update(t);
        if(rs>0){
            afterUpdate(map);
            return  success(null,"");
        }else{
            return fail("更新失败");
        }

    }
    @RequestMapping("get")
    public Object getOne(@RequestBody Map map){
        T t = getBaseService().newinstance(map);
        t=getBaseService().queryOne(t);
        if(null==t)
            return  fail("");
        return success(t,"");
    }

    @RequestMapping("getlist")
    public Object getList(@RequestBody Map map){
        T t = getBaseService().newinstance(map);
        List<T> rs=getBaseService().queryList(t);
        return success(rs,"");
    }

    /**
     * 附带检索条件的分页查询
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("getPageQuery")
    public Object getListByPage(@RequestBody Map map, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        T t = getBaseService().newinstance(map);
        PageInfo<T> pageInfo =getBaseService().queryListByPage(t,pageNo,pageSize);
        return success(pageInfo,"");
    }

    /**
     * 无检索条件的分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("getPage")
    public Object getListByPage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        //T t = getBaseService().newinstance(null);
        T t=null;
        PageInfo<T> pageInfo =getBaseService().queryListByPage(t,pageNo,pageSize);
        return success(pageInfo,"");
    }





    public JSONObject fail(String err) {
        JSONObject object = new JSONObject();
        object.put("status", "FAIL");
        object.put("msg", err);
        object.put("code", 1);
        return object;
    }

    public JSONObject success() {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("code", 0);
        return object;
    }

    public JSONObject success(String msg) {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("msg", msg);
        object.put("code", 0);
        return object;
    }

    public JSONObject success(Object data ,String msg) {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("data", data);
        object.put("msg", msg);
        object.put("code", 0);
        return object;
    }
    /**
     * 从thread local获取网络上下文
     */
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取当前客户端session对象
     * @return
     */
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

}
