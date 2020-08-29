package dataStructures;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainClass {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("/users/nikolabaci/desktop/input.txt");
		Roster CS = new Roster(file);
		
		System.out.println("Press 0 to print the students in the BST, array & waiting list");
		System.out.println("Press 1 to add students");
		System.out.println("Press 2 to remove students");
		System.out.println("Press 3 to search students by name");
		System.out.println("Press 4 to save the list as the final roster");
		System.out.println("Press 5 to save the final roster in the file");
		
		Scanner userInput = new Scanner(System.in);
		while(userInput.hasNext()) {
			int num = Integer.parseInt(userInput.next());
			switch(num) {
			case 0: 
				System.out.println("BST:");
				CS.printRoster();
				System.out.println("---------------------");
				System.out.println("Array:");
				CS.printArray();
				System.out.println("---------------------");
				System.out.println("Waiting list:");
				CS.printWaitingList();
				break;
			case 1:
				System.out.println("To add student, enter the student as: Full Name and ID:");
				CS.append(new Student(userInput.next(), userInput.next(), userInput.nextInt()));
				break;
			case 2:
				System.out.println("To remove student, enter the student as: Full Name and ID:");
				CS.remove(new Student(userInput.next(), userInput.next(), userInput.nextInt()));
				break;
			case 3:
				System.out.println("Enter the full name of the student:");
				CS.searchStudent(userInput.next(), userInput.next()).printList();
				break;
			case 4:
				CS.createArrayRoster();
				System.out.println("Your ordered roster is: ");
				CS.printArray();
				break;
			case 6:
				CS.saveOnFile();
				break;
			default:
				System.out.println("Enter one of the numbers in the menu above.");
			}
			
		}
	}
	
}
 