package projects.file_management;

public class UtilityExecutor {
	public static void main(String[] args) {
		CustomFileUtilities fileUtil = new CustomFileUtilities();
		Thread t1 = new Thread(fileUtil.readFile("sample.txt"));
		Thread t2 = new Thread(fileUtil.searchString("sample.txt", "per"));
		Thread t3 = new Thread(fileUtil.traverseDirectory("FileUtilityProject"));
		Thread t4 = new Thread(fileUtil.copyFile("Sun illustration.jpeg", "destination.jpeg"));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join(); 
			t3.join();
			t4.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}