# org.starchartlabs.flare.managed-credentials

The `managed-credentials` plug-in is a configuration plug-in which adds a standard DSL extension `credentials` to describe credentials which may be laoded and used for build operations

## Application

Apply the plug-in via the buildscript classpath:

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath group: 'org.starchartlabs.flare', name: 'flare-operations-plugins', version: '3.0.0'
    }
}

apply plugin: 'org.starchartlabs.flare.managed-credentials'
```

## Use

To specify the publication information, the DSL breaks down into 4 parts:

```
credentials {
    example {
        environment('ENV1', 'ENV2')
        systemProperties('example_user', 'example_key')
        defaultLogin('defaultuser', 'defaultpassword')
    }
}
```

Overall, this configures a set of credentials named `example` which attempt to load from environment variables, then tries system properties, and finally uses default credentials if both the environment variables and system properties were not present

### example

```
example {
  ...
}
```

Named credential configuration. Allows referencing the built credentials via `credentials.example.username` and `credentials.example.password` within the build file

### enviroment

```
environment('ENV1', 'ENV2')
```

Adds a load source for the `example` credentials - the username will be loaded from `ENV1`, and the password from `ENV2`

### systemProperties

```
systemProperties('example_user', 'example_key')
```

Adds a load source for the `example` credentials - the username will be loaded from `example_user`, and the password from `example_key`

### defaultLogin

```
defaultLogin('defaultuser', 'defaultpassword')
```

Adds a load source for the `example` credentials - the username will be `defaultuser`, and the password `defaultpassword`
