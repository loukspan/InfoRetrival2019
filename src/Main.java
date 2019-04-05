import org.w3c.dom.*;
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

        for (int i = 0; i < xmlFiles.length; i++){
            if (xmlFiles[i].isFile()) {
                System.out.println("File:  " + xmlFiles[i].getName());
                Document doc = dBuilder.parse(xmlFiles[i]);
                Element rootElement = doc.getDocumentElement();
                doc.createElement("text");

                //doc.getDocumentElement().normalize();
                NodeList rcnNodes = doc.getElementsByTagName("project");


                //NodeList nodelist = rootElement.getChildNodes();
                for(int j = 0; j < rcnNodes.getLength(); j++){

                    Node rcnNode = rcnNodes.item(j);

                    //Node node = nodelist.item(j);
                    System.out.println("Node: "+ rcnNode);

                    if(rcnNode.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) rcnNode;
                        System.out.println("element: " + element);

                        //doc.renameNode(element, "title", "text");

                        String rcn = element.getElementsByTagName("rcn").item(0).getTextContent();
                        String acronym = element.getElementsByTagName("acronym").item(0).getTextContent();
                        String objective = element.getElementsByTagName("objective").item(0).getTextContent();
                        String title = element.getElementsByTagName("title").item(0).getTextContent();
                        String identifier = element.getElementsByTagName("identifier").item(0).getTextContent();

                        //element.getElementsByTagName("text") = element.getElementsByTagName("objective").item(0).getTextContent();
                        //element.appendChild((Node) element.getElementsByTagName("text"));

                        NodeList temp = (NodeList) element.getElementsByTagName("title").item(0);

                        System.out.println("temp:  "+temp);

                        //element.appendChild(doc.createElement("text"));
                        doc.renameNode(element.getElementsByTagName("title").item(0), "", "text");


                        System.out.println("final "+element);



                    }
                }

            }
        }
    }
}
