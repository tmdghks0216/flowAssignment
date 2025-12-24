package com.example.flow.fileExtension.service;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.repository.FileExtensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileExtensionService {

    private final FileExtensionMapper extensionMapper;

    public Map<String, List<FileExtension>> selectBlockExtensionList() {
        List<FileExtension> extensionList = extensionMapper.selectFileExtensionList();

        return extensionList.stream()
                .collect(Collectors.groupingBy(FileExtension::getType));
    }
}
