package nhn.academy.config;

import nhn.academy.converter.CsvHttpMessageConverter;
import nhn.academy.resolver.PageableArgumentResolver;
import nhn.academy.resolver.RequesterResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.management.StringValueExp;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvHttpMessageConverter());
        System.out.println("size1 : " + converters.size());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("size2 : " + converters.size());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("format")
                .favorParameter(true).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("csv", new MediaType("text","csv"));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequesterResolver(localeResolver()));
        resolvers.add(new PageableArgumentResolver());
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);  // 기본 Locale 설정
        return localeResolver;
    }

    // security 인가 실습을 위해 추가
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/**").setViewName("admin");
        registry.addViewController("/private-project/**").setViewName("private-project");
        registry.addViewController("/public-project/**").setViewName("public-project");
    }

}
