
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {

	public static final String xmlFilePath = "src/dynamips_example.xml";
	public static final String xml2FilePath = "src/input2.xml";
	
	public static void main(String argv[]) throws XPathExpressionException {

		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFilePath);
			Document doc = documentBuilder.newDocument();
			
			Element root = doc.createElement("object");
			doc.appendChild(root);
			
			Node copy = doc.importNode(document.getDocumentElement(), true);
			root.appendChild(copy);
			
			Element version = doc.createElement("heat_template_version");
			version.appendChild(doc.createTextNode("2013-09-09"));
			root.appendChild(version);
			
			Node scenario = doc.getElementsByTagName("scenario_name").item(0);
			Node hijoscenario = scenario.getFirstChild();
		    String aa = hijoscenario.getTextContent();
			System.out.println(aa);
			Element description = doc.createElement("description");
			description.appendChild(doc.createTextNode(""+aa));
			root.appendChild(description);
			
			
			
			NodeList nodes = doc.getElementsByTagName("vnx");
			for (int i = 0; i < nodes.getLength(); i++) {
			  doc.renameNode(nodes.item(i), null, "resources");
			}
			
			Node resource = doc.getElementsByTagName("resources").item(0);					
			Element privatenet = doc.createElement("private_net");
			resource.appendChild(privatenet);
			
			Element type = doc.createElement("type");
			type.appendChild(doc.createTextNode("OS::Neutron::Net"));
			privatenet.appendChild(type);
			
			Element properties = doc.createElement("properties");
			privatenet.appendChild(properties);
			
			Element namepro = doc.createElement("name");
			namepro.appendChild(doc.createTextNode("VNX"));
			properties.appendChild(namepro);
			
			Element router = doc.createElement("router");
			resource.appendChild(router);
			
			Element typer = doc.createElement("type");
			typer.appendChild(doc.createTextNode("OS::Neutron::Router"));
			router.appendChild(typer);
			
			Element routergate = doc.createElement("router_gateway");
			resource.appendChild(routergate);
			
			Element typerou = doc.createElement("type");
			typerou.appendChild(doc.createTextNode("OS::Neutron::RouterGateway"));
			routergate.appendChild(typerou);
			
			Element propertiesr = doc.createElement("properties");
			routergate.appendChild(propertiesr);
			
			Element routerid = doc.createElement("router_id");
			propertiesr.appendChild(routerid);
			
			Element getre = doc.createElement("get_resource");
			getre.appendChild(doc.createTextNode("router"));
			routerid.appendChild(getre);
			
			Element networkid = doc.createElement("network_id");
			networkid.appendChild(doc.createTextNode("d72f0f5f-d4b2-490a-bfb2-71a584c4e39c"));
			propertiesr.appendChild(networkid);
				
			Node child = resource.getFirstChild();
	        resource.removeChild(child);
	        
	        
	       
	        
	        NodeList nodes2 = doc.getElementsByTagName("vm");
	        NodeList nodes3 = doc.getElementsByTagName("host");
			int i2 = nodes2.getLength();
	        int i3 = nodes3.getLength();
	        
	      
	    
	        	        
	        Node textt = resource.getFirstChild();
	        resource.removeChild(textt);

			
			resource.getAttributes().removeNamedItem("xsi:noNamespaceSchemaLocation");
			resource.getAttributes().removeNamedItem("xmlns:xsi");

					
			NodeList koko = doc.getElementsByTagName("if");
	    	int jiji = koko.getLength();
	    	System.out.println(jiji);
	    	List<String> list = new ArrayList<String>();
	    	List<String> listnet = new ArrayList<String>();
	    	List<String> listneta = new ArrayList<String>();
	    	List<String> listart = new ArrayList<String>();
	    	List<String> listend = new ArrayList<String>();
	    	List<String> netvm = new ArrayList<String>();
	    	List<String> subnets = new ArrayList<String>();
	    	List<String> subnets2 = new ArrayList<String>();
	    	List <String> subhost = new ArrayList<String>();
	    	List <String> subnethost = new ArrayList<String>();
    		
	    	
    		for (int jojo=0; jojo<koko.getLength();jojo++){
	    		Node namess = doc.getElementsByTagName("if").item(jojo);
	    		Node we = namess.getAttributes().getNamedItem("net");
	    		String vamos = we.getTextContent();
	    		
	    		Node web = namess.getChildNodes().item(1);
	    		

	    		String neutron = web.getTextContent();	    		
    		    
	    		
	    		
	    		if (neutron.endsWith(".1/24")==true ) {
		    		
	    			list.add(neutron+","+ vamos);
		    		
	    				String hoho = neutron.replace("1/24", "1");
			    		listnet.add(hoho);
			    		
			    		String hijo = neutron.replace("1/", "0/");
			    		listneta.add(hijo);
			    		
			    		String abuelo = neutron.replace("1/24", "2");
			    		listart.add(abuelo);
			    		
			    		String nieto = neutron.replace("1/24", "254");
			    		listend.add(nieto);
	    				
	    			}
	    		else if (neutron.endsWith(".1/16")==true ) {
		    		
	    			list.add(neutron+","+ vamos);
		    		
	    				String hoho = neutron.replace("1/16", "1");
			    		listnet.add(hoho);
			    		
			    		String hijo = neutron.replace("1/", "0/");
			    		listneta.add(hijo);
			    		
			    		String abuelo = neutron.replace("1/16", "2");
			    		listart.add(abuelo);
			    		
			    		String nieto = neutron.replace("1/16", "254");
			    		listend.add(nieto);
	    				
	    			}
    		}
    		Node namess2 = doc.getElementsByTagName("hostif").item(0);
    		Node we2 = namess2.getAttributes().getNamedItem("net");
    		String vamos2 = we2.getTextContent();
    		Node web2 = namess2.getChildNodes().item(1);
    		String neutron2 = web2.getTextContent();	
    		
	    	if (neutron2.endsWith(".1/24")==true ) {
		    		
	    				list.add(neutron2+","+ vamos2);
		    		
	    				String hoho = neutron2.replace("1/24", "1");
			    		listnet.add(hoho);
			    		
			    		String hijo = neutron2.replace("1/", "0/");
			    		listneta.add(hijo);
			    		
			    		String abuelo = neutron2.replace("1/24", "2");
			    		listart.add(abuelo);
			    		
			    		String nieto = neutron2.replace("1/24", "254");
			    		listend.add(nieto);
	    				
	    			}
	    		
	    	else if (neutron2.endsWith(".1/16")==true ) {
	    		
				list.add(neutron2+","+ vamos2);
    		
				String hoho = neutron2.replace("1/16", "1");
	    		listnet.add(hoho);
	    		
	    		String hijo = neutron2.replace("1/", "0/");
	    		listneta.add(hijo);
	    		
	    		String abuelo = neutron2.replace("1/16", "2");
	    		listart.add(abuelo);
	    		
	    		String nieto = neutron2.replace("1/16", "254");
	    		listend.add(nieto);
				
			}
	    	
	    	
	    	
	    	Collections.sort(list);
    		Collections.sort(listnet);
    		Collections.sort(listneta);
    		Collections.sort(listart);
    		Collections.sort(listend);
    		
			int tmr = koko.getLength();
			System.out.println(tmr);
			
			
			
		for (int g=0; g<list.size();g++){
			String [] campos =	list.get(g).split(",");
			System.out.println(campos[1]);
			subnets2.add(campos[1]);
		}
			
	//		Iterator iter = list.iterator();
	//		Iterator itern = listnet.iterator();
	//		Iterator kiko = listneta.iterator();
	//		Iterator chavo = subnets2.iterator();
	//		while (iter.hasNext() || itern.hasNext()){
	//		  System.out.println(iter.next());
	//		  System.out.println(itern.next());
	//		  System.out.println(kiko.next());
	//		  System.out.println(chavo.next());
	//		}
			
			
			Element parameters = doc.createElement("parameters");
			root.appendChild(parameters);
			
			for (int iw = 0; iw < i2; iw++) {
				Node names = doc.getElementsByTagName("vm").item(iw);
				NodeList oo = names.getChildNodes();
			    int jaja = oo.getLength();
			    Node vms = names.getChildNodes().item(jaja-2);
			    String hh = vms.getNodeName(); 
			    if (hh!="forwarding"){ 
			    	Element vmss = (Element)doc.getElementsByTagName("vm").item(iw);
			    	Node haha = vmss.getElementsByTagName("if").item(0);
			    	NamedNodeMap hihi = haha.getAttributes();
			    	Node jojoo = hihi.getNamedItem("net");
			    	String oa = jojoo.getTextContent();
			    	netvm.add(oa);
			    }
			}
			
			for (int io = 0; io < i3; io++) {
				Element hosts = (Element)doc.getElementsByTagName("host").item(io);
				Node haha20 = hosts.getElementsByTagName("hostif").item(0);
		    	NamedNodeMap hihi20 = haha20.getAttributes();
		    	Node jojoo20 = hihi20.getNamedItem("net");
		    	String oa20 = jojoo20.getTextContent();
		    	subhost.add(oa20);
			}
			
			for (int jfk = 0; jfk<subhost.size();jfk++){
				String jod = subhost.get(jfk);
								
				for (int jk=0; jk<subnets2.size();jk++){
					String das = subnets2.get(jk);
					if (jod.equals(das)==true){
						subnethost.add("private_subnet"+jk);
					}
				}
									
			}
			
			for (int hy = 0; hy<netvm.size();hy++){
				String jo = netvm.get(hy);
								
				for (int k=0; k<subnets2.size();k++){
					String das = subnets2.get(k);
					if (jo.equals(das)==true){
						subnets.add("private_subnet"+k);
					}
				}
									
			}
			
			int yafue2 = subnets2.size();
			System.out.println(yafue2);
			
			
			Iterator<String> yafue = subnethost.iterator();
			while (yafue.hasNext()){
			  System.out.println(yafue.next());
			}
				
			
			int i1000=0;
			
			for (int i6 = 0; i6 < i2; i6++) {
				Node names = doc.getElementsByTagName("vm").item(i6);
				NodeList oo = names.getChildNodes();
			    int jaja = oo.getLength();
			    Node vms = names.getChildNodes().item(jaja-2);
			    String hh = vms.getNodeName(); 
			    if (hh!="forwarding"){ 
				Element servers = doc.createElement("server" + i6);
				resource.appendChild(servers);
				Element typeser = doc.createElement("type");
				typeser.appendChild(doc.createTextNode("OS::Nova::Server"));
				servers.appendChild(typeser);
				Element propertiese = doc.createElement("properties");
				servers.appendChild(propertiese);
				Element name = doc.createElement("name");
				NamedNodeMap attribute = names.getAttributes();
				Node nodeAttr = attribute.getNamedItem("name");
				String aaa = nodeAttr.getTextContent();
				name.appendChild(doc.createTextNode(""+aaa));
				propertiese.appendChild(name);
				
				Element networks = doc.createElement("networks");
				propertiese.appendChild(networks);
				Element port = doc.createElement("port");
				networks.appendChild(port);
				Element getres = doc.createElement("get_resource");
				getres.appendChild(doc.createTextNode("server"+i6+"_port"));
				port.appendChild(getres);
				
				Element serverports = doc.createElement("server"+i6+"_port");
				resource.appendChild(serverports);
				Element typen = doc.createElement("type");
				typen.appendChild(doc.createTextNode("OS::Neutron::Port"));
				serverports.appendChild(typen);
				Element propertiesn = doc.createElement("properties");
				serverports.appendChild(propertiesn);
				Element networkidd = doc.createElement("network_id");
				propertiesn.appendChild(networkidd);
				Element getress = doc.createElement("get_resource");
				getress.appendChild(doc.createTextNode("private_net"));
				networkidd.appendChild(getress);
				
				
				Element fixesips = doc.createElement("fixed_ips");
			    propertiesn.appendChild(fixesips);
			    Element subnetid = doc.createElement("subnet_id");
			    fixesips.appendChild(subnetid);
			    Element getresss = doc.createElement("get_resource");	    
				getresss.appendChild(doc.createTextNode(subnets.get(i6-i1000)));
				subnetid.appendChild(getresss);
				
				Element images = doc.createElement("image"+i6);
				parameters.appendChild(images);
				Element typeima = doc.createElement("type");
				typeima.appendChild(doc.createTextNode("string"));
				images.appendChild(typeima);
				Element descripcionima = doc.createElement("description");
				descripcionima.appendChild(doc.createTextNode("Name of image to use for server"+i6));
				images.appendChild(descripcionima);
				
				Element flavors = doc.createElement("flavor"+i6);
				parameters.appendChild(flavors);
				Element typefla = doc.createElement("type");
				typefla.appendChild(doc.createTextNode("string"));
				flavors.appendChild(typefla);
				Element descripcionfla = doc.createElement("description");
				descripcionfla.appendChild(doc.createTextNode("Flavor to use for servers"+i6));
				flavors.appendChild(descripcionfla);
				
				Element imageser = doc.createElement("image");
				propertiese.appendChild(imageser);
				Element getparame = doc.createElement("get_param");
				getparame.appendChild(doc.createTextNode("image"+i6));
				imageser.appendChild(getparame);
				Element flavoser = doc.createElement("flavor");
				propertiese.appendChild(flavoser);
				Element getparam2 = doc.createElement("get_param");
				getparam2.appendChild(doc.createTextNode("flavor"+i6));
				flavoser.appendChild(getparam2);
			    }
			    else {
			    	i1000++;
			    }
				}
			
			
				
			
			for (int i10 = 0; i10 < i3; i10++) {
				Element serversh = doc.createElement("server" + "host" + i10);
				resource.appendChild(serversh);
				Element typeserh = doc.createElement("type");
				typeserh.appendChild(doc.createTextNode("OS::Nova::Server"));
				serversh.appendChild(typeserh);
				Element propertieseh = doc.createElement("properties");
				serversh.appendChild(propertieseh);
				Element nameh = doc.createElement("name");
				nameh.appendChild(doc.createTextNode("host"+i10));
				propertieseh.appendChild(nameh);
				
				Element networks = doc.createElement("networks");
				propertieseh.appendChild(networks);
				Element port = doc.createElement("port");
				networks.appendChild(port);
				Element getres = doc.createElement("get_resource");
				getres.appendChild(doc.createTextNode("host"+i10+"_port"));
				port.appendChild(getres);
				
				Element serverports = doc.createElement("host"+i10+"_port");
				resource.appendChild(serverports);
				Element typen = doc.createElement("type");
				typen.appendChild(doc.createTextNode("OS::Neutron::Port"));
				serverports.appendChild(typen);
				Element propertiesn = doc.createElement("properties");
				serverports.appendChild(propertiesn);
				Element networkidd = doc.createElement("network_id");
				propertiesn.appendChild(networkidd);
				Element getress = doc.createElement("get_resource");
				getress.appendChild(doc.createTextNode("private_net"));
				networkidd.appendChild(getress);
				Element fixesips = doc.createElement("fixed_ips");
			    propertiesn.appendChild(fixesips);
			    Element subnetid = doc.createElement("subnet_id");
			    fixesips.appendChild(subnetid);
			    Element getresss = doc.createElement("get_resource");
				getresss.appendChild(doc.createTextNode(subnethost.get(i10)));
				subnetid.appendChild(getresss);
				
				
				
				Element imageshost = doc.createElement("image_host"+i10);
				parameters.appendChild(imageshost);
				Element typeimahos = doc.createElement("type");
				typeimahos.appendChild(doc.createTextNode("string"));
				imageshost.appendChild(typeimahos);
				Element descripcionhost = doc.createElement("description");
				descripcionhost.appendChild(doc.createTextNode("Name of image to use for host"+i10));
				imageshost.appendChild(descripcionhost);
				
				Element flavorhost = doc.createElement("flavor_host"+i10);
				parameters.appendChild(flavorhost);
				Element typehost = doc.createElement("type");
				typehost.appendChild(doc.createTextNode("string"));
				flavorhost.appendChild(typehost);
				Element descripcihost = doc.createElement("description");
				descripcihost.appendChild(doc.createTextNode("Flavor to use for host"+i10));
				flavorhost.appendChild(descripcihost);
				
				Element imagehost2 = doc.createElement("image");
				propertieseh.appendChild(imagehost2);
				Element getparame = doc.createElement("get_param");
				getparame.appendChild(doc.createTextNode("image_host"+i10));
				imagehost2.appendChild(getparame);
				Element flavoser = doc.createElement("flavor");
				propertieseh.appendChild(flavoser);
				Element getparam2 = doc.createElement("get_param");
				getparam2.appendChild(doc.createTextNode("flavor_host"+i10));
				flavoser.appendChild(getparam2);
				}
			
			NodeList nets = doc.getElementsByTagName("net");
			
			for (int h=0; h< nets.getLength(); h++ ){
				Element interfaces = doc.createElement("router_interface"+h);
				resource.appendChild(interfaces);
				Element typeinter = doc.createElement("type");
				typeinter.appendChild(doc.createTextNode("OS::Neutron::RouterInterface"));
				interfaces.appendChild(typeinter);
				Element propertiesinter = doc.createElement("properties");
				interfaces.appendChild(propertiesinter);
				Element routeridd = doc.createElement("router_id");
				propertiesinter.appendChild(routeridd);
				Element getresource = doc.createElement("get_resource");
				getresource.appendChild(doc.createTextNode("router"));
				routeridd.appendChild(getresource);
				Element subnetid = doc.createElement("subnet_id");
				propertiesinter.appendChild(subnetid);
				Element getresourcess = doc.createElement("get_resource");
				getresourcess.appendChild(doc.createTextNode("private_subnet"+h));
				subnetid.appendChild(getresourcess);
				
				Element privatesubnets = doc.createElement("private_subnet"+h);
				resource.appendChild(privatesubnets);
				Element typesubnet = doc.createElement("type");
				typesubnet.appendChild(doc.createTextNode("OS::Neutron::Subnet"));
				privatesubnets.appendChild(typesubnet);
				Element propsubnet = doc.createElement("properties");
				privatesubnets.appendChild(propsubnet);
				Element networkid2 = doc.createElement("network_id");
				propsubnet.appendChild(networkid2);
				Element resourcenet = doc.createElement("get_resource");
				resourcenet.appendChild(doc.createTextNode("private_net"));
				networkid2.appendChild(resourcenet);
				Element cidrr = doc.createElement("cidr");
				cidrr.appendChild(doc.createTextNode(listneta.get(h)));
				propsubnet.appendChild(cidrr);
				Element gateway2 = doc.createElement("gateway_ip");
				gateway2.appendChild(doc.createTextNode(listnet.get(h)));
				propsubnet.appendChild(gateway2);
				Element pools = doc.createElement("allocation_pools");
				propsubnet.appendChild(pools);
				Element start = doc.createElement("start");
				start.appendChild(doc.createTextNode(listart.get(h)));
				pools.appendChild(start);
				Element end = doc.createElement("end");
				end.appendChild(doc.createTextNode(listend.get(h)));
				pools.appendChild(end);
			    
			}
			
			int gi = nets.getLength();
			
			NodeList  categorieslist = doc.getElementsByTagName("net");
			while (categorieslist.getLength() > 0) {
			    Node node = categorieslist.item(0);
			    node.getParentNode().removeChild(node);
			}
			
			NodeList  categorieslist2 = doc.getElementsByTagName("vm");
			while (categorieslist2.getLength() > 0) {
			    Node node2 = categorieslist2.item(0);
			    node2.getParentNode().removeChild(node2);
			}
			
			NodeList  categorieslist3 = doc.getElementsByTagName("host");
			while (categorieslist3.getLength() > 0) {
			    Node node3 = categorieslist3.item(0);
			    node3.getParentNode().removeChild(node3);
			}
			
			
			
			
			
			root.insertBefore(version, resource);
			root.insertBefore(description, resource);
			root.insertBefore(parameters, resource);
			
		   
			// write the DOM object to the file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(doc);
			
			StreamResult streamResult = new StreamResult(new File(xml2FilePath));
			transformer.transform(domSource, streamResult);
  
			InputStream in =  new FileInputStream("src/input2.xml");
	        String xml = IOUtils.toString(in);
	        XMLSerializer xmlSerializer = new XMLSerializer(); 
	        JSON json = xmlSerializer.read(xml);  
	        String hi = json.toString();
	        String tmree = hi.replace("\"networks\":{", "\"networks\":[{");
	        String tmree2 = tmree.replace("port\"}}", "port\"}}]");
	        String tmree3 = tmree2.replace("allocation_pools\":{", "allocation_pools\":[{");
	        String tmree4 = tmree3.replace(".254\"}}}", ".254\"}]}}");
	        String tmree5 = tmree4.replace("fixed_ips\":{", "fixed_ips\":[{");
	        
	       
	        List <String> ultimo = new ArrayList<String>();
	        for (int t=0; t< gi; t++ ){
	        String tmre6 = tmree5.replace("private_subnet"+t+"\"}}}},", "private_subnet"+t+"\"}}]}},");
	         tmree5 = tmre6;
	        ultimo.add(tmree5);
	        }
	        
	 
	        System.out.println(ultimo.get(gi-1));
	        
	        
	        
	      
	        File newTextFile = new File("src/final.json");
            FileWriter fileWriter = new FileWriter(newTextFile);
            fileWriter.write(ultimo.get(gi-1));
            fileWriter.close();
			
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}