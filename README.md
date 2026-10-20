# AudiobookStore

A Java application that simulates a music streaming service. You can browse songs and audiobooks from a store, download them to your library, create playlists, and play content through a text-based interface.

## Overview

This is a terminal program that works like Apple Music but with text commands instead of a GUI. The store contains pre-loaded audio content that you can search through and download to build your own library. You can organize songs into playlists, sort your library in different ways, and play individual tracks or entire playlists.

The classes use inheritance. AudioContent is the parent class for Song, AudioBook, and Podcast. Each type stores different information and has different ways of being played. For example, a song shows lyrics, an audiobook lets you pick a chapter, and a podcast has seasons with episodes.

## Codebase

### AudioContent

- Abstract base class that all audio types inherit from
- Stores common info: title, year, id, audio file path, length in minutes
- Has a play() method that subclasses override
- Uses equals() to check if two pieces of content are the same

### Song

- A song has an artist, composer, genre, and lyrics
- Implements Comparable so songs can be sorted alphabetically
- Playing a song displays its lyrics
- Genres in the store: POP, ROCK, RAP, JAZZ (HIPHOP and CLASSICAL are defined in the code but aren't used in the store)

### AudioBook

- An audiobook is organized into chapters
- Has an author and narrator
- Each chapter can be played separately
- You can view the table of contents to see all chapter titles

### Podcast

- A podcast is split into seasons, and each season has episodes
- Has a host name
- Playing an episode needs a season and episode number
- Can view the list of episodes in a season

### AudioContentStore

- Reads the store data from store.txt file when it starts up
- Uses HashMaps to quickly find content by title, artist, or genre
- Handles all the search requests from the user
- Downloads come from here

### Library

- Holds the content you've downloaded
- Keeps songs, audiobooks, and podcasts in separate lists
- Makes sure you don't download the same thing twice
- Manages your playlists
- Can sort songs by year, alphabetically, or by length
- Removes songs from any playlists if you delete them from your library

### Playlist

- A named list of content
- Can mix songs, audiobooks, and podcasts in the same playlist
- Can play everything or just one item
- Can remove items from the playlist

## Features

### Browse and Download

- Look at everything available in the store
- Download one item at a time or download a range
- Download all songs and audiobooks by a specific artist or author
- Download all songs in a specific genre

### Search

- Find a song by its exact title
- Look up all songs and audiobooks by a particular artist or author
- Find all songs in a genre
- Search for any text that appears anywhere (title, artist, etc.)

### Play Content

- Play any song in your library (displays the lyrics)
- Play audiobooks one chapter at a time (the chapter text is printed)
- Play podcasts episode by episode (you pick the season and episode). The current store.txt has no podcasts, so this can't be used yet (see Status)
- View the chapters of an audiobook or episodes of a podcast before playing

### Organize with Playlists

- Create your own playlists with custom names
- Mix different types of content in one playlist (songs, audiobooks, podcasts all together)
- See what's in a playlist
- Play everything in a playlist or just specific items
- Remove items from playlists

### Manage Your Library

- Sort your songs by release year, alphabetically, or by how long they are
- Delete songs from your library (they get removed from playlists too)
- See a list of all the artists in your collection

## Running the Program

Open a terminal, navigate to the project folder, and type:

```bash
javac *.java
java MyAudioUI
```

You'll see a `>` prompt. Type your commands here. Commands don't care about uppercase or lowercase. When a command asks for input, just type what it wants and press Enter.

The program reads `store.txt` from the folder you run it in, so keep `store.txt` next to the `.class` files. If it's missing, the program prints an error and exits.

## Terminal Commands

### Listing Content

| Command     | Input | Description                         |
| ----------- | ----- | ----------------------------------- |
| `STORE`     | none  | List all content in the store       |
| `SONGS`     | none  | List all songs in your library      |
| `BOOKS`     | none  | List all audiobooks in your library |
| `PODCASTS`  | none  | List all podcasts in your library   |
| `ARTISTS`   | none  | List all artists in your library    |
| `PLAYLISTS` | none  | List all your playlists             |

### Downloading Content

| Command         | Input                     | Description                                          |
| --------------- | ------------------------- | ---------------------------------------------------- |
| `DOWNLOAD`      | store number              | Download one item (e.g., `1`)                        |
| `MULTIDOWNLOAD` | from and to store numbers | Download a range (e.g., `1` then `5`)                |
| `DOWNLOADA`     | artist/author name        | Download all by artist/author (e.g., `Alessia Cara`) |
| `DOWNLOADG`     | genre                     | Download all of a genre (e.g., `ROCK`)               |

### Searching the Store

| Command   | Input              | Description                                |
| --------- | ------------------ | ------------------------------------------ |
| `SEARCH`  | exact title        | Search by title (e.g., `Clocks`)           |
| `SEARCHA` | artist/author name | Search by artist/author (e.g., `Coldplay`) |
| `SEARCHG` | genre              | Search by genre (e.g., `POP`)              |
| `SEARCHP` | partial text       | Search any field (e.g., `Ishmael`)         |

### Playback

| Command    | Input                           | Description                                 |
| ---------- | ------------------------------- | ------------------------------------------- |
| `PLAYSONG` | library song number             | Play a song (e.g., `1`)                     |
| `BOOKTOC`  | library book number             | View audiobook chapters (e.g., `1`)         |
| `PLAYBOOK` | book number, chapter number     | Play audiobook chapter (e.g., `1` then `2`) |
| `PODTOC`   | podcast number, season number   | View podcast episodes (e.g., `1` then `2`)  |
| `PLAYPOD`  | podcast number, season, episode | Play podcast episode (e.g., `1`, `2`, `3`)  |

### Playlist Management

| Command     | Input                                       | Description                                                |
| ----------- | ------------------------------------------- | ---------------------------------------------------------- |
| `MAKEPL`    | playlist name                               | Create a playlist (e.g., `Mix`)                            |
| `ADDTOPL`   | playlist name, content type, library number | Add content to playlist (e.g., `Mix` then `SONG` then `1`) |
| `PRINTPL`   | playlist name                               | Display playlist contents (e.g., `Mix`)                    |
| `PLAYPL`    | playlist name, item number                  | Play item in playlist (e.g., `Mix` then `2`)               |
| `PLAYALLPL` | playlist name                               | Play entire playlist (e.g., `Mix`)                         |
| `DELFROMPL` | playlist name, item number                  | Remove item from playlist (e.g., `Mix` then `2`)           |

### Library Management

| Command        | Input               | Description                          |
| -------------- | ------------------- | ------------------------------------ |
| `DELSONG`      | library song number | Delete song from library (e.g., `3`) |
| `SORTBYYEAR`   | none                | Sort songs by year                   |
| `SORTBYNAME`   | none                | Sort songs alphabetically            |
| `SORTBYLENGTH` | none                | Sort songs by length                 |

### Exit

| Command       | Input | Description       |
| ------------- | ----- | ----------------- |
| `Q` or `QUIT` | none  | Close the program |

## Implementation

- **Inheritance and polymorphism:** `AudioContent` is abstract. `Song`, `AudioBook`, and `Podcast` extend it and override `getType()`, `printInfo()`, and `play()`.
- **File I/O:** `AudioContentStore.fileReader()` reads `store.txt` with a `Scanner` and builds the `AudioContent` objects. The file format is one block per item, starting with the keyword `SONG` or `AUDIOBOOK`.
- **Custom exceptions:** `AudioContentNotFoundException` extends `RuntimeException` and is defined at the bottom of `Library.java`. Methods in `Library` throw it, and the command loop in `MyAudioUI` catches it, prints the message, and waits for the next command.
- **HashMaps:** `AudioContentStore` builds three maps in its constructor: title to index, artist (or author) to a list of indexes, and genre to a list of indexes. The search and download methods use these.
- **Collections and sorting:** `ArrayList` holds the library, store, playlists, and episodes. Sorting by year and length uses private `Comparator` classes inside `Library`, and sorting by name uses `Song.compareTo()`.

## Things to Know

- **Store numbers vs library numbers:** These are different and it's easy to get confused. When you type `STORE`, it shows you everything available with store numbers (like 1, 2, 3...). When you type `SONGS`, it shows your downloaded songs with library numbers (which might be 1, 2, 3 or whatever). If a command asks for a song number, it usually wants your library number. If it asks for a store number, use the numbers from the `STORE` listing.
- **Playlist names:** `MAKEPL`, `PRINTPL`, and `PLAYPL` accept names with spaces, but `ADDTOPL`, `PLAYALLPL`, and `DELFROMPL` only read the first word. Use one-word names like `Favorites` so every playlist command works.
- **Genres:** The songs in the store use POP, ROCK, RAP, or JAZZ. Only songs have genres, so `SEARCHG` and `DOWNLOADG` only work with songs.
- **Commands are flexible:** You can type commands in any case. `PLAYSONG`, `playsong`, `PlaySong` all work the same.
- **Duplicate downloads:** If you try to download something you already have, the program will tell you and won't add it twice.

## Status

**Working**
- The program compiles with `javac *.java`.
- Error cases print a message and return to the prompt: bad numbers, missing playlists, duplicate downloads, and invalid podcast numbers.

**Not working or limited**
- **Podcasts:** store.txt has no podcasts, so `PODCASTS`, `PODTOC`, `PLAYPOD`, and `ADDTOPL` with `PODCAST` can't be used with the current store file.
- **Playlist names:** `ADDTOPL`, `PLAYALLPL`, and `DELFROMPL` only read one word, so names with spaces break them.
- **Playback:** No audio engine added yet. Songs print their lyrics and audiobook chapters print their text.

**Not yet tested**
- Only the error cases have been run. The other commands compile but haven't been tested end to end.

## Future Plans

- Load the store from a database instead of the local store.txt file
- Add an audio engine and a graphical interface. Neither exists yet
- Add a shuffle option to change the order of a playlist 