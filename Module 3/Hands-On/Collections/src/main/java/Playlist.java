import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Plays a list of songs.
 *
 */
public class Playlist {

	/** Calculates the total running time of a given list of songs. */
	public static int runningTime(List<Song> playlist) {

		//Variable representing the total runtime of all songs in the playlist.
		int totalRunTime = 0;

		//Iterates through the playlist and adds the runtime of each song to the total runtime.
		for (Song song : playlist) {
			totalRunTime += song.getDuration();
		}

		//Returns the total runtime of all songs in the playlist.
		return totalRunTime;

	}

	/** Drives execution. */
	public static void main(String[] args) {

		List<Song> playlist = new Vector<>();

		playlist.add(new Song("The Weight", "The Band", 1968, 274));
		playlist.add(new Song("Stairway to Heaven", "Led Zeppelin", 1971, 482));
		playlist.add(new Song("Hotel California", "The Eagles", 1977, 390));
		playlist.add(new Song("With or Without You", "U2", 1987, 296));

		for (Song song : playlist) {
			song.play();
		}

		System.out.print("Total running time: ");
		System.out.print(runningTime(playlist));
		System.out.println(" seconds. ");

	}
}
