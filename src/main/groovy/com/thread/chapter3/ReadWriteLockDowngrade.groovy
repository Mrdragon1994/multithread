package com.thread.chapter3

import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * @Author ChangQilong
 * @Date 2020/3/29 23:44
 */
class ReadWriteLockDowngrade {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock()
    private final def readLock = readWriteLock.readLock()
    private final def writeLock = readWriteLock.writeLock()

    private static int data = 0
    public static void main(String[] args) {
        ReadWriteLockDowngrade readWriteLockDowngrade = new ReadWriteLockDowngrade()
        Thread t = new Thread(new Runnable() {
            @Override
            void run() {
                readWriteLockDowngrade.operationWithLockDowngrade()
            }
        })

        t.start()
    }



    void operationWithLockDowngrade() {
        boolean readlockAcquired = false  //先创建一个标记位：读锁是否获得
        writeLock.lock()  //申请写锁
        try {
            //对共享数据进行更新
            data = 1
            println("${data}")
            //当前线程在持有写锁的情况下申请获得读锁
            readLock.lock()  //申请读锁
            readlockAcquired = true  //更改标志位
        } finally {
            writeLock.unlock() //释放写锁
        }
        if (readlockAcquired) {
            try {
                //读取共享变量进行操作
                println("${data}")
            } finally {
                readLock.unlock()
            }
        } else {
            //执行其他操作
        }
    }
}
