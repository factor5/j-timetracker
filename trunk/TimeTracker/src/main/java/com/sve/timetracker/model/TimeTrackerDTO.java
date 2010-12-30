/**
 * 
 */
package com.sve.timetracker.model;

import java.util.Date;

import javax.swing.Timer;

/**
 * This object carries all attributes of the time tracker.
 * 
 * @author Svilen Velikov
 */
public class TimeTrackerDTO {

	/**
	 * The id is unique.
	 */
	private Integer id;

	/**
	 * The name is unique.
	 */
	private String timerName;

	/**
	 * A timer instance.
	 */
	private Timer timer;

	/**
	 * The notes for this timer.
	 */
	private String notes;

	/**
	 * The time that this timer have worked.
	 */
	private ElapsedTime elapsedTime;

	/**
	 * The date when this timer was created.
	 */
	private Date dateCreated;

	/**
	 * Getter method for id.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

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
