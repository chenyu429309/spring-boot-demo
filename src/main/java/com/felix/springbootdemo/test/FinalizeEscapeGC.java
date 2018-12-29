package com.felix.springbootdemo.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象在被GC时的自我拯救
 * finalize只会被系统自动调用一次
 *
 * 有没有必要执行finalize()方法
 *  1、没有覆盖finalize()方法，
 *  2、finalize()方法已被虚拟机调用了
 *
 *当没有与GC ROOT相关联的引用链，被放入F-Queue中，由于finalize()
 * 的优先级很低，所以在虚拟机执行finalize()之前，重新与引用链上的
 * 任一对象建立关联，这个对象被将不会被回收
 *
 */
@Slf4j
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK=null;
    public void isAlive(){
      log.info("活着,{}",SAVE_HOOK);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("finalize方法执行完成");
        FinalizeEscapeGC.SAVE_HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK=new FinalizeEscapeGC();
        //对象第一次拯救自己成功
        SAVE_HOOK=null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5s
        Thread.sleep(500);
        if (SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            log.info("死亡,{}",SAVE_HOOK);
        }
        //
//        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            log.info("死亡,{}",SAVE_HOOK);
        }
    }
}
