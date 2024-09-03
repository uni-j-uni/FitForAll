package org.example.hackathon_test.RecruitPost.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "RecruitPost")
public class RecruitPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "currentRecruit")
    private Integer currentRecruit;

    @Column(name = "totalRecruit")
    private Integer totalRecruit;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "eventTime", nullable = false)
    private String eventTime;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "view")
    private Integer view;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "recruitPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "recruitPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RPComment> rpComments;
}