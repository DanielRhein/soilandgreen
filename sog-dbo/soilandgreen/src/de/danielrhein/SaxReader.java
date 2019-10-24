package de.danielrhein;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket;

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
    private DefaultHandler defaultHandler;
    private SaxListener listener;

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

    public void addSaxListener(SaxListener listener)
    {
        this.listener = listener;
    }



    private class SaxHandler extends DefaultHandler {

        private SaxListener listener = null;

        public void setListener(SaxListener listener) {
            this.listener = listener;
        }

        private void startListenerDocument()
        {
            if (this.listener!=null)
            {
                listener.startDocument();
            }
        }

        private void endListenerDocument()
        {
            if (this.listener!=null)
            {
                listener.endDocument();
            }
        }

        private void startListenerElement(String uri, String localName, String qName, Attributes attributes)
        {
            if (this.listener!=null)
            {
                listener.startElement(uri,localName,qName,attributes);
            }
        }

        private void startListenerEndElement(String uri, String localName, String qName)
        {
            if (this.listener!=null)
            {
                listener.endElement(uri,localName,qName);
            }
        }

        private void readingcontent(String content)
        {
            if (this.listener!=null)
            {
                listener.elementContent(content);
            }
        }

        private void failure()
        {
            if (listener!=null) {
                listener.failure();
            }
        }


        @Override
        public void startDocument() throws SAXException {
            System.out.println(" Document start ");
            startListenerDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println(" Document end ");
            endListenerDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("namespaceUri: " + uri);
            System.out.println("localName: " + localName);
            System.out.println("qname:" + qName);
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.printf("Attribute no %d: %s = %s\n", i, attributes.getQName(i), attributes.getValue(i));
            }
            startListenerElement(uri,localName,qName,attributes);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            System.out.println("Characters:");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < ch.length; i++) {
                System.out.printf(" %1$c (%1x) ", (int) ch[i]);
                stringBuilder.append(ch[i]);
            }
            readingcontent(stringBuilder.toString());
            System.out.println();
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            startListenerEndElement(uri,localName,qName);
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            System.err.println(saxMsg(e));
            failure();
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            System.err.println(saxMsg(e));
            failure();
        }

        private String saxMsg(SAXParseException exception) {
            return "Line: " + exception.getLineNumber() + ", Column: " + exception.getColumnNumber() +
                    "Error: " + exception.getMessage();
        }
    }
}
