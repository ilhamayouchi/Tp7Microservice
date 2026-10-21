package ma.wx.jaxrs.config;

import jakarta.ws.rs.ApplicationPath;
import ma.wx.jaxrs.controllers.CompteRestJaxRSAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class MyConfig extends ResourceConfig {

    public MyConfig() {
        register(CompteRestJaxRSAPI.class);
    }
}