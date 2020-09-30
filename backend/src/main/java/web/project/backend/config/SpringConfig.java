package web.project.backend.config;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class SpringConfig implements WebMvcConfigurer {
	
	
	private final String uploadImagesPath;

	public SpringConfig(@Value("${spring.custom.post.image-path}") String uploadImagesPath) {
		this.uploadImagesPath = uploadImagesPath;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		List<String> imageFolders = Arrays.asList("post", "etc");
	    for(String imageFolder : imageFolders) 
	    {
	    	registry.addResourceHandler("/static/img/" +imageFolder +"/**")
	    	.addResourceLocations("file:///" + uploadImagesPath + imageFolder +"/")
	    	.setCachePeriod(3600)
	        .resourceChain(true)
	        .addResolver(new PathResourceResolver());
	    }
	}
	
	
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


