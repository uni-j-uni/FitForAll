package org.example.hackathon_test.FreePost.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FPComment.Entity.FPComment;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "FreePost")
public class FreePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "totalRecommend")
    private Integer totalRecommend;

    @Column(name = "totalNotRecommend")
    private Integer totalNotRecommend;

    @Column(name = "view")
    private Integer view;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "freePost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FPComment> FPComments;
}
