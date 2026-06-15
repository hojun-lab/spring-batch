package com.system.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.Nullable;

import java.io.File;

public class DeleteOldFilesTasklet implements Tasklet {

    private final String path;
    private final int daysOld;

    private Logger log = LoggerFactory.getLogger(DeleteOldFilesTasklet.class);

    public DeleteOldFilesTasklet(String path, int daysOld) {
        this.path = path;
        this.daysOld = daysOld;
    }

    @Nullable
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File dir = new File(path);
        long cutoffTime = System.currentTimeMillis() - (daysOld * 24 * 60 * 60 * 1000L);

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.lastModified() < cutoffTime) {
                    if (file.delete()) {
                        log.info("파일 삭제: {}", file.getName());
                    } else {
                        log.info("파일 삭제 실패: {}", file.getName());
                    }
                }
            }
        }
        return RepeatStatus.FINISHED;
    }
}
