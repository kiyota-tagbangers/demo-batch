package com.example.demobatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class DemoBatchApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoBatchApplication.class, args);

		// 標準出力をログに残す
		Logger logger = LoggerFactory.getLogger(DemoBatchApplication.class);
		logger.info("hello world");

		// ファイル名を生成
		String fileName = "hello-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());

		// 実行環境のホームディレクトリを取得
		Path homeDir = Paths.get(System.getProperty("user.home"));

		// ファイルオブジェクトを生成
		Path homeDirFile = homeDir.resolve(fileName + ".txt");
		Path tmpDirFile = Paths.get("/tmp", fileName + ".txt");

		// ファイルを作成と書き込みを同時に行う
		Files.write(
				homeDirFile,
				String.format("hello world by %s\n", System.getProperty("user.name")).getBytes(),
				StandardOpenOption.CREATE
		);
		Files.write(
				tmpDirFile,
				String.format("hello world by %s\n", System.getProperty("user.name")).getBytes(),
				StandardOpenOption.CREATE
		);
	}

}
