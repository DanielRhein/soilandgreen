package net.artelis.wita.ui.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Slf4j
public class XmlSaxFilter {

    private static XmlSaxFilter instance;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private XPathFactory xPathFactory;


    public static synchronized XmlSaxFilter getInstance() {
        if (XmlSaxFilter.instance == null) {
            XmlSaxFilter.instance = new XmlSaxFilter();
        }
        return XmlSaxFilter.instance;
    }


    public String getSoapBody(String soapContent, String xPath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilderFactory.setXIncludeAware(true);
        boolean f = documentBuilder.isValidating();


        document = documentBuilder.parse(new InputSource(new StringReader(soapContent)));
        xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        log.trace("Trying xpath {}.", xPath);
        Node node = (Node) xpath.evaluate(xPath, document, XPathConstants.NODE);
        return getStringOfNode(node);
    }


    private String getStringOfNode(Node node) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        StringWriter sw = new StringWriter();
        trans.transform(new DOMSource(node.getChildNodes().item(1)), new StreamResult(sw));
        return sw.toString();
    }

}
