package com.sharpandrew.gradle_tooling_api;

import java.nio.file.Path;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class GradleProjects {
    public static GradleProject getGradleProject(Path pathToGradleProject, Path jvmTmpDir) {
        ProjectConnection projectConnection = GradleConnector.newConnector()
                .forProjectDirectory(pathToGradleProject.toFile())
                .connect();
        try {
            String jvmTmpDirArgument = String.format("-Djava.io.tmpdir=%s", jvmTmpDir);
            return projectConnection.model(GradleProject.class).setJvmArguments(jvmTmpDirArgument).get();
        } finally {
            projectConnection.close();
        }
    }
}
