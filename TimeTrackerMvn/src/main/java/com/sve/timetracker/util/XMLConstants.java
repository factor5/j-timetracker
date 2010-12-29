package com.sve.timetracker.util;

import javax.sql.rowset.spi.XmlReader;

import org.apache.log4j.Logger;

public abstract class XMLConstants {

	protected static final String NL = System.getProperty("line.separator");
	protected static final String TAB = "\t";
	protected static final String TABx2 = TAB + TAB;
	protected static final String TABx3 = TAB + TAB + TAB;
	protected static final String XML_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
	protected static final String NAMESPACE_PREFIX = "xsi";
	protected static final String NONAMESPACE_SCHEMA_LOCATION = "noNamespaceSchemaLocation";
	protected static final String XSD_NAME = "dataStore.xsd";

	protected static final String DATA_STORE = "dataStore";
	protected static final String TIMER = "timer";
	protected static final String MINUTES = "minutes";
	protected static final String HOURS = "hours";
	protected static final String DAYS = "days";
	protected static final String ELAPSED_TIME = "elapsedTime";
	protected static final String DATE_CREATED = "dateCreated";
	protected static final String NOTES = "notes";
	protected static final String TIMER_NAME = "timerName";
	protected static final String ID = "id";
	/**
	 * Loggers instance.
	 */
	protected static final Logger LOG = Logger.getLogger(XmlReader.class);

	public XMLConstants() {

	}

}