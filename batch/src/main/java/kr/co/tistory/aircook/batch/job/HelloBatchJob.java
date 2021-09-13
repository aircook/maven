package kr.co.tistory.aircook.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloBatchJob {

	private static final Logger logger = LoggerFactory.getLogger(HelloBatchJob.class);

	private final String BATCH_NAME = "HelloBatchJob_";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get(BATCH_NAME).start(step1()).next(step2()).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get(BATCH_NAME + "Step1").tasklet((stepContribution, chunkContext) -> {
			logger.info(BATCH_NAME + "Step1 Started >>>>>>>>>>>>>>>>>>>>>>>>>");
			return RepeatStatus.FINISHED;
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get(BATCH_NAME + "Step2").tasklet((stepContribution, chunkContext) -> {
			logger.info((BATCH_NAME + "Step2 Started >>>>>>>>>>>>>>>>>>>>>>>>>"));
			return RepeatStatus.FINISHED;
		}).build();
	}
}
