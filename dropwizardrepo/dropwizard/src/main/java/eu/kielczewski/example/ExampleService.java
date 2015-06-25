package eu.kielczewski.example;

import org.skife.jdbi.v2.DBI;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;

import eu.kielczewski.example.config.ExampleServiceConfiguration;
import eu.kielczewski.example.dao.PackageItemDAO;
import eu.kielczewski.example.hello.HelloResource;
import eu.kielczewski.example.hello.PackageItemResource;

public class ExampleService extends Service<ExampleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ExampleServiceConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-example");
    }

    @Override
    public void run(ExampleServiceConfiguration conf, Environment env) throws Exception {
    	env.addResource(new HelloResource(conf.getMessages()));
    	final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(env, conf.getDatabaseConfiguration(), "oracle");
     
        final PackageItemDAO packageItemDAO = jdbi.onDemand(PackageItemDAO.class);
        final PackageItemResource packageItemResource = new PackageItemResource(packageItemDAO);

        //env.jersey().register(packageItemResource);
        env.addResource(packageItemResource);
    }

}