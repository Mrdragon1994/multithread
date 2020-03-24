package com.thread.chapter2

/**
 * 测试父线程在启动子线程之前对共享变量的更新对于子线程来说是可见的
 * @Author ChangQilong
 * @Date 2020/3/24 22:41
 */
class TestVisibility {

    //共享变量
    static int data = 0

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            void run() {
                sleep(50)
                println(data)
            }
        }
        data = 1
        thread.start()
        sleep(100)
        data = 2
    }
}
