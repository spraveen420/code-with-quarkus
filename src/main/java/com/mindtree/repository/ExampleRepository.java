package com.mindtree.repository;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.model.Example;

@ApplicationScoped
public interface ExampleRepository extends JpaRepository<Example, Long>{

}
