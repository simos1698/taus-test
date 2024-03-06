package taus.test.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
public record TaskDTO(
        Long id,
        String username,
        String title,
        String description,
        Date dueDate
) {}