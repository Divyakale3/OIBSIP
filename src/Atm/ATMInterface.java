package Atm;

import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transaction = 0;
    String transactionHistory = " ";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        this.name = sc.nextLine();
        System.out.println("enter your username:");
        this.userName = sc.nextLine();
        System.out.println("Enter your password:");
        this.password = sc.nextLine();
        System.out.println("Enter your Account Number:");
        this.accountNo = sc.nextLine();
        System.out.println("Registration Successful.Please log in to your Bank Account");
    }

    public boolean login(){
        boolean isLogin=false;
        Scanner sc=new Scanner(System.in);
        while (!isLogin){
            System.out.println("Enter your username:");
            String Username=sc.nextLine();
            if(Username.equals(userName)){
                while (!isLogin){
                    System.out.println("Enter your password:");
                    String Password=sc.nextLine();
                    if(Password.equals(password)){
                        System.out.println("Login Successfully");
                        isLogin=true;
                    }
                    else {
                        System.out.println("Incorrect Password");
                    }
                }
            }
            else {
                System.out.println("Username not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("Enter Amount to withdraw:");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transaction++;
                balance -= amount;
                System.out.println("withdraw successfully");
                String str = amount + " Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch(Exception e) {

        }
    }
    public void deposit(){
        System.out.println("\n Enter Amount to Deposit:" );
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try {
            if (amount<=10000f){
                transaction++;
                balance +=amount;
                System.out.println("\nDeposit Successfully");
                String str=amount+ " Rs deposited\n";
                transactionHistory =transactionHistory.concat(str);
            }else {
                System.out.println("nSorry.The limit is 10000");
            }
        }catch (Exception e){

        }
    }

    public void transfer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Recipient's name:");
        String recipitent=sc.nextLine();
        System.out.println("\nEnter amount to transfer;");
        float amount=sc.nextFloat();
        try{
            if(balance>=amount){
                if(amount<=50000f){
                    transaction++;
                    balance-=amount;
                    System.out.println("\nSuccessfully transferred to"+recipitent);
                    String str=amount +" Rs transferred to "+recipitent+"\n";
                    transactionHistory=transactionHistory.concat(str);
                }else {
                    System.out.println("\nSorry.The limit is 50000");
                }
            }else {
                System.out.println("Insufficient balance");
            }
        }catch (Exception e){

        }
    }
    public void checkBalance(){
        System.out.println("\n your available balance:"+balance+" Rs");
    }

    public void transHistory(){
        if(transaction==0){
            System.out.println("No transaction happened");
        }else{
            System.out.print("\n"+transactionHistory);
        }
    }

}

public class ATMInterface {
    public static int takenIntegerInput(int limit){
        int input=0;
        boolean flag=false;

        while (!flag){
            try {
               Scanner sc=new Scanner(System.in);
               input=sc.nextInt();
               flag=true;
               if (flag && input>limit || input<1){
                   System.out.println("Choose the number between 1 to "+limit);
                   flag=false;
               }
            }catch (Exception e){
                System.out.println("Enter only integer value:");
                flag=false;
            }
        }
        return input;
    }
    public static void main(String[] args){
        System.out.println("Welcome to my ATM Interface");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option:");
        int choose=takenIntegerInput(2);

        if(choose==1){
            BankAccount b=new BankAccount();
            b.register();
            while (true){
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice:");
                int ch=takenIntegerInput(2);
                if(ch==1){
                    if(b.login()){
                        System.out.println("*******Welcome back"+b.name+"***********");
                        boolean isFinished=false;
                        while (!isFinished){
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.check balance \n5.Tranaction History \n6.Exit");
                            System.out.println("enter your choice:");
                            int c=takenIntegerInput(6);
                            switch (c){
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished=true;
                                    break;
                            }
                        }
                    }
                }else {
                    System.exit(0);
                }
            }
        }else {
            System.exit(0);
        }
    }
}
