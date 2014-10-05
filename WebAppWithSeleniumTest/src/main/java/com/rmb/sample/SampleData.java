package com.rmb.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class SampleData {

   /** Logger for this class. */
   private static final org.apache.log4j.Logger LOG =
         Logger.getLogger(SampleData.class);

   /** Timestamp format to get day of week. */
   public static final ThreadLocal<SimpleDateFormat> FORMAT_TIMESTAMP_DAY =
         new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected synchronized SimpleDateFormat initialValue() {
               return new SimpleDateFormat("EEEE");
            }
         };


   public String getDay() {
      final String dayOfWeek = FORMAT_TIMESTAMP_DAY.get().format(new Date());
      LOG.debug("Outputting day of week [" + dayOfWeek + "].");
      return dayOfWeek;

   }

}
