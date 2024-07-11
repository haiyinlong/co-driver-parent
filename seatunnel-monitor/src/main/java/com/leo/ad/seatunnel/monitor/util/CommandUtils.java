package com.leo.ad.seatunnel.monitor.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * CommandUtils
 *
 * @author HaiYinLong
 * @version 2024/07/11 09:57
 **/
@Slf4j
public class CommandUtils {


    public static String executeCommand(String workingDir, String cmd) {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
            builder.directory(new File(workingDir));
            Process process = builder.start();
            // 获取进程的标准输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            // 输出命令执行的结果
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            // 获取错误输出
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            while ((line = errorReader.readLine()) != null) {
                log.warn("Error: " + line);
            }
            // 等待进程执行完成并获取退出状态
            int exitCode = process.waitFor();
            log.info("Command executed with exit code: " + exitCode);
            // 关闭流
            reader.close();
            inputStream.close();
            errorReader.close();
            errorStream.close();
        } catch (IOException | InterruptedException e) {
            log.error("执行命令异常", e);
        }
        return output.toString();
    }

}
