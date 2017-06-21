/*
 * Copyright (C) 2017 The StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package org.starchartlabs.flare.test.operations.plugin;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.internal.impldep.org.testng.Assert;
import org.gradle.testfixtures.ProjectBuilder;
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask;
import org.testng.annotations.Test;

public class MergeCoverageReportsPluginTest {

    private static final String PLUGIN_ID = "org.starchartlabs.flare.merge-coverage-reports";

    @Test
    public void baseAndJacocoPluginsApplied() throws Exception {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(PLUGIN_ID);

        Assert.assertTrue(project.getPluginManager().hasPlugin("base"));
        Assert.assertTrue(project.getPluginManager().hasPlugin("jacoco"));
    }

    @Test
    public void javaPluginAppliedToSubProject() throws Exception {
        Project project = ProjectBuilder.builder().build();
        Project subProject = ProjectBuilder.builder()
                .withParent(project)
                .build();

        project.getPluginManager().apply(PLUGIN_ID);

        Assert.assertTrue(subProject.getPluginManager().hasPlugin("java"));
    }

    @Test
    public void taskAddedAndIntegratedWithBuild() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(PLUGIN_ID);

        Task task = project.getTasks().getByName("mergeCoverageReports");
        Assert.assertTrue(task instanceof MergeCoverageReportsTask);

        Task buildTask = project.getTasks().getByName("build");

        Assert.assertTrue(buildTask.getDependsOn().contains(task));
    }

}
