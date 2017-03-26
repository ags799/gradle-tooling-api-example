package com.sharpandrew.gradle_tooling_api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Paths;
import org.gradle.tooling.model.GradleProject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class GradleProjectsShould {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void return_gradle_project_for_gradle_project() throws Exception {
        temporaryFolder.newFile("build.gradle");
        GradleProject gradleProject = GradleProjects.getGradleProject(
                temporaryFolder.getRoot().toPath(), Paths.get(System.getProperty("java.io.tmpdir")));
        assertThat(gradleProject.getProjectDirectory().getCanonicalPath())
                .isEqualTo(temporaryFolder.getRoot().getCanonicalPath());
    }
}