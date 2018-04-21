package com.sunbufu.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 网络工具类
 *
 * @author sunbufu
 */
public class NetUtils {

    /**本机IP地址*/
    public static String hostIpStr;
    /**本机名称*/
    public static String hostNameStr;

    static{
        InetAddress inetAddress = getInetAddress();
        hostIpStr = inetAddress.getHostAddress();
        hostNameStr = inetAddress.getHostName();
    }

    /**
     * 获取网络地址
     *
     * @return
     * @throws Exception
     */
    private static InetAddress getInetAddress() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    // 排除loopback类型地址
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
