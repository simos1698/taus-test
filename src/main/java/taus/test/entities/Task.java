package taus.test.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import taus.test.dtos.TaskDTO;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "dueDate")
    private Date dueDate;

    public Task(TaskDTO taskDTO, User user){
        this.user = user;
        this.title = taskDTO.title();
        this.description = taskDTO.description();
        this.dueDate = taskDTO.dueDate();
    }

    public TaskDTO toDTO() {
        return TaskDTO.builder()
        .id(id)
        .username(user.getUsername())
        .title(title)
        .description(description)
        .dueDate(dueDate)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}