package taus.test.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date dueDate;

}