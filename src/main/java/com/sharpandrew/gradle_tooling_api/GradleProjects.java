package com.sharpandrew.gradle_tooling_api;

import java.nio.file.Path;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class GradleProjects {
    public static GradleProject getGradleProject(Path pathToGradleProject) {
        ProjectConnection projectConnection = GradleConnector.newConnector()
                .forProjectDirectory(pathToGradleProject.toFile())
                .connect();
        try {
            return projectConnection.model(GradleProject.class).get();
        } finally {
            projectConnection.close();
        }
    }
}
