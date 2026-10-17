import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
import javax.swing.text.AbstractDocument.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		private Map<String, Integer> titleToIndex =  new HashMap<String, Integer>(); //One to content title to index value in the contents arraylist
		private Map<String, ArrayList<Integer>> artistToContent =  new HashMap<String, ArrayList<Integer>>(); 
		private Map<String, ArrayList<Integer>> genreToIndex = new HashMap<String, ArrayList<Integer>>();
		
		public AudioContentStore()
		{
			try
			{
				contents = this.fileReader();
			}
			catch(IOException exception)
			{
				System.out.print(exception.getMessage());
				System.exit(1);
			}

			for(int i = 0; i < contents.size(); i++)
			{
				titleToIndex.put(contents.get(i).getTitle(), i); //One to content title to index value in the contents arraylist
			}

			 
			//Map 2 
			//Audiocontent which will automatically be put into the adudiocontentstore via method delete multiline comment and comment out the manual to use (map2-map3 are under the same comment use them together)
			
			for(int j = 0; j < contents.size(); j++)
			{
				if(contents.get(j).getType().equals(Song.TYPENAME)) //Checks if the current content object is a song
				{
					Song tempSong  = (Song)contents.get(j); 
					//String tempArtist = tempSong.getArtist(); <- use if head hurts 
					if(artistToContent.containsKey(tempSong.getArtist())) //Checks if the artist is already a key
					{
						artistToContent.get(tempSong.getArtist()).add(j); //Access the arraylist indices of a artist and add a new indices when found
					}
					else //If the artist isn't already in the a key in the map. Add the author as a key and a arraylist with the index in which the author was found the first time
					{
						ArrayList<Integer> artistIndices = new ArrayList<Integer>();
						artistIndices.add(j);
						artistToContent.put(tempSong.getArtist(), artistIndices);	
					}
				}
				else if(contents.get(j).getType().equals(AudioBook.TYPENAME)) 	//Checks if the current content object is a audio book
				{
					AudioBook tempAudioBook = (AudioBook) contents.get(j); 
					//String tempAuthor = tempAudioBook.getAuthor(); <- u can use this in the if statements if it hurts ur head 
					if(artistToContent.containsKey(tempAudioBook.getAuthor())) 	//Checks if the author is already a key
					{
						artistToContent.get(tempAudioBook.getAuthor()).add(j); //Access the arraylist indices of a author and add a new indices when found
					}
					else //If the artist isn't already in the a key in the map. Add the author as a key and a arraylist with the index in which the author was found the first time
					{
						ArrayList<Integer> artistIndices = new ArrayList<Integer>(); 
						artistIndices.add(j);
						artistToContent.put(tempAudioBook.getAuthor(), artistIndices);
					}
				}
			}
			//Map 3
			for(int k = 0; k < contents.size(); k++)
			{
				if(contents.get(k).getType().equals(Song.TYPENAME))
				{
					Song tempSong = (Song) contents.get(k);
					String genre = String.valueOf(tempSong.getGenre()); //Change enum genre to a string cause apparently I can
					//add to a map, to do so check if the key exist yet, if not make it exist hehe
					if(genreToIndex.containsKey(genre)) //Key exists so add to the arraylist because apparently it's mutatable 
					{
						genreToIndex.get(genre).add(k);
					}
					else
					{
						ArrayList<Integer> genreIndices = new ArrayList<Integer>();
						genreIndices.add(k);
						genreToIndex.put(genre, genreIndices); 
					}
				}
			} 
			
			//Manually added the content to store 
			/* 
			contents = new ArrayList<AudioContent>();
			
		  // Create some songs audiobooks and podcasts and to store
		   
			String file = "Yesterday, all my troubles";
			contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
			
			file = "I'm sorry if I seem uninterested\r\n"
					+ "Or I'm not listenin' or I'm indifferent\r\n"
					+ "Truly, I ain't got no business here\r\n"
					+ "But since my friends are here, I just came to kick it\r\n"
					+ "But really I would rather be at home all by myself not in this room\r\n"
					+ "With people who don't even care about my well being";
			contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			file = "Yo, Big Shaq, the one and only\r\n"
					+ "Man's not hot, never hot\r\n"
					+ "Skrrat (GottiOnEm), skidi-kat-kat\r\n"
					+ "Boom\r\n"
					+ "Two plus two is four\r\n"
					+ "Minus one that's three, quick maths\r\n"
					+ "Everyday man's on the block\r\n"
					+ "Smoke trees (Ah)";
			contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah", Song.Genre.RAP, file));
			
			file = "The world was on fire and no one could save me but you\r\n"
					+ "It's strange what desire will make foolish people do\r\n"
					+ "I never dreamed that I'd meet somebody like you\r\n"
					+ "And I never dreamed that I'd lose somebody like you";
			contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak", Song.Genre.ROCK, file));
			
			file = "The lights go out and I can't be saved\r\n"
					+ "Tides that I tried to swim against\r\n"
					+ "Have brought me down upon my knees\r\n"
					+ "Oh, I beg, I beg and plead\r\n"
					+ "Singin' come out of things un said";
			contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin", Song.Genre.ROCK, file));
			
			file = "I'm waking up to ash and dust\r\n"
					+ "I wipe my brow and I sweat my rust\r\n"
					+ "I'm breathing in the chemicals";
			contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons", "Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));
			
			file = "Birds flying high\r\n"
					+ "You know how I feel\r\n"
					+ "Sun in the sky\r\n"
					+ "You know how I feel\r\n"
					+ "Breeze driftin' on by\r\n"
					+ "You know how I feel\r\n"
					+ "It's a new dawn\r\n"
					+ "It's a new day\r\n"
					+ "It's a new life\r\n"
					+ "For me";
			contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone", 
					"Anthony Newley, Leslie Bricusse",Song.Genre.JAZZ, file));
			
			file = "Find table spaces, say your social graces\n"
					+ "Bow your head, they're pious here\n"
					+ "But you and I, we're pioneers, we make our own rules\n"
					+ "Our own room, no bias here";
			contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
					"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
			contents.add(book);
			
			book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME,  "", 1422,
					"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
			contents.add(book);
			
			book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME,  "", 3213,
					"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
			contents.add(book);
			
			// Create a podcast object if you are doing the bonus see the makeSeasons() method below
			// It is currently commented out. It makes use of a class Season you may want to also create
			// or change it to something else
			Podcast podcast = new Podcast("The Secret Life of Canada", 2021, "865", Podcast.TYPENAME, "", 0, "Leah-Simone Bowen, Falen Johnson", makeSeasons());
			contents.add(podcast);
			*/
		}
		
		//Given
		public AudioContent getContent(int index) 
		{
			if (index < 1 || index > contents.size())
			{	
				return null;
			}
			return contents.get(index-1);
		}
		
		//Given
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		//Given
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		//Given
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		//Given
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}

		//Given
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		//Given
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		//Given
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}
		
		// Podcast Seasons - Given
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}

		/*
		 * Makes the contents ArrayList from a taking in a file 
		 * Specifically will take a formated file called store.txt and use it to create audiocontent for the store. 
		 * Only creates songs and audiobooks. Podcasts need to be implemented still 
		 */
		private ArrayList<AudioContent> fileReader() throws FileNotFoundException
		{
			File inputFile = new File("store.txt");
			Scanner scanner = new Scanner(inputFile);
			ArrayList<AudioContent> result = new ArrayList<AudioContent>();
			while(scanner.hasNextLine())
			{
				String type = scanner.nextLine();						//1
				if(type.equals(Song.TYPENAME))
				{
					String id = scanner.nextLine();						//2
					String title = scanner.nextLine();					//3
					int year = Integer.parseInt(scanner.nextLine());	//4
					int length = Integer.parseInt(scanner.nextLine());	//5
					String artist = scanner.nextLine();					//6
					String composer = scanner.nextLine();				//7
					String genre = scanner.nextLine();					//8
					//Lyrcis obtaining
					int numOfLines = Integer.parseInt(scanner.nextLine());	//9
					String lyrics = "";
					for(int i = 0; i < numOfLines; i++)
					{
						lyrics += scanner.nextLine() + "\n";
					}
					Song song = new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.valueOf(genre), lyrics);
					result.add(song);
				}
				else if(type.equals(AudioBook.TYPENAME))
				{
					String id = scanner.nextLine();						//2
					String title = scanner.nextLine();					//3
					int year = Integer.parseInt(scanner.nextLine());	//4
					int length = Integer.parseInt(scanner.nextLine());	//5
					String author = scanner.nextLine();					//6
					String narrator = scanner.nextLine(); 				//7
					//Chapter Section
					int numChapters = Integer.parseInt(scanner.nextLine()); //8
					ArrayList<String> chapterTitles = new ArrayList<String>();  
					for(int i = 0; i < numChapters; i++)
					{
						chapterTitles.add(scanner.nextLine());
					}
					String fileChapterLines = "";
					ArrayList<String> chapterLines = new ArrayList<String>();
					for(int j = 0; j < numChapters; j++)
					{
						int numChapterLines = Integer.parseInt(scanner.nextLine());
						for(int k = 0; k < numChapterLines; k++)
						{
							fileChapterLines += scanner.nextLine() + "\r\n";
						}
						chapterLines.add(fileChapterLines);
						fileChapterLines =  "";
					}
					AudioBook book = new  AudioBook(title, year, id, type, "", length, author, narrator, chapterTitles, chapterLines);
					result.add(book);
				}
			}
			return result;
		}

		/*
		 * Get Methods, there's no way to access the maps outside of the class because their private
		 */
		public Map<String, Integer> getTitleToIndexMap()
		{
			return this.titleToIndex;
		}

		public Map<String, ArrayList<Integer>> getArtistToContentMap()
		{
			return this.artistToContent;
		}

		public Map<String, ArrayList<Integer>> getGenreToIndexMap()
		{
			return this.genreToIndex; 
		}
		

		/*
		 * Search Methods
		 */
		//Searchs for a genre and returns the value (index in contents) using a map
		public Integer searchTitle(String title) throws AudioContentNotFoundException
		{
			if(this.titleToIndex.containsKey(title))
			{
				return this.titleToIndex.get(title);
			}
			else
			{
				throw new AudioContentNotFoundException("No matches for " + title);
			}
		}

		//Searchs for a artist and returns the value (Indices in contents where artist appears) using a map
		public ArrayList<Integer> searchArtist(String artist) throws AudioContentNotFoundException
		{
			if(this.artistToContent.containsKey(artist))
			{
				return this.artistToContent.get(artist);
			}
			else
			{
				throw new AudioContentNotFoundException("No matches for " + artist);
			}
		}

		//Searchs for a genre and returns the value (Indices in contents where the song genre appears) using a map
		public ArrayList<Integer> searchGenre(String genre) throws AudioContentNotFoundException
		{
			if(this.genreToIndex.containsKey(genre))
			{
				return this.genreToIndex.get(genre);
			}
			else
			{
				throw new AudioContentNotFoundException("No matches for " + genre);
			}
		}

		//Prints the info of a content at a specific index
		public void printIndex(int a)
		{
			int index = a + 1;
			System.out.print("" + index + ". ");
			contents.get(a).printInfo();
			System.out.println();
		}

		//Prints the info of all the content given their indices (ArrayList<Integer>) in the contents arraylist
		public void printIndex(ArrayList<Integer> a)
		{
			for(int i = 0; i < a.size(); i++)
			{
				printIndex((a.get(i)));
				/*
				int index = a.get(i) + 1;
				System.out.print("" + index + ". ");
				contents.get(a.get(i)).printInfo();
				System.out.println();
				*/
			}
		}

		//Searchs all of the content's datafields to see if a part of a string appears
		public ArrayList<Integer> searchP(String match) throws AudioContentNotFoundException
		{	
			ArrayList<Integer> result = new ArrayList<Integer>();
			for(int i = 0; i < contents.size(); i++)
			{
				AudioContent temp = contents.get(i);
				if(temp.getType().equals(Song.TYPENAME))
				{
					Song tempSong = (Song) temp;
					if(tempSong.getArtist().contains(match))
					{
						result.add(i);
					}
					else if(tempSong.getAudioFile().contains(match))
					{
						result.add(i);
					}
					else if(tempSong.getComposer().contains(match))
					{
						result.add(i);
					}
					else if(String.valueOf(tempSong.getGenre()).contains(match))
					{
						result.add(i);
					}
					else if(tempSong.getId().contains(match))
					{
						result.add(i);
					}
					else if(tempSong.getLyrics().contains(match))
					{
						result.add(i);
					}
					else if(tempSong.getTitle().contains(match))
					{
						result.add(i);
					}
 				}
				else if(temp.getType().equals(AudioBook.TYPENAME))
				{
					AudioBook tempAudioBook = (AudioBook) temp;
					if(tempAudioBook.getAudioFile().contains(match))
					{
						result.add(i);
					}
					else if(tempAudioBook.getAuthor().contains(match))
					{
						result.add(i);
					}
					else if(tempAudioBook.getId().contains(match))
					{
						result.add(i);
					}
					else if(tempAudioBook.getNarrator().contains(match))
					{
						result.add(i);
					}
					else if(tempAudioBook.getTitle().contains(match))
					{
						result.add(i);
					}
					else if(this.stringArrayListContains(match, tempAudioBook.getChapterTitles()))
					{
						result.add(i);
					}
					else if(this.stringArrayListContains(match, tempAudioBook.getChapters()))
					{
						result.add(i);
					}
				}
			}
			if(result.size() == 0)
			{
				throw new AudioContentNotFoundException("No match for " + match);
			}
			return result;
		}

		//A method for search p which allows to figure out if the string is contained in the arraylist of strings
		//Utlizes a for loop
		public boolean stringArrayListContains(String match, ArrayList<String> a)
		{
			boolean result = false;
			for(String s : a)
			{
				if(s.contains(match))
				{
					result = true;
				}
			}
			return result;  
		}
		/*
		 * Basic function to return the size of the library since the arraylist is a private element for security reasons
		 */
		public int storeSize()
		{
			return this.contents.size();
		}
}
