package lapr.project.utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * XMLSerializer
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 07/06/2018.
 */
public final class XMLSerializer {

    private XMLSerializer() {
        throw new IllegalStateException("Utility class!");
    }

    public static void serializeToXML(Object object, Class<?> classType, String filePath) throws Exception {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(classType);
        Marshaller m = context.createMarshaller();
        m.marshal(object, writer);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        PrintWriter out1 = new PrintWriter(new File(filePath), "UTF-8");
        out1.write(writer.toString());
        out1.close();

        //XML pretty
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(filePath))));
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(filePath));
        xformer.transform(source, result);
    }

    public static Object deserializeFromXML(Class<?> classType, String filePath) throws Exception {
        JAXBContext context = JAXBContext.newInstance(classType);
        Unmarshaller m = context.createUnmarshaller();
        return m.unmarshal(new FileReader(filePath));
    }

}
