package com.mindtree;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mindtree.model.Example;
import com.mindtree.repository.ExampleRepository;
import com.mindtree.service.serviceImpl.ExampleServiceImpl;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class ExampleServiceImplTest {

	@InjectMock
    ExampleRepository exampleRepository;
	
	@Inject
	ExampleServiceImpl exampleServiceImpl;
	
    @Test
    public void testNameEndpoint() {
    	Assertions.assertEquals("Good morning Sample", exampleServiceImpl.starter("Sample"));
    }
    
    @Test
    public void findAllSuccessEndpoint() {
    	List<Example> testData= new ArrayList<Example>();
    	testData.add(new Example(1L, "Sample"));
    	testData.add(new Example(2L, "Simple"));
    	when(exampleRepository.findAll()).thenReturn(testData);
    	Assertions.assertEquals(2, exampleServiceImpl.findAll().size());
    	Assertions.assertEquals("Sample", exampleServiceImpl.findAll().get(0).getName());
    }
   
    @Test
    public void findOneValueExist() {
    	when(exampleRepository.findById(1L) ).thenReturn(Optional.of(new Example(1L, "Sample")));
        Assertions.assertEquals("Sample", exampleServiceImpl.findOne(1L).get().getName());
    }
    
    @Test
    public void createFailed() {
    	Example example = new Example(1L, "Sample");
    	Assertions.assertEquals(null, exampleServiceImpl.create(example));
    }
    
    @Test
    public void createSuccess() {
    	Example example1 = new Example("Sample");
    	Example example2 = new Example(1L, "Simple");
    	when(exampleRepository.save(example1)).thenReturn(example2);
    	Assertions.assertEquals("Simple", exampleServiceImpl.create(example1).getName());
    }
    
    @Test
    public void updateFailed() {
    	Example example = new Example("Sample");
    	Assertions.assertEquals(null, exampleServiceImpl.update(example));
    }
    
    @Test
    public void updateSuccess() {
    	Example example = new Example(1L, "Sample");
    	Example example2 = new Example(1L, "Simple");
    	when(exampleRepository.save(example)).thenReturn(example2);
    	Assertions.assertEquals("Simple", exampleServiceImpl.update(example).getName());
    }
    
    @Test
    public void deleteFailed() {
    	Optional<Example> empty = Optional.empty();
    	when(exampleRepository.findById(1L) ).thenReturn(empty);
        Assertions.assertEquals(empty, exampleServiceImpl.delete(1L));
    }
    
    @Test
    public void deleteSuccess() {
    	when(exampleRepository.findById(1L) ).thenReturn(Optional.of(new Example(1L, "Sample")));
    	doNothing().when(exampleRepository).deleteById(1L);
        Assertions.assertEquals("Sample", exampleServiceImpl.delete(1L).get().getName());
    }

}
