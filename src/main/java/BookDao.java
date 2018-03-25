import java.sql.*;

public class BookDao {

    private static final String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "kociakolka";
    private Connection connection;


    public BookDao() {


        try {
            Class.forName ("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection (URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
        } catch (SQLException e) {
            System.out.println("Couldnot establishconnection");
        }
    }

    public Book read(long id) {

        final String sql= "select id, title,  author, year, isbn from book where id = ?";
        try
        {
            PreparedStatement prepStmt= connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            ResultSet result=prepStmt.executeQuery();
            if(result.next()) {
                Book book=new Book();
                book.setId(result.getLong("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getString("isbn"));
                return book;
            }
        }
        catch (SQLException e) {
            System.out.println("Couldnot getemployee");
        }
        return null;
    }

    public void update(Book book) {
        final String sql="update book set title=?, author=?, year=?, isbn=? where id = ?";
        try
        {PreparedStatement prepStmt =connection.prepareStatement(sql);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setInt(3, book.getYear());
            prepStmt.setString(4, book.getIsbn());
            prepStmt.setLong(5, book.getId());
            prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Could not update record");
            e.printStackTrace();
        }
    }

    public void save (Book book) {
        final String sql="insert into book (title, author, year, isbn ) values (?, ?, ?,?)";
        try
        {
            PreparedStatement prepStmt = connection.prepareStatement (sql);
            prepStmt.setString(1,book.getTitle ());
            prepStmt.setString(2,book.getAuthor());
            prepStmt.setDouble(3,book.getYear());
            prepStmt.setString(4,book.getIsbn());
            prepStmt.executeUpdate();
        }
        catch(SQLException e ) {
            System.out.println( "Could not save record ");
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        final String sql= "delete from book where id = ?";
        try
        {
            PreparedStatement prepStmt= connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Couldnot deleterow");
        }
    }

    public Book findByTitle(String tytul){
        final String query="select * from book where title='"+tytul+"'";
        try
        {   Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            String   title = resultSet.getString("title");
            String   author = resultSet.getString("author");
            int   year = resultSet.getInt("year");
            String  isnb = resultSet.getString("isbn");

            Book book=new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setYear(year);
            book.setIsbn(isnb);
            return book;

        }
        catch(SQLException e ) {
            System.out.println( "Could not save record ");
            e.printStackTrace();
        }

      return  null;
    }


    public void close() {
        try {
            connection.close();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }



}

