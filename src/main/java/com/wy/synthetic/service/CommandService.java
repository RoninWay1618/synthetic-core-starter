package com.wy.synthetic.service;

import com.wy.synthetic.dto.CommandDto;
import com.wy.synthetic.dto.CommandPriority;
import com.wy.synthetic.exception.CommandQueueFullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class CommandService {
    private static final Logger log = LoggerFactory.getLogger(CommandService.class);

    @Autowired
    private ThreadPoolTaskExecutor commandExecutor;

    public void submit(CommandDto cmd) {
        Runnable task = () -> log.info("Executing command: {} by {} at {}", cmd.getDescription(), cmd.getAuthor(), cmd.getTime());
        try {
            if (cmd.getPriority() == CommandPriority.CRITICAL) {
                task.run();
            } else {
                commandExecutor.execute(task);
            }
        } catch (java.util.concurrent.RejectedExecutionException ex) {
            throw new CommandQueueFullException("Command queue is full");
        }
    }
}
