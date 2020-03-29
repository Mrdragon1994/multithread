package com.thread.chapter3

import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * @Author ChangQilong*
 * @Date 2020/3/29 23:29
 */
class ReadWriteLockUsage {
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock() //创建一个读写锁的实例
    private final def readLock = readWriteLock.readLock()  //通过读写锁拿到该读写锁的读锁
    private final def writeLock = readWriteLock.writeLock() //通过读写锁拿到该读写锁的写锁

    private static int data = 0


    public static void main(String[] args) {
        ReadWriteLockUsage readWriteLockUsage = new ReadWriteLockUsage()
        Thread t1 = new Thread(new Runnable() {
            @Override
            void run() {
                readWriteLockUsage.reader()
            }
        })

        Thread t2 = new Thread(new Runnable() {
            @Override
            void run() {
                sleep(1000)
                readWriteLockUsage.reader()
            }
        })

        Thread t3 = new Thread(new Runnable() {
            @Override
            void run() {
                readWriteLockUsage.writer()
            }
        })

        Thread t4 = new Thread(new Runnable() {
            @Override
            void run() {
                readWriteLockUsage.reader()
            }
        })

        t1.setName("Thread-1")
        t2.setName("Thread-2")
        t3.setName("Thread-3")
        t4.setName("Thread-4")
        t1.start()
        t2.start()
        t3.start()
        t4.start()
    }


    void reader() {
        readLock.lock()
        try {
            println(Thread.getName() + "读取共享数据data为：${data}")
        } finally {
            readLock.unlock()  //在finally中释放读锁
        }
    }

    void writer() {
        writeLock.lock()
        try {
            data = 1
            println("${Thread.getName()}更新共享数据data为：${data}")
        } finally {
            writeLock.unlock() //在finally中释放写锁
        }
    }
}
