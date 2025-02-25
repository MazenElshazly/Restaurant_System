//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

// Restaurant class representing a restaurant with name, address, phone, tables, and a menu
class Restaurant {
    private String name;
    private String address;
    private String phone;
    private Map<Integer, Table> tables; // Map to store tables by table number
    Menu menu;

    // Default constructor initializing the menu and tables
    public Restaurant() {
        this.menu = new Menu(); // Initialize the menu when a Restaurant object is created
        this.tables = new HashMap<>();
    }

    // Parameterized constructor to initialize restaurant details
    public Restaurant(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.menu = new Menu();
        this.tables = new HashMap<>();
    }

    // Method to add a table to the restaurant
    void addTable(int tableNo, int seatNo) {
        this.tables.put(tableNo, new Table(tableNo, seatNo));
    }

    // Method to display all tables in the restaurant
    public void displayTable() {
        for (Table table : tables.values()) {
            System.out.println("Number: " + table.getTableName() + ", Capacity: " + table.numOfSeats + ", Reserved: " + table.isReserved);
        }
    }

    // Getter for tables
    public Map<Integer, Table> getTables() {
        return tables;
    }

    // Getter for restaurant name
    public String getName() {
        return name;
    }

    // Getter for restaurant address
    public String getAddress() {
        return address;
    }

    // Getter for restaurant phone number
    public String getPhone() {
        return phone;
    }
}

// Menu class representing the menu of the restaurant
class Menu {
    public Vector<Fooditem> fooditems; // Vector to store food items

    // Default constructor initializing the food items vector
    public Menu() {
        fooditems = new Vector<>();
    }

    // Method to add a food item to the menu
    public void addFoodItem(Fooditem item) {
        fooditems.add(item);
    }

    // Method to display the menu
    public void displayMenu() {
        for (int i = 0; i < this.fooditems.size(); i++) {
            System.out.println(i + 1 + "." + this.fooditems.get(i).getName() + " - " + this.fooditems.get(i).getPrice() + "$");
        }
        System.out.println("\n");
    }

    // Method to remove a food item from the menu
    public void removeFoodItem(int itemNo) {
        if (itemNo >= 0 && itemNo < this.fooditems.size()) {
            fooditems.remove(fooditems.elementAt(itemNo - 1));
        } else {
            System.out.println("No food item found, please enter a valid number");
        }
    }
}

// Fooditem class representing a food item with name and price
class Fooditem {
    private String name;
    public int price;

    // Parameterized constructor to initialize food item details
    public Fooditem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Override toString method to return food item details
    @Override
    public String toString() {
        return name + " - $" + price;
    }

    // Getter for food item price
    int getPrice() {
        return price;
    }

    // Getter for food item name
    String getName() {
        return this.name;
    }
}

// Table class representing a table in the restaurant
class Table {
    int numOfSeats; // Number of seats at the table
    int name; // Table number
    boolean isOccupied; // Whether the table is occupied
    Order order; // Order associated with the table
    boolean isReserved; // Whether the table is reserved

    // Parameterized constructor to initialize table details
    public Table(int name, int numOfSeats) {
        this.numOfSeats = numOfSeats;
        this.name = name;
        this.isOccupied = false;
        this.order = new Order();
        this.isReserved = false;
    }

    // Method to reserve the table
    void reserve() {
        isReserved = true;
    }

    // Method to cancel the reservation of the table
    void cancelReservation() {
        isReserved = false;
    }

    // Method to clear the order associated with the table
    void clearTableOrder() {
        this.order.clearOrder();
    }

    // Getter for table name (number)
    int getTableName() {
        return this.name;
    }
}

// Order class representing an order with order ID, items, table, and total sum
class Order {
    private int orderId; // Unique ID for the order
    private Vector<Fooditem> orderItems; // List of food items in the order
    private Table orderTable; // Table associated with the order
    private int orderSum; // Total cost of the order
    private static int Ids = 1; // Static counter for generating unique order IDs

    // Default constructor initializing order details
    public Order() {
        this.orderItems = new Vector<>();
        this.orderId = Ids;
        Ids++; // Increment the counter
        this.orderSum = 0; // Initialize orderSum to 0
    }

    // Parameterized constructor to initialize order details with items and table
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

    // Getter for orderItems
    public Vector<Fooditem> getOrderItems() {
        return orderItems;
    }

