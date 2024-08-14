package com.example.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.binding.CitizenAppRegistrationInputs;
import com.example.demo.entity.CitizenApplicationRegistrationEntity;
import com.example.demo.exception.InvalidSSNException;
import com.example.demo.repository.IApplicationRegistrationRepository;
import com.example.demo.service.ICitizenApplicationRegistratipnService;

import reactor.core.publisher.Mono;

@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistratipnService {

	@Autowired
	private IApplicationRegistrationRepository applicationRegistrationRepository;

	@Autowired
	private RestTemplate template;

	@Value("${ar.ssa-web.url}")
	private String endPointURL;

	@Value("${ar.state}")
	private String targetState;

	@Autowired
	private WebClient client;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs input) throws InvalidSSNException {

		// perform webservice call to check whether SSN is valid or not and to get the
		// state name
		// ResponseEntity<String> response = template.exchange(endPointURL,
		// HttpMethod.GET, null, String.class,input.getSsn());
		// String stateName = =response.getBody();

		// perform webservice call to check whether SSN is valid or not and to get the
		// state name ( using webclient)

		Mono<String> response = client.get().uri(endPointURL, input.getSsn()).retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals,
						res -> res.bodyToMono(String.class).map(r-> new InvalidSSNException("Invalid ssn")))
				.bodyToMono(String.class);
		String stateName = response.block();

		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare entity object
			CitizenApplicationRegistrationEntity entity = new CitizenApplicationRegistrationEntity();
			BeanUtils.copyProperties(input, entity);
			entity.setStateName(stateName);
			// save object
			Integer appId = applicationRegistrationRepository.save(entity).getAppId();
			return appId;

		}
		// register citizen if the he belongs to California state(CA)
		throw new InvalidSSNException("invalid ssn");
	}

}
