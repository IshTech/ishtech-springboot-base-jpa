<!-- Repo-specific instructions. The shared IshTech rules live in .claude/rules/ and are identical across repos; don't put repo-specific content there. -->
# ishtech-springboot-base-jpa

The owner's standing instructions are in `.claude/rules/` (`owner-workflow.md`, `git-and-branches.md`, `versions-and-releases.md`, `build-and-test.md`, `build-tooling.md`, `documentation.md`, `repositories.md`, `cicd-dependency-check.md`). They apply to every task in this repo. This file adds only what is specific to this repo.

## About this repo
- It's a library with no runnable application (the Spring Boot plugin's repackage is skipped in `pom.xml`), so it has only test Level 1 of its own.
- Upstream library: `ishtech-i18n-java` (`rules/repositories.md`). The version is declared in `pom.xml`.
- Dependent tests apply (`rules/build-and-test.md`, section "Dependent tests"): they confirm that a change here doesn't break the projects that use `ishtech-springboot-base-jpa` and has the intended effect in them.
  - Dependent library: `ishtech-springboot-jwtauth` (`rules/repositories.md`).
  - Default dependent: [ishtech-springboot-jwtauth](https://github.com/ishtech/ishtech-springboot-jwtauth).
  - `ishtech-springboot-base-jpa` is public on Maven Central (`README.md`, section "Publish to Maven Central"), so it may have other dependents that nobody can list.

## Read the doc before doing the thing
The docs are the source of truth. Don't guess commands: open the matching file and section first, and follow its links.

| Before you... | Read |
|---|---|
| work out what the library does and how consumers add it | `README.md`, the introduction and section "Usage" |
| run test Level 1 (build with tests) | `README.md`, section "Build", subsection "Local Maven Build" |
| check the default JDK version or the other supported JDK versions | `README.md`, section "Tech stack"; which library version belongs to which JDK version, `JDK-VERSIONS.md`; for the `dev-jdkNN` branches and their releases, `rules/versions-and-releases.md`, section "JDK variants" |
| publish (only when the owner asks) | `README.md`, section "Publish to Maven Central" |
| change the version or anything release-related, or check what CI enforces | `README.md`, section "CI-CD", then `.github/workflows/cicd.yml` |
| report or fix a bug | `KNOWN-ISSUES.md` (format: `rules/documentation.md`) |

If a doc is missing, wrong or unclear, fix the doc (see `rules/documentation.md`) instead of working around it.
