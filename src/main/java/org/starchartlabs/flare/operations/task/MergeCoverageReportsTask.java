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
package org.starchartlabs.flare.operations.task;

import java.io.File;

import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.testing.jacoco.tasks.JacocoReport;

/**
 * Task configured to find *.exec Jacoco coverage reports in sub-projects and merge them into a single *.xml format
 * coverage report
 *
 * @author romeara
 * @since 0.1.0
 */
public class MergeCoverageReportsTask extends JacocoReport {

    public MergeCoverageReportsTask() {
        super();

        setGroup("Verification");
        setDescription("Merges Jacoco coverage reports from multiple modules into a single report");

        executionData(
                getProject().fileTree(getProject().getRootDir().getAbsolutePath()).include("**/build/jacoco/*.exec"));

        // Sub-project, get the main source set. Must be done after evaluation to detect java plug-in
        getProject().getSubprojects().forEach(subproject -> {
            subproject.getPluginManager().withPlugin("java", plugin -> {
                JavaPluginConvention javaPlugin = subproject.getConvention().getPlugin(JavaPluginConvention.class);
                SourceSetContainer sourceSets = javaPlugin.getSourceSets();
                SourceSet mainSourceSet = sourceSets.findByName("main");

                sourceSets(mainSourceSet);
            });

            subproject.getPluginManager().withPlugin("java-library", plugin -> {
                JavaPluginConvention javaPlugin = subproject.getConvention().getPlugin(JavaPluginConvention.class);
                SourceSetContainer sourceSets = javaPlugin.getSourceSets();
                SourceSet mainSourceSet = sourceSets.findByName("main");

                sourceSets(mainSourceSet);
            });
        });

        // GH-20 This doesn't seem to take effect, so the plug-in repeats it
        configureReports();
    }

    // GH-20 Exposed to prevent duplicate definitions. Run by the plug-in (again) to workaround GH-20. Setting
    // description in the constructor works, setting reports values doesn't
    public void configureReports() {
        reports(reports -> {
            File destination = getProject().file(getProject().getBuildDir().toString() + "/reports/jacoco/report.xml");

            reports.getXml().setEnabled(true);
            reports.getXml().setDestination(destination);
            reports.getHtml().setEnabled(false);
            reports.getCsv().setEnabled(false);
        });
    }

}
