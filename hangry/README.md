# Hangry
## Core Idea
The Hangry App allows the user to find and favourite recipes he / she wants to cook. Recipes can be found by searching for the name of the meal. When opening details of a meal, the user is shown the required ingredients + instructions to cook it. If a video showcasing the preparation is available, the user can click on a link to watch it.

## Task
The base Hangry project right now allows for filtering meals by name. It should be extended by the following features:

Detail Screen:
- When clicking one of the meals from the search results, a detail page should open. This includes the meals name, image, youtube link (if it exists), ingredient list and steps to cook it. To get the data, please use the following endpoint: www.themealdb.com/api/json/v1/1/lookup.php?i=52772 -> where `i` refers to the mealId
- If a youtube link is available, show a small play button above the Image. Once tapping on it, redirect to Youtube and play the video
- To show thumbnails for the ingredients, follow the instructions of the bottom of the page: https://www.themealdb.com/api.php
- Furthermore the user should be able to favourite meals in the detail screen



Favouriting:
- If a meal is favourited, it appears in the bottombar tab "Favourites". All favourited meals should be stored offline in a database
- On the favourite tab, meals can be removed from favourites again, removing them from the database
- It is important to know that the full meal is stored offline. When tapping on a meal that is in "Favourites", the same detail screen used for meal search results, is shown. However this time with fully offline data, not from the network
- Bonus: If the user has no internet connection, show an "offline" variant of the search screen. As soon as internet connection is re-established, show the stabdard screen variant
- Ensure you are using ROOM for offline database storage, Retrofit for API calls, Navigation3 for navigation, and MVVM architecture 

## Design
Figma Design: https://www.figma.com/design/m0sxJHGPM07TJZH3YRxt4x/Hangry?node-id=0-1&t=AV2XeHH0xqhl1Veb-1

## Tips
- Check all slides and examples we have gone through, you will find everything you need there
- You can find the Meal API here: https://www.themealdb.com/api.php
- Struggling with the bottom app bar? https://developer.android.com/develop/ui/compose/quick-guides/content/display-bottom-app-bar
- If you struggle, just ask me 💕
