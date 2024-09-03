package org.example.hackathon_test.Calendar.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitPostId", referencedColumnName = "id", nullable = false)
    private RecruitPost recruitPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "eventTime", nullable = false)
    private String eventTime;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;
}