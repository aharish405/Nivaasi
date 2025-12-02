# Nivaasi - Property Management Android App

A modern, mobile-first property management application built with Kotlin and Jetpack Compose for managing PG/Hostel properties.

## Features

- 📊 **Dashboard** - Summary cards, financial overview, occupancy stats
- 🏢 **Property Management** - Floor-wise bed grid with status tracking
- 👥 **Tenant Management** - Search, view, and contact tenants
- 💰 **Transactions** - Track rent, deposits, and expenses
- 🍽️ **Food Menu** - Weekly meal planning
- 👤 **Profile** - Owner info and settings

## Tech Stack

- **Kotlin** - Modern Android development
- **Jetpack Compose** - Declarative UI
- **Material 3** - Latest design system
- **Navigation Component** - Type-safe navigation
- **ViewModel + StateFlow** - Reactive state management
- **Hilt** - Dependency injection

## Design

- **Primary Color:** Sky Blue (#0EA5E9)
- **Card-based Layout** - Clean, modern UI
- **Mobile-First** - Optimized for touch
- **Material 3** - Latest design guidelines

## Build & Run

1. Open project in Android Studio
2. Sync Gradle files
3. Run on emulator or device

```bash
./gradlew assembleDebug
./gradlew installDebug
```

## Project Structure

```
app/src/main/java/com/nivaasi/app/
├── data/          # Models and repository
├── ui/            # Screens, theme, viewmodels
├── navigation/    # Navigation graph
└── MainActivity.kt
```

## Sample Data

The app includes a `FakeRepository` with realistic sample data for demonstration. Replace with actual API integration when ready.

## License

Copyright © 2023 Nivaasi
