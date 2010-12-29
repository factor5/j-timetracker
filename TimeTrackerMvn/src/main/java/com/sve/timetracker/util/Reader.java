package com.sve.timetracker.util;

import java.util.Map;

import com.sve.timetracker.TimeTrackerDTO;

/**
 * Reader interface provides reading functionality.
 * 
 * @author Svilen Velikov
 */
public interface Reader {

	Map<String, TimeTrackerDTO> read(String path,
			Map<String, TimeTrackerDTO> timers);

}
