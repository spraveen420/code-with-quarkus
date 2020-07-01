package com.mindtree.service.serviceImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.mindtree.model.Example;
import com.mindtree.repository.ExampleRepository;
import com.mindtree.service.ExampleService;

@ApplicationScoped
public class ExampleServiceImpl implements ExampleService {
	
	@ConfigProperty(name="Greeting")
	private String greeting;
	
	@ConfigProperty(name="upload.path")
	private Path uploadFolder;
	
	@Inject
	private ExampleRepository exampleRepository;

	public String starter(String name) {
		
		return greeting + " " + name;
	}

	public List<Example> findAll() {
		return exampleRepository.findAll();
	}

	@Transactional
	public Example create(Example example) {
		if(example.getId() != null) {
			return null;
		}
		return exampleRepository.save(example);
	}
	
	@Transactional
	public Example update(Example example) {
		if(example.getId() == null) {
			return null;
		}
		return exampleRepository.save(example);
	}
	
	public Optional<Example> findOne(Long id) {
		return exampleRepository.findById(id);
	}
	
	@Transactional
	public Optional<Example> delete(Long id) {
		Optional<Example> searchResult = findOne(id);
		if(!searchResult.isPresent()) {
			return Optional.empty();
		}
		exampleRepository.deleteById(id);
		return searchResult;
	}

//	@Transactional
//	public MultipartExample fileUpload(MultipartExample fileData) {
//		 try {
//			if (fileData.getFile().read() == -1) {
//				 return null;
//			    }
//			System.out.println(Paths.get(uploadFolder + "/" +fileData.getFileName()));
//			
//			Files.copy(fileData.getFile(), Paths.get(uploadFolder + "/" +fileData.getFileName()),
//                    StandardCopyOption.REPLACE_EXISTING);
//			
//		} catch (IOException e) {
//			return null;
//		}
//		return null;
//	}

}
