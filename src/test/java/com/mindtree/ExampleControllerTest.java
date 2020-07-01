package com.mindtree;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mindtree.controller.ExampleController;
import com.mindtree.model.Example;
import com.mindtree.service.ExampleService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class ExampleControllerTest {

	@InjectMock
    ExampleService exampleService;
	
	@Inject
	ExampleController exampleController;
	
    @Test
    public void testNameEndpoint() {
    	when(exampleService.starter("Sample")).thenReturn("Good morning Sample");
    	Assertions.assertEquals("Good morning Sample", exampleController.starter("Sample").getEntity().toString());
    }
    
    @Test
    public void findAllNoDataEndpoint() {
    	when(exampleService.findAll()).thenReturn(new ArrayList<Example>());
        Assertions.assertEquals(204, exampleController.findAll().getStatus());
    }
    
    @Test
    public void findAllSuccessEndpoint() {
    	List<Example> testData= new ArrayList<Example>();
    	testData.add(new Example(1L, "Sample"));
    	testData.add(new Example(2L, "Simple"));
    	when(exampleService.findAll()).thenReturn(testData);
    	Assertions.assertEquals(2, ((List<Example>)exampleController.findAll().getEntity()).size());
    	Assertions.assertEquals("Sample", ((List<Example>)exampleController.findAll().getEntity()).get(0).getName());
    }
    
    @Test
    public void findOneValueNotExist() {
    	Optional<Example> empty = Optional.empty();
    	when(exampleService.findOne(1L) ).thenReturn(empty);
        Assertions.assertEquals(404, exampleController.findOne(1L).getStatus());
    }
    
    @Test
    public void findOneValueExist() {
    	when(exampleService.findOne(1L) ).thenReturn(Optional.of(new Example(1L, "Sample")));
        Assertions.assertEquals("Sample", ((Optional<Example>)exampleController.findOne(1L).getEntity()).get().getName());
    }
    
    @Test
    public void createFailed() {
    	Example example = new Example(1L, "Sample");
    	when(exampleService.create(example)).thenReturn(null);
    	Assertions.assertEquals(409, exampleController.create(example).getStatus());
    }
    
    @Test
    public void createSuccess() {
    	Example example = new Example(1L, "Sample");
    	when(exampleService.create(example)).thenReturn(example);
    	Assertions.assertEquals("Sample", ((Example)exampleController.create(example).getEntity()).getName());
    }
    
    @Test
    public void updateFailed() {
    	Example example = new Example(1L, "Sample");
    	when(exampleService.update(example)).thenReturn(null);
    	Assertions.assertEquals(404, exampleController.update(example).getStatus());
    }
    
    @Test
    public void updateSuccess() {
    	Example example = new Example(1L, "Sample");
    	when(exampleService.update(example)).thenReturn(example);
    	Assertions.assertEquals("Sample", ((Example)exampleController.update(example).getEntity()).getName());
    }
    
    @Test
    public void deleteFailed() {
    	Optional<Example> empty = Optional.empty();
    	when(exampleService.delete(1L) ).thenReturn(empty);
        Assertions.assertEquals(404, exampleController.delete(1L).getStatus());
    }
    
    @Test
    public void deleteSuccess() {
    	when(exampleService.delete(1L) ).thenReturn(Optional.of(new Example(1L, "Sample")));
        Assertions.assertEquals("Sample", ((Optional<Example>)exampleController.delete(1L).getEntity()).get().getName());
    }

}