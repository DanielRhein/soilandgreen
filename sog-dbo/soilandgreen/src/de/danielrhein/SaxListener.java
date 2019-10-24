package de.danielrhein;

import org.xml.sax.Attributes;

public interface SaxListener {
    void startElement(String uri, String localName, String qName, Attributes attributes);
    void endElement(String uri, String localName, String qName);
    void elementContent(String content);
    void startDocument();
    void endDocument();
    void failure();
}
