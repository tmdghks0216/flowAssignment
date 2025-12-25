package com.example.flow;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class FlowApplicationTests {

	@Autowired
	private FileExtensionService fileExtensionService;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		Map<String, List<FileExtension>> data = fileExtensionService.selectBlockExtensionList();

		List<FileExtension> fixList = data.getOrDefault("FIX", Collections.emptyList());
		List<FileExtension> customList = data.getOrDefault("CUSTOM", Collections.emptyList());

		for (FileExtension fileExtension : fixList) {
			log.info(fileExtension.toString());
		}
	}
}
