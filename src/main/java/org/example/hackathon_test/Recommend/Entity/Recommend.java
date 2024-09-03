package org.example.hackathon_test.Recommend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Recommend")
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freePostId", referencedColumnName = "id", nullable = false)
    private FreePost freePost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "recommendType", nullable = false)
    private String recommendType;

    @Column(name = "isRecommend", nullable = false)
    private Boolean isRecommend = false;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;
}