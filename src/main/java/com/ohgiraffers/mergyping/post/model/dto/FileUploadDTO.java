package com.ohgiraffers.mergyping.post.model.dto;

public class FileUploadDTO {
    private String filePath;
    private String fileDescription;
    private String originFileName;

    public FileUploadDTO() {
    }

    public FileUploadDTO(String filePath, String fileDescription, String originFileName) {
        this.filePath = filePath;
        this.fileDescription = fileDescription;
        this.originFileName = originFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    @Override
    public String toString() {
        return "FileUploadDTO{" +
                "filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", originFileName='" + originFileName + '\'' +
                '}';
    }
}
