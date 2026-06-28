package com.system.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.system.batch.tasklet.ZombieProcessCleanupTasklet;

@Configuration
public class ZombieBatchConfig {
  private final JobRepository jobRepository;

  public ZombieBatchConfig(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Bean
  public Tasklet zombieProcessCleanupTasklet() {
    return new ZombieProcessCleanupTasklet();
  }

  @Bean
  public Step zombieCleanupStep() {
    return new StepBuilder("zombieCleanupStep", jobRepository)
        .tasklet(zombieProcessCleanupTasklet(), new ResourcelessTransactionManager())
        .build();
  }

  @Bean
  public Job zombieCleanupJob() {
    return new JobBuilder("zombieCleanupJob", jobRepository).start(zombieCleanupStep())
        .build();
  }
}
