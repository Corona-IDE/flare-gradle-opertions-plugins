# flare-operations-plugins

[![Travis CI](https://img.shields.io/travis/StarChart-Labs/flare-operations-plugins.svg?branch=master)](https://travis-ci.org/StarChart-Labs/flare-operations-plugins) [![Code Coverage](https://img.shields.io/codecov/c/github/StarChart-Labs/flare-operations-plugins.svg)](https://codecov.io/github/StarChart-Labs/flare-operations-plugins) [![Black Duck Security Risk](https://copilot.blackducksoftware.com/github/groups/StarChart-Labs/locations/flare-operations-plugins/public/results/branches/master/badge-risk.svg)](https://copilot.blackducksoftware.com/github/groups/StarChart-Labs/locations/flare-operations-plugins/public/results/branches/master) [![Maven Central](https://img.shields.io/maven-central/v/org.starchartlabs.flare/flare-operations-plugins.svg)](https://mvnrepository.com/artifact/org.starchartlabs.flare/flare-operations-plugins)

* [Legal](#legal)
* [Contributing](#contributing)
* [Plugins](#plugins)
    * [org.starchartlabs.flare.merge-coverage-reports](#org.starchartlabs.flare.merge-coverage-reports)
    * [org.starchartlabs.flare.increase-test-logging](#org.starchartlabs.flare.increase-test-logging)
    * [org.starchartlabs.flare.dependency-insight](#org.starchartlabs.flare.dependency-insight)

Contains plug-ins for the Gradle build system which simplify configuration or reduce boilerplate for commonly-used conventions

## Legal

Lockdown is distributed under the [MIT License](https://opensource.org/licenses/MIT). There are no requirements for using it in your own project (a line in a NOTICES file is appreciated but not necessary for use)

The requirement for a copy of the license being included in distributions is fulfilled by a copy of the [LICENSE](./LICENSE) file being included in constructed JAR archives

## Contributing

Information for how to contribute to the Flare Operations Plugins can be found in [the contribution guidelines](CONTRIBUTING.md)

## Plug-ins

### org.starchartlabs.flare.merge-coverage-reports

Introduces steps in the standard build process to merge Jacoco code coverage reports from multiple sub-projects into a single report. This is often necessary for submission to external code coverage reporting services.

See the [usage documentation](./doc/merge-coverage-reports.md) for information and requirements for applying the plug-in

### org.starchartlabs.flare.increase-test-logging

Introduces standard conventions for increased logging during test execution

See the [usage documentation](./doc/increase-test-logging.md) for information and requirements for applying the plug-in

### org.starchartlabs.flare.dependency-insight

Introduces standard tasks for viewing dependency information

See the [usage documentation](./doc/dependency-insight.md) for information and requirements for applying the plug-in

## Collaborators

Information for collaborators, including the release process, can be found in the [collaborator documention](./COLLABORATORS.md)
