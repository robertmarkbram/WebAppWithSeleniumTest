package org.rmb.selenium.external;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rmb.selenium.SeleniumConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Test out my Selenium skills on a Wikipedia page.
 *
 * @author RobertMarkBram
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SeleniumConfig.class)
@TestExecutionListeners(
      listeners = { DependencyInjectionTestExecutionListener.class })
public final class TestWikipedia {

   /** URL to start tests with. */
   private static final String INITIAL_URL =
         "http://en.wikipedia.org/wiki/Main_Page";

   /** Logger for this class. */
   private static final Logger LOG = Logger.getLogger(TestWikipedia.class);

   /**
    * Use default webdriver.
    */
   @Autowired
   private WebDriver driver;

   /** Open to {@link #INITIAL_URL}. */
   @Before
   public void sertup() {
      driver.get(INITIAL_URL);
   }

   /**
    * Test that Star Trek page is about Star Trek.
    */
   @Test
   public void testStarTrekPage() {
      LOG.debug("Testing testStarTrekPage");

      // Get search box.
      WebElement searchBox = driver.findElement(By.id("searchInput"));
      searchBox.sendKeys("Star Trek");
      // Do the search.
      searchBox.submit();

      final String expected = "Star Trek - Wikipedia, the free encyclopedia";
      final String actual = driver.getTitle();

      assertEquals("Expected page title [" + expected + "] and actual ["
            + actual + "] were different but should be the same.",
            expected, actual);
      assertTrue("Star Trek page should contain the phrase \"Star Trek\"!",
            driver.getPageSource().contains("Star Trek"));

   }

   /**
    * Test that Cheese page is not about Star Trek.
    */
   @Test
   public void testCheesePageIsNotStarTrek() {
      LOG.debug("Testing testCheesePageIsNotStarTrek");

      // Get search box.
      WebElement searchBox = driver.findElement(By.id("searchInput"));
      searchBox.sendKeys("Cheese!");
      // Do the search.
      searchBox.submit();

      final String expected = "Star Trek - Wikipedia, the free encyclopedia";
      final String actual = driver.getTitle();

      assertNotEquals("Expected page title [" + expected + "] and actual ["
            + actual + "] were the same when they should be different.",
            expected, actual);
      assertFalse("Cheese page should not contain the phrase \"Star Trek\"!",
            driver.getPageSource().contains("Star Trek"));

   }

}
