package org.example.hackathon_test.User.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hackathon_test.CPComment.Entity.CPComment;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;
import org.example.hackathon_test.CoachPost.Entity.CoachPost;
import org.example.hackathon_test.FPComment.Entity.FPComment;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.Recommend.Entity.Recommend;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.Review.Entity.Review;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "isFirst", nullable = false)
    private Boolean isFirst = true;

    @Column(name = "isCoach")
    private Boolean isCoach;

    @CreationTimestamp
    @Column(name="createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name="modifyDate")
    private LocalDateTime modifyDate;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserInfo userInfo;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private CoachInfo coachInfo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitPost> recruitPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RPComment> rpComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calendar> calendars;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreePost> freePosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FPComment> fpComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommend> recommends;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoachPost> coachPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CPComment> cpComments;
}