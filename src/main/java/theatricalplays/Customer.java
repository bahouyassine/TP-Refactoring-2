package theatricalplays;


public class Customer {
    public String name;
    public int id;
    public int credit;
    private static int lastCustomerId = 0;

    public Customer(String name){
        this.name = name;
        this.id = lastCustomerId;
        lastCustomerId++;
        this.credit =0;
   } 

}
