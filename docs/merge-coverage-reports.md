# org.starchartlabs.flare.merge-coverage-reports

The `merge-coverage-reports` plug-in is a convention plug-in which is intended to apply a custom task to the root project of a multi-module java build.

It introduces an integrated build step in the standard build process to merge Jacoco code coverage reports from multiple sub-projects into a single report.

It is assumed that all sub-projects are java projects, or a language that uses and extension of the Gradle Java plug-in.

## Application

Apply the plug-in via the buildscript classpath:

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath group: 'org.starchartlabs.flare', name: 'flare-operations-plugins', version: '2.0.0'
    }
}

apply plugin: 'org.starchartlabs.flare.merge-coverage-reports'
```

## Use

Applying the plug-in automatically integrates the merge step into the build process by creating a `mergeCoverageReports` task. This task depends on the `test` task of all sub-projects, and is in turn depended on by the root project's `check` task

## Input and Output Reports

By default, the mergeCoverageReports task takes reports from each sub-project in `/build/jacoco/*exec`, converting it into a report output in the root project at `${project.buildDir}/reports/jacoco/report.xml`
