package system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import car.CarForRent;
import car.CarForSell;
import person.User;

public abstract class DB {
/*
 * SELECT id FROM reservations
	WHERE (DATE '2012-03-29', DATE '2010-04-01') OVERLAPS (start_date, stop_date)
  AND car_id = 12345;
  true if there is a reservation in this period
 * */
	public static boolean saveClient(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean updateClient(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void saveActivity(int id, String action,String date) {
		// TODO Auto-generated method stub
		
	}

	public static void saveReport(int id, String action, String date) {
		// TODO Auto-generated method stub
		
	}

	public static boolean addCarForSell(int id, CarForSell sellThisCar) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean notRented(int id, SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean rentThis(int id, int id2, SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean unbookCar(int id, int carID, SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean reschaduleCar(int carId, int carId2, SimpleDateFormat ufrom, SimpleDateFormat uto, SimpleDateFormat bfrom,SimpleDateFormat bto) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String passwordOf(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean addCarFeedback(int id, int carId, String feedback, String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean addCompanyFeedback(int id, String feedback, String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean addCarRate(int carId, float rate) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean addCompanyRate(float rate) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String buyCar(int id, int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> myBookedCars(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Activity> userActivity(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<String> seeCompanyFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<String> seeCarFeedback(int carID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String emailOf(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean deleteFeedbackForCar(int carId) {
		// TODO Auto-generated method stub
		return false;
	}

	public static ArrayList<CarForSell> waitingCars() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String acceptForSell(int carId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static float getCompanyRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static boolean setAboutCompany(String something) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String getAboutCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Activity> getDailyReport() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<Activity> getMonthlyReport() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean addCarForRent(CarForRent car) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean deleteCarForSell(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean deleteCarForRent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean saveCarForSell(CarForSell carForSell) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean saveCarForRent(CarForRent carForRent) {
		// TODO Auto-generated method stub
		return false;
	}

	public static ArrayList<String> emails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
