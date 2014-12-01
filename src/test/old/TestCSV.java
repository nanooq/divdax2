package test.old;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import layer2.CSV;
import layer2.Layer2;

import org.junit.Test;

public class TestCSV {
	
	@Test
	public void testHeader() throws IOException{
		File testFile = new File("TestIO.testHeader");
		String aLine = "Hello,World!";
		String bLine = "Hello,World!";
		String regex = ",";
		String[] testHeader = {"iamaheader"};
		CSV testCSV = new CSV(testFile, testHeader);
		testCSV.add(aLine,regex);
		testCSV.add(bLine,regex);
		try {
			Layer2.write(testCSV);			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		String expected = "iamaheader," + CSV.NL + "Hello,World!," + CSV.NL + "Hello,World!,";
		String actual = Layer2.read(testFile);
		assertEquals(expected, actual);
		Layer2.delete(testFile);
	}
	
	@Test
	public void testWriteRead2LinesCSV() throws IOException{
		File testFile = new File("TestIO.testWriteRead2LinesCSV");
		String aLine = "Hello,World!";
		String bLine = "Hello,World!";
		String regex = ",";
		String[] testHeader = {"iamaheader"};
		CSV testCSV = new CSV(testFile, testHeader);
		testCSV.add(aLine,regex);
		testCSV.add(bLine,regex);
		try {
			Layer2.write(testCSV);			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		String expected = "iamaheader," + CSV.NL + "Hello,World!," + CSV.NL + "Hello,World!,";
		String actual = Layer2.read(testFile);
		assertEquals(expected, actual);
		Layer2.delete(testFile);
	}
	
	@Test
	public void testWriteNewReadCSV() throws IOException{
		File testFile = new File("TestIO.testWriteNewReadCSV");
		String aLine = "Hello,World!";
		String regex = ",";
		String[] testHeader = {"iamaheader"};
		CSV testCSV = new CSV(testFile, testHeader);
		testCSV.add(aLine,regex);
		Layer2.delete(testCSV);
		try {
			Layer2.writenew(testCSV);			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		boolean alreadyExists = Layer2.exists(testCSV);
		try {
			Layer2.writenew(testCSV);			
		} catch (IOException e) {
			alreadyExists = true;
		}
		assertTrue(alreadyExists);
		String expected = "iamaheader," + CSV.NL + "Hello,World!,";
		String actual = Layer2.read(testFile);
		assertEquals(expected, actual);
		Layer2.delete(testFile);
	}
	
	@Test
	public void testWriteReadCSV() throws IOException{
		File testFile = new File("TestIO.testWriteReadCSV");
		String aLine = "Hello,World!";
		String regex = ",";
		String[] testHeader = {"iamaheader"};
		CSV testCSV = new CSV(testFile, testHeader);
		testCSV.add(aLine,regex);
		try {
			Layer2.write(testCSV);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		String expected = "iamaheader," + CSV.NL + "Hello,World!,";
		String actual = Layer2.read(testFile);
		assertEquals(expected, actual);
		Layer2.delete(testFile);
	}
}