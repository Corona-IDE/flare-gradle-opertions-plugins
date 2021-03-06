/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.starchartlabs.flare.operations.model.DependencyVersions;

/**
 * Configuration plug-in that adds structures for defining dependency constraints
 *
 * @author romeara
 * @since 3.0.0
 */
public class DependencyVersionsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getLogger().warn(
                "The org.starchartlabs.flare.dependency-versions plug-in from flare-operations-plugins is deprecated. See migration guide at https://github.com/StarChart-Labs/flare-plugins/blob/master/docs/FLARE_OPERATIONS_MIGRATION.md");

        project.getExtensions().add("dependencyVersions", new DependencyVersions(project));
    }

}
