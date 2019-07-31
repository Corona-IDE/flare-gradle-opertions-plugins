# org.starchartlabs.flare.dependency-versions

The `dependency-versions` plug-in is a configuration plug-in which adds a standard DSL extension `dependencyVersions` to allow loading dependency version constraints from a properties file

## Application

Apply the plug-in via the buildscript classpath:

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath group: 'org.starchartlabs.flare', name: 'flare-operations-plugins', version: '3.0.1'
    }
}

apply plugin: 'org.starchartlabs.flare.dependency-versions'
```

## Use

To specify the publication information:

```
dependencyVersions {
    propertiesFile file('...')
}
```

The file referenced is expected to be a properties file with lines in the form of

`group:artifact=version`