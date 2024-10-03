import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UtepTube {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner  = new Scanner(System.in);

		// This will be used for the looping of the menu.
		boolean exit = false;

		File myFile = new File("corpus.csv");
		Scanner fileReader = new Scanner(myFile);


		// Variable for the User Playlist
		int numberOfSongs = 0;
        int totalMin = 0;
        int totalSec = 0;
		String userPlaylist = "";
		String totalPlayTime = "";

        Scanner scanner2 = new Scanner(System.in);

		while(!exit){

			// Main Menu
			String greeting = "Welcome to UtepTube! Please select an option below to continue: ";
			System.out.println(greeting);

			String option1 = "\t1. List videos in corpus";
			System.out.println(option1);
			String option2 = "\t2. Add video to playlist";
			System.out.println(option2);
			String option3 = "\t3. View playlist";
			System.out.println(option3);
			String option4 = "\t4. Clear playlist";
			System.out.println(option4);
			String option5 = "\t5. Close UtepTube";
			System.out.println(option5);

			int choice = scanner.nextInt();

			// Shows list of videos
			if(choice == 1){

				// Formatting List
				Scanner fileScanner1 = new Scanner(myFile);
				fileScanner1.useDelimiter(",");
				System.out.println("+-----------------------------------------------------------------------------------------------+");
				System.out.println("|                                        UtepTube corpus                                        |");
				System.out.println("+-------------+---------------------------------------------------+---------------------+-------+");

				// loop
				while(fileScanner1.hasNextLine()){
					String line = fileScanner1.nextLine();
					Scanner lineReader = new Scanner(line).useDelimiter(",");
					String videoID = lineReader.next();
					String videoTitle = lineReader.next();
					String creator = lineReader.next();
					String mm = lineReader.next();
					String ss = lineReader.next();

					String newLine = "|"+videoID+"  |"+videoTitle+"  |"+creator+"  | "+mm+":"+ss+" |";
					System.out.println(newLine);
					lineReader.close();
				System.out.println("+-------------+---------------------------------------------------+---------------------+-------+");
				}	

			// Part 2
        	} else if (choice == 2){ 

        		// Asks for the video ID
	        	System.out.println("Please enter video ID to add to the playlist: ");
        		String vidId = scanner2.nextLine();

        		// Scan our file again
        		File myFile2 = new File("corpus.csv");
				Scanner fileReader2 = new Scanner(myFile2);

        		//loop
        		while(fileReader2.hasNextLine()){

					//check if song exists
					String line = fileReader2.nextLine();
					Scanner lineReader2 = new Scanner(line).useDelimiter(",");

					//song info
					String videoID = lineReader2.next();
					String videoTitle = lineReader2.next();
					String creator = lineReader2.next();
					String mm = lineReader2.next();
					String ss = lineReader2.next();
					String prAd = lineReader2.next();
					String mrAd = lineReader2.next();
					String poAd = lineReader2.next();
					creator = creator.trim();
					String videoFinal = videoTitle + "\n" + creator;

	
					// Checking if Video ID matches
        			if(vidId.equals(videoID)){
        				System.out.println("\nExcellent Choice! You have added: \n"+videoFinal);
        				System.out.println("\n");

						// create link for playlist
						String songInfoAds = "";
						String link = "https://youtu.be/" + vidId;
						String time = mm + ":" + ss;

						// Adding up the minutes and secods for out total play variable
						int intMin = Integer.valueOf(mm);
						totalMin += intMin;
						int intSec = Integer.valueOf(ss);
                    	totalSec += intSec;

                    	// This is for out Pre, mid, post ads
						String pre = "";
						if(prAd.equals("true")){
						pre = "+30s preroll";
						}

						String mid = "";
						if(mrAd.equals("true")){
							System.out.println("Do you want a 10s midroll Ad? (true/false)");
							boolean choice2 = scanner2.nextBoolean();

							if(choice2){
								mid = "10s midroll";
							} else{
								mid = "2m midroll";
							}
						}

						String post = "";
						if(poAd.equals("true")){
							post = "+5s postroll";
						}

						//single song to be added
						songInfoAds = "1." + link + "| "+  time + " "+ "( " + pre + " " + mid + " " + post + " )";

						//Adding all the total play time
						totalPlayTime = "Total Play Time: " + "0:" + totalMin + ":" + totalSec;

						//add link to existing user playlist
						userPlaylist = userPlaylist + "\n" + songInfoAds;
						System.out.println("Video has been added to your playlist.");

						// Keeps track of how many songs have been added
						numberOfSongs+=1;
        			}
        		fileReader.close();
        		}

        	} else if(choice == 3){
				//print the existing user playlist
        		System.out.println("------------- YOUR PLAYLIST ------------");
				if(numberOfSongs == 0){
					System.out.println("Total play time: 0:00:00");
				}

				else{
					System.out.println(userPlaylist.toString());
					System.out.println(totalPlayTime.toString());
				}

        	} else if(choice == 4){
        		System.out.println("Please confirm you want to clear your current playlist! (True/False)");
        		boolean choice3 = scanner2.nextBoolean();

				if(choice3){
					userPlaylist = "";
					totalPlayTime = "";
					numberOfSongs = 0;
					System.out.println("Your playlist has been cleared!");
				}

        	} else if (choice == 5){
        		System.out.println("Thank you for using UtepTube! Have a good day!:)");
        		exit = true;
        		scanner.close();

        	}
        }	
   	}

}