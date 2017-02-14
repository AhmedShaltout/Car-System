package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
/**======================================== for connection ===================================**/
	
	private static Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true&useSSL=false","root","");
		}
		catch(SQLException |ClassNotFoundException ex){
			return null;
		}
	}
	
	/**==========================================================================================**/
	
	/**responsible for returning values from the database the result can be null**/
	private static ResultSet select(String sql){
		try{
			return getConnection().createStatement().executeQuery(sql);
		}catch (SQLException | NullPointerException e) {
			return null;
		}
	}
	
	/**responsible for executing Queries if done returns true ,on the other hand false**/
	private static boolean editDataBase(String sql){
		try {
			getConnection().createStatement().execute(sql);
			return true;
		} catch (SQLException | NullPointerException e) {
			return false;
		}
	}
	
	public static boolean saveClient(User user) {
		return editDataBase("insert into user(fName,	lName,	gender,	address,	state,	email,	password,	pic,	rentN,	buyN,	sellN) "
				+ " values('"+user.getfName()+"',	'"+user.getlName()+"',	"+user.getGender()+",	'"+user.getAddress()+"',	'"+user.getState()+"',"
						+ "	'"+user.getEmail()+"',	'"+user.getPassword()+"',	'"+user.getPic()+"', "+user.getRentN()+","
								+ " "+user.getBuyN()+", "+user.getSellingNumber()+")");
	}

	public static boolean updateClient(User user) {
		return editDataBase("update user set fName='"+user.getfName()+"',	lName= '"+user.getlName()+"',	gender= "+user.getGender()+","
				+ "	address ='"+user.getAddress()+"',	state= '"+user.getState()+"',	email= '"+user.getEmail()+"',	password = '"+user.getPassword()+"', "
						+ "pic= '"+user.getPic()+"',	rentN=  "+user.getRentN()+",	buyN= "+user.getBuyN()+",	sellN= "+user.getSellingNumber()+" "
								+ "where id="+user.getId()+"");
	}

	public static void saveActivity(int id, String action,String date) {
		editDataBase("insert into activity(id,	actionDate,	action) values("+id+", '"+date+"', '"+action+"')");
	}

	public static void saveReport(int id, String action, String date) {
		editDataBase("insert into report(id,	actionDate,	action) values("+id+", '"+date+"', '"+action+"')");
	}

	public static boolean addCarForSell(CarForSell car) {
		return editDataBase("insert into carforsell(image,motor,color,speed,doors,about,model,type,name,cc,carPrice,available,email)"
				+ "values('"+car.getImage()+"', '"+car.getMotor()+"', '"+car.getColor()+"', "+car.getSpeed()+", "+car.getDoors()+", "
						+ "'"+car.getAbout()+"', '"+car.getModel()+"', '"+car.getType()+"', '"+car.getName()+"', "+car.getCc()+","
								+ ""+car.getCarPrice()+", "+car.getAvailable()+" , '"+car.getEmail()+"')");
	}

	public static boolean notRented(int carId, SimpleDateFormat from, SimpleDateFormat to) {
		ResultSet result=select("select * from rentedcars where carId="+carId+"	and rentFrom='"+from+"' and	rentTo='"+to+"'");
		try {
			if(result.next())
				return true;
		} catch (SQLException | NullPointerException e) {}
		return false;
		
	}

	public static boolean rentThis(int id, int carId, String from, String to) {
		return editDataBase("insert into rentedcars(carId,id,rentFrom,rentTo) "
				+ "values("+id+","+carId+",'"+from+"','"+to+"')");
	}

	public static boolean unbookCar(int id, int carId, String from, String to) {
		return editDataBase("delete from rentedcars where id="+id+" and carId="+carId+" and rentFrom='"+from+"' and rentTo= '"+to+"'");
	}

	public static boolean reschaduleCar(int id, int carId, String ufrom, String uto, String bfrom,String bto) {
		if(!isRentedIn(carId,bfrom,bto)){
			return editDataBase("update rentedcars set rentFrom='"+bfrom+"' , rentTo='"+bto+"' where carId="+carId+" and "
					+ "id="+id+" and	rentFrom='"+ufrom+"' and rentTo='"+uto+"'	");
		}
		return false;
	}

	public static boolean isRentedIn(int carId, String from, String to) {
		ResultSet result=select("select * from rentedcars where carId="+carId+" and rentFrom='"+from+"' and rentTo='"+to+"'");
		try {
			if(result.next())
				return true;
		} catch (SQLException | NullPointerException e) {}
		return false;
	}

	public static boolean addCarFeedback(int id, int carId, String feedback, String time) {
		return editDataBase("insert into carfeedback(carId,id,time,feedback) values("+carId+","+id+",'"+time+"','"+feedback+"')");
	}

	public static boolean addCompanyFeedback(int id, String feedback, String time) {
		return editDataBase("insert into companyfeedback(id,time,feedback) values("+id+",'"+time+"','"+feedback+"')");
	}

	public static boolean addCarRate(int carId, float rate) {
		return editDataBase("update carforrent set rate=(rate + "+rate+")/2 where carId="+carId+"");
	}

	public static boolean addCompanyRate(float rate) {
		return editDataBase("update system set rate = (rate +"+rate+")/2");
	}

	public static String buyCar(int carId) {
		String email = null;
		ResultSet result= select("select email from carforsell where carId="+carId+"");
		try {
			result.next();
			email=result.getString("email");
			select("delete from carforsell where carId="+carId+"");
		} catch (SQLException |NullPointerException e) {}
		return email;
	}

	public static ArrayList<CarForRent> myBookedCars(int id) {
		ResultSet
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

	public static ArrayList<CarForRent> findRentByModel(String carModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByName(String carName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByDate(SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForSell> findSellByNameAndModel(String carName, String carModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForSell> findSellByModel(String carNameOrModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForSell> findSellByName(String carNameOrModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByNameModelAndDate(String carName, String carModel,
			SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByModelAndDate(String carNameOrModel, SimpleDateFormat from,
			SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByNameAndDate(String carNameOrModel, SimpleDateFormat from,
			SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByNameAndModel(String carName, String carModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean exists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}