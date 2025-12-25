package com.example.flow.fileExtension.controller;

import com.example.flow.fileExtension.code.statusCodeEnum;
import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.facade.FileExtensionFacade;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

        statusCodeEnum result = fileExtensionService.updateFixExtension(fileExtension);

        Map<String, Object> response = new HashMap<>();
        response.put("code", result.getCode());
        response.put("msg", result.getMsg());

        return  response;
    }

    @PostMapping("/insert/customExtension")
    public  Map<String, Object> insertCustomExtension(@RequestBody FileExtension fileExtension) {
        log.info("(insertCustomExtension) RequestBody = {}", fileExtension);

        statusCodeEnum result = facade.insetCustomExtension(fileExtension);

        Map<String, Object> response = new HashMap<>();
        response.put("code", result.getCode());
        response.put("msg", result.getMsg());

        return response;
    }

    @DeleteMapping("/delete/customExtension")
    public  Map<String, Object> deleteCustomExtension(@RequestBody FileExtension fileExtension) {
        log.info("(deleteCustomExtension) RequestBody = {}", fileExtension);

        fileExtensionService.deleteFileExtension(fileExtension);

        Map<String, Object> response = new HashMap<>();
        response.put("code", statusCodeEnum.SUCCESS.getCode());
        response.put("msg", statusCodeEnum.SUCCESS.getMsg());

        return response;
    }
}
