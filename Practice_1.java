public class Practice_1{
	public static void demoForPractice1() {
		Rectangle rec = new Rectangle(2,3);
		System.out.println("Chu vi cua hinh chu nhat" + rec.getPerimeter());
		System.out.println("Dien tich cua hinh chu nhat" + rec.getArea());
		Circle cir = new Circle(4);
		System.out.println("Chu vi cua hinh tron" + cir.getPerimeter());
		System.out.println("Dien tich cua hinh tron" + cir.getArea());
		
	}
}
abstract class Shape{
	
	public abstract double getArea();
	public abstract double getPerimeter();
}

class Rectangle extends Shape{
	private double width;
	private double height;
	
	
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return this.width * this.height;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return 2*(this.height + this.width);
	}
}

class Circle extends Shape{
	private double r;
	
	public Circle(double r) {
		super();
		this.r = r;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return this.r * this.r * 3.14;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return this.r * 2 * 3.14;
	}
}
