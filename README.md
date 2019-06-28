# flare-operations-plugins

[![Travis CI](https://img.shields.io/travis/com/StarChart-Labs/flare-operations-plugins.svg?branch=master)](https://travis-ci.com/StarChart-Labs/flare-operations-plugins) [![Code Coverage](https://img.shields.io/codecov/c/github/StarChart-Labs/flare-operations-plugins.svg)](https://codecov.io/github/StarChart-Labs/flare-operations-plugins) [![Black Duck Security Risk](https://copilot.blackducksoftware.com/github/repos/StarChart-Labs/flare-operations-plugins/branches/master/badge-risk.svg)](https://copilot.blackducksoftware.com/github/repos/StarChart-Labs/flare-operations-plugins/branches/master) [![Changelog validated by Chronicler](https://chronicler.starchartlabs.org/images/changelog-chronicler-success.png)](https://chronicler.starchartlabs.org/) [![Maven Central](https://img.shields.io/maven-central/v/org.starchartlabs.flare/flare-operations-plugins.svg)](https://mvnrepository.com/artifact/org.starchartlabs.flare/flare-operations-plugins) [![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

* [Legal](#legal)
* [Contributing](#contributing)
* [Plugins](#plugins)
    * [org.starchartlabs.flare.merge-coverage-reports](#org.starchartlabs.flare.merge-coverage-reports)
    * [org.starchartlabs.flare.increase-test-logging](#org.starchartlabs.flare.increase-test-logging)
    * [org.starchartlabs.flare.dependency-insight](#org.starchartlabs.flare.dependency-insight)

Contains plug-ins for the Gradle build system which simplify configuration or reduce boilerplate for commonly-used conventions

For information on migrating between major plug-in versions, see the [migration guide](./docs/MIGRATIONS.md)

## Legal

The Flare Operations Plug-ins are distributed under the [MIT License](https://opensource.org/licenses/MIT). There are no requirements for using it in your own project (a line in a NOTICES file is appreciated but not necessary for use)

The requirement for a copy of the license being included in distributions is fulfilled by a copy of the [LICENSE](./LICENSE) file being included in constructed JAR archives

## Contributing

Information for how to contribute to the Flare Operations Plugins can be found in [the contribution guidelines](./docs/CONTRIBUTING.md)

## Supported Gradle Versions

### Gradle 3.2.1 through 3.5.1 (Inclusive)

_Supported by plug-in versions 0.x and 1.x_

Plug-in versions 0.x and 1.x work with the Gradle 3 major revision without adjustment

### Gradle 4.x 

_Supported by plug-in version 1.x and 2.x_

#### Plug-in Version 1.x Required Workaround

Plug-in versions 1.x work with Gradle 4, with the exception of the merge coverage reports plug-in. This can be corrected with the workaround described in [GH-20](https://github.com/StarChart-Labs/flare-operations-plugins/issues/20) of applying the following the the root project after applying the plug-in:

```
mergeCoverageReports {
    reports {
        xml.enabled true
        html.enabled false
        csv.enabled false
        
        xml.destination = "${buildDir}/reports/jacoco/report.xml"
    }
}
```

### Gradle 5.x 

_Supported by plug-in version 2.x and 3.x_

## Migrating Gradle Versions

### Gradle 3.x to 4.x

- Upgrade to plug-in versions 1.x
- Apply workaround for [GH-20](https://github.com/StarChart-Labs/flare-operations-plugins/issues/20) if using the merge coverage reports plug-in

### Gradle 4.x to 5.x

- Upgrade to plug-in versions 2.x+

## Plug-ins

### org.starchartlabs.flare.merge-coverage-reports

Introduces steps in the standard build process to merge Jacoco code coverage reports from multiple sub-projects into a single report. This is often necessary for submission to external code coverage reporting services.

See the [usage documentation](./docs/merge-coverage-reports.md) for information and requirements for applying the plug-in

### org.starchartlabs.flare.increase-test-logging

Introduces standard conventions for increased logging during test execution

See the [usage documentation](./docs/increase-test-logging.md) for information and requirements for applying the plug-in

### org.starchartlabs.flare.dependency-reporting

Introduces standard tasks for viewing dependency information in multi-module projects

See the [usage documentation](./docs/dependency-reporting.md) for information and requirements for applying the plug-in

### org.starchartlabs.flare.dependency-insight

*This plug-in is DEPRECATED - use `org.starchartlabs.flare.dependency-insight` instead*

Introduces standard tasks for viewing dependency information in multi-module projects

See the [usage documentation](./docs/dependency-insight.md) for information and requirements for applying the plug-in

## Collaborators

Information for collaborators, including the release process, can be found in the [collaborator documention](./docs/COLLABORATORS.md)
