package il.ac.technion.cs.sd.book.app;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;


import javax.xml.parsers.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

class MapObject{
	Map<String,String> values;
	
	public MapObject(){
		values = new HashMap<>();
	}
	
	public void add(String s, String v){
		values.put(s, v);
	}
	
	public Map<String,String> getValues(){
		return values;
	}
	
	public String getVlaueString(){
		StringBuffer sb = new StringBuffer();
		for(String e : values.keySet()){
			sb.append(e+","+values.get(e)+" ");
		}
		return sb.toString();
	}
	
	public void addAll (MapObject m){
		values.putAll(m.getValues());
	}
	
	public void print (){
		for(String e : values.keySet()){
			System.out.println(e + " " + values.get(e));
		}
	}
}

public class BookScoreInitializerImpl implements BookScoreInitializer {

	//@Injector
	private LineStorage ls;
	
	@Inject
	public BookScoreInitializerImpl (LineStorage ls){
		this.ls = ls;
	}

	
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
		
		Map <String,MapObject> reviwer = new HashMap<>();
		Map <String,MapObject> books = new HashMap<>();
		
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
            
            MapObject mr = new MapObject();
            NodeList reviewsList = reviewerAsElement.getElementsByTagName("Review");
            for (int rev = 0; rev < reviewsList.getLength(); rev++) {
            	MapObject mb = new MapObject();
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
            	
            	mb.add(reviewerAsElement.getAttribute("Id"),
            			reviewElement.getElementsByTagName("Score").item(0).getTextContent());
            	
                if(books.get(reviewElement.getElementsByTagName("Id").item(0).getTextContent()) != null){
                	mb.addAll(books.get(reviewElement.getElementsByTagName("Id").item(0).getTextContent()));
                }
                books.put(reviewElement.getElementsByTagName("Id").item(0).getTextContent(), mb);
                
            	mr.add(reviewElement.getElementsByTagName("Id").item(0).getTextContent()
            			,reviewElement.getElementsByTagName("Score").item(0).getTextContent());
            }
            
            if(reviwer.get(reviewerAsElement.getAttribute("Id")) != null){
            	mr.addAll(reviwer.get(reviewerAsElement.getAttribute("Id")));
            }
            reviwer.put(reviewerAsElement.getAttribute("Id"), mr);
		}
		
		for(String e : books.keySet()){
			ls.appendLine(e+" "+books.get(e).getVlaueString());
			System.out.println(e+" "+books.get(e).getVlaueString());
			System.out.println();
		}
		
		for(String e : reviwer.keySet()){
			ls.appendLine(e+" "+reviwer.get(e).getVlaueString());
			System.out.println(e+" "+reviwer.get(e).getVlaueString());
			System.out.println();
		}
	}

	// TODO : insert sorted items
}
