package com.alexecollins.releasemanager.model;

import lombok.Data;

/**
 * @author alex.collins
 */
@Data
public class ArtifactRepository {
    public enum Type {
        MAVEN_2
    }
    private String id;
    private String name;
    private Type type;
    private String path;
}
