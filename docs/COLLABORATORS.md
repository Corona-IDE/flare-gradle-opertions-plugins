# Collaborator information

## Release Process

* Update the version number to remove the "-SNAPSHOT" designation. All version numbers should be a fully-qualified semantic version of form `<major>.<minor>.<micro>`
* Change the header "Unreleased" in CHANGE_LOG.md to the target release number, and create a new "Unreleased" header above it
* Update the version number in the examples in ./doc/*.md files
* Run a full build via `./gradlew clean build` and manually run a build on the test project `flare-ops-gradle-test` to verify basic functionality in a real project setup
  * If there are any errors, stash the changes to the version number, changelog, and examples until the issue can be corrected and merged to master as a separate commit/issue
* Commit the version number and CHANGE_LOG updates
* Tag the git repository with the fully-qualified semantic version number, preceded by 'v' (ex: `v1.0.0`)
* Upload artifacts to bintray via `./gradlew bintrayUpload -PremoteDeploy -Dbintray_user=<username> -Dbintray_key=<API Key>`
* Verify all artifacts were correctly uploaded - check that POM.xml scopes and version numbers are correct
* Change version number to `<released version> + 1 micro` + `-SNAPSHOT`
* Commit to git
* Push changes and tag to GitHub
* Publish artifacts on BinTray
* [Synchronize BinTray artifacts to Maven central](https://bintray.com/docs/usermanual/uploads/uploads_syncingwiththirdpartyplatforms.html)
* Verify artifacts are present on Maven central
* Create a release on GitHub including all binary and source jars
* Change the `next-release` milestone to the released version number, move any unresolved tickets/pull-requests to a new `next-release` milestone, and close the version'd milestone
