package com.thread.chapter3

import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * ReentrantReadWriteUpgrade是不支持锁的升级的，该demo是为了验证不支持锁的升级
 * @Author ChangQilong
 * @Date 2020/3/30 0:01
 */
class ReentrantReadWriteUpgrade {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock()
    private final def readLock = readWriteLock.readLock()
    private final def writeLock = readWriteLock.writeLock()

    private static def data = 0
    public static void main(String[] args) {
        ReentrantReadWriteUpgrade readWriteUpgrade = new ReentrantReadWriteUpgrade()
        Thread t = new Thread(new Runnable() {
            @Override
            void run() {
                readWriteUpgrade.operationWithLockToUpgrade()
            }
        })

        t.start()
    }

    void operationWithLockToUpgrade() {
        boolean writeLockAcquired = false
        readLock.lock()  //读锁的申请
        try {
            println("${data}")

        } finally {
            readLock.unlock()
            writeLock.lock() //写锁的申请
            data = 1
            writeLockAcquired = true
        }
        if (writeLockAcquired) {
            try {
                println("${data}")
            } finally {
                writeLock.unlock()
            }
        } else {
            println("failed")
        }
    }
}
