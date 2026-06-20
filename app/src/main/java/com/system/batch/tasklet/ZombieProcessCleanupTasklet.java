package com.system.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.Nullable;

public class ZombieProcessCleanupTasklet implements Tasklet {
  private final int processesToKill = 10;
  private int killedProcesses = 0;

  private Logger log = LoggerFactory.getLogger(ZombieProcessCleanupTasklet.class);

  @Nullable
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    killedProcesses++;
    log.info("☠️  프로세스 강제 종료... ({}/{})", killedProcesses, processesToKill);
    if (killedProcesses >= processesToKill) {
      log.info("💀 시스템 안정화 완료. 모든 좀비 프로세스 제거.");
      return RepeatStatus.FINISHED;
    }
    return RepeatStatus.CONTINUABLE;
  }
}
