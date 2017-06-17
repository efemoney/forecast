# Forecast

Simple weather app to showcase MVP architecture on Android. **This is just an experiment**.

#### Building and running the project
 * Android Studio 3.0 Canary 4 with Gradle 4.0
 * Android Gradle Plugin `3.0.0-alpha4`
 * Kotlin `1.1.2-4`
 
This app (along with the unit tests) are written 100% in Kotlin 🎉

Also, this project makes use of the free weather API at [openweathermap.org](openweathermap.org/api).

You need to sign up for a free account and obtain an API key.  
 
Once obtained, copy your API key into the `secrets.properties.sample` file  
```
OWM_API_KEY=<YOUR_OPEN_WEATHER_MAP_API_KEY_GOES_HERE>
```
and rename the file to `secrets.properties`

#### Libraries Used
 * Design Support Libraries
 * AppCompat Support Libraries
 * Google Play Services 11 - for retrieving location
 * Retrofit - as REST API client
 * Gson - for JSON parsing
 * Glide - for image loading
 * Dagger - for dependency injection
 * RxJava/RxAndroid - for reactive programming
 * JUnit - for testing
 
 #### MVP Architecture
 This application uses the Mode-View-Presenter (MVP) architecture. 
 This enables separation of concerns and ultimately a very modular application. 
 As a result, the code is more scalable, maintainable and unit testable.
 
 #### Possible Improvements
 More tests!
