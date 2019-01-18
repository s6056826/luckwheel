package cn.dbw.luckwheel.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "luck_product")
public class LuckProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 概率
     */
    private Float weight;

    /**
     * 是否展示
     */
    private Integer flag;

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
     * 获取商品名
     *
     * @return name - 商品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名
     *
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取概率
     *
     * @return weight - 概率
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * 设置概率
     *
     * @param weight 概率
     */
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * 获取是否展示
     *
     * @return flag - 是否展示
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置是否展示
     *
     * @param flag 是否展示
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}