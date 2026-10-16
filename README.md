# COMS 2270 Homework

My homework projects for COMS 2270 (Object-Oriented Programming) at Iowa State University, written in Java.

Each assignment only contains the classes I wrote. The course-provided `api` package and test harnesses are not included, so the projects won't compile on their own.

## HW1 – Hot Air Balloon (`HW1/Balloon.java`)

Simulates a hot air balloon. The balloon keeps track of:

- its altitude and ground position, each with a maximum
- its vertical and horizontal velocity
- how much fuel is in the tank, and how fast it can burn fuel

`oneSecondUpdate()` advances the simulation by one second: it burns fuel and updates the balloon's position. The class also tracks elapsed time as hours, minutes and seconds, lets you add fuel, adjust the velocities, and reset the balloon to its starting position.

## HW2 – CyGame (`HW2/CyGame.java`)

A two-player, Monopoly-style board game themed around Iowa State. Players take turns rolling and moving around a board of squares. Each square has a type:

| Square | Effect |
|---|---|
| Endzone | Collect a prize for landing on or passing it |
| CyTown | Can be bought; the other player pays rent when they land on it |
| Pay Rent | Pay a standard rent to the other player |
| Fall Behind | Move back one square |
| Blizzard | You can only leave it by rolling an odd number |
| Pass Class | Jump ahead four more squares |

The game ends when one player goes bankrupt or someone reaches the money needed to win.

## HW3 – Boulder Puzzle (`HW3/`)

A sliding-block puzzle like *Rush Hour*. Boulders slide horizontally or vertically across a grid, and the goal is to get your boulder to the exit.

- **`Board`** – holds the grid and the boulders. It handles grabbing a boulder, moving it, listing all possible moves, keeping a move history, undo and reset, and detecting when the puzzle is solved.
- **`Boulder`** – one boulder. It stores the boulder's position, length and orientation, and how it moves.
- **`GridUtil`** – turns a text description of a level into a grid of cells and a list of boulders. `*` is a wall, `e` is the exit, `.` is ground, and `[ ] ^ v #` are boulder pieces.
- **`Solver`** – automatically solves a puzzle. It does a depth-first search over move sequences, skips board states it has already reached in fewer moves, and records every solution found within a move limit.

## HW4 – Train Track Links (`HW4/`)

The pieces of track that connect points in a train simulation. Each link controls where a train can go next and moves it from one track segment to the next.

- **`AbstractLink`** – the shared superclass. It implements the `Crossable` interface and holds the behavior the links have in common.
- **`StraightLink`** – a straight track between two endpoints.
- **`TurnLink`** – a curved track between two endpoints.
- **`CouplingLink`** – joins two track endpoints so a train can go either way.
- **`DeadEndLink`** – the end of a line; nothing connects past it.
- **`SwitchLink`** – a switch that sends trains either straight or onto a turn.
- **`MultiFixedLink`** – a junction with several fixed pairs of connected points.
- **`MultiSwitchLink`** – a junction whose connections can be changed while the simulation is running.
