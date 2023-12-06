# Bowling Game Scorer

## Table of Contents

- [Introduction](#introduction)
- [How_to_Play](#how-to-play)
- [Features](#features)
- [Architecture](#Tech-stack)
- [Design Patterns](#design-patterns)
- [Libraries Used](#libraries-used)
- [Android Keystore system](#android-keystore-system)


## Introduction

This Android application is a simple Bowling Game Scorer that allows users to track and score their bowling games. The application follows the MVVM architecture and incorporates Kotlin Coroutines, Jetpack Compose for the UI, and Hilt for dependency injection.


## How to play

- **Enter Player Name:** Start by entering the player's name before each game.
- **Roll the Ball:** After entering the player's name, you can input the number of pins knocked down in each roll.
- **View Scores:** Once the game is finished, you can view the scores and track your progress.


## Features

- **Bowling Game Scoring:** The app calculates the score of a single player in a bowling game based on standard bowling rules.
- **10 Frames:** A bowling game consists of 10 frames, each with two rolls (except the 10th frame, which can have up to three rolls).
- **Strikes and Spares:** The app handles strikes and spares, calculating bonuses for the next rolls accordingly.
- **Persistent Storage:** User scores are stored in a Room database for persistence.

## Tech stack

- **MVVM Architecture:** The app follows the Model-View-ViewModel architecture for a clear separation of concerns.
- **Kotlin Coroutines:** Asynchronous programming with Kotlin Coroutines is used for background tasks.
- **Jetpack Compose:** The modern Android UI toolkit, Jetpack Compose, is used for building the UI.
- **Hilt:** Dependency injection is handled with Hilt for improved code maintainability and testability.
- **Room Database:** User scores are stored locally using Room, part of the Android Jetpack components.


## Design Patterns

- `Single Responsibility Principle (SRP)`   Each class or interface has a single responsibility related to frame handling. FrameHandler interface defines a single method for handling bonuses.
- `Open/Closed Principle (OCP)` The code is open for extension but closed for modification. New frame types can be added by creating new implementations of the FrameHandler interface.
- `Dependency Inversion Principle (DIP)` The BowlingGame class depends on abstractions (FrameHandler interface) rather than concrete implementations. The FrameHandler instances are injected into the BowlingGame class.


## Libraries Used

- Android Architecture Components (ViewModel, StateFlow)
- Jetpack Compose
- Coroutines
- Hilt for dependency injection
- Room Database


## Android Keystore system

The Android Keystore system lets you store cryptographic keys in a container to make them more difficult to extract from the device.