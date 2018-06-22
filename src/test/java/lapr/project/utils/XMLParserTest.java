package lapr.project.utils;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.matchers.CompareMatcher;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
public class XMLParserTest {

    /**
     * Utils variable to access utils for Strings.
     */
    private StringUtils stringUtils = new StringUtils();

    /**
     * Get OS independent line break.
     *
     * @return OS independent line break "%n".
     */
    private String getLineBreak() {
        if (stringUtils == null) {
            stringUtils = new StringUtils();
        }
        return stringUtils.getLineBreak();
    }

    @Test
    public void ensureXMLDocumentExportToStringDoesNotFail() throws Exception {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><keyword>" + getLineBreak() + "<value>Doors</value>" + getLineBreak() + "</keyword>" + getLineBreak();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create document builder
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Obtain a new document
        Document document = builder.newDocument();
        // Create root element
        Element elementKeyword = document.createElement("keyword");
        // Create a sub-element
        Element elementValue = document.createElement("value");
        // Set the sub-element value
        elementValue.setTextContent("Doors");
        // Add sub-element to root element
        elementKeyword.appendChild(elementValue);
        // Add root element to document
        document.appendChild(elementKeyword);
        XMLParser xmlParser = new XMLParser();
        String result = xmlParser.convertToString(document);
        //assertEquals(expected, result);
        assertThat(result, CompareMatcher.isIdenticalTo(expected));
    }

    @Test
    public void ensureReadFromValidXMLFileWorks() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create document builder
        DocumentBuilder builder = null;
        builder = factory.newDocumentBuilder();
        // Obtain a new document
        Document document = builder.newDocument();
        // Create root element
        Element elementKeyword = document.createElement("keyword");
        // Create a sub-element
        Element elementValue = document.createElement("value");
        // Set the sub-element value
        elementValue.setTextContent("Doors");
        // Add sub-element to root element
        elementKeyword.appendChild(elementValue);
        // Add root element to document
        document.appendChild(elementKeyword);
        Node expected = document.getDocumentElement();
        String filename = "target/test-classes/KeywordXMLValidExample.xml";
        XMLParser xmlParser = new XMLParser();
        Node result = xmlParser.readXMLElementFromFile(filename);
        Diff myDiffIdentical = DiffBuilder.compare(expected).withTest(result).ignoreComments().ignoreWhitespace().checkForIdentical().build();
        assertFalse(myDiffIdentical.hasDifferences());
    }

    @Test
    public void ensureReadFromInvalidXMLFileFails() throws Exception {
        String filename = "target/test-classes/KeywordXMLInvalidExample.xml";
        XMLParser xmlParser = new XMLParser();
        Throwable exception = assertThrows(SAXParseException.class, () -> {
            xmlParser.readXMLElementFromFile(filename);
        });
    }

    @Test
    public void ensureReadFromNonExistingFileFails() throws Exception {
        String filename = "InvalidFileName";
        XMLParser xmlParser = new XMLParser();
        Throwable exception = assertThrows(FileNotFoundException.class, () -> {
            xmlParser.readXMLElementFromFile(filename);
        });

    }

    @Test
    public void ensureDocumentExportToFileFollowedByImportDoesNotFail() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Create document builder
        DocumentBuilder builder = null;
        builder = factory.newDocumentBuilder();
        // Obtain a new document
        Document document = builder.newDocument();
        // Create root element
        Element elementKeyword = document.createElement("keyword");
        // Create a sub-element
        Element elementValue = document.createElement("value");
        // Set the sub-element value
        elementValue.setTextContent("Doors");
        // Add sub-element to root element
        elementKeyword.appendChild(elementValue);
        // Add root element to document
        document.appendChild(elementKeyword);
        Node expected = document.getDocumentElement();
        String filename = "target/test-classes/ExampleOutput.xml";
        XMLParser xmlParser = new XMLParser();
        File outputFile = new File("target/test-classes/ExampleOutput.xml");
        outputFile.delete();
        xmlParser.writeXMLElementToFile(expected, filename);
        Node result = xmlParser.readXMLElementFromFile(filename);
        assertThat(result.getOwnerDocument(), CompareMatcher.isIdenticalTo(expected.getOwnerDocument()).ignoreWhitespace());
    }

}