    // Setter for orderTable
    public void setOrderTable(Table orderTable) {
        this.orderTable = orderTable;
    }

    // Getter for orderSum
    public int getOrderSum() {
        return orderSum;
    }

    // Method to add a food item to the order
    public void addFoodItem(Fooditem food) {
        this.orderItems.add(food);
        this.orderSum += food.getPrice(); // Update the total cost
    }

    // Method to display the order details
    public void displayOrder() {
        if (orderTable == null) {
            System.out.println("""
                    --------------------------\
                    
                    THIS TABLE HAS NO ORDERS
                    ----------------------------""");
        } else {
            System.out.println("Order ID: " + orderId);
            System.out.println("Table: " + orderTable.getTableName()); // Assuming Table has a getTableNumber() method
            System.out.println("Items:");
            for (Fooditem item : orderItems) {
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total: $" + orderSum + "\n\n");
        }
    }

    // Method to clear the order
    public void clearOrder() {
        orderItems.clear();
    }
}

// Customer class representing a customer with name, phone number, and an order
class Customer {
    private String name;
    private String phoneNumber;
    private Order order;

    // Default constructor initializing customer details
    Customer() {
        this.name = "";
        this.phoneNumber = "";
        this.order = new Order();
    }

    // Parameterized constructor to initialize customer details
    Customer(String name, String phoneNumber, Order order) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.order = order;
    }

    // Setter for customer name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for customer name
    public String getName() {
        return name;
    }

    // Setter for customer phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter for customer phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for customer order
    public void setOrder(Order order) {
        this.order = order;
    }

    // Getter for customer order
    public Order getOrder() {
        return order;
    }
}

// Main class to run the restaurant management system
public class Main {
    public static void main(String[] args) {

        // Create a new restaurant with name, address, and phone number
        Restaurant restaurant1 = new Restaurant("ShazlyFoods", "Maadi", "01006666777");

        // Add food items to the restaurant's menu
        restaurant1.menu.addFoodItem(new Fooditem("Crispy Calamari", 12));
        restaurant1.menu.addFoodItem(new Fooditem("Bruschetta al Pomodoro", 9));
        restaurant1.menu.addFoodItem(new Fooditem("Grilled Salmon", 22));
        restaurant1.menu.addFoodItem(new Fooditem("Filet Mignon", 32));
        restaurant1.menu.addFoodItem(new Fooditem("Chicken Parmesan", 18));
        restaurant1.menu.addFoodItem(new Fooditem("New York Cheesecake", 8));
        restaurant1.menu.addFoodItem(new Fooditem("Chocolate Lava Cake", 10));
        restaurant1.menu.addFoodItem(new Fooditem("Tiramisu", 10));
        restaurant1.menu.addFoodItem(new Fooditem("Freshly Brewed Coffee", 11));
        restaurant1.menu.addFoodItem(new Fooditem("Iced Tea", 10));
        restaurant1.menu.addFoodItem(new Fooditem("House-Made Lemonade", 3));

        // Add tables to the restaurant
        restaurant1.getTables().put(51, new Table(51, 5));
        restaurant1.getTables().put(52, new Table(52, 5));
        restaurant1.getTables().put(31, new Table(31, 3));
        restaurant1.getTables().put(41, new Table(41, 4));
        restaurant1.getTables().put(42, new Table(42, 4));
        restaurant1.getTables().put(21, new Table(21, 2));

        // Display welcome message and prompt for user action
        System.out.println("\nWelcome To " + restaurant1.getName() + " at " + restaurant1.getAddress() + "\n-------------------------------");
        System.out.println("choose which action you need\n-------------------------------");

        int choice;
        Scanner scanner = new Scanner(System.in);

        // Main loop to handle user choices
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
                choice = scanner.nextInt();
            }

