package org.starchartlabs.flare.operations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask

//TODO romeara doc
public class MergeCoverageReportsPlugin implements Plugin<Project> {

    private static final String TASK_NAME = 'mergeCoverageReports'

    @Override
    public void apply(Project project) {
        project.apply plugin: 'base'
        project.apply plugin: 'jacoco'

        JacocoReport mergeTask = project.getTasks().create(TASK_NAME, MergeCoverageReportsTask.class);

        project.getTasks().getByName('build').dependsOn mergeTask
    }

}