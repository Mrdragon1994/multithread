package com.thread.chapter3

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * @Author ChangQilong
 * @Date 2020/3/29 22:27
 */
class TestLock {
    private static final def lock = new ReentrantLock() //默认是非公平锁
    private static int sharedData = 0  //共享数据为int类型的0

    public static void main(String[] args) {
        def t = new Thread(new Runnable() {
            @Override
            void run() {
                lock.lock()  //获取锁
                println(lock.getQueueLength())
                try {
                    try {
                        sleep(1000 * 20)
                    } catch(Exception e) {
                        e.printStackTrace()
                    }
                    sharedData = 1
                } finally {
                    lock.unlock()
                }
            }
        })
        t.setName("Thread-01")
        t.start() //启动子线程
        sleep(100) //主线程休眠100ms
        println(lock.isLocked())

        lock.lock()

        try {
            printf("sharedData = %d", sharedData)
        } finally {
            lock.unlock()
        }
    }
}
