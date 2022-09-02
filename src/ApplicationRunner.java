import java.util.*;
import java.io.*;

//Album class having all the attributes and accessor methods.
class Album
{
    //In this class we have the attributes for album.
	private String rank;
	private String title;
	private String artist;
	private String year;
	private String sales;
	private String [] tracks = new String[10000];
	
//	These are the checker varaible for the program
	private int counter = 0;	
	private int albumsSize = 0 ;
	static int albumCounter = 0;
	int trackSize = 0;
	int checker = 0;
	static int totalAlbums=0;
	
	// Constructor that will load all the album text from file using load method and the
        //The album counter will count all the albums.
	Album()
	{
		albumCounter++;
		load();
	}
	
	//This will return rank of the album
	public String getRank()
	{
		return rank;
	}
        //This will return title of the album
	public String getTitle()
	{
		return title;
	}
        //This will return artist of the album
	public String getArtist()
	{
		return artist;
	}
        //This will return year of the album
	public String getYear()
	{
		return year;
	}
        //This will return sales of the album
	public String getSales()
	{
		return sales;
	}
	
	
	//toString Method that will override the object of the class and will return record of an album.
	public String toString()
	{
		
		return rank+":"+title+":"+artist+":"+year+":"+sales+"\n"+getTracks();
		
	}
	
	
	// The load method which will load all the albums from the file and will display to user.
	public void load()
	{
		FileReader fr ;
		BufferedReader br ;
		try
		{
			fr = new FileReader("albums.txt") ;
			br = new BufferedReader(fr) ;
			String line = br.readLine() ;
			//if line is not null keep going
			while(line!=null)
			{
                            //counter will be 0 for every album which means that we will get data for every album when 
                            //counter is 0 otherwise that will not be an album i.e that will be tracks
				if(counter==0)
				{
					String tokens[] = line.split(":") ;
					rank = tokens[0];
					title = tokens[1];
					artist = tokens[2];
					year = tokens[3];
					sales = tokens[4];
					
                                        //here albumsSize will be incremented for every album and counter will alse
					counter++;
					albumsSize++;
				}
				else
				{
                                    //Here counter will not be 0 which means that we are on the lines of tracks
                                    //We will store line which is one track to tokens
					String tokens = line;
					
					//if this condition is true then it means that we have been moved to next album
					if(tokens.equals("----------------------------------------------------------------------------------"))
					{
                                            //Here we will again set counter to 0
						counter=0;
                                                //Here we will break the loop if albums reaches to end
						if(albumsSize==albumCounter)
						{
							break;
						}
					}
				}
				line = br.readLine() ;
			}
			br.close();
			fr.close();
		}
		catch(IOException ie)
		{
			System.out.println(ie.getMessage());
		}
				
	}
	
	//This will add sufficient spaces after title.
	public static String titleSpaces(String value)
	{
		for(int i = value.length(); i<77; i++ )
		{
			value += " ";
		}
		return value;
	}
	
	//This will add sufficient spaces after artist.
	public static String artistSpaces(String value)
	{
		
		for(int i = value.length(); i<26; i++ )
		{
			value += " ";
		}
		return value;
	}
	
	
	//This will load all the tracks of an album.
	public String getTracks()
	{
		String value = "";
                //this forRankOne is for the first album
		int forRankOne = 0;
                //rankCounter is the counter for albums
		int rankCounter = 0;
		int trackCounter = 0;
		boolean flag = false;
		FileReader fr ;
		BufferedReader br ;
		try
		{
			fr = new FileReader("albums.txt") ;
			br = new BufferedReader(fr) ;
			String line = br.readLine() ;
			// it will run until line is not equal to null
			while(line!=null)
			{
                            //in this if line line is not equal to this then it will run because if line is equal to this conditional line
                            //then it would mean next album.
				if(!line.equals("----------------------------------------------------------------------------------"))
				{
                                    //forRankOne is for first album and this condition is for album one tracks to be shown in next condition
					if(Integer.parseInt(rank)==1 && forRankOne==0)
					{
						line = br.readLine();
						forRankOne=1;
					}
                                        //rankCounter will be incremented for every album
                                        //rankCounter and rank if both are equal so then it will return the tracks for 
                                        //the album
					if(rankCounter==Integer.parseInt(rank)-1)
					{
                                            //splitting for minutes and seconds on : 
						String tokens[] = line.split(":");
						String min = tokens[0];
						String sec = tokens[1];
						
                                                //min is for minutes and sec is for seconds after splitting the line on : for every track
						min = min.charAt(min.length()-1)+"";
						sec = sec.charAt(0)+""+sec.charAt(1);
						//Here splitting the line on ( to take all the data of track before the ( because after this we have only seconds and minutes
						String tokens1[] = line.split("\\(");
						
						String track = tokens1[0];
                                                //this condition is only for the spaces indendation
						if(trackCounter>8)
						{
                                                    //trackCounter is incrementing here
							value += "|" + ++trackCounter+"  |"+titleSpaces(track)+"|   "+min+"|   "+sec+"|"+"\n";
						}
						else
						{
                                                    //trackCounter is incrementing here
							value += "|" + ++trackCounter+"   |"+titleSpaces(track)+"|   "+min+"|   "+sec+"|"+"\n";
						}
					}
				}
				else
				{
					line = br.readLine();
					rankCounter++;
				}
				
				
				line = br.readLine();

			}
			
			br.close();
			fr.close();
		}
		catch(IOException ie)
		{
			System.out.println(ie.getMessage());
		}
                //At last we will return the complete data of the track which was assigned to value
		return value;
	}
}

