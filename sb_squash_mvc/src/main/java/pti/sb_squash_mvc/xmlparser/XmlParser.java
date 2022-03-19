package pti.sb_squash_mvc.xmlparser;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.client.RestTemplate;

public class XmlParser {
	
	
	public static double getRate() throws JDOMException, IOException {
		double rate = 0;
		
		RestTemplate rt = new RestTemplate();
		SAXBuilder sb = new SAXBuilder();
		String xml = rt.getForObject("http://api.napiarfolyam.hu/", String.class);
		Document document = sb.build(new StringReader(xml));
		
		Element rootElement = document.getRootElement();
		Element currencyElement = rootElement.getChild("valuta");
		List<Element> currs = currencyElement.getChildren("item");
		Element sale = currs.get(0).getChild("eladas");
		
		rate = Double.valueOf(sale.getValue());
		
		
		return rate;
	}

}
