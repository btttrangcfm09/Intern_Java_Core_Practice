package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.stereotype.*;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface ReferenceFinderRepository extends CrudRepository<Film,Integer>{
    
}
