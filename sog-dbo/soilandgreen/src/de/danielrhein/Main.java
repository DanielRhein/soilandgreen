package de.danielrhein;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        try {
            SaxReader saxReader= new SaxReader();
            saxReader.readFile("resources/informations.html");
            saxReader.readFile("resources/informations-vg1.html");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
