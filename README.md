# PatternFly Fritz2 Showcase

Showcase for [PatternFly Fritz2](https://github.com/patternfly-kotlin/patternfly-fritz2) a [Kotlin/JS](https://kotl.in/js) implementation of [PatternFly 4](https://www.patternfly.org/) based on [fritz2](https://www.fritz2.dev/). 

## Use

The showcase is available online at https://patternfly-kotlin.github.io/patternfly-fritz2-showcase/ 

## Build 

To build and run the showcase locally, you have to first build [Fritz2 MVP](https://github.com/hpehl/fritz2-mvp) which is used by this showcase and which has not yet been published to a repository.

```shell
git clone https://github.com/hpehl/fritz2-mvp.git
cd fritz2-mvp
./gradlew publishToMavenLocal

cd ..

git clone https://github.com/patternfly-kotlin/patternfly-fritz2-showcase.git
cd patternfly-fritz2-showcase
./gradlew run
```
