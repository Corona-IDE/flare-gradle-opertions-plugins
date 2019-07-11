/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyConstraintHandler;
import org.starchartlabs.alloy.core.Preconditions;
import org.starchartlabs.alloy.core.Strings;

/**
 * Represents configuration for loading dependency version constraints from simple files
 *
 * @author romeara
 * @since 3.0.0
 */
public class DependencyVersions {

    private final Project project;

    private Map<String, String> versionsByGroupArtifact;

    public DependencyVersions(Project project) {
        this.project = Objects.requireNonNull(project);

        versionsByGroupArtifact = new HashMap<>();
    }

    public DependencyVersions propertiesFile(File propertiesFile) {
        Objects.requireNonNull(propertiesFile);
        Preconditions.checkArgument(propertiesFile.exists(),
                Strings.format("Dependencies properties file %s does not exist", propertiesFile.getAbsolutePath()));

        try {
            // Can't load via Java Properties class, as it considers the ':' character the same way as '='
            Map<String, String> dependencies = Files.lines(propertiesFile.toPath())
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .filter(line -> !isCommentLine(line))
                    .map(this::parseVersions)
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

            versionsByGroupArtifact.putAll(dependencies);
        } catch (IOException e) {
            throw new GradleException(
                    Strings.format("Error loading properties file %s", propertiesFile.getAbsolutePath()));
        }

        project.getPluginManager().withPlugin("java-library", plugin -> {
            project.getConfigurations().all(configuration -> {
                apply(project.getDependencies().getConstraints(), configuration.getName());
            });
        });

        project.getPluginManager().withPlugin("java", plugin -> {
            project.getConfigurations().all(configuration -> {
                apply(project.getDependencies().getConstraints(), configuration.getName());
            });
        });

        return this;
    }

    private void apply(DependencyConstraintHandler constraintHandler, String configuration) {
        versionsByGroupArtifact.entrySet().stream()
        .map(entry -> entry.getKey() + ":" + entry.getValue())
        .forEach(dependency -> constraintHandler.add(configuration, dependency));

        versionsByGroupArtifact.entrySet().stream()
        .forEach(entry -> project.getLogger().info("Applied {} dependency constraint: {}:{}",
                configuration, entry.getKey(), entry.getValue()));
    }

    private boolean isCommentLine(String line) {
        Objects.requireNonNull(line);

        return line.startsWith("#");
    }

    private Entry<String, String> parseVersions(String line) {
        Objects.requireNonNull(line);

        // Handle lines with comments on the end
        String propertyLine = line.split("#")[0].trim();

        String[] parts = propertyLine.split("=");

        Preconditions.checkArgument(parts.length == 2,
                Strings.format("Invalid properties line format - expect format 'group:artifact=version' (%s)", line));

        return new AbstractMap.SimpleEntry<>(parts[0], parts[1]);
    }

}
