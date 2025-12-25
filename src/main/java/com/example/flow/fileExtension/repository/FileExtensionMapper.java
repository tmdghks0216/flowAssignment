package com.example.flow.fileExtension.repository;

import com.example.flow.fileExtension.entity.FileExtension;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileExtensionMapper {
    List<FileExtension> selectFileExtensionList();
    int updateFixExtension(FileExtension fileExtension);
    int insertFileExtension(FileExtension fileExtension);
    int countCustomExtension();
    boolean isExistsExtension(FileExtension fileExtension);
}
