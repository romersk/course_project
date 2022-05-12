package com.bsuir.process.entity;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Table(name = "process")
@Entity
@Proxy(lazy = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessEvdokimovRD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private UserEvdokimovRD user;

    @Column(name = "start_id")
    private Long startId;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status = "Принят в обработку";

    @Column(name = "recommendations")
    private String recommendations;

    @OneToMany(mappedBy="process")
    @JsonManagedReference
    private List<DocumentEvdokimovRD> document;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEvdokimovRD getUser() {
        return user;
    }

    public void setUser(UserEvdokimovRD user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public List<DocumentEvdokimovRD> getDocument() {
        return document;
    }

    public void setDocument(List<DocumentEvdokimovRD> document) {
        this.document = document;
    }

    public Long getStartId() {
        return startId;
    }

    public void setStartId(Long startId) {
        this.startId = startId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
