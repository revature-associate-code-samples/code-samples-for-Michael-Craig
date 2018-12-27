package main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import pojos.AccountInfo;
import pojos.User;
import service.AccInfoService;
import service.AccTypeService;
import service.UserService;

public class App {

	static UserService uService = new UserService();
	static AccInfoService aIService = new AccInfoService();
	static AccTypeService aTService = new AccTypeService();

	public static void main(String[] args) {
		run();
	}

	static void run() {

		System.out.println("Welcome to Budget Bank" + "\n Would you like to:" + "\n 1. Create a new account"
				+ "\n 2. Log in to an existing account");
		Scanner scan = new Scanner(System.in);
		try {
			int option = scan.nextInt();
			switch (option) {
			case 1:
				createNewAccount();
				break;
			case 2:
				login();
				break;
			default:
				System.out.println("Please enter a number from the menu.");
				run();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number from the menu.");
			run();
		}
	}

	static void createNewAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the following values");
		System.out.println("First name: ");
		String scanFirst = scan.next();
		System.out.println("Last name: ");
		String scanLast = scan.next();
		System.out.println("Username: ");
		String scanUser = scan.next();
		System.out.println("Password: ");
		String scanPass = scan.next();
		try {
			int x = uService.createUser(new User(scanFirst, scanLast, scanUser, scanPass));
			if (x == 1) {
				System.out.println("Oh no! That username has already been taken.");
				run();
			} else if (x == 2) {
				System.out.println("User created");
				run();
			} else {
				System.out.println("Sorry, please try again.");
				run();
			}
		} catch (Exception e) {
			
		}
	}

	static void login() {
		System.out.println("Please enter the following values");
		Scanner scan = new Scanner(System.in);
		System.out.println("Username: ");
		String scanUser = scan.next();
		System.out.println("Password: ");
		String scanPass = scan.next();
		switch (uService.checkUserExists(scanUser, scanPass)) {
		case 0:
			System.out.println("Sorry, that does not match out records.");
			run();
			break;
		case 1:
			System.out.println("Sorry, that does not match out records.");
			run();
			break;
		case 2:
			System.out.println("SUCCESS!");
			User u = uService.loginSuccess(scanUser);
			uService.setLoggedIn(u);
			accountHome();
			break;
		default:
			System.out.println("Sorry, something has gone wrong.");
			run();
			break;
		}
		accountHome();
	}

	static void accountHome() {
		System.out.println("Account Home Page" + "\nOptions" + "\n 1. Open new Account" + "\n 2. View existing accounts"
				+ "\n 3. Log out");
		Scanner scan = new Scanner(System.in);
		try {
			int option = scan.nextInt();
			switch (option) {
			case 1:
				openNewAccount();
				break;
			case 2:
				viewAccounts();
				break;
			case 3:
				run();
				break;
			default:
				System.out.println("Please enter a number from the menu");
				accountHome();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number from the menu1");
			accountHome();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Please enter a number from the menu2");
			accountHome();
		}
	}

	private static void viewAccounts() {
		List<AccountInfo> a = aIService.accsForUser(uService.getLoggedUser().getUserName());
		int total = 0;
		int i;
		for (i = 0; i < a.size(); i++) {
			System.out.println(i + 1 + ". " + aIService.accTypeInfo(a.get(i).getTypeId()));
		}
		System.out.println((i + 1) + ". Go Back");
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt(a.size() + 1);
		if (res == 0) {
			viewAccounts();
		} else if (res == a.size() + 1) {
			accountHome();
		} else {
			myAccounts(a.get(res - 1));
		}
	}

	private static void myAccounts(AccountInfo ai) {
		System.out.println("Options" + "\n 1. Deposit" + "\n 2. Withdraw" + "\n 3. Delete Account" + "\n 4. Go Back");
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt();
		switch (res) {
		case 1:
			deposit(ai);
			break;
		case 2:
			withdraw(ai);
			break;
		case 3:
			deleteAccount(ai);
			break;
		case 4:
			viewAccounts();
			break;
		default:
			viewAccounts();
			break;
		}
	}

	private static void deleteAccount(AccountInfo ai) {
		aIService.delete(ai);
		System.out.println("Account deleted");
		accountHome();
	}

	private static void deposit(AccountInfo ai) {
		System.out.println("Please specify the amount you would like to deposit");
		int d = 0;
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt();
		aIService.deposit(res, ai);
		if (res > 0) {
			System.out.println("Success!  Your new balance is: " + aIService.balance(ai));
		} else {
			System.out.println("Something went wrong...");
			deposit(ai);
		}
	}

	private static void withdraw(AccountInfo ai) {
		System.out.println("How much would you like to withdraw?");
		ai = aIService.update(ai);
		int w = 0;
		Scanner scan = new Scanner(System.in);
	}

	private static void logOutConfirm() {
		System.out.println("Are you sure you would like to log out?" + "\n 1. Yes" + "\n 2. No");
		Scanner scan = new Scanner(System.in);
		try {
			int option = scan.nextInt();
			switch (option) {
			case 1:
				run();
				break;
			case 2:
				accountHome();
				break;
			default:
				accountHome();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a number from the menu");
			accountHome();
		}
	}

	static void openNewAccount() {
		System.out.println("Please specify the type of account you would like to open" + "\n 1. Credit"
				+ "\n 2. Checking" + "\n 3. Savings" + "\n 4. Go back");
		Scanner scan = new Scanner(System.in);
		int ress = scan.nextInt();
		if (ress == 1 || ress == 2 || ress == 3) {
			AccountInfo acc = new AccountInfo();
			acc.setTypeId(ress);
			acc.setUserId(uService.getLoggedUser().getId());
			acc.setBalance(0);
			AccountInfo ai = aIService.saveChange(acc);
			if (ai.getAccountId() > 0) {
				System.out.println("SUCCESS!");
			} else {
				System.out.println("Something went wrong...");
			}
		} else {
			System.out.println("Hm, there was a problem...");
			accountHome();
		}
	}

}