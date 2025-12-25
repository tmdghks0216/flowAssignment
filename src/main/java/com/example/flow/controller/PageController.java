package com.example.flow.controller;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/set")
@RequiredArgsConstructor
public class PageController {

    private final FileExtensionService fileExtensionService;

    @GetMapping(value = "/extension")
    public  String extensionList(ModelMap model){
        Map<String, List<FileExtension>> extensionMap =  fileExtensionService.selectBlockExtensionList();

        model.addAttribute("fixList", extensionMap.getOrDefault("FIX", Collections.emptyList()));
        model.addAttribute("customList", extensionMap.getOrDefault("CUSTOM", Collections.emptyList()));
        return "extensionList";
    }
}
