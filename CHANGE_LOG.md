# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased]
### Changed
- (GH-27) Update minimum required Gradle version to 4.0
- (GH-20) Added workaround to force correct configuration of mergeCoverageReports task upon plug-in application
- (GH-28) Deprecated dependency-insight plug-in due to task name conflicts with Gradle project-report plug-in
- (GH-28) Added dependency-reporting plug-in to replace deprecated dependency-insight plug-in

## [1.1.0]
### Changed
- (GH-19) Corrected formatting errors detected by Checkstyle
- (GH-23) Switch to reactive configuration of values dependent on the java plug-in, to reduce coupling with that specific plug-in

## [1.0.0]
### Changed
- (GH-7) Switch from Groovy to Java implementation

## [0.2.0]
### Added
- (GH-10) Add "dependencyProjectReport" task to allow finding dependency instances specifically of projects in the same multi-module configuration

### Changed
- (GH-9) Switch to passing report location as a file to try and use newer APIs when available

## [0.1.0]
### Added
- Create a plug-in which adds standard behavior for creating a merged code coverage report
- Create a plug-in which adds standard conventions for test logging
- Create a plug-in which adds standard dependency information tasks
