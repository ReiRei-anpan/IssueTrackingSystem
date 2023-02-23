package com.example.IssueTrackingSystem.domain.issue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IssueEntity {
    @Id
    private long id;
    private String summary;
    private String description;

}
