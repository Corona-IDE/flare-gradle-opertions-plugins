package org.starchartlabs.flare.operations.task

import org.gradle.testing.jacoco.tasks.JacocoReport

//TODO romeara doc, test
public class MergeCoverageReportsTask extends JacocoReport {

	public MergeCoverageReportsTask(){
		super()
		
		configure{
            executionData project.fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec")

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