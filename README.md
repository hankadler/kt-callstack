# kotlin-util-callstack

Functions for runtime call stack inspection.

## Table of Contents
- [Setup](#setup)
- [Examples](#examples)
- [License](#license)

## Setup

Add to build.gradle.kts:

```kotlin
repositories {
    maven {
        setUrl("https://dl.bintray.com/hankadler/kotlin-util")
    }
}

dependencies {
    implementation("com.hankadler.util:callstack:0.1.0")
}
```

## Examples

Source:

```kotlin
// CallStackExample.kt

import com.hankadler.util.CallStack

class App {
    fun main() {
        println(CallStack)
        println(CallStack.getCallerOf(this::class.qualifiedName))
    }
}

fun main() {
    App().main()
}
```

Run:
```
[0]: com.hankadler.util.AppKt.main(App.kt)
[1]: com.hankadler.util.AppKt.main(App.kt:11)
[2]: com.hankadler.util.App.main(App.kt:5)
[3]: java.io.PrintStream.println(PrintStream.java:821)
[4]: java.lang.String.valueOf(String.java:2994)
[5]: com.hankadler.util.CallStack.toString(CallStack.kt:49)
[6]: com.hankadler.util.CallStack.getUnits(CallStack.kt:19)
[7]: java.lang.Thread.getStackTrace(Thread.java:1559)

com.hankadler.util.AppKt
```

## License
[MIT](LICENSE) © Hank Adler
