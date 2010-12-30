package com.sve.timetracker.util;

import java.util.Map;

import com.sve.timetracker.model.TimeTrackerDTO;

/**
 * Writer interface provides implementors with writing and updating
 * functionalities.
 * 
 * @author Svilen Velikov
 */
public interface Writer {

	/**
	 * Writes the provided collection to any kind of data store.
	 * 
	 * @param timers
	 *            A collection with TimeTrackerDTO objects.
	 */
	void write(Map<String, TimeTrackerDTO> timers);

	/**
	 * Updates the data store with new data.
	 * 
	 * @param timerDTO
	 *            TimeTrackerDTO object
	 */
	void update(TimeTrackerDTO timerDTO);
}
