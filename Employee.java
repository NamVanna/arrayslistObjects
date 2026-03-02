package CrudTable;
import javax.swing.plaf.nimbus.State;
import  java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class Company{
    ArrayList<Employee> emp = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String url = "jdbc:postgresql://localhost:5432/hrd_new_database" ;
    String user = "postgres";
    String pass = "vanna110806" ;
    public void dataEmployee(){
        try {
            Connection con = DriverManager.getConnection(url , user , pass) ;
            String sql = "select * from employee order by id desc" ;
            Statement st = con.createStatement() ;
            ResultSet  rs = st.executeQuery(sql) ;
            while (rs.next()){
                int  id = rs.getInt("id");
                String name = rs.getString("name");
                int  age  = rs.getInt("age");
                //System.out.println(id+name+age);
                emp.add(new Employee(id, name ,age ));
            }
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //show :
    public void showEmployee(){
        for(Employee em : emp){
            System.out.println(em);
        }
    }

    public void addEmployee(){
        try {
            Connection con = DriverManager.getConnection(url , user , pass) ;
            String sql = "insert into employee(name,age) values (? , ?)" ;
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //input the value
            System.out.print("please input your name : "); String name = sc.nextLine();
            System.out.print("please input your age :"); int age = sc.nextInt();sc.nextLine();

            ps.setString(1,name);
            ps.setInt(2,age);

            int row = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            emp.add(new Employee(id,name,age));
            if(row==1){
                System.out.println("add success");
            }

            con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee(){
        try {
            Connection con = DriverManager.getConnection(url , user , pass) ;
            String sql = "update employee set name = ? , age = ? where id = ?" ;
            PreparedStatement ps = con.prepareStatement(sql);
            //input the value
            System.out.print("please input your id  : "); int  id = sc.nextInt();sc.nextLine();
            System.out.print("please input your new name : "); String name = sc.nextLine();
            System.out.print("please input your new age :"); int age = sc.nextInt();sc.nextLine();

            ps.setInt(3,id);
            ps.setString(1,name);
            ps.setInt(2,age);

            int row = ps.executeUpdate();
            if(row==1){
                System.out.println("update success");
            }
            else {
                System.out.println("update not success");
            }
            con.close();

            //update in arrayslist
            for(int i=0 ; i< emp.size() ; i++){
                if(id==emp.get(i).getId()){
                    emp.get(i).id =id;
                    emp.get(i).name = name;
                    emp.get(i).age = age;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(){
        try {
        Connection con = DriverManager.getConnection(url , user , pass) ;
        String sql = "delete from employee where id = ?" ;
        PreparedStatement ps = con.prepareStatement(sql);
        //input the value
        System.out.print("please input your id  : "); int  id = sc.nextInt();sc.nextLine();

        ps.setInt(1,id);

        int row = ps.executeUpdate();
        String name = "" ;
        int age = 0;
        emp.remove(new Employee(id,name,age));
        if(row==1){
            System.out.println("delete success");
        }
        else {
            System.out.println("delete not success");
        }
        for(int i=0 ; i< emp.size() ; i++){
                if(id==emp.get(i).getId()){
                    emp.remove(i);
                }
        }
        con.close();
    }catch (SQLException e){
        System.out.println(e.getMessage());}

    }

    public static void main(String[] args){
    }
}
public class Employee {
    String name ;
    int id ;
    int age ;
    Employee(int id , String name , int age ){
        this.id = id ;
        this.name = name ;
        this.age = age ;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString(){
        return "id :" +id + " | "+"name : "+name +" | "+ "age :" +age ;
    }


    public static  void main(String[] args){
        Company company = new Company();
        Scanner sc = new Scanner(System.in);
        company.dataEmployee();
        do {
            System.out.println("=========================");

            System.out.println("""
                    1. show Employee 
                    2. add Employee
                    3. update Employee
                    4. delete Employee
                    """);
            System.out.print("please choose the option : ");
            String op = sc.nextLine();
            switch (op){
                case "1" :
                {
                    System.out.println("========show item============");
                    company.showEmployee();
                    break;
                }
                case "2" :{
                    System.out.println("========add item=======");
                    company.addEmployee();

                    break;
                }
                case "3" :{
                    System.out.println("=====update item=======");
                    company.updateEmployee();
                    break;
                }
                case "4" :{
                    System.out.println("=====delete item=======");
                    company.deleteEmployee();
                    break;
                }
                default: {
                    System.out.println("Invalid choice");
                }

            }

        }while (true) ;

    }


}
