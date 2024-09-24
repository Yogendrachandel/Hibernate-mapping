package com.learn.Controller;

import com.learn.entity.Blog;
import com.learn.entity.Owner;

import com.learn.repository.BlogRespository;
import com.learn.repository.OwnerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class BlogToOwnerMappingController {

    @Autowired
    private OwnerRespository ownerRespository;


    @Autowired
    private BlogRespository blogRespository;

    @PostMapping("/save")
    public ResponseEntity<?> saveBlobWithOwner() {

        //Create one owner
        Owner owner =new Owner();
        owner.setName("Rakesh");

        //create one blog
        Blog blog =new Blog();
        blog.setCategory("CAR");
        blog.setContent("Maruti is popular in india");
        blog.setOwner(owner); //because two way mapping

        //create 2nd blog
        Blog blog2 =new Blog();
        blog2.setCategory("JAVA");
        blog2.setContent("Java is popular in world");
        blog2.setOwner(owner); //because two way mapping


        List<Blog> list= new ArrayList<>();
        list.add(blog);
        list.add(blog2);

        //because two way mapping
        owner.setBlogList(list);


        ownerRespository.save(owner);


        return ResponseEntity.status(HttpStatus.CREATED).
                body("Owner with its Blogs created");
    }


    @GetMapping("/getByOwner")
    public ResponseEntity<?> getBlogsByOwner() throws Exception {

        //find by owner to blob
        Optional<Owner>owner= ownerRespository.findById(1);

        if(null == owner.get()){
            throw new Exception("Invalid");
        }



        return ResponseEntity.status(HttpStatus.CREATED).
                body(owner);
    }

    @GetMapping("/getByBlogs")
    public ResponseEntity<?> getOwnerByAllBlogs() throws Exception {

        Blog dbBlog =null;
        Owner dbOwner=null;

        //find by blob to owner
        Optional<Blog>blob= blogRespository.findById(1);

        if(null == blob){
            throw new Exception("Invalid");
        }else{
            dbBlog= blob.get();
            dbOwner= new Owner();
            dbOwner.setId(dbBlog.getOwner().getId());
            dbOwner.setName(dbBlog.getOwner().getName());
            dbOwner.setBlogList(dbBlog.getOwner().getBlogList());

        }

         dbBlog.setOwner(dbOwner);

        System.out.println(" OWNER DETAILS::: -"+dbBlog.getOwner().getId() +"\n"
           +dbBlog.getOwner().getName()+"\n"
        + dbBlog.getOwner().getBlogList());

        return ResponseEntity.status(HttpStatus.CREATED).
                body(dbBlog);
    }


}
