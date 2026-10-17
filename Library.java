import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	private ArrayList<Podcast> 	podcasts; //Was commented out
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); ; //Was commented out
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) throws AudioContentNotFoundException
	{
		if(content == null)  //Moved the if statement from MyAudioUi into here basically
		{
			errorMsg = "Content is null";
			throw new AudioContentNotFoundException(errorMsg);
		}
		else if(content.getType().equals(Song.TYPENAME))//checks if song
		{
			Song newSong = (Song) content; 
			for(int i = 0; i < songs.size(); i++)
			{
				if(newSong.equals(songs.get(i))) //Checks if the song has been downloaded already
				{
					errorMsg = "Song " + newSong.getTitle() + " already downloaded";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
			}
			songs.add(newSong);
			System.out.println("SONG " + newSong.getTitle() + " Added to Library");
		}
		else if(content.getType().equals(AudioBook.TYPENAME)) //checks if audiobook
		{
			AudioBook newAudioBook = (AudioBook) content;
			for(int i = 0; i < audiobooks.size(); i++)
			{
				if(newAudioBook.equals(audiobooks.get(i)))//checks if audiobook has been downloaded already
				{
					errorMsg = "Audiobook " + newAudioBook.getTitle() + " already downloaded";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
			}
			audiobooks.add(newAudioBook);
			System.out.println("AUDIOBOOK " + newAudioBook.getTitle() + " Added to Library");
		}
		else if(content.getType().equals(Podcast.TYPENAME))//checks if podcast
		{
			Podcast newPodcast = (Podcast) content;
			for(int i = 0; i < podcasts.size(); i++)
			{
				if(newPodcast.equals(podcasts.get(i))) //checks if podcasts has been downloaded already
				{
					errorMsg = "Podcast already downloaded";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
			}
			podcasts.add(newPodcast);
			System.out.println("PODCAST " + newPodcast.getTitle() + " Added to Library");
		}
		
		//return true;
	}
	
	// Print Information (printInfo()) about all songs in the array list
	// given
	public void listAllSongs()
	{
		for(int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for(int i = 0; i < this.audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			this.audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for(int i = 0; i < this.podcasts.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			this.podcasts.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for(int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.print(this.playlists.get(i).getTitle());
			System.out.println();
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>();
		for(int i = 0; i < songs.size(); i++)
		{
			if(!artists.contains(songs.get(i).getArtist()))
			{
				artists.add(songs.get(i).getArtist());
			}
		}
		for(int j = 0; j < artists.size(); j++)
		{
			System.out.println(artists.get(j));
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index) throws AudioContentNotFoundException
	{
		if(index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			throw new AudioContentNotFoundException(errorMsg);
		}
		Song deletedSong = songs.get(index-1); //saves the song so we can find it in the playlists
		songs.remove(index-1);                 //then removes it from the library
		for(int i = 0; i < playlists.size(); i++)//loops through all the playlists
		{
			//loops backwards so removing an item doesn't skip the next one
			for(int j = playlists.get(i).getContent().size() - 1; j >= 0; j--)
			{
				AudioContent content = playlists.get(i).getContent().get(j);
				if(content.getType().equals(Song.TYPENAME) && content.equals(deletedSong))
				{
					playlists.get(i).deleteContent(j+1);//removes the song from the playlist
				}
			}
		}
		//return true;
	}
	
	/* Sorting Methods Below
	 * 
	*/
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if(a.getYear() < b.getYear())
			{
				return -1;
			}
			if(a.getYear() > b.getYear())
			{
				return 1;
			}
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
		Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator <Song>
	{
		public int compare(Song a, Song b)
		{
			if(a.getLength() < b.getLength())
			{
				return -1;
			}
			if(a.getLength() > b.getLength())
			{
				return 1;
			}
			return 0;
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(this.songs);
	}

	
	
	/*
	 * Play Content Methods
	 */

	/* 
	 * Play song from songs list
	 * given
	 */
	public void playSong(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		songs.get(index-1).play();
		//return true;
	}
	

	/*
	 * Podcast Methods 
	 */
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		if(index < 1 || index > podcasts.size())
		{
			errorMsg = "Podcast Not Found";
			return false;
		}
		podcasts.get(index-1).selectSeason(season);
		podcasts.get(index-1).selectEpisode(episode);
		podcasts.get(index-1).play();
		return true;  
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{	
		if(index < 1 || index > podcasts.size())
		{
			errorMsg = "Podcast Not Found";
			return false;  
		}
		podcasts.get(index-1).selectSeason(season); //Selects the season, by going through the podcast list, to the podcast object then using select season method
		podcasts.get(index-1).printTOC(); //Method to print out Table of contents
		return true;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException
	{
		if(index < 1 || index > audiobooks.size())
		{
			errorMsg = "Audio Book Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		this.audiobooks.get(index-1).selectChapter(chapter); //index of the audio book isn't indexed yet - selectChapter will index chapter
		this.audiobooks.get(index-1).play();
		//return true; 
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{	
		if(index < 1 || index > audiobooks.size())
		{
			errorMsg = "Audiobook Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		audiobooks.get(index-1).printTOC(); //printToc will enter a for loop to print aslong as the book exists 
		//return true;
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws AudioContentNotFoundException
	{
		for(int i = 0; i < playlists.size(); i++)
		{
			if(playlists.get(i).getTitle().equals(title))
			{
				errorMsg = "Playlist " + title + " already exists";
				throw new AudioContentNotFoundException(errorMsg);
				//return false;
			}
		}
		playlists.add(new Playlist(title));
		//return true;
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) throws AudioContentNotFoundException
	{
		if(!playlists.contains(new Playlist(title)))
		{
			errorMsg = "Playlist Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		playlists.get(playlists.indexOf(new Playlist(title))).printContents();
		//return true;
	}
	
	// Play all content in a playlist
	/*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Plays the specific playlist requested
     *  Input: The specific playlist name
     *  Output: If the playlist exists in the ArrayList of playlists, true of false 
     */
	public void playPlaylist(String playlistTitle) throws AudioContentNotFoundException
	{
		if(!playlists.contains(new Playlist(playlistTitle)))
		{
			errorMsg = "Playlist Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		else
		{
			int playlistIndex = playlists.indexOf(new Playlist(playlistTitle));
			System.out.println(playlistTitle);
			playlists.get(playlistIndex).playAll();
		}
		//return true;
	}
	
	// Play a specific song/audiobook in a playlist
	/*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Prints the song/audiobook/podcast of a specific content in the playlist
     *  Input: Playlist name, index of the specific content
     *  Output: True of false if the playlist exists
     */
	public void playPlaylist(String playlistTitle, int indexInPL) throws AudioContentNotFoundException
	{
		if(!playlists.contains(new Playlist(playlistTitle)))//checks if playlist exists
		{
			errorMsg = "Playlist Not Found";
			throw new AudioContentNotFoundException(errorMsg);
			//return false;
		}
		else
		{
			int playlistIndex = playlists.indexOf(new Playlist(playlistTitle));
			playlists.get(playlistIndex).play(indexInPL);//plays the content
		}
		//return true;
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	// Improvements: add an in statement to check if the playlists exist in the first place
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws AudioContentNotFoundException  
	{
		if(playlists.contains(new Playlist(playlistTitle)))
		{
			if(type.toUpperCase().equals(Song.TYPENAME))//checks if content is a song
			{
				if(index < 1 || index > songs.size())
				{
					errorMsg = "Song Not Found";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
				playlists.get(playlists.indexOf(new Playlist(playlistTitle))).addContent(this.songs.get(index-1));//adds the song to the playlist
				//return true;
			}	
			else if(type.toUpperCase().equals(AudioBook.TYPENAME))//checks if content is a audiobook
			{
				if(index < 1 || index > audiobooks.size())
				{
					errorMsg = "Audio Book Not Found";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
				playlists.get(playlists.indexOf(new Playlist(playlistTitle))).addContent(this.audiobooks.get(index-1));//adds the audiobook to the playlist
				//return true;
			}
			else if(type.toUpperCase().equals(Podcast.TYPENAME))//checks if content is a podcast
			{
				if(index < 1 || index > podcasts.size())
				{
					errorMsg = "Podcast Not Found";
					throw new AudioContentNotFoundException(errorMsg);
					//return false;
				}
				playlists.get(playlists.indexOf(new Playlist(playlistTitle))).addContent(this.podcasts.get(index-1));//adds the podcast to the playlist
				//return true;
			}
		}
		else
		{
			errorMsg = "Playlist Not Found";
			throw new AudioContentNotFoundException(errorMsg);
		}
		//return true;
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	/*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Deletes a specific content from the playlist, by getting the index of the content and name of the playlist
     *  Input: 
     *  Output: 
     */
	public void delContentFromPlaylist(int index, String title)throws AudioContentNotFoundException
	{
		if(playlists.contains(new Playlist(title)))//checks if the playlist exists
		{
			if(playlists.get(playlists.indexOf(new Playlist(title))).contains(index))//checks if the playlist contains the content
			{
				playlists.get(playlists.indexOf(new Playlist(title))).deleteContent(index);//removes the content
				//return true;
			}
			else
			{
				errorMsg = "Content Not Found";
				throw new AudioContentNotFoundException(errorMsg);
			}
		}
		else
		{
			errorMsg = "Playlist Not Found";
			throw new AudioContentNotFoundException(errorMsg);
		}
		//return false;
	}
	
}

//CUSTOM EXCEPTION 
class AudioContentNotFoundException extends RuntimeException
{
    public AudioContentNotFoundException()
    {

    }

    public AudioContentNotFoundException(String message)
    {
        super(message);
    }
}
