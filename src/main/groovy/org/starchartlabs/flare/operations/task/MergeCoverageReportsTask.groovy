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

            //For each Java (or Java-extending) sub-project, get the main source set. Must be done after evaluation to detect java plug-in
            project.subprojects.each { sourceSets it.sourceSets.main }

            reports {
                xml.enabled true
                xml.destination "${project.buildDir}/reports/jacoco/report.xml"
                html.enabled false
                csv.enabled false
            }
        }
    }
}