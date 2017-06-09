# flare-operations-plugins

Contains a variety of compile or test-related build enhancements

## Legal

Lockdown is distributed under the [MIT License](https://opensource.org/licenses/MIT). There are no requirements for using it in your own project (a line in a NOTICES file is appreciated but not necessary for use)

The requirement for a copy of the license being included in distributions is fulfilled by a copy of the [LICENSE](./LICENSE) file being included in constructed JAR archives

# Merge Coverage Reports

Gradle plug-in to merge jacoco code coverages reports for multi-module projects

There are many tools, such as CodeCov, which want to consume a single code coverage report. For projects using the Gradle sub-module pattern, this must be constructed via specialized tasks. The Flare Merged Coverage Reports plug-in reduces the boilerplate for this setup.
