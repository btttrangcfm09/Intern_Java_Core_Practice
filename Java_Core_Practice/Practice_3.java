import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Practice_3{
	public static void test() {
		Management m = new Management();
		m.ManageVehice();
	}
}
class VehicleOwner{
	private String cmndNumber;
	private String fullName;
	private String email;
	
	// contructor
	public VehicleOwner(String cmndNumber, String fullName, String email) {
		super();
		this.cmndNumber = cmndNumber;
		this.fullName = fullName;
		this.email = email;
	}

	// getter & setter
	public String getCmndNumber() {
		return cmndNumber;
	}

	public void setCmndNumber(String cmndNumber) {
		this.cmndNumber = cmndNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
class Management{
	private ArrayList<Vehicle> items = new ArrayList<Vehicle>();
	private Scanner Obj = new Scanner(System.in);
	private ArrayList<VehicleOwner> owners = new ArrayList<VehicleOwner>();
	
	public void ManageVehice() {
		while(true) {
			System.out.println("Chọn các chức năng sau");
            System.out.println("1. Thêm phương tiện vận chuyển");
            System.out.println("2. Tìm kiếm theo vehicle number");
            System.out.println("3. Tìm kiếm xe của chủ xe có owner CMND");
            System.out.println("4. Xóa tất cả xe của nhà cung cấp theo manufacturer");
            System.out.println("5. Tìm nhà cung cấp có nhiều xe nhất");
            System.out.println("6. Sắp xếp xe theo số lượng xe theo thứ số lượng giảm dần");
            System.out.println("7. Thống kê số lượng xe theo từng loại xe");
            System.out.println("0. Thoát chương trìnhtrình");
            int lchon = Obj.nextInt();
            if(lchon == 1) addVehicle();
            else if(lchon == 2) {
            	System.out.println("Nhap vehicle number");
            	String number = Obj.nextLine();
            	Vehicle  res = searchByVehicleNumber(number);
            	res.inTTin();
            }
            else if(lchon == 3) {
            	System.out.println("Nhap CMND");
            	String cmnd = Obj.nextLine();
            	Vehicle res = searchByCmndNumber(cmnd);
            	res.inTTin();
            }
            else if(lchon == 4) {
            	System.out.println("Nhap ten nha sx");
            	String nsx = Obj.nextLine();
            	deleteAlVehicleByManufacturer(nsx);
            }
            else if(lchon == 5) {
            	ArrayList<String> res = showManufacturerHasTheMostVehicle();
            	for(String s : res ) {
            		System.out.println(s);
            	}
            }
            else if(lchon == 6) {
            	List<String> res = sortVehicleType();
            	for(String s : res ) {
            		System.out.println(s); 
            	}
            }
            else if(lchon == 7) {
            	staticVehicleType();
            }
            else {
            	break;
            }
		}
	}
	
	public void addVehicle() {
		Vehicle pt = null;
		System.out.println("Nhap Vehicle number(5 ky tu):");
		String number = Obj.nextLine();
		if(checkVehicleNumber(number) == false) throw new IllegalArgumentException("Vehicle number da ton tai hoac khong hop le");
		System.out.println("Nhap nha san xuat (Honda, Yamaha, Toyota, Suzuki)");
		String manufacturer = Obj.nextLine();
		if(checkManufacturer(manufacturer) == false) throw new IllegalArgumentException("Da ton tai hoac khong hop le");
		System.out.println("Nhap nam san xuat (> 2000 va <= nam hien tai)");
		int yearOfManufacturer = Obj.nextInt();
		if(checkYear(yearOfManufacturer) == false) throw new IllegalArgumentException("Khong hop le");
		System.out.println("Nhap mau");
		String color = Obj.nextLine();
		
		System.out.println("Nhap ID number(12 ki tu)");
		String id = Obj.nextLine();
		if(checkID(id) == false) throw new IllegalArgumentException("Da ton tai hoac khong hop le");
		System.out.println("Nhap email");
		String email = Obj.nextLine();
		if(checkEmail(email) == false) throw new IllegalArgumentException("Khong hop le");
		System.out.println("Nhap ten");
		String name = Obj.nextLine();
		VehicleOwner vo = new VehicleOwner(id, name, email);
		owners.add(vo);
		
		System.out.println("1: Car\n2: Motorbike\n3:Truck");
		int lchon = Obj.nextInt();        
		if(lchon == 1) {
			System.out.println("So cho ngoi");
			int s = Obj.nextInt();
			System.out.println("Nhap engine type");
			String et = Obj.nextLine();
			pt = new Car(number, manufacturer, yearOfManufacturer, color, vo, s, et);
			
		}
		else if(lchon == 2) {
			System.out.println("Nhap capacity");
			int capacity = Obj.nextInt();
			pt = new Motorcycle(number, manufacturer, yearOfManufacturer, color, vo, capacity);
		}
		else {
			System.out.println("Nhap tonnage");
			double t = Obj.nextDouble();
			pt = new Truck(number, manufacturer, yearOfManufacturer, color, vo, t);
		}
		items.add(pt);
	}
	
	// 1
	public Vehicle searchByVehicleNumber(String vehicleNumber) {
		for(Vehicle item : items) {
			if(item.number == vehicleNumber) return item;
		}
		return null;
	}
	//2
	public Vehicle searchByCmndNumber(String cmndNumber) {
		for(Vehicle item : items) {
			VehicleOwner owner = item.owner;
			if(owner.getCmndNumber().equals(cmndNumber)) return item;
		}
		return null;
	}
	//3
	public void deleteAlVehicleByManufacturer(String manufacturer) {
		for(Vehicle item : items) {
			if(item.manufacturer.equalsIgnoreCase(manufacturer)) {
				items.remove(item);
			}
		}
	}
	//4
	public ArrayList<String> showManufacturerHasTheMostVehicle(){
		HashMap<String,Integer> manufacturers = new HashMap<>();
		ArrayList<String> result = new ArrayList<>();
		int maxQuantity = -1;
		for(Vehicle item:items) {
			manufacturers.put(item.manufacturer, manufacturers.get(item.manufacturer)+1);
			maxQuantity = Math.max(maxQuantity,(int) manufacturers.get(item.manufacturer));
		}
		for(Map.Entry<String,Integer> x : manufacturers.entrySet()) {
			if(x.getValue() == maxQuantity) result.add(x.getKey());
		}
		return result;
	}
	
	public Map<Vehicle,Integer> mapAndSortVehicleType(){
		HashMap<Vehicle,Integer> vehicleList = new HashMap<>();
		for(Vehicle item:items) {
			vehicleList.put(item, vehicleList.get(item)+1);
		}
		Map<Vehicle, Integer> sortedMapDesc = vehicleList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
		return sortedMapDesc;
	}
	//7
	public void staticVehicleType(){
		Map<Vehicle, Integer> sortedMapDesc = mapAndSortVehicleType();
		for(Map.Entry<Vehicle,Integer> x : sortedMapDesc.entrySet()) {		
			System.out.println(x.getKey().getType() + ": " + x.getValue());
		}		
	}
	//6
	public List<String> sortVehicleType(){
		Map<Vehicle, Integer> sortedMapDesc = mapAndSortVehicleType();
		List<String> result = sortedMapDesc.keySet().stream().map(Vehicle::getType).collect(Collectors.toList());
		return result;
		
	}
	
	// cac ham phu khac
	// check
	public boolean checkVehicleNumber(String number) {
		if(number.length() != 5) {
			return false;
		}
		for(Vehicle item : items) {
			if(item.number.equals(number)) {
				return false;				
			}
		}
		return true;
	}
	public boolean checkManufacturer(String M) {
		String[] Mans = new String[]{"Honda", "Yamaha", "Toyota", "Suzuki"};
		List<String> listMans = new ArrayList<>(Arrays.asList(Mans));
		if(!listMans.contains(M)) {
			return false;
		}
		return true;
	}
	public boolean checkYear(int y) {
		LocalDate today = LocalDate.now();
		if(y > 2000 && y <= today.getYear()) {
			return true;
		}
		return false;
	}
	public boolean checkID(String id){
		if(id.length() != 12) {
			return false;
		}
		for(VehicleOwner o : owners) {
			if(o.getCmndNumber().contains(id)) {
				return false;				
			}
		}		
		return true;
	}
	public boolean checkEmail(String email) {
	    String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    return email.matches(EMAIL_REGEX);
	}
	
}
abstract class Vehicle{
	protected String number;
	protected String manufacturer;
	protected int yearOfManufacturer;
	protected String color;
	protected VehicleOwner owner;
	
	// contructor
	public Vehicle(String number, String manufacturer, int yearOfManufacturer, String color, VehicleOwner owner) {
		super();
		this.number = number;
		this.manufacturer = manufacturer;
		this.yearOfManufacturer = yearOfManufacturer;
		this.color = color;
		this.owner = owner;
	}

	// getter && setter
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getYearOfManufacturer() {
		return yearOfManufacturer;
	}

	public void setYearOfManufacturer(int yearOfManufacturer) {
		this.yearOfManufacturer = yearOfManufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public VehicleOwner getOwner() {
		return owner;
	}

	public void setOwner(VehicleOwner owner) {
		this.owner = owner;
	}
	
	public abstract String getType();
	
	public void inTTin() {
		System.out.println("Number Vehicle: " + this.number);
		System.out.println("Manufacrer: " + this.manufacturer);
		System.out.println("Nam san xuat: " + this.yearOfManufacturer);
		System.out.println("Mau: " + this.color);
    }
}
class Car extends Vehicle{
	private int numberOfSeat;
	private String engineType;
	private String type = "Car";
	// contructor
	public Car(String number, String manufacturer, int yearOfManufacturer, String color, VehicleOwner owner, int numberOfSeat, String engineType) {
		super(number, manufacturer, yearOfManufacturer, color, owner);
		this.numberOfSeat = numberOfSeat;
		this.engineType = engineType;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public void inTTin() {
		super.inTTin();
		System.out.println("So cho ngoi: " + this.numberOfSeat);
		System.out.println("loai dong co: " + this.engineType);
		System.out.println("Loai phuong tien: " + this.type);
	}
}

class Motorcycle extends Vehicle{
	private int capacity;
	private String type = "Motorcycle";
	// contructor
	public Motorcycle(String number, String manufacturer, int yearOfManufacturer, String color, VehicleOwner owner, int capacity) {
		super(number, manufacturer, yearOfManufacturer, color, owner);
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public void inTTin() {
		super.inTTin();
		System.out.println("Capacity: " + this.capacity);
		System.out.println("loai phuong tien: " + this.type);
	}
}

class Truck extends Vehicle{
	private double tonnage;
	private String type  = "Truck"; 
	// contructor
	public Truck(String number, String manufacturer, int yearOfManufacturer, String color, VehicleOwner owner, double tonnage) {
		super(number, manufacturer, yearOfManufacturer, color, owner);
		this.tonnage = tonnage;
	}

	// getter & setter
	public double getTonnage() {
		return tonnage;
	}


	public void setTonnage(double tonnage) {
		this.tonnage = tonnage;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	 @Override
	 public void inTTin() {
		 super.inTTin();
		 System.out.println("Tonnage: " + this.tonnage);
		 System.out.println("loai phuong tien: " + this.type);
	 }
}



