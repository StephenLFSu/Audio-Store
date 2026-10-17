import java.util.ArrayList;

public class Podcast extends AudioContent
{
	public static final String TYPENAME =	"PODCAST";

    private String host; //host name
    private ArrayList<Season> seasons; //the seasons of the podcast
    private int currentSeason = 0; 
    private int currentEpisode = 0;

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Constructor
     *  Input: title, year, id, type, audioFile, length, host, seasons
     *  Output: None, initializes the data fields
     */
    public Podcast(String title, int year, String id, String type, String audioFile, int length, String host, ArrayList<Season> seasons)
    {
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
    }

    public String getType()
    {
        return TYPENAME;
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Prints the info of the podcast, calls the supermethod to reduce code and podcast's own data fields, the name of the host, number of seasons
     *  Input: None
     *  Output: None, prints podcast info
     */
    public void printInfo()
    {
        super.printInfo();
        System.out.println("Host: " + this.host);
        System.out.println("Seasons: " + this.seasons.size());
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Prints the table of contents of the podcast
     *  Input: None
     *  Output: None, prints the episode title of a podcast season
     */
    public void printTOC()
    {
        for(int i = 0; i < this.seasons.get(currentSeason).episodeTitles.size(); i++)
        {
            int index = i + 1;
            System.out.println("Episode " + index + ". " + this.seasons.get(currentSeason).episodeTitles.get(i));
            System.out.println();
        }
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Prints the infomation of a podcast object
     *  Input: None
     *  Output: None, prints the current season, episode, episode title, host, title,
     */
    public void play()
    {
        super.setAudioFile(this.seasons.get(currentSeason).episodeTitles.get(currentEpisode) + "\n" + this.seasons.get(currentSeason).episodeFiles.get(currentEpisode));
        super.play();
    }
    
    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Selects the season to play and sets currentSeason
     *  Input: The index of the season reqested
     *  Output: None, sets the current season
     */
    public void selectSeason(int season)
    {
        if(season >= 1 && season <= seasons.size())
        {
            currentSeason = season - 1; //indexs the season 
        }
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Selects the episode of the current season to play, sets the variable used in the season's class arraylist of episode attributes 
     *  Input: The index of the episode requested
     *  Output: None, sets the current episode
     */
    public void selectEpisode(int episode)
    {
        if(episode >= 1 && episode <= seasons.get(currentSeason).episodeFiles.size())
        {
            currentEpisode = episode - 1;
        }
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Determine if there are two podcasts are equal, if equal the host are the same, and seasons are the same, to check if seasons are equal the equal method in seasons will be called
     *  Input: A object which can be casted into a podcast object 
     *  Output: If two podcasts are the same, true or false
     */
    public boolean equals(Object other)
    {
        Podcast otherPodcast = (Podcast) other;
        return super.equals(otherPodcast) && this.getHost().equals(otherPodcast.getHost()) && this.getSeasons().equals(otherPodcast.getSeasons());
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Accessory method, used in equals method of this class
     *  Input: None
     *  Output: String host data field
     */
    public String getHost()
    {
        return this.host;
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Accessory method, used in equals method of this class
     *  Input: None
     *  Output: ArrayList Seasons data field
     */
    public ArrayList<Season> getSeasons()
    {
        return this.seasons;
    }
}
