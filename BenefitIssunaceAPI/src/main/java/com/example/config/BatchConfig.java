package com.example.config;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.dao.IEligibilityDeterminationRepository;
import com.example.entity.ElibilityDetailsEntity;

@Configuration
public class BatchConfig {

	@Autowired
	IEligibilityDeterminationRepository determinationRepository;

	@Bean
	RepositoryItemReader<ElibilityDetailsEntity> createReader() {
		return new RepositoryItemReaderBuilder<ElibilityDetailsEntity>().repository(determinationRepository)
				.methodName("findAll").build();
	}

	@Bean
	FlatFileItemWriter<ElibilityDetailsEntity> createWriter() {
		return new FlatFileItemWriterBuilder<ElibilityDetailsEntity>()
				.name("file-writer").resource(new FileSystemResource("beneficiries_list.csv")).lineSeparator("\r\n")
				.delimited().delimiter(",").names("edTracId", "caseNo", "holderName", "planName", "planStatus",
						"planStartDate", "planEndDate", "benifitAmt", "denialReason", "bankName", "accountNumber")
				.build();
	}
}
