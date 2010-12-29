package com.sve.timetracker.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sve.timetracker.ElapsedTime;
import com.sve.timetracker.TimeTrackerDTO;

public class TestWriter {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final Map<String, TimeTrackerDTO> timers = new HashMap<String, TimeTrackerDTO>();

		final TimeTrackerDTO timer = new TimeTrackerDTO();
		timer.setId(1);
		timer.setTimerName("timer1");
		timer.setNotes("ala bala");
		timer.setDateCreated(new Date());
		final ElapsedTime elapsedTime = new ElapsedTime();
		elapsedTime.setDays(1);
		elapsedTime.setHours(5);
		elapsedTime.setMinutes(23);
		timer.setElapsedTime(elapsedTime);

		timers.put("1", timer);

		final TimeTrackerDTO timer2 = new TimeTrackerDTO();
		timer2.setId(2);
		timer2.setTimerName("timer2");
		timer2.setNotes("ala bala 222");
		timer2.setDateCreated(new Date());
		final ElapsedTime elapsedTime2 = new ElapsedTime();
		elapsedTime2.setDays(2);
		elapsedTime2.setHours(2);
		elapsedTime2.setMinutes(2);
		timer2.setElapsedTime(elapsedTime2);

		timers.put("2", timer2);

		XMLWriter writer;
		try {
			writer = new DataStoreXMLWriter("dataStore-1.xml");
			writer.write(timers);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
