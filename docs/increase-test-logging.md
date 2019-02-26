# org.starchartlabs.flare.increase-test-logging

The `increase-test-logging` plug-in is a convention plug-in which is intended to apply a standard behavior of increasing the test log output levels during Gradle's `test` phase.

It is assumed that all projects are java projects, or a language that uses and extension of the Gradle Java plug-in.

## Application

Apply the plug-in via the buildscript classpath:

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath group: 'org.starchartlabs.flare', name: 'flare-operations-plugins', version: '0.2.0'
    }
}

apply plugin: 'org.starchartlabs.flare.increase-test-logging'
```

## Replaced Boilerplate

The plug-in has the effect of replacing the boilerplate:

```
test {
    testLogging {
        exceptionFormat 'full'

        quiet {
          events 'failed', 'skipped'
        }

        info {
          events 'failed', 'skipped', 'passed', 'standard_out', 'standard_error'
        }

        debug {
          events 'failed', 'skipped', 'passed', 'standard_out', 'standard_error', 'started'
        }
    }
}
```
