package com.example.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.entity.ElibilityDetailsEntity;

public class EDDetailsProcessor implements ItemProcessor<ElibilityDetailsEntity, ElibilityDetailsEntity> {

	@Override
	public ElibilityDetailsEntity process(ElibilityDetailsEntity item) throws Exception {
		if (item.getPlanStatus().equalsIgnoreCase("Approved")) {
			return item;
		} else {
			return null;
		}
	}

}
