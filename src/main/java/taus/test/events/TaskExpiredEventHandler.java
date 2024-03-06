package taus.test.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskExpiredEventHandler {

    @EventListener
    public void handleTaskExpiredEvent(TaskExpiredEvent event) {
        Long taskId = event.getTaskId();
        System.out.println("Task expired: " + taskId);
    }
}