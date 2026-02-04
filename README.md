# OTP Authentication App

This project is an Android application that implements OTP-based authentication with session handling using Kotlin and Jetpack Compose.

---

## 1. OTP Logic and Expiry Handling

- OTP is generated using random numeric values.
- Each OTP is associated with a timestamp.
- The expiry time is set (for example: 60 seconds).
- When the user enters OTP, the app checks:
  - If the OTP matches
  - If the current time is within the expiry limit
- If expired, the user is asked to request a new OTP.

This prevents reuse of old OTPs and improves security.

---

## 2. Data Structures Used and Why

### a) StateFlow / MutableStateFlow
- Used to manage UI state.
- Helps in observing authentication status in real time.
- Automatically updates UI when data changes.

### b) Data Classes
- Used for representing session and OTP-related data.
- Makes code clean and readable.

### c) SharedPreferences / Session Storage
- Used to store login/session information locally.
- Helps in maintaining user login state.

These structures were chosen for simplicity, performance, and compatibility with Android architecture.

---

## 3. External SDK / Libraries Used

### Firebase Authentication (Optional / If Used)
- Used for secure OTP verification.
- Provides reliable backend support.
- Reduces manual security handling.

### Jetpack Compose
- Used for UI development.
- Makes UI declarative and easy to manage.
- Reduces boilerplate code.

### Kotlin Coroutines
- Used for background tasks.
- Helps in handling delays and timers for OTP expiry.

These SDKs were chosen for modern Android development and industry relevance.

---

## 4. Use of GPT vs Self-Implementation

### Used GPT For:
- Understanding OTP flow logic.
- Debugging errors.
- Structuring ViewModel and Factory classes.
- Learning best practices in MVVM.
- Improving README and documentation.

### Implemented and Understood Personally:
- UI screens using Jetpack Compose.
- ViewModel and State management.
- OTP validation logic.
- Session handling.
- Git and GitHub integration.
- Error handling and testing.

GPT was used as a learning assistant, while core logic and implementation were done by understanding and applying concepts.

---

## How to Run the Project

1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle.
4. Run on Emulator or Physical Device.

---
