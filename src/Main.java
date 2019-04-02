import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        File path = new File("Parsed files");
        File[] xmlFiles = path.listFiles();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;

        for (int i = 0; i < xmlFiles.length; i++){
            if (xmlFiles[i].isFile()) {
                System.out.println("File " + xmlFiles[i].getName());
                String tempfilename = xmlFiles[i].getName();
                String filename = "Parsed files\\"+tempfilename;
                //String filename = "Parsed files\\project-rcn-193157_en.xml";
                System.out.println("filename "+ filename);

                doc = dBuilder.parse(filename);
                Element rootElement = doc.getDocumentElement();
                //System.out.println("Root Element: " + rootElement.getNodeName());

                NodeList nodelist = rootElement.getChildNodes();
                for(int j = 0; j < nodelist.getLength(); j++){
                    Node node = nodelist.item(j);

                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) node;
                        if(element.getNodeName().equals("objective")){
                            //String objective = element.getElementsByTagName("objctive").item(0).getTextContent();
                            //System.out.println("objective"+ objective);
                        }

                    }
                }

            }
        }
    }
}
