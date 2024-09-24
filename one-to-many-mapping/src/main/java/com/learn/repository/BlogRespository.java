package com.learn.repository;

import com.learn.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRespository extends JpaRepository<Blog,Integer>{
}