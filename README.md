A backend replica of apple music, only the logic no actual interface works by interacting with the terminal

User Guide

Open a terminal in the project folder and type:
 
    javac *.java
    java MyAudioUI
 
Type a command at the > prompt and press Enter.
Commands are not case sensitive (download, DOWNLOAD and Download all work).
When a command asks for more input, type each value and press Enter after each one.

 
 
COMMANDS

Command         What to type after
 
LISTING
STORE           nothing (lists everything in the store)
SONGS           nothing (lists songs in your library)
BOOKS           nothing (lists audiobooks in your library)
PODCASTS        nothing (lists podcasts in your library)
ARTISTS         nothing (lists the artists in your library)
PLAYLISTS       nothing (lists your playlist names)
 
DOWNLOADING
SINGLEDOWNLOAD  one store number, e.g. 1
MULTIDOWNLOAD   from store number, then to store number, e.g. 1 then 5
DOWNLOADA       artist or author name, e.g. Alessia Cara
DOWNLOADG       genre, e.g. ROCK
 
SEARCHING
SEARCH          exact title, e.g. Clocks
SEARCHA         artist or author name, e.g. Coldplay
SEARCHG         genre, e.g. POP
SEARCHP         any part of any field, e.g. Ishmael
 
PLAYING
PLAYSONG        library song number, e.g. 1
BOOKTOC         library book number, e.g. 1
PLAYBOOK        book number, then chapter number, e.g. 1 then 2
PODTOC          podcast number, then season number
PLAYPOD         podcast number, season number, episode number
 
PLAYLISTS
MAKEPL          playlist name, e.g. Mix
ADDTOPL         playlist name, content type (SONG, AUDIOBOOK or PODCAST), library number e.g. Mix then SONG then 1
PRINTPL         playlist name
PLAYPL          playlist name, then item number in the playlist
PLAYALLPL       playlist name
DELFROMPL       playlist name, then item number in the playlist
 
LIBRARY
DELSONG         library song number
SORTBYYEAR      nothing (sorts songs by year)
SORTBYNAME      nothing (sorts songs alphabetically)
SORTBYLENGTH    nothing (sorts songs by length)
 
EXIT
Q or QUIT       nothing

NOTES

- Store numbers and library numbers are different. Use STORE to see store numbers
  and SONGS / BOOKS / PODCASTS to see library numbers.
- Playlist names must be one word.
- Song genres in the store: POP, ROCK, RAP, JAZZ.