package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "OWNER_DETAILS")

public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;


    public Owner() {

    }

    //One owner write multiple blog
    //mappedBy="owner" ,tells that relationship will maintain by Blog class owner column
    @OneToMany(cascade = CascadeType.ALL,mappedBy="owner",fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude//creating problem while bidirection mapping
    private List<Blog> blogList;


    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}