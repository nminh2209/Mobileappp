# Assignment 2 - Music Rental App

## Overview
This project is a Music Rental App that allows users to browse and rent various musical instruments. The app categorizes instruments into different rental items, such as guitars, drum sets, keyboards, and violins. Users can view detailed information about each instrument and make rental bookings.

## Features
- **Main Activity**: Displays a list of rental items (instruments) using a RecyclerView.
- **Item Detail Activity**: Shows detailed information about a selected rental item and allows users to initiate a rental.
- **Instrument List Activity**: Displays a list of specific instruments categorized under each rental item when clicked.
- **Dynamic Data**: Utilizes a data provider to manage and retrieve rental items and instruments.

## Project Structure
```
Assigment2
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── musi
│   │   │   │               ├── MainActivity.kt
│   │   │   │               ├── ItemDetailActivity.kt
│   │   │   │               ├── InstrumentListActivity.kt
│   │   │   │               ├── adapters
│   │   │   │               │   └── RentalItemAdapter.kt
│   │   │   │               ├── models
│   │   │   │               │   ├── RentalItem.kt
│   │   │   │               │   └── Instrument.kt
│   │   │   │               └── utils
│   │   │   │                   └── DataProvider.kt
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_item_detail.xml
│   │   │   │   │   └── activity_instrument_list.xml
│   │   │   │   └── drawable
│   │   │   │       ├── guitar_image.xml
│   │   │   │       ├── drum_image.xml
│   │   │   │       ├── piano_image.xml
│   │   │   │       └── violin_image.xml
│   │   ├── AndroidManifest.xml
├── build.gradle
└── README.md
```

## Setup Instructions
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Ensure that you have the necessary SDKs and dependencies installed.
4. Build and run the application on an emulator or physical device.

## Future Enhancements
- Implement user authentication to manage user profiles and rental history.
- Add a search feature to allow users to find specific instruments quickly.
- Include a review and rating system for rented instruments.

## License
This project is licensed under the MIT License.