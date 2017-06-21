/*
 * Copyright (C) 2017 The StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask

/**
 * Plug-in which applies the Jacoco coverage plug-in, if not present, and a custom task which merges coverage results from sub-projects into a single XML report
 *
 * @author romeara
 * @since 0.1.0
 */
public class MergeCoverageReportsPlugin implements Plugin<Project> {

    private static final String TASK_NAME = 'mergeCoverageReports'

    @Override
    public void apply(Project project) {
        //Base: Provides build task, Java: Provides sourceSets (and sourceSets.main configuration), Jacoco: Provides coverage instrumentation
        project.apply plugin: 'base'
        project.apply plugin: 'jacoco'

        JacocoReport mergeTask = project.getTasks().create(TASK_NAME, MergeCoverageReportsTask.class);

        project.getTasks().getByName('check').dependsOn mergeTask

        //Merge coverage depends on output from all subproject tests
        mergeTask.dependsOn { project.subprojects*.test }
    }
}