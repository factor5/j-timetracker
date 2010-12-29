package com.sve.timetracker;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriter {

	// Namespaces
	private static final String GARDENING = "http://com.bdaum.gardening";
	private static final String XHTML = "http://www.w3.org/1999/xhtml";

	public static void main(final String[] args) throws XMLStreamException {

		// Create an output factory
		final XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		// Set namespace prefix defaulting for all created writers
		// xmlof.setProperty("javax.xml.stream.isPrefixDefaulting",
		// Boolean.TRUE);

		// Create an XML stream writer
		final XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(System.out);

		// Write XML prologue
		xmlw.writeStartDocument();
		xmlw.writeCharacters("\n");
		// Write a processing instruction
		xmlw
				.writeProcessingInstruction("xml-stylesheet href='catalog.xsl' type='text/xsl'");
		xmlw.writeCharacters("\n");
		// Now start with root element
		xmlw.writeStartElement("product");
		// Set the namespace definitions to the root element
		// Declare the default namespace in the root element
		xmlw.writeNamespace("aaa", GARDENING);
		xmlw.writeAttribute("bbb", "html://aaa.com/", "myAttr", "attr.xsd");
		// Writing a few attributes
		xmlw.writeCharacters("\n");
		// Declare XHTML prefix
		// xmlw.setPrefix("xhtml",XHTML);
		// Different namespace for description element
		xmlw.writeStartElement("description");
		// Declare XHTML namespace in the scope of the description element
		// xmlw.writeNamespace("xhtml",XHTML);
		xmlw
				.writeCharacters("A tulip of almost black color. Blossoms in April & May");
		xmlw.writeEndElement();
		xmlw.writeCharacters("\n");
		// Shorthand for empty elements
		xmlw.writeEmptyElement("supplier");
		xmlw.writeAttribute("name", "Floral22");
		xmlw.writeCharacters("\n");
		// xmlw.writeEndElement();
		// Write document end. This closes all open structures
		xmlw.writeEndDocument();
		// Close the writer to flush the output
		xmlw.close();
	}

}