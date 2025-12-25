package com.example.flow.fileExtension.controller;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.facade.FileExtensionFacade;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileExtensionRestController {

    final FileExtensionService fileExtensionService;
    final FileExtensionFacade facade;

    @PostMapping("/update/fixExtension")
    public  Map<String, Object> updateFixExtension(@RequestBody FileExtension fileExtension) {
        log.info("(updateFixExtension) RequestBody = {}", fileExtension);

        return  fileExtensionService.updateFixExtension(fileExtension);
    }

    @PostMapping("/insert/customExtension")
    public  Map<String, Object> insertCustomExtension(@RequestBody FileExtension fileExtension) {
        log.info("(insertCustomExtension) RequestBody = {}", fileExtension);
        int result = facade.insetCustomExtension(fileExtension);
        Map<String, Object> response = new HashMap<>();
        response.put("code", result);

        if (result == 10){
            response.put("msg", "최대 200개까지만 등록 가능합니다.");
        } else if (result == 11){
            response.put("msg", "이미 등록된 확장자입니다.");
        } else {
            response.put("msg", "등록 성공!");
        }
        return response;
    }
}
