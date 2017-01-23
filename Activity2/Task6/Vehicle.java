package oopactivity2;

public abstract class Vehicle {
	protected float speed;
	
	
	public Vehicle(int numberOfWheels){
		assert numberOfWheels >= 2 && numberOfWheels <= 4;
	}
	
	public abstract void move();
	
	public void stop(){
		
	}
}
