# Character Finder - Android Development with Kotlin

```
When the button clicked, three request run simultaneously in three different methods. Each method is reponsible
to pass the string content of a html response success of blog content api to three different string util methods
as findTenthCharacter, findEveryTenthCharacter and findOccurrenceOfEachWord. These methods performe on IO thread
and the result of this methods will be updated on Main thread and attach to ui by dataBinding
```

## Technologies
- Impl unit test for blog content service and correctness of each character finder methods
- Kotlin Coroutines
- Kotlin Flow
- Lifecycle viewmodel and livedata
- Retrofit
- MVVM design pattern with repository
- databinding
- Dependency Injection - Dagger-Hilt