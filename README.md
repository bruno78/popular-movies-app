# Popular Movies App version 1.0


<p align="center"><img src="https://cdn.rawgit.com/bruno78/popular-movies-app/5b33a0a7/screenshots/Screen%20Shot%202018-05-28%20at%2011.52.18%20AM.png" width="200" alt="movie list image"> <img src="https://cdn.rawgit.com/bruno78/popular-movies-app/5b33a0a7/screenshots/Screen%20Shot%202018-05-28%20at%2011.51.41%20AM.png" width="200" alt="Movie detail"></p>


## Project Summary

Most of us can relate to kicking back on the couch and enjoying a movie with friends and family.
In this project, you’ll build an app to allow users to discover the most popular movies playing.


You’ll build the complete functionality of this app in two stages which you will submit separately.

## Instructions

Download or clone this repo on your machine, open the project using Android Studio. Once Gradle builds
the project, click "run" and choose an emulator.

## Stage 1:  Main Discovery Screen, A Details View, and Settings
### User Experience

In this stage you’ll build the core experience of your movies app.


Your app will:

* Upon launch, present the user with an grid arrangement of movie posters.
* Allow your user to change sort order via a setting:
    * The sort order can be by most popular, or by top rated
* Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
    * original title
    * movie poster image thumbnail
    * A plot synopsis (called overview in the api)
    * user rating (called vote_average in the api)
    * release date

## Rubric

### Common Project Requirements

- [x] App is written solely in the Java Programming Language.
- [x] Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.
- [x] UI contains an element (i.e a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated.
- [x] UI contains a screen for displaying the details for a selected movie.
- [x] Movie details layout contains title, release date, movie poster, vote average, and plot synopsis.
- [x] App utilizes stable release versions of all libraries, Gradle, and Android Studio.

### User Interface - Function

- [x] When a user changes the sort criteria (“most popular and highest rated”) the main view gets updated correctly.
- [x] When a movie poster thumbnail is selected, the movie details screen is launched.

### Network API Implementation

- [x] In a background thread, app queries the /movie/popular or /movie/top_rated API for the sort 
criteria specified in the settings menu.

### General Project Guidelines

- [x] App conforms to common standards found in the Android Nanodegree General Project Guidelines 
(NOTE: For Stage 1 of the Popular Movies App, it is okay if the app does not restore the data using 
onSaveInstanceState/onRestoreInstanceState)

### Problems faced:

1. When scroll down the list, it goes a bit slow but ok, but when goes up, it skips and jumps to the top. 

**Solution:** Add these lines in the MoviesActivity:

```java
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
```

and if you are using Picasso and still have these issues, add the commented lines:

```java
    Picasso.with(context)
            .load(file)
            .fit()
            // .resize().centerCrop() // this is optional in case needs improvement
            .into(imageView);
```

2. Back button on the Action bar in the Settings menu wasn't refreshing with the new list when settings changed

**Solution:** Remove `android:launchMode="singleTop"` from MainActivity section in the AndroidManifest.xml 

3. Add back button to a Action bar:

**Solution:** Add these lines inside of your child Activity:
```xml
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value="com.brunogtavares.popmovies.MoviesActivity"/>
```

4. Extra space on the right of the each poster when displaying the movie grid list:

**Solutio:** make sure the FrameLayout that wraps the ImageView related to poster has the margin
and padding set to 0.

