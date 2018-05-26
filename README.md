# Popular Movies App

## Project Summary

Most of us can relate to kicking back on the couch and enjoying a movie with friends and family.
In this project, you’ll build an app to allow users to discover the most popular movies playing.


You’ll build the complete functionality of this app in two stages which you will submit separately.

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

### Problems faced:

1. When scroll down the list, it goes a bit slow but ok, but when goes up, it skips and jumps to the top. 

*Solution:* Add these lines in the MoviesActivity:

```java
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemViewCacheSize(20);
    recyclerView.setDrawingCacheEnabled(true);
    recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
```

and if you are using Picasso add these lines:

```java
    Picasso.with(context)
            .load(file)
            .fit()
            // .resize().centerCrop() // this is optional in case needs improvement
            .into(imageView);
```
