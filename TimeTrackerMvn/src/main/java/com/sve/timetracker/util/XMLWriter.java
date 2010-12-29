package com.sve.timetracker.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sve.timetracker.TimeTrackerDTO;

/**
 * TimeTracker dataStore.xml writer.
 * 
 * @author Svilen Velikov
 */
public abstract class XMLWriter extends XMLConstants implements Writer {

	/**
	 * The XMLStreamWriter instance.
	 */
	private XMLStreamWriter xmlWriter;

	/**
	 * Constructor.
	 * 
	 * @param path
	 *            Destination file path.
	 * @throws IOException
	 *             if any occurs.
	 * @throws XMLStreamException
	 *             if any occurs.
	 */
	public XMLWriter(final String path) throws XMLStreamException, IOException {
		final XMLOutputFactory factory = XMLOutputFactory.newInstance();
		xmlWriter = factory.createXMLStreamWriter(new FileWriter(path));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final Map<String, TimeTrackerDTO> timers) {
		LOG.debug("Start writing to data store.");
		try {
			writeXMLProlog(xmlWriter);

			writeRoot(xmlWriter);

			for (final TimeTrackerDTO timer : timers.values()) {
				writeTimer(xmlWriter, timer);
			}

			xmlWriter.writeEndDocument();
			LOG.debug("Writing completed.");
		} catch (final XMLStreamException e) {
			e.printStackTrace();
		} finally {
			try {
				xmlWriter.close();
			} catch (final XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Template method. Writes the xml prolog.
	 * 
	 * @param xmlWriter
	 *            XMLStreamWriter instance.
	 * @throws XMLStreamException
	 *             if any occurs.
	 */
	protected abstract void writeXMLProlog(XMLStreamWriter xmlWriter)
			throws XMLStreamException;

	/**
	 * Template method. Writes the data store root tag.
	 * 
	 * @param xmlWriter
	 *            XMLStreamWriter instance.
	 * @throws XMLStreamException
	 *             if any occurs.
	 */
	protected abstract void writeRoot(XMLStreamWriter xmlWriter)
			throws XMLStreamException;

	/**
	 * Template method. Writes a timer node.
	 * 
	 * @param xmlWriter
	 *            XMLStreamWriter instance.
	 * @throws XMLStreamException
	 *             if any occurs.
	 */
	protected abstract void writeTimer(XMLStreamWriter xmlWriter,
			TimeTrackerDTO timer) throws XMLStreamException;

	/**
	 * Writes an xml node using provided name and value. Element will be closed.
	 * 
	 * @param xmlWriter
	 *            XMLStreamWriter instance.
	 * @param element
	 *            The name of the node.
	 * @param value
	 *            The value.
	 * @throws XMLStreamException
	 *             if occurs.
	 */
	protected void writeElement(final XMLStreamWriter xmlWriter,
			final String element, final String value) throws XMLStreamException {
		xmlWriter.writeStartElement(element);
		xmlWriter.writeCharacters(value);
		xmlWriter.writeEndElement();
		xmlWriter.writeCharacters(NL);
	}
}
