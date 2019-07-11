# org.starchartlabs.flare.dependency-insight

The `dependency-insight` plug-in is a convention plug-in which is intended to apply standard tasks for task types provided by the Gradle APIs related to dependency reporting. It is intended for use on multi-module projects - for single-module projects, Gradle provides the similar `dependencies` and `dependenciesInsight` tasks

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

apply plugin: 'org.starchartlabs.flare.dependency-insight'
```

## Use

Three tasks are provided by the plug-in:

### dependencyReport

Applies Gradle's [DependencyReportTask](https://docs.gradle.org/3.2.1/dsl/org.gradle.api.tasks.diagnostics.DependencyReportTask.html), allowing output of the full dependency tree of the project(s)

### dependencyInsightReport

Applies Gradle's [DependencyInsightReportTask](https://docs.gradle.org/3.2.1/dsl/org.gradle.api.tasks.diagnostics.DependencyInsightReportTask.html), which allows determination of where a dependency is introduced. Takes two arguments

* `--configuration`
    * The Gradle configuration to search for instances of the given dependency in. (Ex: `runtime`)
* `--dependency`
    * All or part of the dependency name to find (Ex: `org.testng`, `testng`)
    
### dependencyProjectReport

Applies Gradle's [DependencyInsightReportTask](https://docs.gradle.org/3.2.1/dsl/org.gradle.api.tasks.diagnostics.DependencyInsightReportTask.html), with a matching specification configured to match any projects in a multi-module environment

* `--configuration`
    * The Gradle configuration to search for instances of the given dependency in. (Ex: `runtime`)
    
## Replaced Boilerplate

The plug-in has the effect of replacing the boilerplate:

```
task dependencyReport(type: DependencyReportTask) {}

task dependencyInsightReport(type: DependencyInsightReportTask) {}

task dependencyProjectReport(type: DependencyInsightReportTask) {
    spec = new ProjectDependencyResultSpec(project)
}
```
