/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.api.tasks.testing.Test;

/**
 * Plug-in which applies a convention of increased logging during testing
 *
 * @author romeara
 * @since 0.1.0
 */
public class IncreaseTestLoggingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPluginManager().withPlugin("java", plugin -> {
            TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);

            testTasks.forEach(it -> {
                it.getTestLogging().setExceptionFormat("full");
                it.getTestLogging().getQuiet().events("failed", "skipped");
                it.getTestLogging().getInfo().events("failed", "skipped", "passed", "standard_out", "standard_error");
                it.getTestLogging().getDebug().events("failed", "skipped", "passed", "standard_out", "standard_error",
                        "started");
            });
        });

        project.getPluginManager().withPlugin("java-library", plugin -> {
            TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);

            testTasks.forEach(it -> {
                it.getTestLogging().setExceptionFormat("full");
                it.getTestLogging().getQuiet().events("failed", "skipped");
                it.getTestLogging().getInfo().events("failed", "skipped", "passed", "standard_out", "standard_error");
                it.getTestLogging().getDebug().events("failed", "skipped", "passed", "standard_out", "standard_error",
                        "started");
            });
        });
    }
}
