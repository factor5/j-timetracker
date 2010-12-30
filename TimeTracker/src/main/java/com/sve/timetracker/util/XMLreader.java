package com.sve.timetracker.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.sve.timetracker.model.TimeTrackerDTO;

/**
 * Time tracker data store reader. The dataStore is a xml file.
 * 
 * @author Svilen Velikov
 */
public class XMLreader extends XMLConstants implements Reader {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, TimeTrackerDTO> read(final String path,
			final Map<String, TimeTrackerDTO> timers) {
		try {

			final FileInputStream fis = new FileInputStream(path);
			final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			final XMLStreamReader reader = inputFactory
					.createXMLStreamReader(fis);

			TimeTrackerDTO timer = null;

			int eventType = -1;
			do {
				eventType = reader.next();
				if (eventType == XMLStreamConstants.START_ELEMENT) {
					if (TIMER.equals(reader.getLocalName())) {
						timer = new TimeTrackerDTO();
						parseTimerInfo(reader, timer);
						timers.put(timer.getTimerName(), timer);
						LOG.debug("timer:" + timer);
					}
				}
			} while (eventType != XMLStreamConstants.END_DOCUMENT);
		} catch (final FileNotFoundException e) {
			LOG.debug(e);
		} catch (final XMLStreamException e) {
			LOG.debug(e);
		}
		return null;
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

}
