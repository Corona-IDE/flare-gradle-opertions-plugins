/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.test.operations.plugin;

import java.io.File;
import java.net.URL;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.starchartlabs.flare.test.operations.IntegrationTestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = { IntegrationTestListener.class })
public class IncreaseTestLoggingIntegrationTest {

    private File testProjectDirectory;

    @BeforeClass
    public void setup() throws Exception {
        URL directory = this.getClass().getClassLoader()
                .getResource("org/starchartlabs/flare/test/increase/test/logging");

        testProjectDirectory = new File(directory.toURI());
    }

    @Test
    public void appliesWithoutError() throws Exception {
        BuildResult result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(testProjectDirectory)
                .withArguments("clean")
                .build();

        TaskOutcome outcome = result.task(":clean").getOutcome();
        Assert.assertTrue(TaskOutcome.SUCCESS.equals(outcome) || TaskOutcome.UP_TO_DATE.equals(outcome));
    }

}
