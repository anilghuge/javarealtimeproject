package com.example.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.entity.ElibilityDetailsEntity;
import com.example.model.ElibilityDetails;

@Component
public class EDDetailsProcessor implements ItemProcessor<ElibilityDetailsEntity, ElibilityDetails> {

	@Override
	public ElibilityDetails process(ElibilityDetailsEntity item) throws Exception {
		if (item.getPlanStatus().equalsIgnoreCase("Approved")) {
			ElibilityDetails details=new ElibilityDetails();
			BeanUtils.copyProperties(item, details);
			return details;
		} else {
			return null;
		}
	}

}
