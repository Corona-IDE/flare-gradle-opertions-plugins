/*
 * Copyright (C) 2017 The StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package org.starchartlabs.flare.test.operations.plugin;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.diagnostics.DependencyInsightReportTask;
import org.gradle.api.tasks.diagnostics.DependencyReportTask;
import org.gradle.internal.impldep.org.testng.Assert;
import org.gradle.testfixtures.ProjectBuilder;
import org.starchartlabs.flare.operations.dsl.ProjectDependencyResultSpec;
import org.testng.annotations.Test;

public class DependencyReportingPluginTest {

    private static final String PLUGIN_ID = "org.starchartlabs.flare.dependency-reporting";

    @Test
    public void tasksAdded() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(PLUGIN_ID);

        Task dependencyReport = project.getTasks().getByName("dependencyFullReport");
        Assert.assertTrue(dependencyReport instanceof DependencyReportTask);

        Task dependencyInsightReport = project.getTasks().getByName("dependencyDetailReport");
        Assert.assertTrue(dependencyInsightReport instanceof DependencyInsightReportTask);

        Task dependencyProjectReport = project.getTasks().getByName("dependencyProjectDetailReport");
        Assert.assertTrue(dependencyProjectReport instanceof DependencyInsightReportTask);
        Assert.assertTrue(((DependencyInsightReportTask) dependencyProjectReport)
                .getDependencySpec() instanceof ProjectDependencyResultSpec);
    }

}
