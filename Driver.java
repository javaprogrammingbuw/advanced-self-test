import java.util.Random;

/*
Author: Nathalie Lang
*/

public class Driver{
	private static String[] firstNames = {"Anna", "Robert", "John", "Jenny", "Matthias", "Kevin"};
	private static String[] lastNames = {"Roberts", "Jackson", "Doe", "Smith", "Porter", "Miller"};
	private static Random r = new Random();

	public static void main(String[] args){
		Account[] accounts = generateAccounts();
		System.out.println();
		displayInteraction(accounts);

	}

	private static Account[] generateAccounts(){
		System.out.println("-----------Generating Accounts-----------");
		Account[] accounts = new Account[10];
		for(int i = 0; i<accounts.length; i++){
			int age = generateAge();
			Owner o = new Owner(generateName(), age);
			if(age<18){
				int limit = r.nextInt(200);
				accounts[i] = new YouthAccount(o, "DE"+r.nextInt(1000000000), limit);
				System.out.println("Created a YouthAccount for " + o.getName() + " with limit: " + limit);
			}
			else{
				int rand = r.nextInt(2);
				if(rand == 0){
					int credit = r.nextInt(5000);
					accounts[i] = new StandardAccount(o, "DE"+r.nextInt(1000000000), credit);
					System.out.println("Created a StandardAccount for " + o.getName() + " with credit: " + credit);
				}
				else{
					double interest = r.nextDouble();
					accounts[i] = new InterestAccount(o, "DE"+r.nextInt(1000000000), interest);
					System.out.println("Created an InterestAccount for " + o.getName() + " with interest: " + interest + "%");
				}
			}
			System.out.println("...");
		}
		return accounts;
	}

	private static void displayInteraction(Account[] accounts){
		System.out.println("-------Starting Interactions------");
		String className = "";
		double d;
		for(Account a : accounts){
			className = a.getClass().getSimpleName();
			System.out.println(className);
			d = r.nextDouble()*100;
			System.out.println("Depositing: " + d);
			System.out.println(a.deposit(d));
			d = r.nextDouble()*100;
			System.out.println("Withdrawing: " + d);
			System.out.println(a.withdraw(d));
			System.out.println("Balance in the end of the month:");
			System.out.println(a.creditMonthlyInterest());
			System.out.println();
		}
	}

	private static String generateName(){
		String name = "";
		name += firstNames[r.nextInt(6)];
		name += " ";
		name += lastNames[r.nextInt(6)];
		return name;
	}

	private static int generateAge(){
		return r.nextInt(100);
	}

}