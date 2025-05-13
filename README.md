🎬 KinoPoisk Clone (Android)
============================

> A full-featured Android app for movie and TV series discovery with dynamic collections, search filters, local storage, and modern architecture using Kotlin, Hilt, Paging 3, Room, and Clean Architecture

* * *

🧩 Description
--------------

This is a fully functional Android application built as a clone of the Kinopoisk app, featuring:

*   Homepage screen with curated lists: Premieres, Popular, Top 250, Series, and **dynamic genre/country-based sections**
*   Movie/TV detail screens with cast, staff, gallery, and related content
*   Advanced search with real-time filtering by genre, country, year, rating, and more
*   User-defined collections: "Favorites", "Want to Watch", "Watched"
*   Integration with [Kinopoisk Unofficial API](https://kinopoiskapiunofficial.tech/)
*   Offline-first design with Room caching and DataStore preferences

* * *

🧱 Features
-----------

Feature

Description

✅ **Homepage Screen**
Main dashboard with horizontal scrollable sections

✅ **Dynamic Collections**
Sections like “France / Drama”, “Japan / Anime” generated dynamically

✅ **Search Screen**
Real-time search with dynamic filtering using Genre/Country/Year/Rating

✅ **Movie Detail Screen**
Includes poster, title, crew, actors, gallery, reviews, related films

✅ **Actor & Staff Screens**
View actor details and filmography

✅ **Collections Management**
Add/remove movies from user-defined collections

✅ **Paging 3 Support**
Infinite scrolling with reactive loading

✅ **Hilt DI**
Full dependency injection using Dagger Hilt

✅ **Coroutines + Flow**
Modern async programming model

✅ **Room Database**
Caching movies, collections, history

✅ **DataStore Preferences**
Saves user filters, settings

✅ **Material Design 3**
Fully themed UI with adaptive color schemes

✅ **Firebase Crashlytics**
Error reporting and crash tracking

✅ **Retrofit + Kotlinx Serialization**
Efficient network communication

* * *

🚀 Tech Stack
-------------

Kotlin
Gradle + KTS
Retrofit + Kotlinx Serialization
Kotlin Coroutines + ViewModelScope
Flow + StateFlow
Hilt
Room Persistence Library
Glide
RecyclerView + Paging 3
Jetpack Navigation Component
Firebase Crashlytics
Material 3, BottomSheetDialogFragment, RangeSlider, ChipGroup, ViewPager2

* * *

📱 Screenshots
--------------



\[Insert screenshots here when available\]

* * *

🛠 Project Structure
--------------------

app/

├── data/

│ ├── api/ → Retrofit interfaces

│ ├── model/ → DTO models and Room entities

│ └── repository/ → Repository implementations

│

├── domain/

│ ├── usecases/ → Business logic layer

│ └── model/ → Domain models

│

├── presentation/

│ ├── homepage/ → Homepage screen

│ ├── detail/ → Movie/TV details

│ ├── search/ → Search functionality

│ ├── collections/ → User collections

│ └── staff/ → Actors & directors

│

├── di/

│ └── AppModule.kt → Hilt modules

│

└── core/

└── extensions/ → Utility functions, custom decorators

* * *

🧪 Key Implementation Details
-----------------------------

### 🔁 Dynamic Collection Generation


class GetDynamicGenreCountryUseCase @Inject constructor(

private val repository: Repository

) {

operator fun invoke(): Flow<List<Movie\>> \= flow {

val filters \= repository.getFilters()

val randomGenre \= filters.genres.shuffled().first()

val randomCountry \= filters.countries.shuffled().first()

  

val response \= repository.getMoviesByGenreAndCountry(

countryId \= randomCountry.id,

genreId \= randomGenre.id

)

  

emit(response.items)

}.catch { e \->

throw IOException("Не удалось получить фильмы по стране и жанру", e)

}.flowOn(Dispatchers.IO)

}

* * *

### 🧰 Search Filters

The app supports advanced search with:

*   Type filter: All / Film / Series
*   Country: One selection
*   Genre: One selection
*   Year range
*   Rating range (RangeSlider)
*   Hide already watched toggle

All selected filters are persisted via **DataStore** .

* * *

### 🗂 Local Data Storage

#### Room Entities:

*   `MovieEntity` – stores minimal required info
*   `CollectionEntity` – user-created collections
*   `WatchHistoryEntity` – tracks viewed films

#### DataStore:

*   Stores user preferences
*   Last used search filters
*   Theme settings
*   App metadata

* * *

### 🧭 Navigation

Navigation uses **Jetpack Navigation Component** with:

*   `BottomNavigationView` for main tabs
*   Safe Args for typed navigation
*   Shared Element Transitions for smooth movie/actor transitions

Example:


val directions \= HomepageFragmentDirections.actionOpenDetails(filmId)

findNavController().navigate(directions)

* * *

📦 Sample API Requests
----------------------

@GET("/api/v2.2/films/top?type=TOP\_250\_BEST\_FILMS")

suspend fun getTop250(): TopMoviesResponse

  

@GET("/api/v2.2/films/filters")

suspend fun getFilters(): FiltersResponse

  

@GET("/api/v2.2/films?countries={countryId}&genres={genreId}")

suspend fun getMoviesByGenreAndCountry(

@Query("countries") Int countryId,

@Query("genres") Int genreId

): CollectionsResponse

* * *

🧬 Sealed Class for UI State
----------------------------

sealed class HomeUiState<out T\> {

data class Success<out T\>(val data: T) : HomeUiState<T\>()

data class Error(val exception: Throwable) : HomeUiState<Nothing\>()

object Loading : HomeUiState<Nothing\>()

}

Used in ViewModel:


private val \_uiState \= MutableStateFlow<HomeUiState<List<MovieSection\>>>(HomeUiState.Loading)

val uiState: StateFlow<HomeUiState<List<MovieSection\>>> \= \_uiState.asStateFlow()

* * *

📌 Sample Use Case
------------------

class GetPremieresUseCase @Inject constructor(

private val repository: Repository

) {

operator fun invoke(): Flow<List<Movie\>> \= repository.getPremieres()

}

* * *

🧩 Sample Section Rendering
---------------------------

Each section on the homepage includes:

*   Title
*   Horizontal list of 20 items max
*   "See all" button if more than 20 items
*   Clickable movie cards with shared element transition

RecyclerView + GridLayoutManager for 2-column layout:

<androidx.recyclerview.widget.RecyclerView

android:id\="@+id/recycler\_view"

android:layout\_width\="match\_parent"

android:layout\_height\="wrap\_content"

android:padding\="4dp"

android:clipToPadding\="false"/>

* * *

📦 Dependencies (`build.gradle.kts`)
------------------------------------

dependencies {

implementation(libs.androidx.core.ktx)

implementation(libs.appcompat)

implementation(libs.material)

  

// Hilt

implementation(libs.hilt.android)

kapt(libs.hilt.compiler)

  

// ViewModel + LiveData

implementation(libs.lifecycle.viewmodel.ktx)

implementation(libs.lifecycle.runtime.ktx)

  

// Coroutines + Flow

implementation(libs.kotlinx.coroutines.android)

implementation(libs.kotlinx.serialization.json)

  

// Retrofit

implementation(libs.retrofit)

implementation(libs.retrofit.kotlinx.serialization.converter)

implementation(libs.okhttp.logging.interceptor)

  

// Paging 3

implementation(libs.paging.runtime)

  

// Room

implementation(libs.room.runtime)

implementation(libs.room.ktx)

annotationProcessor(libs.room.compiler)

kapt(libs.room.compiler)

  

// Glide

implementation(libs.glide)

kapt(libs.glide.compiler)

  

// DataStore

implementation(libs.datastore.preferences)

  

// Firebase

implementation(platform("com.google.firebase:firebase-bom:32.1.1"))

implementation("com.google.firebase:firebase-analytics-ktx")

implementation("com.google.firebase:firebase-crashlytics-ktx")

}

* * *

🧾 License
----------

MIT License  
Feel free to change it to your preferred license (Apache, GPL, etc.)

* * *

💡 Contributing
---------------

Contributions are welcome! Please open an issue first before making a PR.

* * *

📬 Contact
----------

Sergey Orlov 
📧 [formyfrontend@gmail.com](mailto:formyfrontend@gmail.com)  
🔗 LinkedIn: [https://linkedin.com/in/your-profile](https://linkedin.com/in/your-profile)  
📱 GitHub: [https://github.com/forskillzor/](https://github.com/forskillzor/)

* * *

⭐️ Show Your Support
--------------------

If you like this project, give it a ⭐️!

* * *

🧑‍💻 Author
------------

Sergey Orlov
_Self-taught Android Developer | Kotlin Enthusiast_

* * *

🧠 Why This Project?
--------------------

This project was created during a personal journey into **Android development** , showcasing:

*   Real-world use of **Clean Architecture**
*   Solid implementation of **MVVM + Flow + StateFlow**
*   Beautiful **UI design** based on provided mockups
*   **Professional-grade structure** , ready for job applications or portfolio showcase

* * *

✅ Future Improvements
---------------------

*   Migrate to **Jetpack Compose**
*   Implement **Dark Mode toggle**
*   Add **user authentication**
*   Add widgets for **favorite films**
*   Make layouts **tablet-friendly**
