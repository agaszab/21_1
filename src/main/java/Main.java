import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

   public static void main (String[] args) throws SQLException {
        Main main = new Main();
        main.run();
    }

    private void run (){
        Book book =new Book ( "Testowa" , "o. Majka", 2011, "tfgd4");
        BookDao dao=new BookDao();
        dao.save(book);
        updateBook();
        dao.close();

    }


    private void updateBook (){
        Scanner scn=new Scanner(System.in);
        Book book =new Book();
        BookDao dao=new BookDao();
        System.out.println("Podaj tytuł książki, którą chcesz zaktualizować: ");
        String title=scn.nextLine();
        book=dao.findByTitle(title);
        System.out.println("Chcesz zaktualizować dane dotyczące książki: ");
        book.toString();
        System.out.println("Podaj tytuł: ");
        String newTitle=scn.nextLine();
        System.out.println("Podaj autora: ");
        String newAuthor=scn.nextLine();
        System.out.println("Podaj tytuł: ");
        int newYear=scn.nextInt();
        scn.nextInt();
        System.out.println("Podaj isbn: ");
        String newISBM=scn.nextLine();
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setYear(newYear);
        book.setIsbn(newISBM);
        dao.update(book);
    }
}
