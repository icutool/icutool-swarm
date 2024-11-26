package cn.icutool.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IFileUtil {
    /**
     * 判断文件的路径是否存在,不存在创建
     * @param path
     * @return
     */
    public static String judgeCreatePath(String path){
        // 获取文件的目录路径
        Path directoryPath = Paths.get(path).getParent();
        // 检查并创建路径
        try {
            createDirectoryIfNotExists(directoryPath);
            System.out.println("路径存在或已创建: " + directoryPath);
        } catch (IOException e) {
            System.err.println("创建路径失败: " + e.getMessage());
        }
        return path;
    }

    /**
     * 创建路径
     * @param path
     * @throws IOException
     */
    public static void createDirectoryIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
    public static void createDirectoryIfNotExists(String path) throws IOException {
        Path directoryPath = Paths.get(path);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }
}
