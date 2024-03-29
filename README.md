# Android Github - Top Trending Users

## Covered As part of this project
- Clean architecture with MVVM
- 100% Kotlin code (except 1 AutoValue model class)
- Android arch components - ViewModel, LiveData, etc
- Rx for async calls
- Dagger for dependency injection
- Retrofit for network calls
- Glide for loading images
- AutoValue and Gson for Data model parsing
- Mockito & JUnit framework for Testing

## App Structure
Each feature screen will be added as new *package* in project. eg: toptrendingdetail, toptrendinglist
Each feature *package* will follow - Clean architecture with MVVM.

#### View (Activity)
* can be found in *view* package
* handles input from user

#### ViewModel
* can be found in *viewmodel* package
* sets and removes callbacks in UseCases
* acts as a routing between UseCases and the View
* Can capture and save the UI states

#### UseCases (Domain logic)
* can be found in *domain* package
* has callbacks which point to *ViewModel*
* facilitates Rx observables with deals with the request asynchronously by calling synchronous repositories and calls callbacks as a result
* Cleanup: trackUseCase() method for registering UseCases. cleanup() method will make sure all UseCases unsubscribed their RX observables

#### Repositories
* Network or memory repositories are only contacted from *UseCases*. They work synchronously making them very clean and easy to understand

#### Networking
* Networking is provided using Retrofit interface `GithubApi` in *core/network/api* package

#### Model
* can be found in *core/network/model* package
* built on auto-value and parcelable

#### Dependency Injection
* can be found under *injection* package
* each View, ViewModel, Domain, Repository will have its own injection package class, so it could be plug and play anywhere in the app

#### Core package
* This will have all the utility classes shared across app

#### Testing
* can be found under *test* package
* Have covered one-one unit test for each Domain, ViewModel and Repository


#### Dagger Scopes
* 2 Levels of Scoping - Application and Activity
