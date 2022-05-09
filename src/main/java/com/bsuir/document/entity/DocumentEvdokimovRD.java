package com.bsuir.document.entity;

import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Table(name = "document")
@Entity
@Proxy(lazy = false)
public class DocumentEvdokimovRD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="process_id")
    @JsonBackReference
    private ProcessEvdokimovRD process;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProcessEvdokimovRD getProcess() {
        return process;
    }

    public void setProcess(ProcessEvdokimovRD process) {
        this.process = process;
    }
}
