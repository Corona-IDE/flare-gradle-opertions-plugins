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
 * @author romeara
 * @since 0.1.0
 */
public class DependencyInsightPlugin implements Plugin<Project> {

    private static final String LIST_TASK_NAME = "dependencyReport";

    private static final String INSIGHT_TASK_NAME = "dependencyInsightReport";

    private static final String PROJECT_TASK_NAME = "dependencyProjectReport";

    @Override
    public void apply(Project project) {
        // Task which will show what the dependency set of the project is in a tree form
        Task listTask = project.getTasks().create(LIST_TASK_NAME, DependencyReportTask.class);
        listTask.setDescription("Show the dependency set of the project in a tree form");

        // Task which will show what is introducing a particular dependency
        Task insightTask = project.getTasks().create(INSIGHT_TASK_NAME, DependencyInsightReportTask.class);
        insightTask.setDescription("Shows occurances of a particular dependency");

        // Task which will show results filtered to project dependencies
        DependencyInsightReportTask projectTask = project.getTasks().create(PROJECT_TASK_NAME,
                DependencyInsightReportTask.class);
        projectTask.setDependencySpec(new ProjectDependencyResultSpec(project));
        projectTask.setDescription("Shows dependencies on other projects in a multi-module environment");
    }

}
