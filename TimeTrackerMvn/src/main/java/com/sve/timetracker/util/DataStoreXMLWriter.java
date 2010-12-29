package com.sve.timetracker.util;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sve.timetracker.TimeTrackerDTO;

/**
 * Implementation of data store writer.
 * 
 * @author Svilen Velikov
 */
public class DataStoreXMLWriter extends XMLWriter {

	/**
	 * Constructor.
	 * 
	 * @param path
	 *            Path to destination file.
	 * @throws XMLStreamException
	 *             if occurs.
	 * @throws IOException
	 *             if occurs.
	 */
	public DataStoreXMLWriter(final String path) throws XMLStreamException,
			IOException {
		super(path);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeXMLProlog(final XMLStreamWriter xmlWriter)
			throws XMLStreamException {
		xmlWriter.writeStartDocument("UTF-8", "1.0");
		xmlWriter.writeCharacters(NL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeRoot(final XMLStreamWriter xmlWriter)
			throws XMLStreamException {
		xmlWriter.writeStartElement(DATA_STORE);
		xmlWriter.writeNamespace(NAMESPACE_PREFIX, XML_NAMESPACE);
		xmlWriter.writeAttribute(NAMESPACE_PREFIX, XML_NAMESPACE,
				NONAMESPACE_SCHEMA_LOCATION, XSD_NAME);
		xmlWriter.writeCharacters(NL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeTimer(final XMLStreamWriter xmlWriter,
			final TimeTrackerDTO timer) throws XMLStreamException {
		xmlWriter.writeCharacters(TAB);
		xmlWriter.writeStartElement(TIMER);
		xmlWriter.writeAttribute(ID, String.valueOf(timer.getId()));

		xmlWriter.writeCharacters(NL + TABx2);
		writeElement(xmlWriter, TIMER_NAME, timer.getTimerName());

		xmlWriter.writeCharacters(TABx2);
		writeElement(xmlWriter, NOTES, timer.getNotes());

		xmlWriter.writeCharacters(TABx2);
		writeElement(xmlWriter, DATE_CREATED, timer.getDateCreated().toString());

		xmlWriter.writeCharacters(TABx2);
		xmlWriter.writeStartElement(ELAPSED_TIME);
		xmlWriter.writeCharacters(NL + TABx3);
		writeElement(xmlWriter, DAYS, timer.getElapsedTime().getDays()
				.toString());
		xmlWriter.writeCharacters(TABx3);
		writeElement(xmlWriter, HOURS, timer.getElapsedTime().getHours()
				.toString());
		xmlWriter.writeCharacters(TABx3);
		writeElement(xmlWriter, MINUTES, timer.getElapsedTime().getMinutes()
				.toString());
		xmlWriter.writeCharacters(TABx2);
		xmlWriter.writeEndElement();
		xmlWriter.writeCharacters(NL + TAB);
		xmlWriter.writeEndElement();
		xmlWriter.writeCharacters(NL);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final TimeTrackerDTO timerDTO) {
		// TODO Auto-generated method stub

	}

}
