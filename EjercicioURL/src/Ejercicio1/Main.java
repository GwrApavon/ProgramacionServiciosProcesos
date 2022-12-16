/**
 * 
 */
package Ejercicio1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		URL url=null;
		URLConnection urlCon=null;
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		
		try {
			System.out.println("Cuantos monumentos quieres ver?");
			int cantidad = s.nextInt();
			//url = new URL("https://www.zaragoza.es/sede/servicio/monumento.xml?srsname=wgs84&rows="+cantidad+"&fl=id,title");		
			
			DocumentBuilder builder = factoria.newDocumentBuilder();
			Document d  = builder.parse("https://www.zaragoza.es/sede/servicio/monumento.xml?srsname=wgs84&rows="+cantidad+"&fl=id,title");
			d.getDocumentElement().normalize();
			NodeList nl = d.getElementsByTagName("monumento");
			System.out.println("Lista de monumentos:");
			for(int i = 0; i < cantidad; i++) {
				Node n = nl.item(i);
//				if(n.getNodeType() == Node.) {
//					Element el = (Element) n.getChildNodes()
//				}
				System.out.println();
			}

		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.close();
		
	}

}
