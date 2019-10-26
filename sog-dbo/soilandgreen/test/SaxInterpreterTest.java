import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import de.danielrhein.SaxPlantDTOInterpreter;
import de.danielrhein.SaxReader;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class SaxInterpreterTest {

    SaxReader saxReader;
    SaxPlantDTOInterpreter interpreter;
    DocumentReadingListenerUtil listenerUtil;



    public SaxInterpreterTest() throws ParserConfigurationException, SAXException {
    }

    @Before
    public void before() throws ParserConfigurationException, SAXException {
        saxReader = new SaxReader();
        listenerUtil = new DocumentReadingListenerUtil();
        interpreter = new SaxPlantDTOInterpreter(listenerUtil);
    }

    @Test
    public void testSaxReader() throws ParserConfigurationException, SAXException, IOException {
        saxReader.addSaxListener(interpreter);
        saxReader.readFile("test_resource/minimaltest.html");
    }
}
