package com.felix.springbootdemo.test;

public class PretenureSizeThresholdDetails {
    public static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3145728 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * PretenureSizeThreshold设置的值，当对象超过这个值，直接进去老年代
     * 这个例子可以看出：eden space 8192K，from space 1024K,to space 1024K
     *  eden和一个survivor区域为9216K，当a1，a2，a3进去
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] a4;

        a4 = new byte[5* _1MB];
    }
}
