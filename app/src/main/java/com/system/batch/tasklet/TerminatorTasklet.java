package com.system.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class TerminatorTasklet {

  private final Logger log = LoggerFactory.getLogger(TerminatorTasklet.class);

  @Bean
  @StepScope
  public Tasklet terminatorTasklet(
      @Value("#{jobParameters['questDifficulty']}") QuestDifficulty questDifficulty) {
    return (contribution, chunkContext) -> {
      log.info("⚔️ 시스템 침투 작전 개시!");
      log.info("임무 난이도: {}", questDifficulty);
      // 난이도에 따른 보상 계산
      int baseReward = 100;
      int rewardMultiplier = switch (questDifficulty) {
        case EASY -> 1;
        case NORMAL -> 2;
        case HARD -> 3;
        case EXTREME -> 5;
      };
      int totalReward = baseReward * rewardMultiplier;
      log.info("💥 시스템 해킹 진행 중...");
      log.info("🏆 시스템 장악 완료!");
      log.info("💰 획득한 시스템 리소스: {} 메가바이트", totalReward);
      return RepeatStatus.FINISHED;
    };
  }
}
