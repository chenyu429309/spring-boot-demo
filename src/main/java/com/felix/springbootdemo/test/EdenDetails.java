package com.felix.springbootdemo.test;

public class EdenDetails {
    public static final int _1MB = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * 这个例子可以看出：eden space 8192K，from space 1024K,to space 1024K
     *  eden和一个survivor区域为9216K，当a1，a2，a3进去
     * @param args
     */
    public static void main(String[] args) {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[4 * _1MB];
    }
}
