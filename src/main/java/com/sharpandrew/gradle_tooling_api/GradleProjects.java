package com.sharpandrew.gradle_tooling_api;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class GradleProjects {
    public static void main(String[] args) {
        Path pathToGradleProject = Paths.get(args[0]);
        Path jvmTmpDir = Paths.get(args[1]);
        System.out.println(getGradleProject(pathToGradleProject, jvmTmpDir));
    }

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
