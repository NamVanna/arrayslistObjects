package arrays.arrayObject;


import java.util.ArrayList;
import java.util.Scanner;

class Company {

    Scanner sc = new Scanner(System.in);
    ArrayList<Employee> emp= new ArrayList<>();
     int id = 1;
    public void addEmployee(){
        System.out.println("====================");
        System.out.println("=====add employee=====");
        System.out.println("your id is  : " + id);
        System.out.print("Please input your name :");
        String name  = sc.nextLine().strip();
        System.out.println("Please input your age :");
        String ages = sc.nextLine().strip();
        int age = Integer.parseInt(ages);
        emp.add(new Employee(id, name , age ));
        id++;
    }

    public void showEmployee(){  //method override using here
        for(Employee em : emp ){
            System.out.println(em);
        }
    }


    public void updateEmployee(){
        System.out.print("Please input your id   :");
        String ids  = sc.nextLine().strip();
        int id = Integer.parseInt(ids);
        boolean found = false ;
        for(int i =0 ; i < emp.size() ; i++){
             found = true ;
            if(id == emp.get(i).getId()){
                System.out.println("Please input your name  :");
                String name = sc.nextLine().strip();
                System.out.println("Please input your age :");
                String ages = sc.nextLine().strip();
                int age = Integer.parseInt(ages);
//                emp.add(new Employee(id, name , age ));  //this is mean add the new
                emp.get(i).setId(id);  // or show can right emp.get(i).id = id  ;
                emp.get(i).setName(name) ;
                emp.get(i).setAge(age);
                System.out.println("Employee updated successfully!");
                break;
            }
        }
        if(found){
            System.out.println("not found !!");
        }
    }


}
class Employee{
    int id ;
    String name ;
    int age ;
    Employee(int id , String name , int age ){
        this.id = id  ;
        this.name = name ;
        this.age = age ;
    }
    //use for override method java for show data

    @Override
    public String toString(){
        return "Id :" + id +"\nName : " + name + "\nAge : " + age ;
    }

    public int getId(){
        return id ;
    }

    public void setId(int id){
        this.id = id ;
    }


    public void setName(String name){
        this.name = name ;
    }
    public void setAge(int age ){
        this.age = age ;
    }



}

public class arraysObjectMethods {
    public static  void main(String[] args)
    {
        Company company = new Company();

        Scanner sc = new Scanner(System.in);

        boolean option = true  ;

        do{

            System.out.println("""
                    1. add employee
                    2. show employee
                    3. update employee
                    """);
            System.out.println("PLease chose one option : ");
            String choose = sc.nextLine();



            switch (choose) {
                case  "1"  : {
                    System.out.println("====".repeat(25));
                    company.addEmployee();
                    break;
                }
                case "2" :
                {
                    System.out.println("========================");
                    System.out.println("======show all employee===========");
                    company.showEmployee();  //about this one need to override method string for show the value

                    break;
                }
                case "3" :
                {
                    System.out.println("================= ");
                    System.out.println("===update employee======");
                    company.updateEmployee();
                    break;
                }
            }

        }while (option);

    }
}
