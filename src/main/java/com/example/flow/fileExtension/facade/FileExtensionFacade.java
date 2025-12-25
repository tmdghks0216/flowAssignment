package com.example.flow.fileExtension.facade;

import com.example.flow.fileExtension.entity.FileExtension;
import com.example.flow.fileExtension.service.FileExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileExtensionFacade {
    private final FileExtensionService fileExtensionService;

    public int insetCustomExtension(FileExtension fileExtension) {
        // 개수 제한 체크
        int currentCount = fileExtensionService.countCustomExtension();

        if(currentCount >= 200){
            log.warn("커스텀 확장자 개수 초과");
            return 10;
        }

        //중복체크
        if(fileExtensionService.isExistsExtension(fileExtension)){
            log.warn("커스텀 확장자 중복 추가 불가");
            return 11;
        }

        return fileExtensionService.insertFileExtension(fileExtension);
    }
}
