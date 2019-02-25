iTunes Search App (MVVM)
======

This project demonstrates how to use [Android Architecture Components][1] (View Model, Data Binding, Live Data, lifecycle aware) along with [Realm][2] database and [Dagger 2][3] dependency injection to build a robust application (scalable, testable, less bug, easy to maintain, support offline mode and lanscape mode).

## App

I used the [official iTunes Search API][4] to develop a iTunes Search Application on Android with basic features like searching movies and viewing the details.

### Features
1) Default Search (seach term 'Star')
2) Custom Search
3) Track Details

### Architecture overview

Some common problems like tight coupling of code that even a small change in one portion of code results into changes/bugs in some other part of code.

The goal is to build things in such a distributed manner that keeps Android related stuff at one place and separates out all other entities that does not require android to run, then to further split apart the non-Android dependent piece as well thereby achieving code modularity, scalability, easy to maintain, test friendly and so on. 

Why MVVM? Google introduced [Android Architecture Components][1] which included ViewModel rather than Presenter and hence the proof that even Google is supporting MVVM.

There is a slight learning curve associated to MVVM but once you get into it, you will definitely appreciate how it separates the concerns between the app components.

### Persistence - Realm

[Realm][2] is a mobile database, a replacement of SQLite and ORMs. Realm works well in Android Architecture Components, see [Android Architecture Components and Realm][5]

### Testability

TODO: Unit testing and UI automated tests.


[1]: https://developer.android.com/topic/libraries/architecture/index.html
[2]: https://realm.io
[3]: https://github.com/google/dagger
[4]: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/
[5]: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel
[6]: https://academy.realm.io/posts/android-architecture-components-and-realm/
