package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class ProcessSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSample.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        Process process = new ProcessBuilder("notepad.exe").start(); // Java 5

        ProcessHandle handle = process.toHandle(); // Java 9

        LOGGER.info("Process command: {}", handle.info().command().orElse(""));
        LOGGER.info("Process parent command: {}",
                handle.parent().map(ProcessHandle::info).flatMap(ProcessHandle.Info::command).orElse(""));

        handle.onExit() // A CompletableFuture<ProcessHandle>
                .thenAccept(h -> LOGGER.info("Process {} terminated", h.pid()));

        process.waitFor();

        LOGGER.info("Process CPU duration: {}", handle.info().totalCpuDuration().orElse(Duration.ZERO));
    }
}
