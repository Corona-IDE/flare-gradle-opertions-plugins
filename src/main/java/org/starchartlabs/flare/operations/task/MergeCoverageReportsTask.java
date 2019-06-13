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

        executionData(
                getProject().fileTree(getProject().getRootDir().getAbsolutePath()).include("**/build/jacoco/*.exec"));

        // Sub-project, get the main source set. Must be done after evaluation to detect java plug-in
        getProject().getSubprojects().forEach(it -> {
            it.getPluginManager().apply("java");

            JavaPluginConvention javaPlugin = it.getConvention().getPlugin(JavaPluginConvention.class);
            SourceSetContainer sourceSets = javaPlugin.getSourceSets();
            SourceSet mainSourceSet = sourceSets.findByName("main");

            sourceSets(mainSourceSet);
        });

        getReports().getXml().setEnabled(true);
        getReports().getXml().setDestination(
                getProject().file(getProject().getBuildDir().toString() + "/reports/jacoco/report.xml"));
        getReports().getHtml().setEnabled(false);
        getReports().getCsv().setEnabled(false);
    }

}
