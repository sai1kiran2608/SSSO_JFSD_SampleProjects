package projects.file_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class CustomFileUtilities {

	// File Reading Line by Line
	public Runnable readFile(String filePath) {
		return () -> {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				System.out.println("File does not exist or is unreadable.");
			}
		};
	}

	// File Searching for a Given String
	public Runnable searchString(String filePath, String str) {
		return () -> {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					String line;
					boolean found = false;
					int lineNum = 0;
					int wordNum = 0;

					while ((line = reader.readLine()) != null) {
						lineNum++;
						String[] words = line.split(" ");
						wordNum = 0;
						for (String word : words) {
							wordNum++;
							if (word.equalsIgnoreCase(str)) {
								found = true;
								break;
							}
						}
					}
					if (found == false) {
						System.out.println("string " + "'" + str + "'" + " not found");
					} else {
						System.out.println("string " + "'" + str + "'" + " found on line number " + lineNum + " as "
								+ wordNum + " word in the line");
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				System.out.println("File is empty or does not exist.");
			}
		};
	}

	// Recursively Listing All Files in each Folder
	public Runnable traverseDirectory(String filePath) {
		return () -> {
			File folder = new File(filePath);
			if (folder.isDirectory() && folder.exists()) {
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isDirectory()) {
							System.out.println("Directory : " + file);
							traverseDirectory(file.getPath());
						} else {
							System.out.println("File : " + file);
						}
					}
				}
			}
		};
	}

	// Copying a File from Source to Destination
	public Runnable copyFile(String sourceFilePath, String destinationFilePath) {
		return () -> {
			File sourceFile = new File(sourceFilePath);
			File destinationFile = new File(destinationFilePath);

			if (!(sourceFile.exists()) || !(sourceFile.isFile())) {
				System.out.println("Source file not found.");
			}

			try (FileInputStream fis = new FileInputStream(sourceFile);
					FileOutputStream fos = new FileOutputStream(destinationFile)) {
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
				fos.flush();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}

}
