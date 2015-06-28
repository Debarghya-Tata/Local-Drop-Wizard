package eu.kielczewski.example.auth;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

import eu.kielczewski.example.core.User;

public class GreetingAuthenticator

implements Authenticator<BasicCredentials, User> {

	private String login;
	
	private String password;
	
	public GreetingAuthenticator(String string, String string2) {
		this.login =  string;
		this.password = string2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<User> authenticate(BasicCredentials credentials)
			throws AuthenticationException {

		if (password.equals(credentials.getPassword())) {
			return Optional.of(new User());
		} else {

			return Optional.absent();

		}
	}
}
