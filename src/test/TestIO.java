package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import layer2.Layer2;

import org.junit.Test;

public class TestIO {
	
	@Test
	public void testRead(){
		File testFile = new File("TestIO.testRead");
		try {
			String expectedContent = Layer2.write(
					testFile, "hello world!" + System.lineSeparator());
			String actualContent = Layer2.read(testFile);
			assertEquals(expectedContent, actualContent);			
		} catch (IOException e){
			e.printStackTrace();
			fail();
		}
		
		Layer2.delete(testFile);
	}
	
	@Test
	public void testDeleteExistCreateNew(){
		File testFile = new File("TestIO.testDeleteExistWrite");
		Layer2.delete(testFile);
		assertFalse(Layer2.exists(testFile));
		assertFalse(Layer2.delete(testFile));
		try {
			String expectedContent = Layer2.writenew(
					testFile, "hello world!" + System.lineSeparator());
			String actualContent = Layer2.read(testFile);
			assertEquals(expectedContent, actualContent);			
		} catch (IOException e){
			e.printStackTrace();
			fail();
		}
		boolean alreadyCreated = false;
		try {
			String expectedContent = Layer2.writenew(
					testFile, "hello world!" + System.lineSeparator());
			String actualContent = Layer2.read(testFile);
			assertEquals(expectedContent, actualContent);			
		} catch (IOException e){
			alreadyCreated = true;
		}
		assertTrue(alreadyCreated);
		assertTrue(Layer2.exists(testFile));
		assertTrue(Layer2.delete(testFile));	
		Layer2.delete(testFile);
	}
	
	@Test
	public void testDeleteExistWrite(){
		File testFile = new File("TestIO.testDeleteExistWrite");
		Layer2.delete(testFile);
		assertFalse(Layer2.exists(testFile));
		assertFalse(Layer2.delete(testFile));
		try {
			String expectedContent = Layer2.write(
					testFile, "hello world!" + System.lineSeparator());
			String actualContent = Layer2.read(testFile);
			assertEquals(expectedContent, actualContent);			
		} catch (IOException e){
			e.printStackTrace();
			fail();
		}
		assertTrue(Layer2.exists(testFile));
		assertTrue(Layer2.delete(testFile));	
		Layer2.delete(testFile);
	}
}