  //TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;


class Restaurant{
   private String name;
   private String address;
   private String phone;
   private Map<Integer, Table> tables;
  // private Customer customer;
    Menu menu;
    public Restaurant() {
        this.menu = new Menu();// Initialize the menu when a Restaurant object is created
        this.tables = new HashMap<>();
        //this.customer = new Customer();
    }
    public Restaurant(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.menu = new Menu();
        this.tables = new HashMap<>();
    }
    void addTable(int tableNo, int seatNo){
        this.tables.put(tableNo, new Table(tableNo,seatNo));
    }
    public void displayTable() {
        for(Table table : tables.values()) {
            System.out.println("Number: "+table.getTableName()+", Capacity: "+table.numOfSeats+ ", Reserved: "+table.isReserved);
        }
    }
    public Map<Integer, Table> getTables() {
            return tables;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
}
class Menu{
    public
    Vector<Fooditem> fooditems;
    public Menu() {
        fooditems = new Vector<>();
    }
    // Method to add a food item to the menu
    public void addFoodItem(Fooditem item) {
        fooditems.add(item);
    }
    public void displayMenu() {
        for (int i = 0; i < this.fooditems.size(); i++) {
            System.out.println(i+1+"."+this.fooditems.get(i).getName()+" - "+this.fooditems.get(i).getPrice()+"$");
        }
        System.out.println("\n");
    }
    public void removeFoodItem(int itemNo) {
        if(itemNo >= 0 && itemNo < this.fooditems.size()) {
            fooditems.remove(fooditems.elementAt(itemNo-1));
        }else{
            System.out.println("No food item found, please enter a valid number");
    }
    }


}
class Fooditem{
   private String name;
public int price;
    public Fooditem(String name, int price) {
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString() {
        return name + " - $" + price;
    }
    int getPrice(){
        return price;
    }

    String getName(){
        return this.name;
    }
}
class Table{
    int numOfSeats;
    int name;
    boolean isOccupied;
    Order order;
    boolean isReserved;
    public Table(int name,int numOfSeats) {
        this.numOfSeats = numOfSeats;
        this.name = name;
        this.isOccupied = false;
        this.order = new Order();
        this.isReserved = false;

    }
    void reserve() {
        isReserved = true;
    }

    void cancelReservation(){
        isReserved = false;
    }

     void clearTableOrder(){
        this.order.clearOrder();
    }

     int getTableName() {
        return this.name;
     }

}

class Order {
    private int orderId; // Unique ID for the order
    private Vector<Fooditem> orderItems; // List of food items in the order
    private Table orderTable; // Table associated with the order
    private int orderSum; // Total cost of the order
    private static int Ids = 1; // Static counter for generating unique order IDs

    public Order() {
        this.orderItems = new Vector<>();
        this.orderId = Ids;
        Ids++; // Increment the counter
        this.orderSum = 0;// Initialize orderSum to 0
    }


    public Order(Vector<Fooditem> orderItems, Table orderTable) {
                this.orderItems = orderItems;
                this.orderTable = orderTable;
                this.orderId = Ids;
                Ids++; // Increment the counter
                calculateOrderSum(); // Calculate the total cost of the order
            }
            // Method to calculate the total cost of the order
            private void calculateOrderSum() {
                this.orderSum = 0;
                for (Fooditem item : orderItems) {
                    this.orderSum += item.getPrice();
                }
            }

            // Getter for orderId
            public int getOrderId() {
                return orderId;
    }


            public Vector<Fooditem> getOrderItems() {
                return orderItems;
            }

            // Setter for orderTable
            public void setOrderTable(Table orderTable) {
                this.orderTable = orderTable;
            }


            public int getOrderSum() {
                return orderSum;
            }

            // Method to add a food item to the order
            public void addFoodItem(Fooditem food) {
                this.orderItems.add(food);
                this.orderSum += food.getPrice(); // Update the total cost
            }


