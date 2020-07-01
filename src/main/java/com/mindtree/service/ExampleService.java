package com.mindtree.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.mindtree.model.Example;

@ApplicationScoped
public interface ExampleService {

	public String starter(String name);
	public List<Example> findAll();
	public Example create(Example example);
	public Example update(Example example);
	public Optional<Example> findOne(Long id);
	public Optional<Example> delete(Long id);
//	public MultipartExample fileUpload(MultipartExample fileData);
}
