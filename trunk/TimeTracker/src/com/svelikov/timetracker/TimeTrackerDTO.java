/**
 * 
 */
package com.svelikov.timetracker;

import java.util.Date;

import javax.swing.Timer;

/**
 * @author Svilen Velikov
 */
public class TimeTrackerDTO {

	private Integer id;

	private String timerName;

	private Timer timer;

	private String notes;

	private ElapsedTime elapsedTime;

	private Date dateCreated;

	/**
	 * Constructor.
	 */
	public TimeTrackerDTO() {
		elapsedTime = new ElapsedTime();
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer
	 *            the timer to set
	 */
	public void setTimer(final Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * @return the timerName
	 */
	public String getTimerName() {
		return timerName;
	}

	/**
	 * @param timerName
	 *            the timerName to set
	 */
	public void setTimerName(final String timerName) {
		this.timerName = timerName;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the elapsedTime
	 */
	public ElapsedTime getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * @param elapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(final ElapsedTime elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TimeTrackerDTO [id=" + id + ", timerName=" + timerName
				+ ", notes=" + notes + ", elapsedTime=" + elapsedTime
				+ ", dateCreated=" + dateCreated + "]";
	}

}
