package de.danielrhein;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxReader {

    private final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    private SAXParser saxParser;
    DefaultHandler defaultHandler;

    public SaxReader(String filename) throws SAXException, IOException, ParserConfigurationException {
        saxParserFactory.setValidating(false);
        saxParser = saxParserFactory.newSAXParser();
        defaultHandler = new SaxHandler();
        saxParser.parse(new File(filename), defaultHandler);
    }

    public SaxReader() throws ParserConfigurationException, SAXException {

    }

    public void readFile(String filename) throws SAXException, IOException, ParserConfigurationException {
        saxParserFactory.setValidating(false);
        saxParser = saxParserFactory.newSAXParser();
        defaultHandler = new SaxHandler();
        saxParser.parse(new File(filename), defaultHandler);
    }

    private class SaxHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            System.out.println(" Document start ");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println(" Document end ");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("namespaceUri: " + uri);
            System.out.println("localName: " + localName);
            System.out.println("qname:" + qName);
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.printf("Attribute no %d: %s = %s\n", i, attributes.getQName(i), attributes.getValue(i));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            System.out.println("Characters:");
            for (int i = 0; i < ch.length; i++) {
                System.out.printf(" %1$c (%1x) ", (int) ch[i]);
            }
            System.out.println();
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            System.err.println(saxMsg(e));
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            System.err.println(saxMsg(e));
        }

        private String saxMsg(SAXParseException exception) {
            return "Line: " + exception.getLineNumber() + ", Column: " + exception.getColumnNumber() +
                    "Error: " + exception.getMessage();
        }
    }
}
