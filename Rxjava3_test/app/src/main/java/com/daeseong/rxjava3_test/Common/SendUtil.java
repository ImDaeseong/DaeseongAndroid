package com.daeseong.rxjava3_test.Common;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.core.Observable;

public class SendUtil {

    public Observable<Boolean> SendMessage(String serverIP, int port, String sMsg){

        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                try {

                    DatagramSocket datagramSocket = new DatagramSocket();
                    datagramSocket.setBroadcast(true);
                    InetAddress address = InetAddress.getByName(serverIP);

                    byte[] bytesend;
                    bytesend = sMsg.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(bytesend, bytesend.length, address, port);
                    datagramSocket.send(sendPacket);
                    datagramSocket.close();
                    return true;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
    }
}
