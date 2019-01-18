package cn.dbw.luckwheel.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "luck_user_product")
public class LuckUserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String uid;

    private Integer pid;

    /**
     * 商品名字
     */
    private String pname;

    /**
     * 是否兑换 0未发货  1发货了
     */
    private Integer exchange;

    private Date ltime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取商品名字
     *
     * @return pname - 商品名字
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置商品名字
     *
     * @param pname 商品名字
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 获取是否兑换 0未发货  1发货了
     *
     * @return exchange - 是否兑换 0未发货  1发货了
     */
    public Integer getExchange() {
        return exchange;
    }

    /**
     * 设置是否兑换 0未发货  1发货了
     *
     * @param exchange 是否兑换 0未发货  1发货了
     */
    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }

    /**
     * @return ltime
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * @param ltime
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }
}