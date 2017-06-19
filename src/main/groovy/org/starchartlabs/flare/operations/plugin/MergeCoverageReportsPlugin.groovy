package org.starchartlabs.flare.operations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask

//TODO romeara doc, test
public class MergeCoverageReportsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.apply plugin: 'base'
        project.apply plugin: 'jacoco'

        JacocoReport mergeTask = project.getTasks().create('mergeCoverageReport', MergeCoverageReportsTask.class);

        project.getTasks().getByName('build').dependsOn mergeTask
    }

}