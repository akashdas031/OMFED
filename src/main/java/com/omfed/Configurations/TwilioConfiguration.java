package com.omfed.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioConfiguration {

	@Value("${twilio.accountSid}")
	private String accountSid;
	@Value("${twilio.authToken}")
	private String authToken;
	@Value("${twilio.phoneNumber}")
	private String phoneNumber;
	public void init() {
		Twilio.init(accountSid,authToken);
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
}
