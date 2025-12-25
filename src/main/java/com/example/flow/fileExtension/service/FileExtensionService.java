package com.example.flow.fileExtension.service;

import com.example.flow.fileExtension.code.statusCodeEnum;
import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.repository.FileExtensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    @Transactional
    public statusCodeEnum updateFixExtension(FileExtension fileExtension) {
        Map<String, Object> rtnMap = new HashMap<>();
        int result = extensionMapper.updateFixExtension(fileExtension);

        if (result < 0) {
           return statusCodeEnum.FAIL;
        } else {
            return statusCodeEnum.SUCCESS;
        }
    }

    @Transactional
    public int insertFileExtension(FileExtension fileExtension) {

        return extensionMapper.insertFileExtension(fileExtension);
    }

    public int countCustomExtension(FileExtension fileExtension) {

        return extensionMapper.countCustomExtension(fileExtension);
    }

    public boolean isExistsExtension(FileExtension fileExtension) {

        return extensionMapper.isExistsExtension(fileExtension);
    }

    @Transactional
    public int deleteFileExtension(FileExtension fileExtension) {

        return extensionMapper.deleteFileExtension(fileExtension);
    }
}
