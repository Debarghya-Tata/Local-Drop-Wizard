package eu.kielczewski.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ExampleServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessagesConfiguration messages) {
        this.messages = messages;
    }
    
    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }
}
