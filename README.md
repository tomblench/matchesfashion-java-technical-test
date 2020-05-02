# MatchesFashion Java Technical Test

The solution is provided as an IntelliJ IDEA project _and_ a Gradle project. Either can be used for building and running.

IntelliJ tasks are provided:
- "Run main class with Don Quixote first sentence". As described, using one of the provided sample input files.
- "org.blench.matchesfashion.test in MatchesFashionTest". Run all JUnit tests.

Examples below are focussed on running with gradlew at the command line.

# Tests

Run JUnit tests with `./gradlew test`. Test report is in the usual place (`build/reports/tests/test/`).

# Running

## Running with file as input

`./gradlew run -PinputFile=$file`

```
Thomass-MacBook-Pro:MatchesFashionTest tomblench$ ./gradlew run -PinputFile=./donquixote-first3paras.txt 

> Task :run
of, the, and

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date
```

## Running with stdin input

Type input at terminal and end with Ctrl-D or pipe input in from elsewhere.

`./gradlew --console plain run`

```
Thomass-MacBook-Pro:MatchesFashionTest tomblench$ ./gradlew --console plain run
> Task :clean
> Task :compileJava
> Task :processResources NO-SOURCE
> Task :classes
It was the best of times,
it was the worst of times,
it was the age of wisdom,
it was the age of foolishness,
it was the epoch of belief,
it was the epoch of incredulity,
it was the season of Light,
it was the season of Darkness,
it was the spring of hope,
it was the winter of despair,

^D
> Task :run
it, of, the

BUILD SUCCESSFUL in 4s
3 actionable tasks: 3 executed
```

## Running with pre-canned input

`./gradlew runWithDonQuixote`

```
Thomass-MacBook-Pro:MatchesFashionTest tomblench$ ./gradlew runWithDonQuixote

> Task :runWithDonQuixote
a, of, on

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date
```

## Running with debug output switched on

`./gradlew run -PinputFile=./donquixote-firstsentence.txt -DdumpTable=true`

```
Thomass-MacBook-Pro:MatchesFashionTest tomblench$ ./gradlew run -PinputFile=./donquixote-firstsentence.txt -DdumpTable=true

> Task :run
[6=[a], 5=[of], 4=[on], 2=[an, and, in, lance, the, to], 1=[away, beef, buckler, call, coursing, desire, extra, for, fridays, gentlemen, greyhound, hack, have, his, i, income, keep, la, lean, lentils, lived, long, made, mancha, mind, more, most, mutton, name, nights, no, not, old, olla, one, or, pigeon, quarters, rack, rather, salad, saturdays, scraps, since, so, sundays, than, that, there, those, three, village, which, with]]
a, of, on

BUILD SUCCESSFUL in 998ms
2 actionable tasks: 1 executed, 1 up-to-date
```
