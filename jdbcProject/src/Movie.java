
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Movie {
    private String id = new String();
    private String name = new String();
    private String actors = new String();
    private String dor = new String();
    private String genre = new String();
    private String director = new String();
    private String boxcollection = new String();

    private String driver = null;
    private String user = null;
    private String password = null;
    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    Scanner sc = null;

    Movie() throws ClassNotFoundException, SQLException {
        driver = "jdbc:mysql://localhost:3306/flat2dbms";
        user = "root";
        password = "";

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(driver, user, password);
        stmt = con.createStatement();

        sc = new Scanner(System.in);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getActors() {
        return actors;
    }
    
    public String getDor() {
        return dor;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getBoxcollection() {
        return boxcollection;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setActors(String actors) {
        this.actors = actors;
    }
    
    public void setDor(String dor) {
        this.dor = dor;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    public void setBoxcollection(String boxcollection) {
        this.boxcollection = boxcollection;
    }
    
    void input() {
        System.out.print("Enter movie name : ");
        name = sc.nextLine();
        System.out.print("Enter actors : ");
        actors = sc.nextLine();
        System.out.print("Enter date of release : ");
        dor = sc.nextLine();
        System.out.print("Enter genre : ");
        genre = sc.nextLine();
        System.out.print("Enter director : ");
        director = sc.nextLine();
        System.out.print("Enter box office collection : ");
        boxcollection = sc.nextLine();
    }
    
    void displayRecords() throws SQLException {
        rs = stmt.executeQuery("select * from movie");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("|%5s |%10s |%10s |%15s |%15s |%20s |%15s |\n", "ID", "Name", "Actors", "Release Date", "Genre", "Director", "Box Office");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        while(rs.next()) {
            id = rs.getString("id");
            name = rs.getString("name");
            actors = rs.getString("actors");
            dor = rs.getString("dor");
            genre = rs.getString("genre");
            director = rs.getString("director");
            boxcollection = rs.getString("boxcollection");

            System.out.printf("|%5s |%10s |%10s |%15s |%15s |%20s |%15s |\n", id, name, actors, dor, genre, director, boxcollection);
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
    }

    void displayRecords(String id) throws SQLException {
        rs = stmt.executeQuery("select * from movie where id = " + id);
        rs.next();
        id = rs.getString("id");
        name = rs.getString("name");
        actors = rs.getString("actors");
        dor = rs.getString("dor");
        genre = rs.getString("genre");
        director = rs.getString("director");
        boxcollection = rs.getString("boxcollection");

        System.out.println("ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("Actors : " + actors);
        System.out.println("Date of Release : " + dor);
        System.out.println("Genre : " + genre);
        System.out.println("Director : " + director);
        System.out.println("Box office Collection : " + boxcollection);
        System.out.println("-----------------------------------------------------");
    
    }

    void insertRecords() throws SQLException {
        int n;
        input();
        String sql = "insert into movie (name, actors, dor, genre, director, boxcollection) " + 
                     "values (?,?,?,?,?,?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, actors);
        pstmt.setString(3, dor);
        pstmt.setString(4, genre);
        pstmt.setString(5, director);
        pstmt.setString(6, boxcollection);

        n = pstmt.executeUpdate();

        if(n == 1) System.out.println(n + " record inserted successfully...");
        else System.out.println("Failed to insert record...");
    }

    void insertRecords(String name, String actors, String dor, String genre, String director, String boxcollection) throws SQLException {
        int n;

        String sql = "insert into movie (name, actors, dor, genre, director, boxcollection) " + 
                     "values (?,?,?,?,?,?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, actors);
        pstmt.setString(3, dor);
        pstmt.setString(4, genre);
        pstmt.setString(5, director);
        pstmt.setString(6, boxcollection);

        n = pstmt.executeUpdate();

        if(n == 1) System.out.println(n + " record inserted successfully...");
        else System.out.println("Failed to insert record...");
    }

    void updateRecords() throws SQLException{
        int n;
        String id;
        String sql;
        System.out.print("Enter record id to be updated : ");
        id = sc.nextLine();
        displayRecords(id);
        input();

        sql = "update movie set name = ?, actors = ?, dor = ?, genre = ?, director = ?, boxcollection = ? where id = ?";
        pstmt = con.prepareStatement(sql);

        pstmt.setString(1, name);
        pstmt.setString(2, actors);
        pstmt.setString(3, dor);
        pstmt.setString(4, genre);
        pstmt.setString(5, director);
        pstmt.setString(6, boxcollection);
        pstmt.setString(7, id);
        n = pstmt.executeUpdate();
        System.out.println(n + " record updated...");
    }

    void deleteRecords() throws SQLException {
        int n;
        String id;
        String sql;
        System.out.print("Enter record id to be deleted : ");
        id = sc.nextLine();

        sql = "delete from movie where id = " + id;
        n = stmt.executeUpdate(sql);

        System.out.println(n + " record deleted...");
    }

    void databaseClose() throws SQLException {
        sc.close();
        con.close();
    }
}
