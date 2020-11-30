package pres.qm.netty.network;

import java.net.NetworkInterface;
import java.util.Enumeration;


public class NetworkInterfaceDemo {
    public static void main(String[] args) throws Exception {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println("网络设备名称 " + networkInterface.getName());
            System.out.println("网络设备显示名称 " + networkInterface.getDisplayName());
            System.out.println("网络接口索引 " + networkInterface.getIndex());
            System.out.println("是否开启并运行 " + networkInterface.isUp());
            System.out.println("是否为回调接口 " + networkInterface.isLoopback());
            System.out.println("最大传输单元 " + networkInterface.getMTU());

            System.out.println("是否为虚拟接口 " + networkInterface.isVirtual());
            System.out.println("取得父接口信息 " + networkInterface.getParent());
            System.out.println("取得子接口信息 " + networkInterface.getSubInterfaces());
            System.out.println("取得物理地址 " + networkInterface.getHardwareAddress());
            System.out.println("获取IP地址 " + networkInterface.getInetAddresses().toString());

            System.out.println();
        }
    }
}
