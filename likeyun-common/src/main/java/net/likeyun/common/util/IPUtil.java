package net.likeyun.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Description: ip工具类
 * @Author: lfy
 * @Date: 2019/12/6 17:45
 */
@Slf4j
public final class IPUtil {
    private IPUtil() {
    }

    public static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (log.isInfoEnabled()) {
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String s : ips) {
                if (!("unknown".equalsIgnoreCase(s))) {
                    ip = s;
                    break;
                }
            }

        }
        return ip;
    }

    public static String getCityInfo(String ip) {
        try {
            InputStream inputStream = null;
            //db
            String dbPath = IPUtil.class.getResource("/ip2region/ip2region.db").getPath();
            File file = new File(dbPath);
            if (!file.exists()) {
                String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
                dbPath = tmpDir + "ip.db";
                System.out.println(dbPath);
                file = new File(dbPath);
                inputStream = IPUtil.class.getClassLoader().getResourceAsStream("ip2region/ip2region.db");
                if (inputStream != null) {
                    FileUtils.copyInputStreamToFile(inputStream, file);
                } else {
                    throw new RuntimeException();
                }
            }

            //查询算法
            int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
            //DbSearcher.BINARY_ALGORITHM //Binary
            //DbSearcher.MEMORY_ALGORITYM //Memory
            try {
                DbConfig config = new DbConfig();
                DbSearcher searcher = new DbSearcher(config, dbPath);

                //define the method
                Method method;
                method = searcher.getClass().getMethod("btreeSearch", String.class);
//                switch (algorithm) {
//                    case DbSearcher.BTREE_ALGORITHM:
//                        method = searcher.getClass().getMethod("btreeSearch", String.class);
//                        break;
//                    case DbSearcher.BINARY_ALGORITHM:
//                        method = searcher.getClass().getMethod("binarySearch", String.class);
//                        break;
//                    case DbSearcher.MEMORY_ALGORITYM:
//                        method = searcher.getClass().getMethod("memorySearch", String.class);
//                        break;
//                }
                DataBlock dataBlock;
                if (!Util.isIpAddress(ip)) {
                    System.out.println("Error: Invalid ip address");
                }
                dataBlock = (DataBlock) method.invoke(searcher, ip);

                if (inputStream != null) {
                    inputStream.close();
                }
                return dataBlock.getRegion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


}
