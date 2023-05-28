# CookieRunGachaSim
simulates the Cookie Run Kingdom Gacha for both cookies and treasure. The app features the full animation for the cookie gacha with the option to skip. The total amout that would be spent in USD is also displayed along with the total amount of gems spent. The inventory of pulled cookies and treasure is also viewable and can be sorted through to see the amount obtained. All of the rates match the ingame rates for the gacha found on https://cookierunkingdom.fandom.com/wiki/Gacha in the probabilites seciton.

## Tech Stack
- Jetpack Compose
- Room Db
- Koin
- Media 3 / Exoplayer
- Coil
- Accompanist Navigation Compose
- Jetpack Preferences Datastore
- Firebase Crashlytics
- Orbit MVI

## Project Structure
The project is broken up into feature packages each containing the Compose UI elements, UseCases, and Koin Module specific to the feature.
The App uses MVI and MVVM. for the gacha screens MVI with Orbit is used as the only state being observed is the current gacha pull. For the other screens such as the inventory screen MVVM is used. The ViewModels call the data layer logic through usecases that can be reused between the ViewModels for common tasks.

# App Images
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/a0e907d6-d630-41f6-adea-2e039cb2b19d" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/398b8334-d38a-47c1-ad6a-8e47f5210672" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/2aab0292-5ec1-4dda-8731-12c6a4b18aae" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/48a5c6e5-8c40-419a-b0df-9f9947cf8faa" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/1cd13e6d-d586-4f29-89c7-20999b77a337" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/5f6e43c7-e245-4ed4-bead-2462a282007b" width = "400" height = "200" />
<img src="https://github.com/SilvVF/CookieRunGachaSim/assets/98186105/06f85064-df24-445d-b4fe-7e80a85b09c2" width = "400" height = "200" />
