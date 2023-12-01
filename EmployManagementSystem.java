//ALLLLL IMPORTS

import java.util.*;
import java.io.*;

//MENU OF EMS

class MainMenu {
  public void menu() {
      System.out.println("\t\t*******************************************");
      System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
      System.out.println("\t\t*******************************************");
      System.out.println("\t\t\t    --------------------\n\t\t\t    --------------------");
      System.out.println("\n\nPress 1 : To Add an Employee Details");
      System.out.println("Press 2 : To See an Employee Details ");
      System.out.println("Press 3 : To Delete an Employee Details");
      System.out.println("Press 4 : To Exit the EMS Portal");
  }
}

// Interface
interface DisplayInfo {
    void display();
}

//Abstract class
abstract class Employee {
    //  Constructor
    public Employee() {
    }

    // Abstract method
    abstract void getInfo();
}

//  Multilevel inheritance
class EmployDetail extends Employee implements DisplayInfo {
    String name;
    String position;
    String employ_id;
    String employ_salary;
    String employ_contact;

    // Implementation of abstract method
    @Override
    public void getInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee's name --------: ");
        name = sc.nextLine();

        System.out.print("Enter Employee's ID ----------: ");
        employ_id = sc.nextLine();

        System.out.print("Enter Employee's Position ----: ");
        position = sc.nextLine();
        System.out.print("Enter Employee contact Info --: ");
        employ_contact = sc.nextLine();
        System.out.print("Enter Employee's Salary ------: ");
        employ_salary = sc.nextLine();
    }

    // Implementation of interface method
    @Override
    public void display() {
        System.out.println("Employee ID: " + employ_id);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Position: " + position);
        System.out.println("Employee Contact: " + employ_contact);
        System.out.println("Employee Salary: " + employ_salary);
    }
}

// To add details of Employee

class Employee_Add extends EmployDetail {
    public void createFile() {
        Scanner sc = new Scanner(System.in);

        EmployDetail emp = new EmployDetail();
        emp.getInfo();
        //I don't sometimes errors are threre.. fix it
        try {

          String customLocation = "/Users/saptarshi/Desktop/Employee/";

          File f1 = new File(customLocation + "file" + emp.employ_id + ".txt");
          if (f1.createNewFile()) {
              FileWriter myWriter = new FileWriter("file" + emp.employ_id + ".txt");
              myWriter.write("Employee ID:" + emp.employ_id + "\n" + "Employee Name     :" + emp.name + "\n" +
                      "Employee Contact  :" + emp.employ_contact + "\n" + "Employee position :" + emp.position + "\n" +
                      "Employee Salary   :" + emp.employ_salary);
              myWriter.close();
              System.out.println("\nEmployee has been Added :)\n");

              System.out.print("\nPress Enter to Continue...");
              sc.nextLine();
          } else {
              System.out.println("\nEmployee already exists :(");
              System.out.print("\nPress Enter to Continue...");
              sc.nextLine();
          }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

//To Show details of Employee

class Employee_Show extends EmployDetail {
    public void viewFile(String s) throws Exception {
        File file = new File("file" + s + ".txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }
}


//To Delete details of Employee 
class Employee_Remove
{
    public void removeFile(String ID)
    {

    File file = new File("file"+ID+".txt");
      if(file.exists())
       {
         if(file.delete());
         {
           System.out.println("\nEmployee has been removed Successfully");
         }
       }
      else
       {
            System.out.println("\nEmployee does not exists :( ");
       }
     }
}


//To Exit from the EMS Portal

class CodeExit {
    public void out() {
        System.out.println("\n***********************************************");
        System.out.println("Thank You For Using our Software :) - Team 8 ");
        System.out.println("*****************************************");
        System.exit(0);
    }
}


//MAIN CLASS TO RUN THE PROGRAM
class EmployManagementSystem
{
  public static void main(String argsStrings[])
  {
    /** To clear the output Screen **/
    System.out.print("\033[H\033[2J");

    Scanner sc =new Scanner(System.in);
    Employee_Show epv =new Employee_Show();

    int i=0;

    /*** Callining Mainmenu Class function ****/
    MainMenu obj1 = new MainMenu();
    obj1.menu();

    /*** Initialising loop for Menu Choices ***/
    while(i<5)
    {

      System.out.print("\nPlease Enter choice :");
      i=Integer.parseInt(sc.nextLine());

      /** Switch Case Statements **/
      switch(i)
      {
        case 1:
        {
        // Creating class's object and calling Function using that object
        Employee_Add ep =new Employee_Add();
        ep.createFile();

        System.out.print("\033[H\033[2J");
        obj1.menu();
        break;
        }
        case 2:
        {
          System.out.print("\nPlease Enter Employee's ID :");
          String s=sc.nextLine();
          try
          {
            epv.viewFile(s);}
            catch(Exception e)
                {System.out.println(e);}


            System.out.print("\nPress Enter to Continue...");
            sc.nextLine();
            System.out.print("\033[H\033[2J");
            obj1.menu();
            break;
          }

        case 3:
        {
          System.out.print("\nPlease Enter Employee's ID :");
          String s=sc.nextLine();
          Employee_Remove epr =new Employee_Remove();
          epr.removeFile(s);

          System.out.print("\nPress Enter to Continue...");
          sc.nextLine();
          System.out.print("\033[H\033[2J");
          obj1.menu();
          break;
        }
        case 4:
        {
          CodeExit obj = new CodeExit();
          obj.out();
        }
      }
    }
  }
}