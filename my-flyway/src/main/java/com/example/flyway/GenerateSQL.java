package com.example.flyway;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 生成SQL文件
 */
public class GenerateSQL {
    public static void main(String[] args) {
        String content = "INSERT INTO `user` (`id`, `name`, `age`, `email`) VALUES (%s, '测试多文件', 00, '123@abc.com');";
        String path = "./db/migration/004测试多文件性能/";
        String fileName = "V20221212_%s__测试多文件.sql";
        for (int i = 10000; i < 12000; i++) {
//            FileUtil.writeString(content, path+i+".sql", StandardCharsets.UTF_8);
            FileUtil.writeUtf8String(String.format(content, i),
                    path + String.format(fileName, StrUtil.padPre("" + i, 5, "0"))); // V20221019_001__插入数据002
        }
        
    }
}
