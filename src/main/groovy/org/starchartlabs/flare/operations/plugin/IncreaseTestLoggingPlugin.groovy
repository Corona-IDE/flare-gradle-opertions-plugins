/*
 * Copyright (C) 2017 The StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plug-in which applies a convention of increased logging during testing
 *
 * @author romeara
 * @since 0.1.0
 */
public class IncreaseTestLoggingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        //Base: Provides test configuration
        project.apply plugin: 'java'

        project.test {
            testLogging {
                exceptionFormat 'full'

                quiet { events 'failed', 'skipped' }

                info { events 'failed', 'skipped', 'passed', 'standard_out', 'standard_error' }

                debug { events 'failed', 'skipped', 'passed', 'standard_out', 'standard_error', 'started' }
            }
        }
    }
}