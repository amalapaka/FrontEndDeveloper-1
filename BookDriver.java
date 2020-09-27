// --== CS400 File Header Information ==--
// Name: Amulya Malapaka
// Email: amalapaka@wisc.edu
// Team: Team AD
// TA: Sophie Stephenson
// Lecturer: Professor Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Scanner;
/**
 * Front end for the project
 * @author Amulya Malapaka
 *
 */
public class BookDriver {
	private static Scanner scnr = new Scanner(System.in);
	private static int action = 0;
	private static HashTableMap<String, Book> htm = new HashTableMap<String, Book>();
/**
 * Gives user the opportunity to add a book and makes sure the ISBN is valid
 * 	and catches inputs with the wrong type
 */
	public static void addBook() {
		try {
			scnr.nextLine();
			System.out.print("Enter title: ");
			String title = scnr.nextLine();
			System.out.print("Enter author: ");
			String author = scnr.nextLine();
			System.out.print("Enter publisher: ");
			String publisher = scnr.nextLine();
			System.out.print("Enter publication year: ");
			int publication_year = scnr.nextInt();
			System.out.print("Enter ISBN: ");
			String isbn = scnr.next();
			if(validISBN(isbn)) {
			Book book = new Book(title, author, publisher, publication_year, isbn);
			htm.put(isbn, book);
			System.out.println("Added " + book.getTitle());
		}
		else {
			System.out.println("Invalid ISBN");
			scnr.nextLine();
		}
		}
		catch (Exception e) {
			System.out.println("Wrong type!");
			scnr.nextLine();
		}
	}
	/**
	 * Gives user the opportunity to get specific information about a book and makes sure the ISBN is valid
	 * 	and catches inputs with ISBNs that have been removed
	 */
	public static void getBook() {
		try {
		System.out.print("Enter ISBN: ");
		String isbn = scnr.next();
		Book book = htm.get(isbn);
		if(validISBN(isbn)) {
			while(action != 6) {
				System.out.println();
				System.out.println("Please choose which piece of information you want!");
				System.out.println("Enter 1 for the title.");
				System.out.println("Enter 2 for the author.");
				System.out.println("Enter 3 for the publisher. ");
				System.out.println("Enter 4 for the publication year.");
				System.out.println("Enter 5 for the MLA citation.");
				System.out.println("Enter 6 to go back.");
				System.out.println();
				System.out.print("Choice: ");
				action = scnr.nextInt();
				if(action == 1) {
					System.out.println("Title: " + book.getTitle());
				} else if(action == 2) {
					System.out.println("Author: " + book.getAuthor());
				} else if(action == 3) {
					System.out.println("Publisher: " + book.getPublisher());;
				} else if(action == 4) {
					System.out.println("Publication year: " + book.getPublicationYear());
				} else if(action == 5) {
					System.out.println("MLA citation: " + book.getCitation());
				}
				else {
					break;
				}
			}
			
		}
		else {
			System.out.println("Wrong input");
			scnr.nextLine();
		}
		}
		catch (Exception e) {
			System.out.println("That ISBN is not in the library.");
			scnr.nextLine();
		}
	}
	/**
	 * Removes book and makes sure the ISBN is valid
	 * 	and catches inputs that don't exist in the library
	 */
	public static void removeBook() {
		try {
			System.out.print("Enter ISBN: ");
			String isbn = scnr.next();
			if(validISBN(isbn)) {
				Book remBook = htm.remove(isbn);
				System.out.println("Removed " + remBook.getTitle());
			}
			
		}
		catch (Exception e) {
			System.out.println("Book not found.");
			scnr.nextLine();
		}
		
	}
	/**
	 * Makes sure ISBN is valid by checking length and that the string only contain numbers and the character 'x'
	 * @param isbn - provided isbn
	 * @return - whether the isbn is valid or not
	 */
	public static boolean validISBN(String isbn) {
		if(isbn.length() != 10 && isbn.length() != 13) {
			return false;
		}
		else {
			for(int i = 0; i < isbn.length(); i++) {
				if((isbn.toLowerCase().charAt(i) < 48 || isbn.toLowerCase().charAt(i) > 57) 
						&& isbn.toLowerCase().charAt(i) != 'x') {
					return false;
				}
			}
			
		}
		return true;
	}
	/**
	 * Reads DataWrangler file and catches FileNotFoundException
	 */
	public static void readDWF(){
		try {
			DataWrangler.readInputFile(htm, "Book_data.csv");
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args)  {	
			readDWF();
			System.out.println("Welcome to the library!");
			//while loop to make sure the program doesn't take another action if 4 has been pressed
			while(action != 4) {
			System.out.println();
			System.out.println("Please choose an action!");
			System.out.println("Enter 1 to add a book.");
			System.out.println("Enter 2 to search for a book.");
			System.out.println("Enter 3 to remove a book. ");
			System.out.println("Enter 4 to quit.");
			System.out.println();
			System.out.print("Action: ");
	try {
		
			action = scnr.nextInt();
			if(action == 1) {
				addBook();
			} else if(action == 2) {
				getBook();
			} else if(action == 3) {
				removeBook();
			}
			else {
				System.out.println("Thanks for visiting! Goodbye!");
				System.exit(0);
			}
		}
		
		catch (Exception e) {
			System.out.println("That entry is not valid!");
			scnr.nextLine();
		}
			}
	}
}
