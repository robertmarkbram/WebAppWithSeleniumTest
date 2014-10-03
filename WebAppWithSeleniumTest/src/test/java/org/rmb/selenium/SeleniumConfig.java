package org.rmb.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * This class can be used by Selenium tests to configure them as below.
 * </p>
 *
 * <pre>
 * @ContextConfiguration(classes = SeleniumConfig.class)
 * </pre>
 *
 * @author RobertMarkBram
 */
public final class SeleniumConfig {

   /**
    * @return base URL for testing this web app.
    */
   @Bean
   public String baseUrl() {
      return "http://localhost:8888/trackandtrace/";
   }

   /**
    * @return web driver to use for selenium tests..
    */
   @Bean
   public WebDriver driver() {
      return new CloseableFirefoxDriver();
   }

   /**
    * An implementation of WebDriver that Spring will close when finished.
    *
    * @author RobertMarkBram
    */
   class CloseableFirefoxDriver //
         extends FirefoxDriver implements DisposableBean {
      /**
       * {@inheritDoc}
       * @see org.springframework.beans.factory.DisposableBean#destroy() */
      public void destroy() throws Exception {
         quit();
      }
   }

}
