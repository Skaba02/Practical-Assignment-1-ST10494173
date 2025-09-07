
package com.myapp.test;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import tv.series.SeriesModel;
import tv.series.TVSeriesManager;

public class TVSeriesManagerTest {
    private TVSeriesManager manager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUp() {
        manager = new TVSeriesManager();
        // Redirect System.out to capture print statements
        System.setOut(new PrintStream(outContent));
        // Add a sample series for testing
        manager.addSeries(new SeriesModel("S1", "Breaking Bad", 16));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testSearchSeries_Found() {
        outContent.reset();
        manager.searchSeries("S1");
        assertTrue("Should find Breaking Bad", outContent.toString().contains("Breaking Bad"));
    }
    
    @Test
    public void testSearchSeries_NotFound() {
        outContent.reset();
        manager.searchSeries("XYZ");
        assertTrue("Should show not found message", outContent.toString().contains("No series found"));
    }
    
    @Test
    public void testUpdateSeries_ValidAge() {
        outContent.reset();
        manager.updateSeriesAge("S1", "12");
        assertTrue("Should show success message", outContent.toString().contains("Age restriction updated"));
    }
    
    @Test
    public void testUpdateSeries_InvalidAgeTooHigh() {
        outContent.reset();
        manager.updateSeriesAge("S1", "25");
        assertTrue("Should show age range error", outContent.toString().contains("Age must be between"));
    }
    
    @Test
    public void testUpdateSeries_InvalidAgeTooLow() {
        outContent.reset();
        manager.updateSeriesAge("S1", "1");
        assertTrue("Should show age range error", outContent.toString().contains("Age must be between"));
    }
    
    @Test
    public void testUpdateSeries_InvalidAgeFormat() {
        outContent.reset();
        manager.updateSeriesAge("S1", "abc");
        assertTrue("Should show invalid input error", outContent.toString().contains("Invalid age input"));
    }
    
    @Test
    public void testDeleteSeries_Found() {
        outContent.reset();
        manager.deleteSeries("S1");
        assertTrue("Should show deletion success", outContent.toString().contains("Series deleted"));
    }
    
    @Test
    public void testDeleteSeries_NotFound() {
        outContent.reset();
        manager.deleteSeries("XYZ");
        assertTrue("Should show not found error", outContent.toString().contains("Series ID not found"));
    }
}