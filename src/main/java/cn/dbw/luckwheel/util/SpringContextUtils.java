package cn.dbw.luckwheel.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SpringContextUtils implements ApplicationContextAware {
    private  static ApplicationContext APPLICATION_CONTEXT=null;
    private  static final  ReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            SpringContextUtils.APPLICATION_CONTEXT=applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
            if(null!=APPLICATION_CONTEXT){
                return APPLICATION_CONTEXT;
            }else {
                return null;
            }
        }finally {
           lock.unlock();
        }


    }
}
