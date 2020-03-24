package com.thread.chapter1

/**
 * @Author ChangQilong* @Date 2020/3/23 16:45
 */
class WelcomeApp {
    public static void main(String[] args) {

        Thread thread = new WelcomeThread()
        thread.run()
        thread.start()
       // thread.start()
        println("1. welcome ${Thread.currentThread().getName()}")
    }
}

class WelcomeThread extends Thread {
    @Override
    void run() {
        println("2. welcome ${currentThread().getName()}")
    }
}