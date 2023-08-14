import java.util.Scanner;

public class MovieCaller {
    public void caller() {
        Scanner sc = new Scanner(System.in);
        try {
            Movie m = new Movie();
            String ch;
            String id;
            while(true) {
                System.out.println("1. Insert Records");
                System.out.println("2. Delete Records");
                System.out.println("3. Update Records");
                System.out.println("4. Display Records");
                System.out.println("5. Display 1 Record");
                System.out.println("6. Exit");
                System.out.print("Enter ur choice : ");
                ch = sc.nextLine();
                if(ch.equals("1")) m.insertRecords();
                else if(ch.equals("2")) m.deleteRecords();
                else if(ch.equals("3")) m.updateRecords();
                else if(ch.equals("4")) m.displayRecords();
                else if(ch.equals("5")) {
                    System.out.print("Enter record id to display : ");
                    id = sc.nextLine();
                    m.displayRecords(id);
                }
                else if(ch.equals("6")) {
                    m.databaseClose();
                    sc.close();
                    break;
                }
                else System.out.println("Wrong Choice...");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
