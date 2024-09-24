package com.learn.Controller;

import com.learn.entity.Answer;
import com.learn.entity.Question;
import com.learn.repository.AnswerQuestionRepository;
import com.learn.repository.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/bi-direction")
public class AnswerToQuestionBiDirectionMappingController {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;


    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;


    @PostMapping("/save")
    public ResponseEntity<?> saveQuestionAnswer() {

        //First Insert Answer into DB
        Answer answer = new Answer();
        answer.setId(101L);
        answer.setAnswer("Java is programing language");

        //second Insert Question into DB
        Question question =new Question(100L,"What is JAVA",answer);

        answer.setQuestion(question);

        questionAnswerRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).
                body("Question/Answer both created");

    }




    @PutMapping("/update")
    public ResponseEntity<?> updateQuestionAnswer() {

        Question question = null;
        Answer answer=null;

        Optional<Question> optional = questionAnswerRepository.findById(100L);

        if (optional.get() != null) {
            question = optional.get();
            answer =question.getAnswer();

        }

        question.setQuestion("Is java is programing language?");
        answer.setAnswer("Yes,java is programing language");

        //First answer update then question
        questionAnswerRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).
                body("Question/Answer both  Updated");

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteQuestionAnswer() {

        Question question = null;

        Optional<Question> optional = questionAnswerRepository.findById(100L);

        if (optional.get() != null) {
            question = optional.get();

        }

        //First question ,then answer will delete
        questionAnswerRepository.delete(question);
        return ResponseEntity.status(HttpStatus.CREATED).
                body("Question/Answer both deleted");

    }

    @GetMapping("/get")
    public ResponseEntity<?> getAnswertoQuestionBiDirectional() {

        Answer answer = null;

        //Note - here we are fetching records from Answer -> question
        Optional<Answer> optional = answerQuestionRepository.findById(101L);

        if (optional.get() != null) {
            answer = optional.get();

        }


        return ResponseEntity.status(HttpStatus.CREATED).
                body(answer);

    }


}
