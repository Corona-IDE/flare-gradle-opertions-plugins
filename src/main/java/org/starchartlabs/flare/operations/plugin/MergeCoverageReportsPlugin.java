/*
 * Copyright (c) Feb 24, 2019 StarChart Labs Authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    romeara - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.flare.operations.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask;

/**
 * Plug-in which applies the Jacoco coverage plug-in, if not present, and a custom task which merges coverage results
 * from sub-projects into a single XML report
 *
 * @author romeara
 * @since 0.1.0
 */
public class MergeCoverageReportsPlugin implements Plugin<Project> {

    private static final String TASK_NAME = "mergeCoverageReports";

    @Override
    public void apply(Project project) {
        // Base: Provides build task, Java: Provides sourceSets (and sourceSets.main configuration), Jacoco: Provides
        // coverage instrumentation
        project.getPluginManager().apply("base");
        project.getPluginManager().apply("jacoco");

        MergeCoverageReportsTask mergeTask = project.getTasks().create(TASK_NAME, MergeCoverageReportsTask.class);
        // GH-20 For some reason, things like description stay after creation in constructor, but the reports object
        // seems to be reset
        mergeTask.configureReports();

        project.getTasks().getByName("check").dependsOn(mergeTask);

        // Merge coverage depends on output from all subproject tests
        project.getSubprojects().forEach(it -> {
            it.getPluginManager().withPlugin("java", plugin -> {
                Task test = it.getTasks().getByName("test");
                if (test != null) {
                    mergeTask.dependsOn(test);
                }
            });

            it.getPluginManager().withPlugin("java-library", plugin -> {
                Task test = it.getTasks().getByName("test");
                if (test != null) {
                    mergeTask.dependsOn(test);
                }
            });
        });
    }

}
