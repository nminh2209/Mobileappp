# Music Studio Rental App

## Overview
The Music Studio Rental App is a simple proof of concept application designed for a music studio that offers monthly rentals of musical instruments and equipment. The app allows clients to view available rental items, navigate through them, and book items for immediate pickup.

## Features
- Display of rental items one at a time with a "next" button for navigation.
- Detailed view of each rental item with a "borrow" button.
- In-memory data storage for rental items.
- User-friendly interface with various UI elements including RatingBar and multi-choice widgets.
- Error checking for required fields during the booking process.
- Toast or Snackbar notifications for booking actions.

## User Stories
1. **As a client**, I want to view available rental items one at a time so that I can easily decide which item to rent.
2. **As a client**, I want to see detailed information about a rental item, including its rating and attributes, so that I can make an informed decision before borrowing.

## Use Cases
- **Viewing Rental Items**: The user opens the app and sees the first rental item displayed. They can click "next" to view the next item.
- **Borrowing an Item**: The user selects an item and clicks the "borrow" button, which takes them to a detail screen where they can confirm their booking.

## Layout Sketches
### Layout 1: Main Activity
- **Widgets**:
  - ImageView for displaying the rental item image.
  - TextView for the item name.
  - RatingBar for the item rating.
  - TextView for the price per month.
  - Button for "Next".

### Layout 2: Item Detail Activity
- **Widgets**:
  - ImageView for displaying the rental item image.
  - TextView for detailed item information.
  - RadioGroup for selecting rental options.
  - Button for "Save" and "Cancel".

## Styles
- Consistent text size and color defined in `styles.xml` for headings and body text.
- Color resources defined in `colors.xml` for buttons and backgrounds to maintain a cohesive theme.

## Data Handling
- The app uses Parcelable objects to pass rental item data between activities, allowing for efficient data transfer without persistent storage.
- Error checking is implemented to ensure all required fields are filled before proceeding with a booking.

## Notifications
- The app utilizes Toast or Snackbar notifications to inform users of successful bookings or cancellations, enhancing user experience by providing immediate feedback.

## Setup Instructions
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Build and run the application on an Android device or emulator.

## Conclusion
The Music Studio Rental App serves as a foundational tool for clients to rent musical instruments and equipment easily. It emphasizes user experience and efficient data handling while providing essential features for rental management.