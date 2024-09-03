package org.example.hackathon_test.CoachPost.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CPComment.Entity.CPComment;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "CoachPost")
public class CoachPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "isAnswer", nullable = false)
    private Boolean isAnswer = false;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;

    @OneToOne(mappedBy = "coachPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private CPComment cpComment;
}
