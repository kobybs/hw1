package il.ac.technion.cs.sd.book.app;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;


public class BookScoreInitializerImpl implements BookScoreInitializer {

	@Override
	public void setup(String xmlData) {
		DocumentBuilderFactory factory =
		DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(
				"<Root>"+
						  "<Reviewer Id=\"123\">"+
						    "<Review>"+
						      "<Id>Foobar</Id>"+
						      "<Score>10</Score>"+
						    "</Review>"+
						    "<Review>"+
						      "<Id>Moobar</Id>"+
						      "<Score>3</Score>"+
						    "</Review>"+
						  "</Reviewer>"+
						  "<Reviewer Id=\"123\">"+
						    "<Review>"+
						      "<Id>Boobar</Id>"+
						      "<Score>5</Score>"+
						    "</Review>"+
						  "</Reviewer>"+
						"</Root>");
		ByteArrayInputStream input = null;
		try {
			input = new ByteArrayInputStream(
			   xmlStringBuilder.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(input);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList reviewerList = doc.getElementsByTagName("Reviewer");
		
		for (int temp = 0; temp < reviewerList.getLength(); temp++) {
            Node reviewer = reviewerList.item(temp);
            System.out.println("\nCurrent Element :" 
                    + reviewer.getNodeName());
            Element reviewerAsElement = (Element) reviewer;
            System.out.println("reviewer id : " 
                    + reviewerAsElement.getAttribute("Id"));
            
            NodeList reviewsList = reviewerAsElement.getElementsByTagName("Review");
            for (int rev = 0; rev < reviewsList.getLength(); rev++) {
            	Node reviewNode = reviewsList.item(rev);
            	Element reviewElement = (Element) reviewNode;
            	
            	System.out.println("book id : " + 
            	reviewElement.getElementsByTagName("Id")
                .item(0)
                .getTextContent());
            	
            	System.out.println("book score : " + 
                    	reviewElement.getElementsByTagName("Score")
                        .item(0)
                        .getTextContent());
            	
            }
            
		}
	}

}
