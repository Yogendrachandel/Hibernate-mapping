package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "BLOG_DETAILS")
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;


    @ManyToOne(cascade = CascadeType.ALL)//Many Blog belongs to one Owner
    @JoinColumn(name = "fk_owner_id")//Create forignkey in Blog table
    @JsonBackReference
    @ToString.Exclude//creating problem while bidirection mapping
    private Owner owner;


    public Blog() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "category='" + category + '\'' +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", owner=" + owner +
                '}';
    }
}