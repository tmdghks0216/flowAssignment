package com.example.flow.fileExtension.controller;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileExtensionRestController {

    final FileExtensionService fileExtensionService;

    @PostMapping("/update/fixExtension")
    public  Map<String, Object> updateFixExtension(@RequestBody FileExtension fileExtension) {
        log.info("(updateFixExtension) RequestBody = {}", fileExtension);

        return  fileExtensionService.updateFixExtension(fileExtension);
    }
}
