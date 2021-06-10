package co.kr.gravy.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	/* 
	 * CORS 설정하기
	 * CORS를 설정해주지 않거나 제대로 설정하지 않은 경우, 원하는대로 리소스를 공유하지 못하게 됩니다.
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // CORS를 적용할 URL패턴을 정의
                .allowedOrigins("*") //allowedOrigins 메소드를 이용해서 자원 공유
                .allowedMethods("*"); //allowedMethods를 이용해서 허용할 HTTP method를 지정
	}
}
