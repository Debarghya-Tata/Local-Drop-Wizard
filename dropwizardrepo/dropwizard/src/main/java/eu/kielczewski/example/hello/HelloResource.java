package eu.kielczewski.example.hello;

import eu.kielczewski.example.config.MessagesConfiguration;
import eu.kielczewski.example.core.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.yammer.dropwizard.auth.Auth;

@Path(value = "/hello")
public class HelloResource {

    private final MessagesConfiguration conf;

    public HelloResource(MessagesConfiguration conf) {
        this.conf = conf;
    }

    @GET
    public String sayHello(@Auth User user) {
        return conf.getHello();
    }

}
