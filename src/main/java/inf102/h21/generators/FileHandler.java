package inf102.h21.generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import inf102.h21.management.Job;
import inf102.h21.management.Location;

public class FileHandler {

	public static void writeToFile(int numberOfRobots, int numberOfJobs, String file, ArrayList<Location> robots,
			ArrayList<Job> jobs) throws IOException {
		//printout
		File output = new File(file);
		BufferedWriter out = new BufferedWriter(new FileWriter(output));
		out.write(String.format(Locale.US,"%d %d\n", numberOfRobots, numberOfJobs));
		for (Location location: robots) 
			out.write(String.format(Locale.US,"%f %f\n", location.x, location.y));
		for (Job job: jobs) 
			out.write(String.format(Locale.US,"%f %f %f %d\n", job.location.x, job.location.y, job.time, job.robotsNeeded));
		out.flush();
		out.close();
	}
}
