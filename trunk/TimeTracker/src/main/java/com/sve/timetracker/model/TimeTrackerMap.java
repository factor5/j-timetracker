/**
 * 
 */
package com.sve.timetracker.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * A map implementation time tracker store.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerMap implements Map<Integer, TimeTrackerDTO> {

	private final Map<Integer, TimeTrackerDTO> map = new HashMap<Integer, TimeTrackerDTO>();

	@Override
	public TimeTrackerDTO get(final Object key) {
		return map.get(key);
	}

	@Override
	public TimeTrackerDTO put(final Integer key, final TimeTrackerDTO value) {
		return map.put(key, value);
	}

	@Override
	public TimeTrackerDTO remove(final Object key) {
		return map.remove(key);
	}

	@Override
	public boolean containsKey(final Object key) {
		return false;
	}

	@Override
	public boolean containsValue(final Object value) {
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<Integer, TimeTrackerDTO>> entrySet() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Set<Integer> keySet() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Collection<TimeTrackerDTO> values() {
		return null;
	}

	@Override
	public void clear() {

	}

	@Override
	public void putAll(final Map<? extends Integer, ? extends TimeTrackerDTO> m) {

	}

}