/* This is the main class which have main method for running the applications*/
public class ApplicationRunner
{
    
    public static void main(String[] args) 
    {
        Scanner inp = new Scanner(System.in);
       //This is arraylist for all the albums 
        ArrayList <Album> albums = new ArrayList<Album>();
       
        // This will add all the albums from file to an arraylist data structure.
        for(int i = 0 ;i<20 ;i++)
        {
        	Album obj = new Album();
        	albums.add(obj);
        }
        int choice = 1;
        
        /*This is the menu for the program in which user have to choose for operations*/
        do
        {
        	System.out.println("\nList albums........ 1");
        	System.out.println("Select album........ 2");
        	System.out.println("Search titles........ 3");
        	System.out.println("Exit........ 0\n");
        	
        	System.out.print("Enter choice:> ");
        	choice = inp.nextInt();
        	System.out.println("");
        	
        	switch(choice)
            {
            	case 1 :  // It will show all the list of albums when user select case 1;
	            		System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------");
	            		System.out.println(" | Rank | Title                                                                  	| Artist                     | Year 	| Sales |");
	            		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
	            		for(int i = 0 ; i<20 ; i++)
	            		{
                                        //this is for albums with rank number 10 or greater to have same spaces as of less than 9 ranks in output
	            			if(i > 8)
	            			{
	            				System.out.println(" |  "+ albums.get(i).getRank()+"  | " + Album.titleSpaces(albums.get(i).getTitle())+" | " + Album.artistSpaces(albums.get(i).getArtist())+" | " + albums.get(i).getYear() +"	| "+ albums.get(i).getSales()+"	| ");
	            			}
	            			else
	            			{
	            				System.out.println(" |  "+ albums.get(i).getRank()+"   | " + Album.titleSpaces(albums.get(i).getTitle())+" | " + Album.artistSpaces(albums.get(i).getArtist())+" | " + albums.get(i).getYear() +"	| "+ albums.get(i).getSales()+"	| ");
	            			}
	            			
	            		}
	            		System.out.println("-------------------------------------------------------------------------------------------------------------------------------\n");
	            		break;
            	
            	case 2 : //It will display all the tracks from a particular album to the user upon selecting the choice.
            			System.out.print("Enter album rank from list [1 - 20] :> ");
            			int albumRank = inp.nextInt();
            			int i = albumRank - 1;
            			System.out.println("");
            				
                                //This is StringBuilder class as shown in the requirements for using ... it will append all values and at last will output that
            			StringBuilder str = new StringBuilder();
            			
            			str.append("Album Title : \t\t"+ albums.get(i).getTitle()+"\n");
            			str.append("Artist : \t\t"+ albums.get(i).getArtist()+"\n");
            			str.append("Year of release : \t"+ albums.get(i).getYear()+"\n");
            			str.append("Sales to date : \t"+ albums.get(i).getSales()+"\n\n");
            			str.append("--------------------------------------------------------------------------------------------------"+"\n");
            			str.append("|No. |Title 									   |Mins|Secs |"+"\n");
            			str.append("--------------------------------------------------------------------------------------------------\n");
            			str.append(albums.get(i).getTracks()+"\n");
            			str.append("--------------------------------------------------------------------------------------------------\n");
            			
            			System.out.println(str.toString());
            			
            			break;
            			
            	case 3 : // This will search the tracks in the albums and will show to the user when user search for a particular track.
            			System.out.print("Enter search word or phrase > ");
            			String search = inp.next();
            			System.out.println("\n-------------");
            			
            			boolean printHead = false;
            			boolean flag = false;
            			
                                //This loop is to retrieve albums one by one
            			for(int j = 0; j < Album.albumCounter; j++)
            			{
            				printHead = true;
                                        //It is to get all the tracks of the album
        				String track = albums.get(j).getTracks();
                                        //Splitting all the albums on next line
            				String tokens[] = track.split("\\r?\\n");
                                        //this will run upto the length of the tracks
            				for(int l = 0 ; l<tokens.length; l++)
            				{
            					//These following three lines will get first word of each track and then will convert that to lower case
                                                //and then check that with the searched word
            					String strOrig = tokens[l];
            					strOrig = strOrig.toLowerCase();
            					int intIndex = strOrig.indexOf(search);
                                                //if intIndex is not equal to -1 then it means that track word matched with the searched word
    							if(intIndex != - 1) 
    							{
                                                            //printHead is initially true so it will go inside this.
    								if(printHead)
    								{
                                                                    // This will print all detail of the album and of the track where it found the searched word
    									System.out.println("\nArist	("+ albums.get(j).getArtist()+")	Album  ("+albums.get(j).getTitle()+")" );
    									System.out.println("Matching song title(s) : \n------------");
    									printHead = false;
    								}
                                                                //This will print the founded words in capital as per requirements
    								String [] tokens1 = strOrig.split("\\|"); 
    								System.out.println("Track  "+tokens1[1]+"."+" "+tokens1[2].toUpperCase());
    								
    							}
    						}
            			}
            			
            		break;
            	case 0 :
            		System.exit(0);
            		break;
            		
            	default:
            		System.out.println("Please Enter correct Choice...");
            }
        	
        }
        while(choice!=0);

    } // End of main method
    
}// End of main class.
