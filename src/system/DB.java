package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import car.CarForRent;
import car.CarForSell;
import person.User;

public abstract class DB {

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

	public static void saveReport(String action, String date) {
		editDataBase("insert into report(actionDate,action) values('"+date+"', '"+action+"')");
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

	public static boolean addCarFeedback(String email , int carId, String feedback, String time) {
		return editDataBase("insert into carfeedback(carId,email,time,feedback) values("+carId+","+email+",'"+time+"','"+feedback+"')");
	}

	public static boolean addCompanyFeedback(String email, String feedback, String time) {
		return editDataBase("insert into companyfeedback(email,time,feedback) values("+email+",'"+time+"','"+feedback+"')");
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
		ArrayList<CarForRent> list=new ArrayList<>();
		ResultSet fromTo= select("select * from rentedcars where id="+id+"");
		try {
			while(fromTo.next()){
				ResultSet carInfo=select("select * from carforrent where carId="+fromTo.getInt("carId")+"");
				CarForRent car;
				if((car=careateCarRent(carInfo,fromTo.getString("rentFrom"),fromTo.getString("rentTo")))!=null)
					list.add(car);	
			}
		} catch (SQLException | NullPointerException e) {}
		return list;
		
	}

	private static CarForRent careateCarRent(ResultSet carInfo, String from, String to) {
		try {
			carInfo.next();
			CarForRent car=new CarForRent(carInfo.getFloat("pricePerH"), carInfo.getFloat("rate"), from, to);
			car.editCarProfileForDB(carInfo.getString("image"), carInfo.getString("motor"), carInfo.getString("color"),
					carInfo.getFloat("speed"), carInfo.getShort("doors"), carInfo.getString("about"),carInfo.getString("model") ,
					carInfo.getString("type"),carInfo.getString("name") , carInfo.getInt("cc"), carInfo.getInt("carId"));
			return car;
		} catch (SQLException |NullPointerException e) {
			return null;
		}
	}

	public static ArrayList<Activity> userActivity(int id) {
		ArrayList<Activity> list=new ArrayList<>();
		ResultSet result= select("select * from activity where id="+id+"");
		try {
			while(result.next()){
				list.add(new Activity(result.getString("action"),result.getString("actionDate")));
			}
		} catch (SQLException | NullPointerException e) {}
		return list;
	}

	public static ArrayList<Feedback> seeCompanyFeedback() {
		ArrayList<Feedback>feed= new ArrayList<>();
		ResultSet result=select("select * from companyfeedback ordered by time");
		try {
			while(result.next()){
				feed.add(new Feedback(result.getString("feedback"), result.getString("email"),result.getString("time")));
			}
		} catch (SQLException |NullPointerException  e) {}
		return feed;
	}

	public static ArrayList<Feedback> seeCarFeedback(int carID) {
		ArrayList<Feedback>feed= new ArrayList<>();
		ResultSet result=select("select * from carfeedback where carId="+carID+" ordered by time");
		try {
			while(result.next()){
				feed.add(new Feedback(result.getString("feedback"), result.getString("email"),result.getString("time")));
			}
		} catch (SQLException |NullPointerException  e) {}
		return feed;
	}

	public static String emailOf(int id) {
		String s=null;
		ResultSet result= select("select email from user where id="+id+"");
		try {
			result.next();
			s=result.getString("email");
		} catch (SQLException |NullPointerException e) {}
		return s;
	}

	public static ArrayList<CarForSell> waitingCars() {
		ResultSet result =select("select * from carforsell where available=0 ");
		CarForSell car;
		ArrayList<CarForSell>list =new ArrayList<>();
		while((car=createCarForSell(result))!=null){
			list.add(car);
		}
		return list;
	}

	private static CarForSell createCarForSell(ResultSet result) {
		try {
			result.next();
			CarForSell car= new CarForSell(result.getFloat("carPrice"),result.getString("email") ,result.getBoolean("available") );
			car.editCarProfileForDB(result.getString("image"), result.getString("motor"),result.getString("color") ,result.getFloat("speed") ,
					result.getShort("doors"),result.getString("about") ,result.getString("model") ,result.getString("type") ,result.getString("name") ,
					result.getInt("cc"),result.getInt("carId"));
			return car;
		} catch (SQLException |NullPointerException e) {}
		return null;
	}

	public static String acceptForSell(int carId) {
		ResultSet result= select("select email from carforsell where carId="+carId+"");
		String s=null;
		try {
			result.next();
			s=result.getString("email");
			editDataBase("update carforsell set available=1 where carId="+carId+"");
		} catch (SQLException |NullPointerException e) {}
		return s;
	}

	public static float getCompanyRate() {
		ResultSet result=select("select rate from system");
		try {
			result.next();
			return result.getFloat("rate");
		} catch (SQLException |NullPointerException e) {}
		return -1f;
	}

	public static boolean setAboutCompany(String something) {
		return editDataBase("update system set about= '"+something+"'");
	}

	public static String getAboutCompany() {
		ResultSet result= select("select about from system");
		try {
			result.next();
			return result.getString("about");
		} catch (SQLException |NullPointerException e) {}
		return null;
	}

	public static ArrayList<Activity> getDailyReport() {
		ResultSet result= select("select * from report where actionDate >="+LocalDateTime.now().toLocalDate()+"");
		ArrayList<Activity> list=new ArrayList<>();
		try {
			while(result.next()){
				list.add(new Activity(result.getString("action"),result.getString("actionDate")));
			}
		} catch (SQLException |NullPointerException e) {}
		return list;
	}

	public static ArrayList<Activity> getMonthlyReport() {
		ResultSet result= select("select * from report where actionDate >="+LocalDateTime.now().minusDays(30).toLocalDate()+"");
		ArrayList<Activity> list=new ArrayList<>();
		try {
			while(result.next()){
				list.add(new Activity(result.getString("action"),result.getString("actionDate")));
			}
		} catch (SQLException |NullPointerException e) {}
		return list;
	}

	public static boolean addCarForRent(CarForRent car) {
		return editDataBase("insert into carforrent(image,motor,color,speed,doors,about,model,type,name,cc,pricePerH,rate)"
				+ "values('"+car.getImage()+"', '"+car.getMotor()+"', '"+car.getColor()+"', "+car.getSpeed()+", "+car.getDoors()+""
						+ "'"+car.getAbout()+"','"+car.getModel()+"', '"+car.getType()+"', '"+car.getName()+"', "+car.getCc()+""
								+ ""+car.getPricePerH()+","+car.getRate()+")");
	}

	public static boolean deleteCarForSell(int id) {
		return editDataBase("delete from carforsell where carId="+id+"");
	}

	public static boolean deleteCarForRent(int id) {
		return editDataBase("delete from carforrent where carId="+id+"");
	}

	public static ArrayList<CarForRent> findRentByModel(String carModel) {
		ResultSet carInfo= select("select * from carforrent where model like'%"+carModel+"%'");
		CarForRent m;
		ArrayList<CarForRent>list= new ArrayList<>();
		while((m=careateCarRent(carInfo, "", ""))!=null){
			list.add(m);
		}
		return list;
	}

	public static ArrayList<CarForRent> findRentByName(String carName) {
		ResultSet carInfo= select("select * from carforrent where name like'%"+carName+"%'");
		CarForRent m;
		ArrayList<CarForRent>list= new ArrayList<>();
		while((m=careateCarRent(carInfo, "", ""))!=null){
			list.add(m);
		}
		return list;
	}
	
	public static ArrayList<CarForRent> findRentByDate(String from, String to) {
		ArrayList<CarForRent>list= new ArrayList<>();
		ArrayList<Integer> unAllowed=new ArrayList<>();
		ResultSet resultDate= select("select carId from rentedcars where ('"+from+"','"+to+"') OVERLAPS (rentFrom,rentTo)");
		try {
			while(resultDate.next()){
				unAllowed.add(resultDate.getInt("carId"));
			}
		} catch (SQLException |NullPointerException e) {}
		StringBuffer cond= new StringBuffer();
		if(!unAllowed.isEmpty()){
			cond.append(" where carId !="+unAllowed.get(0)+" ");
			for(int x=1;x<unAllowed.size();x++)
				cond.append(" and != "+unAllowed.get(x)+" ");
		}
		ResultSet carInfo=select("select * from carforrent "+cond+"");
		CarForRent e;
		while((e=careateCarRent(carInfo, "", ""))!=null)
			list.add(e);
		return list;
	}

	public static ArrayList<CarForSell> findSellByNameAndModel(String carName, String carModel) {
		ResultSet carInfo= select("select * from carforsell where model like'%"+carModel+"%' and name like '%"+carName+"%'");
		CarForSell m;
		ArrayList<CarForSell>list= new ArrayList<>();
		while((m=createCarForSell(carInfo))!=null){
			list.add(m);
		}
		return list;
	}

	public static ArrayList<CarForSell> findSellByModel(String Model) {
		ResultSet carInfo= select("select * from carforsell where model like'%"+Model+"%'");
		CarForSell m;
		ArrayList<CarForSell>list= new ArrayList<>();
		while((m=createCarForSell(carInfo))!=null){
			list.add(m);
		}
		return list;
	}

	public static ArrayList<CarForSell> findSellByName(String carName) {
		ResultSet carInfo= select("select * from carforsell where name like'%"+carName+"%'");
		CarForSell m;
		ArrayList<CarForSell>list= new ArrayList<>();
		while((m=createCarForSell(carInfo))!=null){
			list.add(m);
		}
		return list;
	}

	public static ArrayList<CarForRent> findRentByNameModelAndDate(String carName, String carModel,
			SimpleDateFormat from, SimpleDateFormat to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByModelAndDate(String carNameOrModel, String from,String to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByNameAndDate(String carNameOrModel, String from,String to) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CarForRent> findRentByNameAndModel(String carName, String carModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean exists(String email) {
		return editDataBase("update user set email="+email+" where email="+email+"");
	}

}