            // Handle user choice
            if (choice == 1) {
                // Create an order for a table
                restaurant1.displayTable();
                System.out.println("\nenter the table name");
                Scanner scanner2 = new Scanner(System.in);
                int tableName = scanner2.nextInt();
                while (restaurant1.getTables().get(tableName) == null) {
                    System.out.println("Please enter the table name correctly");
                    tableName = scanner2.nextInt();
                }
                if (!restaurant1.getTables().get(tableName).order.getOrderItems().isEmpty()) {
                    restaurant1.getTables().get(tableName).clearTableOrder();
                }

                restaurant1.getTables().get(tableName).order.setOrderTable(restaurant1.getTables().get(tableName));
                restaurant1.menu.displayMenu();
                Scanner scanner3 = new Scanner(System.in);

                int item = 0;
                int amount;
                while (true) {
                    System.out.println("\nitem Number:");
                    item = scanner3.nextInt();
                    while (item < -1 || item > restaurant1.menu.fooditems.size()) {
                        System.out.println("Please enter the item number correctly");
                        item = scanner3.nextInt();
                    }
                    if (item == -1) {
                        break;
                    }
                    System.out.println("amount:");
                    amount = scanner3.nextInt();
                    while (amount < 1) {
                        System.out.println("Please enter the amount correctly, cannot be zero or -ve");
                        amount = scanner3.nextInt();
                    }

                    for (int i = 0; i < amount; i++) {
                        restaurant1.getTables().get(tableName).order.addFoodItem(restaurant1.menu.fooditems.get(item - 1));
                    }
                }
                System.out.println("Order ID: " + restaurant1.getTables().get(tableName).order.getOrderId());
                System.out.println("Ordered Items: " + restaurant1.getTables().get(tableName).order.getOrderItems());
                System.out.println("Order Sum: " + restaurant1.getTables().get(tableName).order.getOrderSum() + "$\n\n--------------------------");
                System.out.println("Order Table: " + tableName + "\n");

            } else if (choice == 2) {
                // Print order details for a table
                restaurant1.displayTable();
                System.out.println("\nenter the table name");
                Scanner scanner4 = new Scanner(System.in);
                int table = scanner4.nextInt();
                restaurant1.getTables().get(table).order.displayOrder();

            } else if (choice == 3) {
                // Add an item to the menu
                System.out.println("enter Food Name\n");
                Scanner scanner5 = new Scanner(System.in);
                String foodName = scanner5.nextLine();
                System.out.println("enter Food Price\n");
                int foodPrice = scanner5.nextInt();
                restaurant1.menu.addFoodItem(new Fooditem(foodName, foodPrice));
                scanner5.close();
            } else if (choice == 4) {
                // Remove an item from the menu
                restaurant1.menu.displayMenu();
                System.out.println(" Enter Food Item number\n");
                Scanner scanner6 = new Scanner(System.in);
                int FoodItemNum = scanner6.nextInt();
                restaurant1.menu.removeFoodItem(FoodItemNum);
            } else if (choice == 5) {
                // Add a table to the restaurant
                System.out.println("enter the table name");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                System.out.println("enter table seat capacity");
                int capacity = scanner5.nextInt();
                restaurant1.addTable(table, capacity);
            } else if (choice == 6) {
                // Reserve a table
                System.out.println("\nenter the table number");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                restaurant1.getTables().get(table).reserve();
            } else if (choice == 7) {
                // Cancel a reservation
                System.out.println("\nenter the table number");
                Scanner scanner5 = new Scanner(System.in);
                int table = scanner5.nextInt();
                restaurant1.getTables().get(table).cancelReservation();
            } else if (choice == 8) {
                // Display the menu
                restaurant1.menu.displayMenu();
            } else if (choice == 9) {
                // Display all tables
                restaurant1.displayTable();
            } else if (choice == 10) {
                // Handle takeaway orders
                System.out.println("Enter Customer Name");
                Scanner scanner7 = new Scanner(System.in);
                String customerName = scanner.nextLine();
                customerName = scanner7.nextLine();
                System.out.println("Enter Customer phone number");
                String customerPhone = scanner.nextLine();
                restaurant1.menu.displayMenu();
                Order order1 = new Order();
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
                Customer customer1 = new Customer(customerName, customerPhone, order1);
                customer1.setName(customerName);
                System.out.println("Here are the order's details:\n");
                System.out.println("Name: " + customerName + " \nPhone Number:" + customer1.getPhoneNumber() + "\nOrder Details: ");

                System.out.println("Order ID: " + customer1.getOrder().getOrderId());
                System.out.println("Items:");
                for (Fooditem item : customer1.getOrder().getOrderItems()) {
                    System.out.println("- " + item.getName() + " - $" + item.getPrice());
                }
                System.out.println("Total: $" + customer1.getOrder().getOrderSum() + "\n\n");

            } else if (choice == 11) {
                // Exit the program
                break;
            }
        }
    }
}
