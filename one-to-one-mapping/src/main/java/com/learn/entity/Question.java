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
@Table(name = "Question")
public class Question  implements Serializable{

    @Id
    @Column(name = "id")
    private Long question_id;

    @Column(name = "question")
    private String question;

       /* ONE_directional mapping, open this last 3 line comments for one direction mapping
     operation flow:
     step 1 -create table answer
     step2- create table question
     step3- Alter command run for question , to add foreign key (fk_answer_id) references answer
     */
    //fk_answer_id is the join column in Question Table
   //   @JoinColumn(name = "fk_answer_id")
   //   @OneToOne(cascade = CascadeType.ALL) //Here one direction ,mapping Answer ---> Question
   //   private Answer answer;


    /*
     Bidrectional mapping from ANswer to question
    */
    //fk_answer_id is the join column in Question Table
    @JoinColumn(name = "fk_answer_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference// Use only in bidrectional mapping , Answer ->Question
    private Answer answer;


}
