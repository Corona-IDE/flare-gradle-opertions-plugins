/*
 * Copyright (c) Jun 19, 2017 Corona IDE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    "romeara" - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.flare.test.operations.plugin;

import java.io.File;

import org.gradle.api.Project;
import org.gradle.api.reporting.Report;
import org.gradle.api.reporting.SingleFileReport;
import org.gradle.testfixtures.ProjectBuilder;
import org.gradle.testing.jacoco.tasks.JacocoReport;
import org.starchartlabs.flare.operations.task.MergeCoverageReportsTask;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MergeCoverageReportsTaskTest {

    @Test
    public void configure() throws Exception {
        Project project = ProjectBuilder.builder().build();
        JacocoReport mergeTask = project.getTasks().create("mergeTest", MergeCoverageReportsTask.class);

        SingleFileReport xml = mergeTask.getReports().getXml();
        Assert.assertEquals(xml.isEnabled(), true);
        Assert.assertEquals(xml.getDestination(), new File(project.getBuildDir(), "reports/jacoco/report.xml"));

        Report html = mergeTask.getReports().getHtml();
        Assert.assertEquals(html.isEnabled(), false);

        Report csv = mergeTask.getReports().getCsv();
        Assert.assertEquals(csv.isEnabled(), false);
    }

}
