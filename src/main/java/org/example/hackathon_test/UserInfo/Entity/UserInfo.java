package org.example.hackathon_test.UserInfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.User.Entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "UserInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "isGuardian", nullable = false)
    private Boolean isGuardian;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "disabilityCF", nullable = false)
    private String disabilityCF;

    @Column(name = "disabilityK", nullable = false)
    private String disabilityK;

    @Column(name = "disabilityKK", nullable = false)
    private String disabilityKK;

    @Column(name = "muscle")
    private Boolean muscle;

    @Column(name = "stretching")
    private Boolean stretching;

    @Column(name = "ball")
    private Boolean ball;

    @Column(name = "water")
    private Boolean water;

    @Column(name = "cardio")
    private Boolean cardio;

    @Column(name = "exerciseIntensity")
    private String exerciseIntensity;

    @CreationTimestamp
    @Column(name="createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name="modifyDate")
    private LocalDateTime modifyDate;
}