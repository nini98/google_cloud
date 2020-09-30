package web.project.backend.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64Utils {
    public static final String BASE_64_PREFIX_PNG = "data:image/png;base64,";
    public static final String BASE_64_PREFIX_JPEG = "data:image/jpeg;base64,";
    public static final String BASE_64_PREFIX_GIF = "data:image/gif;base64,";

    public static List<byte[]> decodeBase64ToBytes(List<String> imageList) {
    	
    	if(imageList.isEmpty())
    		return null;
    	
    	List<byte[]> imageBytes = new ArrayList<>();
    	
    	for(int i = 0;i < imageList.size();i++)
    	{
    		String imageString = imageList.get(i);
    		
    		if (imageString.startsWith(BASE_64_PREFIX_PNG))
                imageBytes.add(Base64.getDecoder().decode(imageString.substring(BASE_64_PREFIX_PNG.length())));
            else if (imageString.startsWith(BASE_64_PREFIX_JPEG))
            	imageBytes.add(Base64.getDecoder().decode(imageString.substring(BASE_64_PREFIX_JPEG.length())));
            else if (imageString.startsWith(BASE_64_PREFIX_GIF))
            	imageBytes.add(Base64.getDecoder().decode(imageString.substring(BASE_64_PREFIX_GIF.length())));
            else
                imageBytes.add(null);
    	}
    	
        
    	return imageBytes;
    }

}