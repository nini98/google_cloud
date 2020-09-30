package web.project.backend.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JsoupUtils {

	
	public static List<String> ImageParser(String PostContent) {
        
		List<String> images = new ArrayList<>();
		
		try {
			
	        Document doc = Jsoup.parse(PostContent);
	        
	        Elements imgTag = doc.getElementsByTag("img");
	        
	        for(int i = 0;i < imgTag.size();i++)
	        {
	        	images.add(imgTag.get(i).attr("src"));
	        }
	        
	        return images;
		} catch(Exception e) {
			log.error("HTML Parser Error");
			return null;
		}
    }

}
