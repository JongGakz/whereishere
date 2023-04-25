package twogtwoj.whereishere.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    private static final int MAX_UPLOAD_FILE_COUNT = 2;

    private boolean isExceedMaxUploadFileCount(MultipartHttpServletRequest request) {
        return request.getMultiFileMap().size() > MAX_UPLOAD_FILE_COUNT;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {// 다중 파일
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile)); // 리스트에 넣어줌
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {//저장하는 로직
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);//저장할 떄는 originalFilename 가 아닌 고유한 값을 저장. 확장자까지 저장함
        multipartFile.transferTo(new File(getFullPath(storeFileName))); // 고유한 파일명을 만들어줌. 서버에 이 이름으로 저장됨

        return new UploadFile(originalFilename, storeFileName); // 객체에 던져줌
    }

    public void updateFile(String orginalStoreFileName, MultipartFile multipartFile) throws IOException {//저장하는 로직

        multipartFile.transferTo(new File(getFullPath(orginalStoreFileName))); // 고유한 파일명을 만들어줌. 서버에 이 이름으로 저장됨
        // 객체에 던져줌
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
