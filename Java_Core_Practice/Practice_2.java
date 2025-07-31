import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Practice_2{
	public static void demoForPractice2() {
		DSHHManagement.createDSHH();
	}
}

class DSHHManagement{
	public static void createDSHH(){
		ArrayList<Product> items = new ArrayList<Product>();
		System.out.println("Chon loai san phan ban muon them vao:");
		System.out.println("1: Food.");
		System.out.println("2: Electronic.");
		System.out.println("3: Crockery.");
		Scanner Obj = new Scanner(System.in);
		int loai = Obj.nextInt();
		System.out.println("Nhap product code:");
		String productCode;
		productcode:
		while(true) {
			productCode = Obj.nextLine();
			for(Product item : items) {
				if(!item.code.equals(productCode)) {
					break productcode;
				}
				else {
				    System.out.println("product code da ton tai. Vui long nhap cai khac");
				}
			}			
		}
		
		System.out.println("Nhap ten san pham");
		String productName = Obj.nextLine();
		System.out.println("Nhap so luong trong kho");
		int inventoryQuantity = Obj.nextInt();
		System.out.println("Nhap gia");
		double price = Obj.nextDouble();
		if(loai == 1) {
			System.out.println("Nhap ngay san xuat");
			int year = Obj.nextInt(), month = Obj.nextInt(),date = Obj.nextInt();
			LocalDate dateOfManufature = LocalDate.of(year, month, date);
			System.out.println("Nhap ngay het han");
			int year2 = Obj.nextInt(), month2 = Obj.nextInt(),date2 = Obj.nextInt();
			LocalDate dateOfExpiration = LocalDate.of(year2, month2, date2);
			System.out.println("Nhap ten nha san xuat");
			String supplier = Obj.nextLine();
			Food f = new Food(productCode,productName,inventoryQuantity,price,dateOfManufature,dateOfExpiration, supplier);
			items.add(f);
		}
		else if(loai == 2) {
			System.out.println("Nhap thoi han(so thang tinh tu luc mua) bao hanh");
			double monthWarrantyPeriod = Obj.nextDouble();
			System.out.println("Nhap thoi luong pin cua thiet bi");
			int capacity = Obj.nextInt();
			Electronics e = new Electronics(productCode,productName,inventoryQuantity,price,monthWarrantyPeriod,capacity);
			items.add(e);
		}
		else {
			System.out.println("Nhap thong tin ve san pham");
			String information = Obj.nextLine();
			System.out.println("Nhap ngay nhap");
			int year = Obj.nextInt(), month = Obj.nextInt(),date = Obj.nextInt();
			LocalDate dateOfArrival = LocalDate.of(year, month, date);
			Crockery c = new Crockery(productCode,productName,inventoryQuantity,price,information,dateOfArrival);
			items.add(c);
		}
	}
}

abstract class Product{
	protected String code;
	protected String name;
	protected int inventoryQuantity;
	protected double unitPrice;
	protected double vatValue;
	
	// constructor
	public Product(String code, String name, int inventoryQuantity, double unitPrice) {
		super();
		this.code = code;
		this.name = name;
		this.inventoryQuantity = inventoryQuantity;
		this.unitPrice = unitPrice;
	}
	
	// setter && getter
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getVAT() {
		return this.vatValue;
	}
	
	public void inTTin() {
		System.out.println("Ten san pham: " + this.name);
		System.out.println("So luong trong kho: " + this.inventoryQuantity);
		System.out.println("VAT: " + this.vatValue);
	}
	
	public abstract String measureConsumption(); // true is sold 
}

class Food extends Product{
	private LocalDate dateOfManufature;
	private LocalDate dateOfExpiration;
	// contructor
	public Food(String code, String name, int inventoryQuantity, double unitPrice, LocalDate dateOfManufature,
			LocalDate dateOfExpiration, String supplier) {
		super(code, name, inventoryQuantity, unitPrice);
		this.dateOfManufature = dateOfManufature;
		this.dateOfExpiration = dateOfExpiration;
		this.supplier = supplier;
		this.vatValue = 0.05;
	}
	
	//getter && setter
	public LocalDate getDateOfManufature() {
		return dateOfManufature;
	}
	public void setDateOfManufature(LocalDate dateOfManufature) {
		this.dateOfManufature = dateOfManufature;
	}
	public LocalDate getDateOfExpiration() {
		return dateOfExpiration;
	}
	public void setDateOfExpiration(LocalDate dateOfExpiration) {
		this.dateOfExpiration = dateOfExpiration;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	private String supplier;
	@Override
	public String measureConsumption() {
		// TODO Auto-generated method stub
		String trangThai = "Sold";
		LocalDate today = LocalDate.now();
		if(this.inventoryQuantity > 0 && this.dateOfExpiration.isBefore(today)) {
			System.out.println("San pham " + this.name + " kho ban");
			trangThai = "Hard to sell";
		}
		return trangThai;
	}
	
}

class Electronics extends Product{
	private double monthWarrantyPeriod;
	private int capacity;
	
	// contructor
	public Electronics(String code, String name, int inventoryQuantity, double unitPrice, double monthWarrantyPeriod,
			int capacity) {
		super(code, name, inventoryQuantity, unitPrice);
		this.monthWarrantyPeriod = monthWarrantyPeriod;
		this.capacity = capacity;
		this.vatValue = 0.05;
	}

	// getter && setter
	public double getMonthWarrantyPeriod() {
		return monthWarrantyPeriod;
	}
	public void setMonthWarrantyPeriod(double monthWarrantyPeriod) {
		this.monthWarrantyPeriod = monthWarrantyPeriod;
	}
	public int getCapacity() {
		return this.capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String measureConsumption() {
		// TODO Auto-generated method stub
		if (this.inventoryQuantity < 3) {
			System.out.println("San pham " + this.name + " da ban");
			return "Sold";
		}
		return "Hard to sell";
	}
}

class Crockery extends Product{
	private String information;
	private LocalDate dateOfArrival;
	
	// contructor
	public Crockery(String code, String name, int inventoryQuantity, double unitPrice, String information,
			LocalDate dateOfArrival) {
		super(code, name, inventoryQuantity, unitPrice);
		this.information = information;
		this.dateOfArrival = dateOfArrival;
		this.vatValue = 0.05;
	}

	// getter && setter
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public LocalDate getDateOfArrival() {
		return dateOfArrival;
	}

	public void setDateOfArrival(LocalDate dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}

	@Override
	public String measureConsumption() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		if (this.inventoryQuantity > 50 && this.dateOfArrival.isBefore(today) 
			&& ChronoUnit.DAYS.between(this.dateOfArrival, today) > 10) {
			System.out.println("San pham " + this.name + "kho ban");
			return "Hard to sell";
		}
		return "Sold";
	}
}
