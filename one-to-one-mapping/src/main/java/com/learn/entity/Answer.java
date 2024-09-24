package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Answer")
public class Answer  implements Serializable{

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "answer")
    private String answer;



       /*
    To make bidirectional relationship
    step 1 - If we only pass @OnetoOne then Answer table will also create one  Forignkey column and store the PK of Question into this column

    Step 2- @OneToOne(mappedBy = "answer"), by using (mappedBy = "answer") this - no extra column created into Answer table , because now mappedby
    telling that relationship will be maintained by  answer field declare in Question table
     */

    @OneToOne(mappedBy = "answer",cascade = CascadeType.ALL)
    @JsonManagedReference //to avoid infinite loops when circular dependecny
    Question question;


}

/*
The @JsonManagedReference annotation is used in Jackson to handle bidirectional
relationships between entities, preventing infinite recursion during serialization.
 When you have two entities with a parent-child relationship, you can use @JsonManagedReference on the parent side
and @JsonBackReference on the child side.

Here’s a simple example:

Java

public class User {
    public int id;
    public String name;

    @JsonManagedReference
    public List<Item> userItems;
}

public class Item {
    public int id;
    public String itemName;

    @JsonBackReference
    public User owner;
}
AI-generated code. Review and use carefully. More info on FAQ.
In this setup:

@JsonManagedReference is placed on the parent reference (userItems in User), which will be serialized normally.
@JsonBackReference is placed on the child reference (owner in Item), which will be omitted during serialization to avoid infinite loops.
This way, Jackson can serialize and deserialize these entities without running into issues with circular references.
 */