package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import layer2.Layer2;

import org.junit.Test;

public class TestJsoup {	

	ArrayList<String> sources = new ArrayList<String>();
	
	@Test
	public void testContent() throws IOException {
		String testURL="http://nanooq.org/test.html";
		String expected="hello world!";
		String actual = Layer2.read(testURL).text();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testOnline() throws IOException {
		String testURL="http://nanooq.org/test.html";
		Layer2.read(testURL);
	}
}
