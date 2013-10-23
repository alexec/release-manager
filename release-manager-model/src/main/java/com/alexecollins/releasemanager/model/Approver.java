package com.alexecollins.releasemanager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author alex.collins
 */
@Data
public class Approver {
    private String id;
    @Indexed(unique = true)
    private String name;
}
