package twogtwoj.whereishere.file;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;// 고객이 업로드한 파일명. 고유한 파일명이 아니고 충돌할 수 있음. ex. evian.jpg
    private String storeFileName;// 서버에서 내부적으로 관리하는 파일명

       public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
