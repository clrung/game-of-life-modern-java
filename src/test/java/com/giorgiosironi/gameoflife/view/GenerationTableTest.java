package com.giorgiosironi.gameoflife.view;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenerationTableTest {

	@Test
	public void testRendersATableRepresentingAnAreaOfThePlaneStartingFromTheOrigin() {
		GenerationTable table = new GenerationTable(2, 3);
		assertEquals(
			"<table>"
			+ "<tr>"
			+ "<td></td>"
			+ "<td></td>"
			+ "<td></td>"
			+ "</tr>"
			+ "<tr>"
			+ "<td></td>"
			+ "<td></td>"
			+ "<td></td>"
			+ "</tr>"
			+ "</table>",
			table.toString()
		);
		
	}

}
