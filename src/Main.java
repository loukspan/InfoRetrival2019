import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        File path = new File("Parsed files");
        File[] xmlFiles = path.listFiles();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        int count = 193157;

        for (int i = 0; i < xmlFiles.length; i++){
            if (xmlFiles[i].isFile()) {
                //System.out.println("File:  " + xmlFiles[i].getName());
                Document doc = dBuilder.parse(xmlFiles[i]);
                doc.createElement("text");

                NodeList rcnNodes = doc.getElementsByTagName("project");

                //NodeList nodelist = rootElement.getChildNodes();
                for(int j = 0; j < rcnNodes.getLength(); j++){

                    Node rcnNode = rcnNodes.item(j);

                    //System.out.println("Node: "+ rcnNode);

                    if(rcnNode.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) rcnNode;
                        //System.out.println("element: " + element);

                        String rcn = element.getElementsByTagName("rcn").item(0).getTextContent();
                        String acronym = element.getElementsByTagName("acronym").item(0).getTextContent();
                        String objective = element.getElementsByTagName("objective").item(0).getTextContent();
                        String title = element.getElementsByTagName("title").item(0).getTextContent();
                        String identifier = element.getElementsByTagName("identifier").item(0).getTextContent();

                        //rename title to text
                        Node tempTitle = element.getElementsByTagName("title").item(0);
                        doc.renameNode(tempTitle, null, "text");

                        //concatanate title with objective
                        String newtitle = title + " "+ objective;
                        tempTitle.setTextContent(newtitle);

                        //remove objective node
                        Node tempobjective = element.getElementsByTagName("objective").item(0);
                        doc.getDocumentElement().removeChild(tempobjective);



                    }
                }
                //Convert xml file to String
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                //initialize StreamResult with File object to save to file
                StreamResult result = new StreamResult(new StringWriter());
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);
                String xmlString = result.getWriter().toString();

                JSONObject obj = XML.toJSONObject(xmlString);
                System.out.println(obj.toString(4));


                //write jsons to file
                try{
                    FileWriter fileWriter = new FileWriter("Jsons\\json"+ count++ + ".json");
                    fileWriter.write(obj.toString(4));
                    fileWriter.flush();
                    fileWriter.close();
                }catch (IOException e){
                    System.out.println("exception " + e.getMessage());
                }
            }
        }

    }
}
