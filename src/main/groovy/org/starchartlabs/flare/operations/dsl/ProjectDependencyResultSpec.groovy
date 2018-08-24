/*
 * Copyright (c) Aug 23, 2018 StarChart Labs Authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    romeara - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.flare.operations.dsl;

import org.gradle.api.Project;
import org.gradle.api.artifacts.ModuleVersionIdentifier;
import org.gradle.api.artifacts.component.ComponentSelector;
import org.gradle.api.artifacts.component.ModuleComponentSelector;
import org.gradle.api.artifacts.result.DependencyResult;
import org.gradle.api.artifacts.result.ResolvedDependencyResult;
import org.gradle.api.specs.Spec;

/**
 * Dependency matching specification which is designed to only match against other projects/modules in the same multi-module system as the provided project
 *
 * <p>
 * Heavily based on default Gradle <a href="https://github.com/gradle/gradle/blob/master/subprojects/diagnostics/src/main/java/org/gradle/api/tasks/diagnostics/internal/dsl/DependencyResultSpec.java">"DependencyResultSpec"</a>
 *
 * @author romeara
 * @since 0.2.0
 */
public class ProjectDependencyResultSpec implements Spec<DependencyResult> {

    private final Set<String> projectIdentifiers

    private final Project project

    public ProjectDependencyResultSpec(Project project) {
        this.project = project
        projectIdentifiers = new HashSet<>();

        project.rootProject.allprojects{p ->
            projectIdentifiers.add(p.path)

            project.getLogger().debug("Adding path ${p.path} to search configuration of project ${project.path}")
        }
    }

    @Override
    public boolean isSatisfiedBy(DependencyResult candidate) {
        if (candidate instanceof ResolvedDependencyResult) {
            return matchesRequested(candidate) || matchesSelected((ResolvedDependencyResult) candidate);
        } else {
            return matchesRequested(candidate);
        }
    }

    private boolean matchesRequested(DependencyResult candidate) {
        ComponentSelector requested = candidate.getRequested();

        if (requested instanceof ModuleComponentSelector) {
            ModuleComponentSelector requestedModule = (ModuleComponentSelector) requested
            String requestedCandidate = "${requestedModule.group}:${requestedModule.module}:${requestedModule.version}"

            project.getLogger().debug("Comparing DependencyResult ${requestedCandidate} to known projects")

            return projectIdentifiers.any{ p -> requestedCandidate.contains(p) }
        }

        return false;
    }

    private boolean matchesSelected(ResolvedDependencyResult candidate) {
        ModuleVersionIdentifier selected = candidate.getSelected().getModuleVersion();
        String selectedCandidate = "${selected.group}:${selected.name}:${selected.version}"

        project.getLogger().debug("Comparing ResolvedDependencyResult ${selectedCandidate}  to known projects")

        return projectIdentifiers.any{ p -> selectedCandidate.contains(p) }
    }
}
