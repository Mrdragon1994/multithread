package com.thread.chapter2

/**
 * @Author ChangQilong
 * @Date 2020/3/24 23:23
 */
class TestVisibility2 {
    static int data = 0 //共享变量
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            void run(){
                Tools.randomPause(50)
                data = 1
            }
        }
        thread.start()
        try {
            thread.join()
        } catch(Exception e) {

        }
        println(data)
    }
}

class Tools {
   static void randomPause(int millis) {
       int next = new Random().nextInt(millis)
       Thread.sleep(next)
   }
}
