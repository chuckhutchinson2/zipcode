package org.location.controller;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan ({"org.location"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Spring Boot Beans");
            System.out.println("-----------------");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            
            System.out.println("Environment Variables");
            System.out.println("---------------------");
            String[] environmentVariables = System.getProperties().keySet().toArray(new String[0]);
            Arrays.sort(environmentVariables);
            for (String variable : environmentVariables) {
            	System.out.println(variable + " : " + System.getProperty(variable));            	
            }
        };
    }
    
    @Autowired
    private TypeResolver typeResolver;

    private ApiKey apiKey() {
      return new ApiKey("mykey", "api_key", "header");
    }
    
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
            new SecurityReference("mykey", authorizationScopes));
      }

    private SecurityContext securityContext() {
      return SecurityContext.builder()
          .securityReferences(defaultAuth())
          .forPaths(PathSelectors.regex("/*"))
          .build();
    }
    @Bean
    public Docket locationApi() {
      return new Docket(DocumentationType.SWAGGER_2)
          .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
          .pathMapping("/")
          .directModelSubstitute(LocalDate.class,
              String.class)
          .genericModelSubstitutes(ResponseEntity.class)
          .alternateTypeRules(
              newRule(typeResolver.resolve(DeferredResult.class,
                      typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                  typeResolver.resolve(WildcardType.class)))
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET,
              newArrayList(new ResponseMessageBuilder()
                  .code(500)
                  .message("500 message")
                  .responseModel(new ModelRef("Error"))
                  .build()))
          .securitySchemes(newArrayList(apiKey()))
          .securityContexts(newArrayList(securityContext()))
          .enableUrlTemplating(true)
          .tags(new Tag("Location Service", "Location based APIs")) 
          ;
    }
    
    @Bean
    UiConfiguration uiConfig() {
      return new UiConfiguration(
          "/",// url
          "none",       // docExpansion          => none | list
          "alpha",      // apiSorter             => alpha
          "schema",     // defaultModelRendering => schema
          UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
          true,        // enableJsonEditor      => true | false
          true,         // showRequestHeaders    => true | false
          60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }
    
}