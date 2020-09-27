import java.util.Scanner;

public class BookDriver {
	private static Scanner scnr = new Scanner(System.in);
	private static int action = 0;
	private static BackEndHash bh = new BackEndHash();

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
			bh.add(book);
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
	public static void getBook() {
		try {
		System.out.println("Enter ISBN");
		String isbn = scnr.next();
		Book book = bh.get(isbn);
		if(validISBN(isbn)) {
			System.out.println("Title: " + book.getTitle() + "\n Author: " + book.getAuthor()
					+ "\n Publisher: " + book.getPublisher() + "\n Publication Year: " + book.getPublicationYear());	
		}
		else {
			System.out.println("Invalid ISBN");
			scnr.nextLine();
		}
		}
		catch (Exception e) {
			System.out.println("Wrong input");
			scnr.nextLine();
		}
	}
	public static void removeBook() {
		try {
			System.out.println("Enter ISBN: ");
			String isbn = scnr.next();
			if(validISBN(isbn)) {
				Book remBook = bh.remove(isbn);
				System.out.println("Removed " + remBook.getTitle());
			}
			else {
				System.out.println("Invalid ISBN");
				scnr.nextLine();
			}
		}
		catch (Exception e) {
			System.out.println("Book not found.");
			scnr.nextLine();
		}
		
	}
	public static boolean validISBN(String isbn) {
		if(isbn.length() != 10 && isbn.length() != 13) {
			return false;
		}
		else {
			for(int i = 0; i < isbn.length(); i++) {
				if((isbn.toLowerCase().charAt(i) < 49 || isbn.toLowerCase().charAt(i) > 57) 
						&& isbn.toLowerCase().charAt(i) != 'x') {
					return false;
				}
			}
			
		}
		return true;
	}
	public static void main(String[] args) {			
			System.out.println("Welcome to the library!");
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
		DataWrangler.readInputFile(bh, "Book_data.csv");
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
