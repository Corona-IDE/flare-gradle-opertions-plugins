package org.starchartlabs.flare.operations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

//TODO romeara doc, test
public class MergeCoverageReportsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.apply plugin: 'base'
        project.apply plugin: 'jacoco'

        JacocoReport mergeTask = project.getTasks().create('mergeCoverageReport', JacocoReport.class);

        project.getTasks().getByName('mergeCoverageReport').configure{
            executionData project.fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec")

            project.subprojects.each { sourceSets it.sourceSets.main }

            reports {
                xml.enabled true
                xml.destination "${project.buildDir}/reports/jacoco/report.xml"
                html.enabled false
                csv.enabled false
            }
        }

        project.getTasks().getByName('build').dependsOn mergeTask
    }

}