package com.example.service;

import org.springframework.batch.core.JobExecution;

public interface IBenifiitIssuanceMgmtService {

	public JobExecution sendAmountToBenificries()throws Exception;
}
