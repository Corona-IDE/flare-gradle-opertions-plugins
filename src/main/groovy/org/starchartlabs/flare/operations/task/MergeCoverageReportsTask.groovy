/*
 * Copyright (C) 2017 The StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.task

import org.gradle.testing.jacoco.tasks.JacocoReport

/**
 * Task configured to find *.exec Jacoco coverage reports in sub-projects and merge them into a single *.xml format coverage report
 *
 * @author romeara
 * @since 0.1.0
 */
public class MergeCoverageReportsTask extends JacocoReport {

    public MergeCoverageReportsTask(){
        super()

        configure{
            executionData project.fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec")

            //Sub-project, get the main source set. Must be done after evaluation to detect java plug-in
            project.subprojects.each {
                it.apply plugin: 'java'
                sourceSets it.sourceSets.main
            }

            reports {
                xml.enabled true
                xml.destination "${project.buildDir}/reports/jacoco/report.xml"
                html.enabled false
                csv.enabled false
            }
        }
    }
}