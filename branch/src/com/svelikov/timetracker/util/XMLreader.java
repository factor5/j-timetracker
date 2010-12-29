package com.svelikov.timetracker.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.svelikov.timetracker.TimeTrackerDTO;

/**
 * Time tracker data store reader. The dataStore is a xml file.
 * 
 * @author Svilen Velikov
 */
public class XMLreader implements Reader {

	private static final String TIMER = "timer";
	private static final String MINUTES = "minutes";
	private static final String HOURS = "hours";
	private static final String DAYS = "days";
	private static final String ELAPSED_TIME = "elapsedTime";
	private static final String DATE_CREATED = "dateCreated";
	private static final String NOTES = "notes";
	private static final String TIMER_NAME = "timerName";
	private static final String ID = "id";

	/**
	 * Loggers instance.
	 */
	private static final Logger LOG = Logger.getLogger(XmlReader.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(final String path) {
		try {

			final FileInputStream fis = new FileInputStream(path);
			final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			final XMLStreamReader reader = inputFactory
					.createXMLStreamReader(fis);

			final TimeTrackerDTO timer = new TimeTrackerDTO();

			int eventType = -1;
			do {
				eventType = reader.next();
				if (eventType == XMLStreamConstants.START_ELEMENT) {
					if (TIMER.equals(reader.getLocalName())) {
						parseTimerInfo(reader, timer);
					}
					LOG.debug("timer:" + timer);
				}
			} while (eventType != XMLStreamConstants.END_DOCUMENT);
		} catch (final FileNotFoundException e) {
			LOG.debug(e);
		} catch (final XMLStreamException e) {
			LOG.debug(e);
		}
	}

	/**
	 * Called on every timer tag found in the dataStore xml file. This parses
	 * the timer tag: attributes and nested tags.
	 * 
	 * @param reader
	 *            XMLStreamReader.
	 * @param timer
	 *            The TimeTrackerDTO instance.
	 * @throws XMLStreamException
	 *             If any error occurs during xml parsing.
	 */
	private void parseTimerInfo(final XMLStreamReader reader,
			final TimeTrackerDTO timer) throws XMLStreamException {
		// read timer attributes
		for (int i = 0; i < reader.getAttributeCount(); i++) {
			final String attributeName = reader.getAttributeLocalName(i);
			final String value = reader.getAttributeValue(i);
			if (ID.equals(attributeName)) {
				timer.setId(Integer.valueOf(value));
			}
		}

		// read timer data
		int eventType = -1;
		do {
			eventType = reader.next();
			if (eventType == XMLStreamConstants.START_ELEMENT) {
				if (TIMER_NAME.equals(reader.getLocalName())) {
					final String timerName = reader.getElementText();
					timer.setTimerName(timerName);
				}
				if (NOTES.equals(reader.getLocalName())) {
					final String notes = reader.getElementText();
					timer.setNotes(notes);
				}
				if (DATE_CREATED.equals(reader.getLocalName())) {
					final String dateCreated = reader.getElementText();
					timer.setDateCreated(new Date());
				}
				if (ELAPSED_TIME.equals(reader.getLocalName())) {
					readElapsedTime(reader, timer);
				}
			}
		} while (eventType != XMLStreamConstants.END_ELEMENT);
	}

	/**
	 * Called on elapsedTime attribute. This parses the nested tags.
	 * 
	 * @param reader
	 *            XMLStreamReader.
	 * @param timer
	 *            The TimeTrackerDTO instance.
	 * @throws XMLStreamException
	 *             If any error occurs during xml parsing.
	 */
	private void readElapsedTime(final XMLStreamReader reader,
			final TimeTrackerDTO timer) throws XMLStreamException {
		int eventType = -1;
		do {
			eventType = reader.next();
			if (eventType == XMLStreamConstants.START_ELEMENT) {
				if (DAYS.equals(reader.getLocalName())) {
					final String days = reader.getElementText();
					timer.getElapsedTime().setDays(Integer.valueOf(days));
				}
				if (HOURS.equals(reader.getLocalName())) {
					final String hours = reader.getElementText();
					timer.getElapsedTime().setHours(Integer.valueOf(hours));
				}
				if (MINUTES.equals(reader.getLocalName())) {
					final String minutes = reader.getElementText();
					timer.getElapsedTime().setMinutes(Integer.valueOf(minutes));
				}
			}
		} while (eventType != XMLStreamConstants.END_ELEMENT);
	}

	@Override
	public void read() {

	}

	@Override
	public void read(final InputStream in) {

	}
}
