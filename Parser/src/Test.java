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

	
	public static final String xmlFilePath = "src/tutorial_ubuntu.xml";
	public static final String[] parts1 = xmlFilePath.split("/");
	public static final String[] parts2 = parts1[1].split("\\.");
	public static final String xml2FilePath = "src/input2.xml";
	public static final String xml3FilePath = "src/recomendacion.xml";
	public static int regu =0;
	
	public static void main(String argv[]) throws XPathExpressionException {

		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFilePath);
			Document doc = documentBuilder.newDocument();
			
			DocumentBuilderFactory documentBuilderFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder2 = documentBuilderFactory2.newDocumentBuilder();
			Document documento = documentBuilder2.parse(xml3FilePath);
			
			
			List<String> listrecorte = new ArrayList<String>();
			NodeList routers55 = documento.getElementsByTagName("router");
			for (int i =0;i<routers55.getLength();i++){
				Node routerdecisor =  documento.getElementsByTagName("router").item(i);
				String decisionroute = routerdecisor.getTextContent();
				listrecorte.add(decisionroute);
			}
			
			NodeList imagenes = documento.getElementsByTagName("imagen");
			for (int p=0;p<imagenes.getLength();p++){
			Node imagenes2 = documento.getElementsByTagName("imagen").item(p);
			String imagen = imagenes2.getFirstChild().getTextContent();
			System.out.println(imagen);
			}
			
			NodeList flavors2 = documento.getElementsByTagName("flavor");
			
			
				
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
				
				Element securitygroup = doc.createElement("securitygroup");
				resource.appendChild(securitygroup);
				Element typesecu = doc.createElement("Type");
				typesecu.appendChild(doc.createTextNode("AWS::EC2::SecurityGroup"));
				securitygroup.appendChild(typesecu);
				Element propertiessecu = doc.createElement("Properties");
				securitygroup.appendChild(propertiessecu);	
				Element groupdescription = doc.createElement("GroupDescription");
				groupdescription.appendChild(doc.createTextNode("grupodeseguridad"));
				propertiessecu.appendChild(groupdescription);
				Element SecurityGroupIngress = doc.createElement("SecurityGroupIngress");
				propertiessecu.appendChild(SecurityGroupIngress);
				Element qq = doc.createElement("qq");
				SecurityGroupIngress.appendChild(qq);
				Element CidrIp = doc.createElement("CidrIp");
				CidrIp.appendChild(doc.createTextNode("0.0.0.0/24"));
				qq.appendChild(CidrIp);
				Element FromPort = doc.createElement("FromPort");
				FromPort.appendChild(doc.createTextNode("22"));
				qq.appendChild(FromPort);
				Element ToPort = doc.createElement("ToPort");
				ToPort.appendChild(doc.createTextNode("22"));
				qq.appendChild(ToPort);
				Element IpProtocol = doc.createElement("IpProtocol");
				IpProtocol.appendChild(doc.createTextNode("tcp"));
				qq.appendChild(IpProtocol);
				Element qq2 = doc.createElement("qq");
				SecurityGroupIngress.appendChild(qq2);
				Element CidrIp2 = doc.createElement("CidrIp");
				CidrIp2.appendChild(doc.createTextNode("0.0.0.0/24"));
				qq2.appendChild(CidrIp2);
				Element FromPort2 = doc.createElement("FromPort");
				FromPort2.appendChild(doc.createTextNode("-1"));
				qq2.appendChild(FromPort2);
				Element ToPort2 = doc.createElement("ToPort");
				ToPort2.appendChild(doc.createTextNode("-1"));
				qq2.appendChild(ToPort2);
				Element IpProtocol2 = doc.createElement("IpProtocol");
				IpProtocol2.appendChild(doc.createTextNode("icmp"));
				qq2.appendChild(IpProtocol2);
				
				
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
		    	List<String> privanets = new ArrayList<String>();
		    	List<String> subnets2 = new ArrayList<String>();
		    	List <String> subhost = new ArrayList<String>();
		    	List <String> subnethost = new ArrayList<String>();
		    	List <String> nethost = new ArrayList<String>();
		    	List<String> interfaces22 = new ArrayList<String>();
		    	List<String> routers23 = new ArrayList<String>();
		    	List<String> gatewaysnet = new ArrayList<String>();
		    	List<String> interfaces33 = new ArrayList<String>();
		    	List<String> routers33 = new ArrayList<String>();
		    	List<String> gatewaysnet33 = new ArrayList<String>();
		    	
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
				
		
				for (int iw = 0; iw < i2; iw++) {
					Node names = doc.getElementsByTagName("vm").item(iw);
					NodeList oo = names.getChildNodes();
				    int jaja = oo.getLength();
				    Node vms = names.getChildNodes().item(jaja-2);
				    String hh = vms.getNodeName(); 
				    interfaces44.clear();
				    
				    if (hh!="forwarding"){ 
				    	Element vmss = (Element)doc.getElementsByTagName("vm").item(iw);
				    	Node haha = vmss.getElementsByTagName("if").item(0);
				    	NamedNodeMap hihi = haha.getAttributes();
				    	Node jojoo = hihi.getNamedItem("net");
				    	String oa = jojoo.getTextContent();
				    	netvm.add(oa);
				    }
				    else {
				    	
				    	if (listrecorte.get(regu).equals("Virtual_Router")==true){
				    	Element routerss = doc.createElement("router" + iw);
						resource.appendChild(routerss);
						Element typerout = doc.createElement("type");
						typerout.appendChild(doc.createTextNode("OS::Neutron::Router"));
						routerss.appendChild(typerout);
						Element gateways = doc.createElement("router_gateway" + iw);
						resource.appendChild(gateways);
						Element typegate = doc.createElement("type");
						typegate.appendChild(doc.createTextNode("OS::Neutron::RouterGateway"));
						gateways.appendChild(typegate);
						Element propergate = doc.createElement("properties");
						gateways.appendChild(propergate);
						Element routeridga = doc.createElement("router_id");
						propergate.appendChild(routeridga);				
						Element getregate = doc.createElement("get_resource");
						getregate.appendChild(doc.createTextNode("router"+ iw));
						routeridga.appendChild(getregate);
						Element networkidgate = doc.createElement("network_id");
						networkidgate.appendChild(doc.createTextNode("d72f0f5f-d4b2-490a-bfb2-71a584c4e39c"));
						propergate.appendChild(networkidgate);
				    	
						Element routers22 = (Element)doc.getElementsByTagName("vm").item(iw);
				    	NodeList haha22 = routers22.getElementsByTagName("if");
				    	
				    	for (int jodm=0;jodm<haha22.getLength();jodm++){
				    	Node haha23 = routers22.getElementsByTagName("if").item(jodm);
				    	NamedNodeMap hihi22 = haha23.getAttributes();
				    	Node jojoo22 = hihi22.getNamedItem("net");
				    	String oas = jojoo22.getTextContent().toLowerCase();
				    	
				    	Node gate = routers22.getElementsByTagName("ipv4").item(jodm);
				    	String gat22 = gate.getTextContent();
				    	String gat23 =gat22.replace("/24", "");
				    	
				    	String router33 = "router" + iw;
				    	interfaces22.add(oas);
				    	routers23.add(router33);
				    	gatewaysnet.add(gat23);
				    	}
				    					    	
				    }
				    else if (listrecorte.get(regu).equals("Instance")==true){
				    	Element routerss = doc.createElement("router" + iw);
						resource.appendChild(routerss);
						Element typerout = doc.createElement("type");
						typerout.appendChild(doc.createTextNode("OS::Neutron::Router"));
						routerss.appendChild(typerout);
						Element gateways = doc.createElement("router_gateway" + iw);
						resource.appendChild(gateways);
						Element typegate = doc.createElement("type");
						typegate.appendChild(doc.createTextNode("OS::Neutron::RouterGateway"));
						gateways.appendChild(typegate);
						Element propergate = doc.createElement("properties");
						gateways.appendChild(propergate);
						Element routeridga = doc.createElement("router_id");
						propergate.appendChild(routeridga);				
						Element getregate = doc.createElement("get_resource");
						getregate.appendChild(doc.createTextNode("router"+ iw));
						routeridga.appendChild(getregate);
						Element networkidgate = doc.createElement("network_id");
						networkidgate.appendChild(doc.createTextNode("d72f0f5f-d4b2-490a-bfb2-71a584c4e39c"));
						propergate.appendChild(networkidgate);
				    	
						Element routers22 = (Element)doc.getElementsByTagName("vm").item(iw);
				    	NodeList haha22 = routers22.getElementsByTagName("if");
				    	
				    	for (int jodm=0;jodm<haha22.getLength();jodm++){
				    	Node haha23 = routers22.getElementsByTagName("if").item(jodm);
				    	NamedNodeMap hihi22 = haha23.getAttributes();
				    	Node jojoo22 = hihi22.getNamedItem("net");
				    	String oas = jojoo22.getTextContent().toLowerCase();
				    	
				    	Node gate = routers22.getElementsByTagName("ipv4").item(jodm);
				    	String gat22 = gate.getTextContent();
				    	String gat23 =gat22.replace("/24", "");
				    	
				    	String router33 = "router" + iw;
				    	interfaces22.add(oas);
				    	routers23.add(router33);
				    	gatewaysnet.add(gat23);
				    	}
				    					    	
				    }
				    else if (listrecorte.get(regu).equals("Instance")==true){
				    	Element servers = doc.createElement("router" + iw);
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
						
						
						Element routers22 = (Element)doc.getElementsByTagName("vm").item(iw);
				    	NodeList haha22 = routers22.getElementsByTagName("if");
						
				    	for (int jodm=0;jodm<haha22.getLength();jodm++){
							Node haha23 = routers22.getElementsByTagName("if").item(jodm);
					    	NamedNodeMap hihi22 = haha23.getAttributes();
					    	Node jojoo22 = hihi22.getNamedItem("net");
					    	String oas = jojoo22.getTextContent().toLowerCase();
					    	
					    	Node gate = routers22.getElementsByTagName("ipv4").item(jodm);
					    	String gat22 = gate.getTextContent();
					    	String gat23 =gat22.replace("/24", "");
					    	
					    	String router33 = "router" + iw;
					    	interfaces33.add(oas);
					    	routers33.add(router33);
					    	gatewaysnet33.add(gat23);
					    	interfaces44.add(oas);
						}
				    	
				    	for (int ya=0;ya < interfaces44.size();ya++){
				    		
				    		Element port = doc.createElement("mm");
							networks.appendChild(port);
							Element port22 = doc.createElement("port");
							port.appendChild(port22);
							Element getres = doc.createElement("get_resource");
							if (interfaces44.size()<interfaces33.size()){
							int hj = ya+interfaces33.size()-interfaces44.size();
							getres.appendChild(doc.createTextNode("port_router"+hj));}
							else{
							getres.appendChild(doc.createTextNode("port_router"+ya));	
							}
							
							port22.appendChild(getres);
							
							
				    	}
						
						
						if (imagenes.getLength()==1){
							Node imagenes2 = documento.getElementsByTagName("imagen").item(0);
							String imagen = imagenes2.getFirstChild().getTextContent();
							Element imageser = doc.createElement("image");
							propertiese.appendChild(imageser);
							imageser.appendChild(doc.createTextNode(""+imagen));
							}
						else if (imagenes.getLength()>1){
							Node imagenes2 = documento.getElementsByTagName("imagen").item(iw-regu);
							String imagen = imagenes2.getFirstChild().getTextContent();
							Element imageser = doc.createElement("image");
							propertiese.appendChild(imageser);
							imageser.appendChild(doc.createTextNode(""+imagen));
							}
						
						
						
						if (flavors2.getLength()==1){
							Node flavors22 = documento.getElementsByTagName("flavor").item(0);
							String flavor = flavors22.getFirstChild().getTextContent();
							Element flavorser = doc.createElement("flavor");
							propertiese.appendChild(flavorser);
							flavorser.appendChild(doc.createTextNode(""+flavor));
							}
						else if (flavors2.getLength()>1){
							Node flavors22 = documento.getElementsByTagName("flavor").item(iw-regu);
							String flavor = flavors22.getFirstChild().getTextContent();
							Element flavorser = doc.createElement("flavor");
							propertiese.appendChild(flavorser);
							flavorser.appendChild(doc.createTextNode(""+flavor));
							}
				    }
				    	regu++;
				    }
				   
				}
				
				for (int ya=0;ya < interfaces33.size();ya++){
					
					
					Element serverports = doc.createElement("port_router"+ya);
					resource.appendChild(serverports);
					Element typen = doc.createElement("type");
					typen.appendChild(doc.createTextNode("OS::Neutron::Port"));
					serverports.appendChild(typen);
					Element propertiesn = doc.createElement("properties");
					serverports.appendChild(propertiesn);
					Element networkidd = doc.createElement("network_id");
					propertiesn.appendChild(networkidd);
					Element getress = doc.createElement("get_resource");
					getress.appendChild(doc.createTextNode("private_"+interfaces33.get(ya)));
					networkidd.appendChild(getress);
					
					Element fixesips = doc.createElement("fixed_ips");
					propertiesn.appendChild(fixesips);
					Element gatewayip = doc.createElement("ip_address");
					fixesips.appendChild(gatewayip);
					gatewayip.appendChild(doc.createTextNode(gatewaysnet33.get(ya)));
					
				    Element subnetidporti = doc.createElement("subnet_id");
				    fixesips.appendChild(subnetidporti);
				    Element getresss = doc.createElement("get_resource");	    
					getresss.appendChild(doc.createTextNode("private_sub"+interfaces33.get(ya)));
					subnetidporti.appendChild(getresss);
					
					
					
				}
				
				for (int ya=0;ya < interfaces22.size();ya++){
					Element interfaces = doc.createElement("router_interface"+ya);
					resource.appendChild(interfaces);
					Element typeinter = doc.createElement("type");
					typeinter.appendChild(doc.createTextNode("OS::Neutron::RouterInterface"));
					interfaces.appendChild(typeinter);
					Element propertiesinter = doc.createElement("properties");
					interfaces.appendChild(propertiesinter);
					Element routeridd = doc.createElement("router_id");
					propertiesinter.appendChild(routeridd);
					Element getresource = doc.createElement("get_resource");
					getresource.appendChild(doc.createTextNode(routers23.get(ya)));
					routeridd.appendChild(getresource);
					Element subnetid = doc.createElement("port_id");
					propertiesinter.appendChild(subnetid);
					Element getresourcess = doc.createElement("get_resource");
					getresourcess.appendChild(doc.createTextNode("port_interface"+ya));
					subnetid.appendChild(getresourcess);
					
					Element port_interface = doc.createElement("port_interface"+ya);
					resource.appendChild(port_interface);
					Element typeporti = doc.createElement("type");
					typeporti.appendChild(doc.createTextNode("OS::Neutron::Port"));
					port_interface.appendChild(typeporti);
					Element propertiesporti = doc.createElement("properties");
					port_interface.appendChild(propertiesporti);
					Element networkidd = doc.createElement("network_id");
					propertiesporti.appendChild(networkidd);
					Element getress = doc.createElement("get_resource");
					getress.appendChild(doc.createTextNode("private_"+interfaces22.get(ya)));
					networkidd.appendChild(getress);
					Element fixesips = doc.createElement("fixed_ips");
					propertiesporti.appendChild(fixesips);
					Element gatewayip = doc.createElement("ip_address");
					fixesips.appendChild(gatewayip);
					gatewayip.appendChild(doc.createTextNode(gatewaysnet.get(ya)));
					
				    Element subnetidporti = doc.createElement("subnet_id");
				    fixesips.appendChild(subnetidporti);
				    Element getresss = doc.createElement("get_resource");	    
					getresss.appendChild(doc.createTextNode("private_sub"+interfaces22.get(ya)));
					subnetidporti.appendChild(getresss);
					
				System.out.println("Las interfaces son "+ interfaces22.get(ya)+routers23.get(ya)+gatewaysnet.get(ya));
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
							nethost.add("private_net"+jk);
						}
					}
										
				}
				
				for (int hy = 0; hy<netvm.size();hy++){
					String jo = netvm.get(hy);
									
					for (int k=0; k<subnets2.size();k++){
						String das = subnets2.get(k);
						if (jo.equals(das)==true){
							subnets.add("private_subnet"+k);
							privanets.add("private_net"+k);
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
					getress.appendChild(doc.createTextNode(privanets.get(i6-i1000)));
					networkidd.appendChild(getress);
					
			//		Element securityser = doc.createElement("security_groups");
			//		propertiesn.appendChild(securityser);
			//		Element getsecur = doc.createElement("get_resource");
			//		getsecur.appendChild(doc.createTextNode("securitygroup"));
			//		securityser.appendChild(getsecur);
					
					
					
					Element fixesips = doc.createElement("fixed_ips");
				    propertiesn.appendChild(fixesips);
				    Element subnetid = doc.createElement("subnet_id");
				    fixesips.appendChild(subnetid);
				    Element getresss = doc.createElement("get_resource");	    
					getresss.appendChild(doc.createTextNode(subnets.get(i6-i1000)));
					subnetid.appendChild(getresss);
					
					if (imagenes.getLength()==1){
						Node imagenes2 = documento.getElementsByTagName("imagen").item(0);
						String imagen = imagenes2.getFirstChild().getTextContent();
						Element imageser = doc.createElement("image");
						propertiese.appendChild(imageser);
						imageser.appendChild(doc.createTextNode(""+imagen));
						}
					else if (imagenes.getLength()>1){
						Node imagenes2 = documento.getElementsByTagName("imagen").item(i6-i1000);
						String imagen = imagenes2.getFirstChild().getTextContent();
						Element imageser = doc.createElement("image");
						propertiese.appendChild(imageser);
						imageser.appendChild(doc.createTextNode(""+imagen));
						}
					
					
					
					if (flavors2.getLength()==1){
						Node flavors22 = documento.getElementsByTagName("flavor").item(0);
						String flavor = flavors22.getFirstChild().getTextContent();
						Element flavorser = doc.createElement("flavor");
						propertiese.appendChild(flavorser);
						flavorser.appendChild(doc.createTextNode(""+flavor));
						}
					else if (flavors2.getLength()>1){
						Node flavors22 = documento.getElementsByTagName("flavor").item(i6-i1000);
						String flavor = flavors22.getFirstChild().getTextContent();
						Element flavorser = doc.createElement("flavor");
						propertiese.appendChild(flavorser);
						flavorser.appendChild(doc.createTextNode(""+flavor));
						}
					
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
					getress.appendChild(doc.createTextNode(nethost.get(i10)));
					networkidd.appendChild(getress);
					
		//			Element securityser = doc.createElement("security_groups");
		//			propertiesn.appendChild(securityser);
		//			Element getsecur = doc.createElement("get_resource");
		//			getsecur.appendChild(doc.createTextNode("securitygroup"));
		//			securityser.appendChild(getsecur);
					
					Element fixesips = doc.createElement("fixed_ips");
				    propertiesn.appendChild(fixesips);
				    Element subnetid = doc.createElement("subnet_id");
				    fixesips.appendChild(subnetid);
				    Element getresss = doc.createElement("get_resource");
					getresss.appendChild(doc.createTextNode(subnethost.get(i10)));
					subnetid.appendChild(getresss);
					
					
					if (imagenes.getLength()==1){
						Node imagenes2 = documento.getElementsByTagName("imagen").item(0);
						String imagen = imagenes2.getFirstChild().getTextContent();
						Element imageser = doc.createElement("image");
						propertieseh.appendChild(imageser);
						imageser.appendChild(doc.createTextNode(""+imagen));
						}
					
					else if (imagenes.getLength()>1){
						int imagehost22 = imagenes.getLength()-1;
						Node imagenes2 = documento.getElementsByTagName("imagen").item(imagehost22);
						String imagen = imagenes2.getFirstChild().getTextContent();
						Element imageser = doc.createElement("image");
						propertieseh.appendChild(imageser);
						imageser.appendChild(doc.createTextNode(""+imagen));
						}
					
					if (flavors2.getLength()==1){
						Node flavors22 = documento.getElementsByTagName("flavor").item(0);
						String flavor = flavors22.getFirstChild().getTextContent();
						Element flavorser = doc.createElement("flavor");
						propertieseh.appendChild(flavorser);
						flavorser.appendChild(doc.createTextNode(""+flavor));
						}
					else if (flavors2.getLength()>1){
						int flavorhost22 = flavors2.getLength()-1;
						Node flavors22 = documento.getElementsByTagName("flavor").item(flavorhost22);
						String flavor = flavors22.getFirstChild().getTextContent();
						Element flavorser = doc.createElement("flavor");
						propertieseh.appendChild(flavorser);
						flavorser.appendChild(doc.createTextNode(""+flavor));
						}
					}
				
				NodeList nets = doc.getElementsByTagName("net");
				
				for (int h=0; h< nets.getLength(); h++ ){
					
					Element privatenets = doc.createElement("private_net"+h);
					resource.appendChild(privatenets);
					Element typenet = doc.createElement("type");
					typenet.appendChild(doc.createTextNode("OS::Neutron::Net"));
					privatenets.appendChild(typenet);
					
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
					resourcenet.appendChild(doc.createTextNode("private_net"+h));
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
		        String tmree3 = tmree2.replace("security_groups\":{", "security_groups\":[{");
		        String tmree4 = tmree3.replace("securitygroup\"},","securitygroup\"}],");
		        
		        String tmree5 = tmree4.replace("allocation_pools\":{", "allocation_pools\":[{");
		        String tmree6 = tmree5.replace(".254\"}}}", ".254\"}]}}");
		        String tmree7 = tmree6.replace("fixed_ips\":{", "fixed_ips\":[{");
		        
		       
		        List <String> ultimo = new ArrayList<String>();
		        for (int t=0; t< gi; t++ ){
		        String tmre8 = tmree7.replace("private_subnet"+t+"\"}}}},", "private_subnet"+t+"\"}}]}},");
		         tmree7 = tmre8;
		        ultimo.add(tmree7);
		        }
		        
		 
		        System.out.println(ultimo.get(gi-1));
		        
		        
		        
		      
		        File newTextFile = new File("src/"+parts2[0]+".json");
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
