package com.example.config;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.dao.IEligibilityDeterminationRepository;
import com.example.entity.ElibilityDetailsEntity;
import com.example.model.ElibilityDetails;
import com.example.processor.EDDetailsProcessor;

@Configuration
public class BatchConfig {

	@Autowired
	IEligibilityDeterminationRepository determinationRepository;

	@Autowired
	private EDDetailsProcessor processor;

	@Bean(name = "reader")
	RepositoryItemReader<ElibilityDetailsEntity> createReader() {
		
		HashMap<String,Sort.Direction> sorts=new HashMap<String,Sort.Direction>();
		sorts.put("caseNo", Sort.Direction.ASC);
		//or .sorts(Map.of("caseNo", Sort.Direction.ASC)
		return new RepositoryItemReaderBuilder<ElibilityDetailsEntity>()
				.name("repo-reader")
				.repository(determinationRepository)
				.methodName("findAll")
				.sorts(sorts)
				.build();
	}

	@Bean(name = "writer")
	FlatFileItemWriter<ElibilityDetails> createWriter() {
		return new FlatFileItemWriterBuilder<ElibilityDetails>().name("file-writer")
				.resource(new FileSystemResource("beneficiries_list.csv")).lineSeparator("\r\n").delimited()
				.delimiter(",")
				.names("caseNo", "holderName", "planName", "planStatus", "benifitAmt", "bankName", "accountNumber")
				.build();
	}

	// step obj
	@Bean(name = "step1")
	Step createSetp1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("step1", jobRepository)
				.<ElibilityDetailsEntity, ElibilityDetails>chunk(3, platformTransactionManager).reader(createReader())
				.processor(processor).writer(createWriter()).build();
	}

	// job obj
	@Bean(name = "job1")
	Job createJob(JobRepository jobRepository, Step step1) {
		return new JobBuilder("job1", jobRepository).incrementer(new RunIdIncrementer()).start(step1).build();

	}

}
