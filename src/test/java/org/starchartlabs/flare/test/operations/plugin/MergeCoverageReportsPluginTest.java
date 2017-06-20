/*
 * Copyright (c) Jun 19, 2017 Corona IDE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    "romeara" - initial API and implementation and/or initial documentation
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
        Assert.assertTrue(project.getPluginManager().hasPlugin("java"));
        Assert.assertTrue(project.getPluginManager().hasPlugin("jacoco"));
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
