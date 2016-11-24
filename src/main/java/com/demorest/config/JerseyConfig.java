package com.demorest.config;

import com.demorest.endpoint.ArtistEndpoint;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequestAttributeListener;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        super();
        register(ArtistEndpoint.class);
        register(JacksonFeature.class);
        register(CORSFilter.class);
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        packages("io.swagger.jaxrs.json");
    }
}