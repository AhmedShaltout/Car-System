package system;

public class CarRentSystemException{
    
    public static boolean isUserThere(String email) throws Exception{
        if(!DB.exists(email))
            throw new Exception("user not found");
        return true;
    }
    
    public static boolean isEmail(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
    static boolean isString(String word) throws Exception{
		if(word!=null&&!word.equals("")){
			for(int y=0;y<word.length();y++ ){
				if(word.charAt(y)!=' '){
					return true;
				}
			}
		}
		throw new Exception("Invalid Text : "+word+"");
	}
    public static void phoneNumberException(String phone) throws Exception{
    	if(isNumber(phone)){
    		Long x=Long.parseLong(phone);
            if(phone.charAt(0)!='0'&&phone.charAt(1)!='1')
                throw new Exception("Phone number must start with 01");
            else if(x<1000000000||x>1999999999)
                throw new Exception("Enter a valid number");
            else if(phone.length()!=11)
                throw new Exception("Phone number must have 11 digits");
    	}
    }

	static boolean isNumber(String number) throws Exception{
		try{
			Long.parseLong(number);
			return true;
		}catch(Exception e){
			throw new Exception("Invalid Number : "+number+"");
		}
	}

    public static void mismatchCheckBox(int numbers) throws Exception{
        if(numbers==-1)
            throw new Exception("You need to choose from the menus");
    }

}