/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.plugin;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.starchartlabs.flare.operations.model.CredentialConfiguration;

/**
 * Configuration plug-in that adds structures for defining credentials which can be referenced throughout the build
 * system
 *
 * @author romeara
 * @since 3.0.0
 */
public class ManagedCredentialsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getLogger().warn(
                "The org.starchartlabs.flare.managed-credentials plug-in from flare-operations-plugins is deprecated. See migration guide at https://github.com/StarChart-Labs/flare-plugins/blob/master/docs/FLARE_OPERATIONS_MIGRATION.md");

        NamedDomainObjectContainer<CredentialConfiguration> credentials = project
                .container(CredentialConfiguration.class, name -> {
                    return new CredentialConfiguration(name);
                });

        project.getExtensions().add("credentials", credentials);
    }

}
