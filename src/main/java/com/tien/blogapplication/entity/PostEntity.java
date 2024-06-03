package com.tien.blogapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "username")
    private String username;
//    @Column(name = "userid")
//    private String userId;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @Column(name = "timeStamp")
    private String timeStamp;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
}