            public void displayOrder() {
                if(orderTable == null){
                    System.out.println("""
                            --------------------------\
                            
                            THIS TABLE HAS NO ORDERS
                            ----------------------------""");
                }else {
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Table: " + orderTable.getTableName()); // Assuming Table has a getTableNumber() method
                    System.out.println("Items:");
                    for (Fooditem item : orderItems) {
                        System.out.println("- " + item.getName() + " - $" + item.getPrice());
                    }
                    System.out.println("Total: $" + orderSum + "\n\n");
                }
    }
    public void clearOrder(){
        orderItems.clear();
    }
}
class Customer{
    private String name;
    private String phoneNumber;
    private Order order;
    Customer() {
        this.name = "";
        this.phoneNumber = "";
        this.order = new Order();
    }
    Customer(String name, String phoneNumber, Order order) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.order = order;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}

public class Main {
    public static void main(String[] args) {

        Restaurant restaurant1 = new Restaurant("ShazlyFoods","Maadi","01006666777");
        restaurant1.menu.addFoodItem(new Fooditem("Crispy Calamari",12));
        restaurant1.menu.addFoodItem(new Fooditem("Bruschetta al Pomodoro",9));
        restaurant1.menu.addFoodItem(new Fooditem("Grilled Salmon",22));
        restaurant1.menu.addFoodItem(new Fooditem("Filet Mignon",32));
        restaurant1.menu.addFoodItem(new Fooditem("Chicken Parmesan",18));
        restaurant1.menu.addFoodItem(new Fooditem("New York Cheesecake",8));
        restaurant1.menu.addFoodItem(new Fooditem("Chocolate Lava Cake",10));
        restaurant1.menu.addFoodItem(new Fooditem("Tiramisu",10));
        restaurant1.menu.addFoodItem(new Fooditem("Freshly Brewed Coffee",11));
        restaurant1.menu.addFoodItem(new Fooditem("Iced Tea",10));
        restaurant1.menu.addFoodItem(new Fooditem("House-Made Lemonade",3));

        restaurant1.getTables().put(51,new Table(51,5));
        restaurant1.getTables().put(52,new Table(52,5));
        restaurant1.getTables().put(31,new Table(31,3));
        restaurant1.getTables().put(41,new Table(41,4));
        restaurant1.getTables().put(42,new Table(42,4));
        restaurant1.getTables().put(21,new Table(21,2));

        System.out.println("\nWelcome To "+restaurant1.getName()+" at "+restaurant1.getAddress()+"\n-------------------------------");
        System.out.println("choose which action you need\n-------------------------------");

        int choice;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1 - Create an order
                    2 - Print order details
                    3 - Add an item to the menu
                    4 - Remove an item from the menu
                    5 - Add table
                    6 - Reserve Table
                    7 - Cancel a reservation
                    8 - Display menu
                    9 - Display all tables
                    10 - Takeaway
                    11 - Exit
                    """);

            choice = scanner.nextInt();
            while (choice < 0 || choice > 11) {
                System.out.println("Invalid choice, enter valid choice");
                choice=scanner.nextInt();
            }

            if(choice==1) {
                restaurant1.displayTable();
                System.out.println("\nenter the table name");
                Scanner scanner2 = new Scanner(System.in);
                int tableName = scanner2.nextInt();
                while(restaurant1.getTables().get(tableName) == null) {
                    System.out.println("Please enter the table name correctly");
                    tableName=scanner2.nextInt();
                }
                if (!restaurant1.getTables().get(tableName).order.getOrderItems().isEmpty()) {
                    restaurant1.getTables().get(tableName).clearTableOrder();
                }

                restaurant1.getTables().get(tableName).order.setOrderTable(restaurant1.getTables().get(tableName));
                restaurant1.menu.displayMenu();
                Scanner scanner3 = new Scanner(System.in);

                int item;
                int amount;
                while (true) {
                    System.out.println("\nitem Number:");
                    item = scanner3.nextInt();
                    if (item == -1) {
                        break;
                    }
                    System.out.println("amount:");
                    amount = scanner3.nextInt();

                    for (int i = 0; i < amount; i++) {
                        restaurant1.getTables().get(tableName).order.addFoodItem(restaurant1.menu.fooditems.get(item - 1));
                    }


                }
                System.out.println("Order ID: " + restaurant1.getTables().get(tableName).order.getOrderId());
                System.out.println("Ordered Items: " + restaurant1.getTables().get(tableName).order.getOrderItems());
                System.out.println("Order Sum: " + restaurant1.getTables().get(tableName).order.getOrderSum() + "$\n\n--------------------------");


            }else if(choice == 2) {
                restaurant1.displayTable();
                    System.out.println("\nenter the table name");
                    Scanner scanner4 = new Scanner(System.in);
                    int table = scanner4.nextInt();
                    restaurant1.getTables().get(table).order.displayOrder();

            }else if(choice == 3) {
                System.out.println("enter Food Name\n");
                Scanner scanner5 = new Scanner(System.in);
                String foodName = scanner5.nextLine();
                System.out.println("enter Food Price\n");
                int foodPrice = scanner5.nextInt();
                restaurant1.menu.addFoodItem(new Fooditem(foodName,foodPrice));
                scanner5.close();
            }
            else if(choice == 4) {
                restaurant1.menu.displayMenu();
                System.out.println(" Enter Food Item number\n");
                Scanner scanner6 = new Scanner(System.in);
                int FoodItemNum = scanner6.nextInt();
                restaurant1.menu.removeFoodItem(FoodItemNum);
            }
            else if(choice == 5) {
                System.out.println("enter the table name");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                System.out.println("enter table seat capacity");
                int capacity = scanner5.nextInt();
                restaurant1.addTable(table,capacity);
            }
            else if(choice == 6) {
                System.out.println("\nenter the table number");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                restaurant1.getTables().get(table).reserve();
            }
            else if(choice == 7) {
                System.out.println("\nenter the table number");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                restaurant1.getTables().get(table).cancelReservation();
            }
            else if(choice == 8) {
                restaurant1.menu.displayMenu();
            }
            else if(choice == 9) {
                restaurant1.displayTable();
            }
            else if(choice == 10) {
                System.out.println(
                        "Enter Customer Name"
                );
                Scanner scanner7 = new Scanner(System.in);
                String customerName=scanner.nextLine();
                scanner7.nextLine();
                System.out.println("Enter Customer phone number");
                String customerPhone=scanner.nextLine();
                restaurant1.menu.displayMenu();
                Order order1 =new Order();
                int item1;
                int amount2;
                while (true) {
                    System.out.println("\nitem Number:");
                    item1 = scanner.nextInt();
                    if (item1 == -1) {
                        break;
                    }
                    System.out.println("amount:\n");
                    amount2 = scanner.nextInt();

                    for (int i = 0; i < amount2; i++) {
                        order1.addFoodItem(restaurant1.menu.fooditems.get(item1 - 1));
                    }

                }
                Customer customer1=new Customer(customerName,customerPhone,order1);
                customer1.setName(customerName);
                System.out.println("Here are the order's details:\n");
                System.out.println("Name: "+customerName+" \nPhone Number:"+customer1.getPhoneNumber()+" Order Details: ");


                System.out.println("Order ID: " + customer1.getOrder().getOrderId());
                System.out.println("Items:");
                for (Fooditem item : customer1.getOrder().getOrderItems()) {
                    System.out.println("- " + item.getName() + " - $" + item.getPrice());
                }
                System.out.println("Total: $" + customer1.getOrder().getOrderSum() + "\n\n");

            }
            else if(choice == 11){
                break;
            }

        }
    }
}
