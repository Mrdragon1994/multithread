package com.thread.chapter2

/**
 * @Author ChangQilong
 * @Date 2020/3/26 23:15
 */
class TestAtom {

    public static void main(String[] args) {

        AtomicityDemo atomicityDemo = new AtomicityDemo()

        Thread thread = new Thread() {
            @Override
            void run() {
                atomicityDemo.updateHostInfo("123", 12)
            }
        }

        Thread thread1 = new Thread() {
            @Override
            void run() {
                sleep(500)
                atomicityDemo.connect()
            }
        }
        thread.start()
        thread1.start()
    }
}

class AtomicityDemo {

    private HostInfo hostInfo = new HostInfo()

    public void updateHostInfo(String ip, int port) {
        hostInfo.setIp(ip)
        hostInfo.setPort(port)
    }

    public void connect() {
        println(hostInfo.getIp())
        println(hostInfo.getPort())
    }

     class HostInfo {
        private String ip
        private int port
        private static aaa

        HostInfo() {
        }

        HostInfo(String ip, int port) {
            this.ip = ip
            this.port = port
        }

        String getIp() {
            return ip
        }

        void setIp(String ip) {
            this.ip = ip
        }

        int getPort() {
            return port
        }

        void setPort(int port) {
            this.port = port
        }
    }
}