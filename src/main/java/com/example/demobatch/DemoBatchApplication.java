package com.example.demobatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class DemoBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBatchApplication.class, args);

		// 実行環境のホームディレクトリを取得
		// String homeDir = System.getProperty("user.home");

		// 標準出力をログに残す
		Logger logger = LoggerFactory.getLogger(DemoBatchApplication.class);
		logger.info("hello world");

		// hello.txt ファイルを作成
		// File file = new File(homeDir, "hello.txt");
		File file = new File("/tmp", "hello.txt");
		try {
			file.createNewFile();

			FileWriter writer = new FileWriter(file);
			writer.write("hello world\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
