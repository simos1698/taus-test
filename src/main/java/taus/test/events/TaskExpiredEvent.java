package taus.test.events;

import org.springframework.context.ApplicationEvent;

public class TaskExpiredEvent extends ApplicationEvent {

    private Long taskId;

    public TaskExpiredEvent(Object source, Long taskId) {
        super(source);
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}