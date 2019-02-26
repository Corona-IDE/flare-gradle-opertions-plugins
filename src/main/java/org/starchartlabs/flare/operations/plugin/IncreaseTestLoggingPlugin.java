/*
 * Copyright (c) Feb 25, 2019 StarChart Labs Authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    romeara - initial API and implementation and/or initial documentation
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
        // java: Provides test configuration
        project.getPluginManager().apply("java");

        TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);

        testTasks.forEach(it -> {
            it.getTestLogging().setExceptionFormat("full");
            it.getTestLogging().getQuiet().events("failed", "skipped");
            it.getTestLogging().getInfo().events("failed", "skipped", "passed", "standard_out", "standard_error");
            it.getTestLogging().getDebug().events("failed", "skipped", "passed", "standard_out", "standard_error",
                    "started");
        });
    }
}
