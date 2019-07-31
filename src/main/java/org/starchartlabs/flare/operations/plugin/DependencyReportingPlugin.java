/*
 * Copyright (c) Feb 25, 2019 StarChart Labs Authors.
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
import org.gradle.api.tasks.diagnostics.DependencyInsightReportTask;
import org.gradle.api.tasks.diagnostics.DependencyReportTask;
import org.starchartlabs.flare.operations.dsl.ProjectDependencyResultSpec;

/**
 * Plug-in which applies tasks which allow inspection of dependency information
 *
 * <p>
 * Replacement for legacy dependency-insight plug-in as of 2.0.0 due to task name conflicts with Gradle project-report
 * plug-in
 *
 * @author romeara
 * @since 2.0.0
 */
public class DependencyReportingPlugin implements Plugin<Project> {

    private static final String LIST_TASK_NAME = "dependencyFullReport";

    private static final String INSIGHT_TASK_NAME = "dependencyDetailReport";

    private static final String PROJECT_TASK_NAME = "dependencyProjectDetailReport";

    @Override
    public void apply(Project project) {
        project.getLogger().warn(
                "The org.starchartlabs.flare.dependency-reporting plug-in from flare-operations-plugins is deprecated. See migration guide at https://github.com/StarChart-Labs/flare-plugins/blob/master/docs/FLARE_OPERATIONS_MIGRATION.md");

        // Task which will show what the dependency set of the project is in a tree form
        Task listTask = project.getTasks().create(LIST_TASK_NAME, DependencyReportTask.class);
        listTask.setGroup("Help");
        listTask.setDescription("Show the dependency set of the project in a tree form");

        // Task which will show what is introducing a particular dependency
        Task insightTask = project.getTasks().create(INSIGHT_TASK_NAME, DependencyInsightReportTask.class);
        insightTask.setGroup("Help");
        insightTask.setDescription("Shows occurances of a particular dependency");

        // Task which will show results filtered to project dependencies
        DependencyInsightReportTask projectTask = project.getTasks().create(PROJECT_TASK_NAME,
                DependencyInsightReportTask.class);
        projectTask.setDependencySpec(new ProjectDependencyResultSpec(project));
        projectTask.setGroup("Help");
        projectTask.setDescription("Shows dependencies on other projects in a multi-module environment");
    }

}
