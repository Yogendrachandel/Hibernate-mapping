package com.learn.repository;

import com.learn.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRespository  extends JpaRepository<Owner,Integer>{
}