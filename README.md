# ishtech-springboot-base-jpa
A foundational Spring Boot JPA library providing a reusable boilerplate for entity, repository, service, specification, and mapper layers.
It encapsulates common CRUD patterns, filtering mechanisms, and mapping logic through well-structured base classes and interfaces.
This helps keep business modules lightweight, letting them focus only on domain-specific logic while inheriting standardized behavior and conventions.

## Tech stack

- JDK 25 (default)
- Other supported JDK versions:
  - JDK 21
  - JDK 17

Which library version to use for your JDK version: [JDK-VERSIONS.md](JDK-VERSIONS.md).

## Usage

- Note: in pom.xml / build.gradle put required version number

### Maven

```
<dependency>
	<groupId>fi.ishtech.springboot</groupId>
	<artifactId>ishtech-springboot-base-jpa</artifactId>
	<version>${ishtech-springboot-base-jpa.version}</version>
</dependency>

```

### Gradle

```
implementation("fi.ishtech.springboot:ishtech-springboot-base-jpa:${ishtechSpringbootBaseJpaVersion}")
```

## Build

This is a library; it **does not run** as a standalone application.

### Maven

#### Local Maven Build

- Build without tests

```
./mvnw clean install -DskipTests
```

- Build with Junit tests

```
./mvnw clean install
```

## Publish to Maven Central

- Deploy to Sonatype Central

  ```
  ./mvnw clean deploy -P gpg -P central-publishing
  ```

  - To wait longer for Maven Central to publish a release than the default of 1800 seconds, add `-DwaitMaxTime=<seconds>` with a value above 1800
  - `waitMaxTime` has no effect on SNAPSHOT versions

  ```
  ./mvnw clean deploy -P gpg -P central-publishing -DwaitMaxTime=3600
  ```

## CI-CD
### Workflow Steps
The workflow `.github/workflows/cicd.yml` runs as a single job:
- Validate Project Version
    - Validates the project version in `pom.xml` against the branch, tag or release, and decides whether the run deploys
    - Ensures:
        - main → release version `x.y.z`
        - Non-main branches → `-SNAPSHOT` version
        - Release tag or pushed tag matches the `pom.xml` version
- Maven Compile, Maven Test
    - Compile, then run the JUnit tests
- Import GPG key, Publish to Maven Central Sonatype
    - Only when the run deploys (see below)
    - ✅ Release to Maven Central
        - GitHub release with tag `vx.y.z`, on `main` only
        - Push of tag `vx.y.z-jdkNN`, on `dev-jdkNN` only, after the release tag `vx.y.z` is merged into it and the CI run for that release succeeded
    - ⚙️ SNAPSHOT to Sonatype Central (manual deploy)
        - Triggered via workflow_dispatch with manual_deploy = true, on any branch except `main`
        - Version must be `x.y.z-SNAPSHOT` on `dev`, `x.y.z-jdkNN-SNAPSHOT` on `dev-jdkNN`, and `x.y.z-<qualifier>-SNAPSHOT` on other branches (qualifier: letters, digits, `-` or `_`, not ending in `jdkNN`)
    - ❌ No Deploy
        - When manual_deploy = false (default)
        - On normal push
        - On main, and on manual runs from a tag
    - Wait time: Maven Central publishing waits at most 1800 seconds by default. The repository variable `CENTRAL_PUBLISHING_WAIT_MAX_TIME`, or the workflow_dispatch input `central_publishing_wait_max_time`, sets a longer wait (a whole number of seconds above 1800)

## Known Issues

See [KNOWN-ISSUES.md](KNOWN-ISSUES.md).
