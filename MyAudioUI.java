import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();//takes in a action from the user
			try
			{

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) //This is the single download varient which calls on the download method.
				{
					int startIndex = 0;
					
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						startIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
	
					if(startIndex > store.storeSize())
					{
						throw new AudioContentNotFoundException("Content number doesn't exist");
					}
					else	
					{
						AudioContent contentToAdd = store.getContent(startIndex);
						mylibrary.download(contentToAdd);
					}
				}
				else if (action.equalsIgnoreCase("MULTIDOWNLOAD")) //Multiline Varient of download
				{
					int startIndex = 0;
					int endIndex = 0;
					
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						startIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					
					
					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						endIndex = scanner.nextInt();
						scanner.nextLine();
					}
					if(endIndex < startIndex)
					{
						throw new AudioContentNotFoundException("End Index smaller than start Index");
					}
					if (endIndex > store.storeSize())
					{
						throw new AudioContentNotFoundException("Content request bigger than store selection");
					}
					
					for(int i = startIndex; i <= endIndex; i++)
					{
						try
						{	
							AudioContent contentToAdd = store.getContent(i);
							mylibrary.download(contentToAdd);
						}
						catch(AudioContentNotFoundException exception)
						{
							System.out.println(exception.getMessage());
						}
					} 
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					// Print error message if the song doesn't exist in the library
					int songIndex = 0;
					System.out.print("Song Number: ");
					if(scanner.hasNextInt())
					{
						songIndex = scanner.nextInt();
						scanner.nextLine();
					}
					//mylibrary.playSong(songIndex);
					try
					{
						mylibrary.playSong(songIndex);
					}
					catch(AudioContentNotFoundException exception)
					{
						System.out.println(exception.getMessage());
					}
					/* 
					if(!mylibrary.playSong(songIndex))
					{
						System.out.println(mylibrary.getErrorMessage());
					}*/
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
				// Print error message if the book doesn't exist in the library
					int bookIndex = 0;
					System.out.print("Audio Book Number: "); //asks the user for the index of the audiobook
					if(scanner.hasNextInt())
					{
						bookIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.printAudioBookTOC(bookIndex);
					/* 
					if(!mylibrary.printAudioBookTOC(bookIndex))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
					*/
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int bookIndex = 0;
					int bookChapter = 0; 
					System.out.print("Audio Book Number: ");
					if(scanner.hasNextInt())
					{
						bookIndex = scanner.nextInt();
						scanner.nextLine();
					}
					System.out.print("Chapter: ");
					if(scanner.hasNextInt())
					{
						bookChapter = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playAudioBook(bookIndex, bookChapter);
					/*
					if(!mylibrary.playAudioBook(bookIndex, bookChapter)) 
					{
						System.out.println(mylibrary.getErrorMessage());
					}
					*/
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					int podcastIndex = 0;
					int seasonIndex = 0;
					System.out.print("Podcast Number: ");
					if(scanner.hasNextInt())
					{
						podcastIndex = scanner.nextInt();
						scanner.nextLine();
					}
					System.out.print("Season: ");
					if(scanner.hasNextInt())
					{
						seasonIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.printPodcastEpisodes(podcastIndex, seasonIndex);
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					int podcastIndex = 0;
					int seasonIndex = 0;
					int episodeIndex = 0;
					System.out.print("Podcast Number: ");
					if(scanner.hasNextInt())
					{
						podcastIndex = scanner.nextInt();
						scanner.nextLine();
					}
					System.out.print("Season: ");
					if(scanner.hasNextInt())
					{
						seasonIndex = scanner.nextInt();
						scanner.nextLine();
					}
					System.out.print("Episode: ");
					if(scanner.hasNextInt())
					{
						episodeIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playPodcast(podcastIndex, seasonIndex, episodeIndex);
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String playlistTitle = "";
					System.out.print("Playlist Title: ");
					if(scanner.hasNext())
					{
						playlistTitle = scanner.next();
						scanner.nextLine();
					}
					//System.out.println(playlistTitle);
					mylibrary.playPlaylist(playlistTitle);
					/*
					if(!mylibrary.playPlaylist(playlistTitle))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
					*/
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					String playlistTitle = "";
					int contentIndex = 0;
					System.out.print("Playlist Title: ");
					if(scanner.hasNextLine())
					{
						playlistTitle = scanner.nextLine();
						//scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if(scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playPlaylist(playlistTitle, contentIndex);
					/*
					if(!mylibrary.playPlaylist(playlistTitle, contentIndex))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
					*/
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int songIndex = 0;
					System.out.print("Library Song #: ");
					if(scanner.hasNextInt())
					{
						songIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.deleteSong(songIndex);
					/*if(!mylibrary.deleteSong(songIndex))
					{
						System.out.println(mylibrary.getErrorMessage());
					}*/
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String playlistTitle = "";
					System.out.print("Playlist Title: ");
					if(scanner.hasNextLine())
					{
						playlistTitle = scanner.nextLine();
						//scanner.nextLine();
					}
					mylibrary.makePlaylist(playlistTitle);
					/*if(!mylibrary.makePlaylist(playlistTitle))
					{
						System.out.println(mylibrary.getErrorMessage());
					}*/
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String playlistTitle = "";
					System.out.print("Playlist Title: ");
					if(scanner.hasNextLine())
					{
						playlistTitle = scanner.nextLine();
						//scanner.nextLine();
					}
					mylibrary.printPlaylist(playlistTitle);
					/*if(!mylibrary.printPlaylist(playlistTitle))
					{
						System.out.println(mylibrary.getErrorMessage());
					}*/
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					int contentIndex = 0;
					String playlistTitle = "";
					String typeOfContent = "";
					System.out.print("Playlist Title: ");
					if(scanner.hasNext())
					{
						playlistTitle = scanner.next();
						scanner.nextLine();
					}
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if(scanner.hasNext())
					{
						typeOfContent = scanner.next();
						scanner.nextLine();
					}
					System.out.print("Library Content #: ");
					if(scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.addContentToPlaylist(typeOfContent, contentIndex, playlistTitle);
					/* 
					if(!mylibrary.addContentToPlaylist(typeOfContent, contentIndex, playlistTitle))
					{
						System.out.println(mylibrary.getErrorMessage());
					}*/
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String playlistTitle = "";
					int contentIndex = 0;
					System.out.print("Playlist Title: ");
					if(scanner.hasNext())
					{
						playlistTitle = scanner.next();
						scanner.nextLine();
					}
					System.out.print("Playlist Content #: ");
					if(scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.delContentFromPlaylist(contentIndex, playlistTitle);
					/*
					if(!mylibrary.delContentFromPlaylist(contentIndex, playlistTitle))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
					*/
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}
				//Search action
				//Allows the user to search for a song in the store
				else if (action.equalsIgnoreCase("SEARCH"))
				{
					String title = ""; 
					System.out.print("Title: ");
					if(scanner.hasNextLine())
					{
						title = scanner.nextLine();
						//scanner.nextLine();
					}
					store.printIndex(store.searchTitle(title)); 
				}
				//Search action
				//Allows the user to search for the song's of a artist in the store
				else if(action.equalsIgnoreCase("SEARCHA"))
				{
					String artist = ""; 
					System.out.print("Artist: ");
					if(scanner.hasNextLine())
					{
						artist = scanner.nextLine();
						//scanner.nextLine();
					}
					store.printIndex(store.searchArtist(artist)); 
				}
				//Search action
				///Allows the user to search for the song's of a genre in the store
				else if(action.equalsIgnoreCase("SEARCHG"))
				{
					String genre = "";
					System.out.print("Genre: ");
					if(scanner.hasNextLine())
					{
						genre = scanner.nextLine();
					}
					store.printIndex(store.searchGenre(genre));
				}
				//Download artist action
				//Allows the user to download all the songs made by a artist in the store
				else if(action.equalsIgnoreCase("DOWNLOADA"))
				{
					String artist = ""; 
					System.out.print("Artist Name: ");
					if(scanner.hasNextLine())
					{
						artist = scanner.nextLine();
					}
					ArrayList<Integer> contentIndices = store.searchArtist(artist);
					for(Integer i : contentIndices)
					{
						try
						{
							AudioContent contentToAdd = store.getContent(i+1);
							mylibrary.download(contentToAdd);
						}
						catch(AudioContentNotFoundException exception)
						{
							System.out.println(exception.getMessage());
						}
					}
					/*for(int i = 0; i < contentIndices.size(); i++)
					{
						mylibrary.download(store.getContent(contentIndices.get(i)+1));
					}*/
				}
				//Download genre action
				//Allows the user to download all the songs of a genre by a artist	in the store
				else if(action.equalsIgnoreCase("DOWNLOADG"))
				{
					String genre = ""; 
					System.out.print("Genre: ");
					if(scanner.hasNextLine())
					{
						genre = scanner.nextLine();
					}
					ArrayList<Integer> contentIndices = store.searchGenre(genre); 
					for(Integer i : contentIndices) 
					{
						try
						{
							AudioContent contentToAdd = store.getContent(i+1);
							mylibrary.download(contentToAdd);
						}
						catch(AudioContentNotFoundException exception)
						{
							System.out.println(exception.getMessage());
						}
					}
				}
				//Searchs for a index in which a user inputed string appears
				else if(action.equalsIgnoreCase("SEARCHP"))
				{
					String match = "";
					System.out.print("Part to search: ");
					if(scanner.hasNextLine())
					{
						match = scanner.nextLine();
					}
					store.printIndex(store.searchP(match));
				}
			}
			catch(AudioContentNotFoundException exception)
			{
				System.out.print(exception.getMessage());
				System.out.println();
			}
			System.out.print("\n>");
		}
		
	}
}
