package com.yido.clubd.config;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class AppConfig  extends WebMvcConfigurerAdapter{
	
	@Value("${file.root}") String rootDir;
	
    @Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// Restful 한 서비스에서 APPLICATION_JSON_VALUE로 지정하여도 충분하나
		// IE9에서 JSON을 iframe transport로 전송 시 파일로 저장하려는 버그 발생으로 인해 아래와 같이 선언.
		MediaType API_PRODUCES_MEDIATYPE = MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=utf-8");
		configurer.ignoreAcceptHeader(false) // HttpRequest Header의 Accept 무시 여부
				.favorPathExtension(true) // 프로퍼티 값을 보고 URL의 확장자에서 리턴 포맷을 결정 여부
				.ignoreUnknownPathExtensions(true) // 모든 미디어 유형으로 해결할 수없는 경로 확장자를 가진 요청을 무시할지 여부
				.favorParameter(true) // URL 호출 시 특정 파라미터로 리턴포맷 전달 허용 여부 ex)a.do?format=json
				.mediaType("json", MediaType.APPLICATION_JSON_UTF8); // UTF-8 기반 JSON 타입 선언
				

	}
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	String imgDir = "file:///" + rootDir + "/";
        registry.addResourceHandler("/store/**").addResourceLocations(imgDir);
    }
    

}
