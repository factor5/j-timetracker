package com.sve.timetracker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import com.sve.timetracker.TimeTrackerDTO;
import com.sve.timetracker.util.DataStoreXMLWriter;
import com.sve.timetracker.util.Reader;
import com.sve.timetracker.util.XMLWriter;
import com.sve.timetracker.util.XMLreader;

public class TestReader {

	/**
	 * @param args
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	public static void main(final String[] args) throws XMLStreamException,
			IOException {
		final Reader reader = new XMLreader();
		final Map<String, TimeTrackerDTO> timers = new HashMap<String, TimeTrackerDTO>();
		reader.read("dataStore-1.xml", timers);

		final XMLWriter writer = new DataStoreXMLWriter("testDataStore.xml");
		writer.write(timers);
	}

}
