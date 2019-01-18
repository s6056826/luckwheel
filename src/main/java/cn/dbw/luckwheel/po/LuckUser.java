package cn.dbw.luckwheel.po;

import javax.persistence.*;

@Table(name = "luck_user")
public class LuckUser {

    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String uid;

    private String uname;

    private String province;

    private String city;

    private String address;

    private String tel;

    /**
     * 可抽奖次数
     */
    @Column(name = "luck_num")
    private Integer luckNum;

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
     * @return uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取可抽奖次数
     *
     * @return luck_num - 可抽奖次数
     */
    public Integer getLuckNum() {
        return luckNum;
    }

    /**
     * 设置可抽奖次数
     *
     * @param luckNum 可抽奖次数
     */
    public void setLuckNum(Integer luckNum) {
        this.luckNum = luckNum;
    }
}