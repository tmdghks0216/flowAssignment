package com.example.flow.fileExtension.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FileExtension {
    private int id;
    private String extension;
    private String type;
    private String enable;
    private Date createDt;
    private Date updateDt;
}
