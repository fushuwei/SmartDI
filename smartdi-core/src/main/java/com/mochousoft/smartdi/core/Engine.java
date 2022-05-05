package com.mochousoft.smartdi.core;

import com.mochousoft.smartdi.common.exception.SDIException;
import com.mochousoft.smartdi.common.exception.impl.GlobalErrorCode;
import com.mochousoft.smartdi.common.meta.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 入口类
 */
public class Engine {

    private static final Logger logger = LoggerFactory.getLogger(Engine.class);

    /**
     * 程序入口
     *
     * @param args
     */
    public static void entry(final String[] args) {
        // 输出环境信息
        outputEnvInfo();

        // 校验
    }

    /**
     * 开始执行
     *
     * @param config 任务配置
     */
    public static void start(Configuration config) {
    }

    public static void outputEnvInfo() {
        StringBuilder sb = new StringBuilder("\n\n========================= 环境信息 =========================");

        sb.append("\n操作系统: ").append(System.getProperty("os.name")).append(" (").append(System.getProperty("os.version")).append(")");
        sb.append("\nJDK版本: ").append(System.getProperty("java.version"));
        sb.append("\nSDI_HOME: ").append(System.getProperty("sdi.home"));
        sb.append("\n日志文件: ").append(System.getProperty("log.dir")).append("/").append(System.getProperty("log.filename")).append(".log");
        sb.append("\n日志级别: ").append(System.getProperty("log.level"));

        sb.append("\n\n========================= JVM信息 =========================");

        /*
        	osInfo:	Oracle Corporation 1.8 25.162-b12
            jvmInfo:	Windows 10 amd64 10.0
            cpu num:	8

            totalPhysicalMemory:	-0.00G
            freePhysicalMemory:	-0.00G
            maxFileDescriptorCount:	-1
            currentOpenFileDescriptorCount:	-1

            GC Names	[PS MarkSweep, PS Scavenge]

            MEMORY_NAME                    | allocation_size                | init_size
            PS Eden Space                  | 1,335.50MB                     | 64.00MB
            Code Cache                     | 240.00MB                       | 2.44MB
            Compressed Class Space         | 1,024.00MB                     | 0.00MB
            PS Survivor Space              | 10.50MB                        | 10.50MB
            PS Old Gen                     | 2,713.50MB                     | 171.00MB
            Metaspace                      | -0.00MB                        | 0.00MB
         */

        sb.append("\n\n========================= 任务信息 =========================");
        // sb.append("\n接口名称: ").append("abc");

        sb.append("\n");
        logger.info(sb.toString());
    }

    public static void main(String[] args) {
        int exitStatus = 0;

        try {
            Engine.entry(args);
        } catch (Throwable cause) {
            if (cause instanceof SDIException) {
                logger.error("SDI执行异常", cause);
            } else {
                logger.error("SDI执行异常", SDIException.newInstance(GlobalErrorCode.DEFAULT_ERROR, cause));
            }
            exitStatus = 1;
        }

        System.exit(exitStatus);
    }
}
