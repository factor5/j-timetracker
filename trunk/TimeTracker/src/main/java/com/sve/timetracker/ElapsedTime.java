/**
 * 
 */
package com.sve.timetracker;

/**
 * This is a dto object that holds fields to represent elapsed time and
 * convenient toString method for logging/output.
 * 
 * @author Svilen Velikov
 */
public class ElapsedTime {

	private Integer days;

	private Integer hours;

	private Integer minutes;

	public ElapsedTime() {
		this.days = 0;
		this.hours = 0;
		this.minutes = 0;
	}

	@Override
	public String toString() {
		final StringBuilder elapsedTime = new StringBuilder(8);
		if (days < 10) {
			elapsedTime.append("0" + days);
		} else {
			elapsedTime.append(days);
		}
		elapsedTime.append(":");

		if (hours < 10) {
			elapsedTime.append("0" + hours);
		} else {
			elapsedTime.append(hours);
		}
		elapsedTime.append(":");

		if (minutes < 10) {
			elapsedTime.append("0" + minutes);
		} else {
			elapsedTime.append(minutes);
		}
		return elapsedTime.toString();
	}

	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(final Integer days) {
		this.days = days;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(final Integer hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public Integer getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(final Integer minutes) {
		this.minutes = minutes;
	}

}
