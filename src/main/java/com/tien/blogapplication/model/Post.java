package com.tien.blogapplication.model;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String title;
    private String description;
    private String username;
    private String email;
    private List<String> categories;
    private Blob photo;
    private MultipartFile photoUpload;
    private String timeStamp;
}
