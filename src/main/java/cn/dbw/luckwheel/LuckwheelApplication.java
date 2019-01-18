package cn.dbw.luckwheel;

import cn.dbw.luckwheel.util.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.dbw.luckwheel.mapper",markerInterface = MyMapper.class)
public class LuckwheelApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckwheelApplication.class, args);
    }

}

