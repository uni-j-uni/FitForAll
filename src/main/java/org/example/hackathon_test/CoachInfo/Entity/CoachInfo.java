package org.example.hackathon_test.CoachInfo.Entity;

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
@Table(name = "CoachInfo")
public class CoachInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "career", nullable = false)
    private String career;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "normalLicense")
    private String normalLicense;

    @Column(name = "sportsLicense")
    private String sportsLicense;

    @Column(name = "cprLicense")
    private String cprLicense;

    @Column(name = "etcLicense")
    private String etcLicense;

    @CreationTimestamp
    @Column(name="createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name="modifyDate")
    private LocalDateTime modifyDate;
}