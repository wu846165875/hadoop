package com.lww.hadoop.hadoop2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTest {
	@Test
	public void test() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2019-07-05");
		Calendar car = Calendar.getInstance();
		car.setTime(date);
		
		Calendar car2 = Calendar.getInstance();
		date = sdf.parse("2019-01-07");
		car2.setTime(date);
		
		while(car.get(Calendar.DAY_OF_WEEK)!=car2.get(Calendar.DAY_OF_WEEK)) {
			car.add(Calendar.DAY_OF_WEEK, -1);
		}
		System.out.println(sdf.format(car.getTime()));
	}
}
