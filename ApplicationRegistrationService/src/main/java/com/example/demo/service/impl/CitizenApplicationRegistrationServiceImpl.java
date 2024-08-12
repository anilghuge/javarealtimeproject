package com.example.demo.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.binding.CitizenAppRegistrationInputs;
import com.example.demo.entity.CitizenApplicationRegistrationEntity;
import com.example.demo.repository.IApplicationRegistrationRepository;
import com.example.demo.service.ICitizenApplicationRegistratipnService;

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

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs input) {

		// perform webservice call to check whether SSN is valid or not and to get the
		// state name
		ResponseEntity<String> response = template.exchange(endPointURL, HttpMethod.GET, null, String.class,
				input.getSsn());
		String stateName = response.getBody();

		// register citizen if the he belongs to California state(CA)
		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare entity object
			CitizenApplicationRegistrationEntity entity = new CitizenApplicationRegistrationEntity();
			BeanUtils.copyProperties(input, entity);
			entity.setStateName(stateName);
			// save object
			Integer appId = applicationRegistrationRepository.save(entity).getAppId();
			return appId;
		}
		return 0;
	}

}
