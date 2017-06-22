/*
 * Copyright (c) Jun 21, 2017 Corona IDE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    "romeara" - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.flare.test.operations.plugin;

import org.gradle.api.Project;
import org.gradle.api.tasks.testing.logging.TestExceptionFormat;
import org.gradle.api.tasks.testing.logging.TestLogEvent;
import org.gradle.api.tasks.testing.logging.TestLogging;
import org.gradle.api.tasks.testing.logging.TestLoggingContainer;
import org.gradle.internal.impldep.org.testng.Assert;
import org.gradle.testfixtures.ProjectBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IncreaseTestLoggingPluginTest {

    private static final String PLUGIN_ID = "org.starchartlabs.flare.increase-test-logging";

    private Project project;

    @BeforeClass
    public void setupProject() {
        project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(PLUGIN_ID);
    }

    @Test
    public void javaPluginApplied() throws Exception {
        Assert.assertTrue(project.getPluginManager().hasPlugin("java"));
    }

    @Test
    public void expectionFormatApplied() throws Exception {
        org.gradle.api.tasks.testing.Test testTask = (org.gradle.api.tasks.testing.Test) project.getTasks()
                .getByName("test");

        TestLoggingContainer logging = testTask.getTestLogging();

        Assert.assertEquals(logging.getExceptionFormat(), TestExceptionFormat.FULL);
    }

    @Test
    public void quietLoggingApplied() throws Exception {
        org.gradle.api.tasks.testing.Test testTask = (org.gradle.api.tasks.testing.Test) project.getTasks()
                .getByName("test");

        TestLoggingContainer loggingContainer = testTask.getTestLogging();
        TestLogging logging = loggingContainer.getQuiet();

        Assert.assertEquals(logging.getEvents().size(), 2);
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.FAILED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.SKIPPED));
    }

    @Test
    public void infoLoggingApplied() throws Exception {
        org.gradle.api.tasks.testing.Test testTask = (org.gradle.api.tasks.testing.Test) project.getTasks()
                .getByName("test");

        TestLoggingContainer loggingContainer = testTask.getTestLogging();
        TestLogging logging = loggingContainer.getInfo();

        Assert.assertEquals(logging.getEvents().size(), 5);
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.FAILED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.SKIPPED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.PASSED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.STANDARD_OUT));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.STANDARD_ERROR));
    }

    @Test
    public void debugLoggingApplied() throws Exception {
        org.gradle.api.tasks.testing.Test testTask = (org.gradle.api.tasks.testing.Test) project.getTasks()
                .getByName("test");

        TestLoggingContainer loggingContainer = testTask.getTestLogging();
        TestLogging logging = loggingContainer.getDebug();

        Assert.assertEquals(logging.getEvents().size(), 6);
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.FAILED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.SKIPPED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.PASSED));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.STANDARD_OUT));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.STANDARD_ERROR));
        Assert.assertTrue(logging.getEvents().contains(TestLogEvent.STARTED));
    }

}
