import java.util.ArrayList;

public class Season 
{
    public ArrayList<String> episodeFiles;
    public ArrayList<String> episodeTitles;  
    public ArrayList<Integer> episodeLengths;

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Constructor Method
     *  Input: Parameters: ArrayList for audiofiles, epsiode titles, and episode length in minutes
     *  Output: None, initlializes the Season object.
     */
    public Season(ArrayList<String> episodeFiles, ArrayList<String> episodeTitles, ArrayList<Integer> episodeLengths)
    {
        this.episodeFiles = episodeFiles;
        this.episodeTitles = episodeTitles;
        this.episodeLengths = episodeLengths;
    }
    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Constructor Method
     *  Input: none
     *  Output: Initlializes the season object
     */
    public Season()
    {
        this.episodeFiles = new ArrayList<String>();
        this.episodeTitles = new ArrayList<String>();
        this.episodeLengths = new ArrayList<Integer>();
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Used to check if two season objects are equal, they are equal if their episodeFiles, episodeTitles, and episodeLengths are equal
     *  Input: Object other, a object that can be casted into a season object
     *  Output: 
     */
    public boolean equals(Object other)
    {
        Season otherSeason = (Season) other;
        return this.getEpisodeFiles().equals(otherSeason.getEpisodeFiles()) && this.getEpisodeTitles().equals(otherSeason.getEpisodeTitles()) && this.getEpisodeLengths().equals(otherSeason.getEpisodeLengths());
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Asscessory Method
     *  Input: None
     *  Output: ArrayList episodeFiles
     */
    public ArrayList<String> getEpisodeFiles()
    {
        return this.episodeFiles;
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Asscessory Method
     *  Input: None
     *  Output: ArrayList episodeTitles
     */
    public ArrayList<String> getEpisodeTitles()
    {
        return this.episodeTitles;
    }

    /*  Author: Stephen Su
     *  Date: March 20, 2023
     *  Purpose: Asscessory Method
     *  Input: None
     *  Output: ArrayList episodeLengths
     */
    public ArrayList<Integer> getEpisodeLengths()
    {
        return this.episodeLengths;
    }
}
