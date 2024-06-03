package com.tien.blogapplication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String username;
    private String email;
    private List<String> categories;
    private String photo;
    private String timeStamp;

    public PostResponse(Long id, String title, String description, String username, String email, List<String> categories, String timeStamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.email = email;
        this.categories = categories;
        this.timeStamp = timeStamp;
    }


    public PostResponse(Long id, String title, String description, String username, String email, List<String> categories, byte[] photoBytes, String timeStamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.email = email;
        this.categories = categories;
        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
        this.timeStamp = timeStamp;
    }

//    public RoomResponseDto(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, byte[] photoBytes) {
//        this.id = id;
//        this.roomType = roomType;
//        this.roomPrice = roomPrice;
//        this.isBooked = isBooked;
//        this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
//    }
}
