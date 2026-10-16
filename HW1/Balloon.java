package hw1;
/**
 * A class representing a Balloon that uses the given parameters. 
 * in seconds, minutes, and hours.
 * @author [redacted]
 * @version 1.0
 */
public class Balloon {
/**
 * Stores the value of Vertical Velocity.
 */
	private int verticalVel;
	/**
	 * Stores the value of Horizontal velocity.
	 */
	private int horizontalVel;
	/**
	 * Stores the value of the altitude.
	 */
	private int altitude;
	/**
	 * Stores the value of the max altitude.
	 */
	private int maxAlti;
	/**
	 * Stores the value of the ground position.
	 */
	private int groundPos;
	/**
	 * Stores the value of the max ground position
	 */
	private int maxGroundPos;
	/**
	 * Stores the value of fuel.
	 */
	private double fuel;
	/**
	 * Stores the value of the tank capacity
	 */
	private double tankCapacity;
	/**
	 * Stores the value of the max burn rate.
	 */
	private double maxBurnRate;
	/**
	 * Stores the value for time.
	 */
	private int time;
	
	/**
	 * The constructor initializes the balloon with the given parameters.
	 * All simulation factors were initialized to zero.
	 * @param groundPosition the initial position of the balloon relative to the ground.
	 * @param maxGroundPosition the initial position of the balloon relative to the ground.
	 * @param maxAltitude is the highest number for the balloon to fly (up towards the ceiling).
	 * @param tankCapacity  the max amount of fuel the fuel tank can contain.
	 */
	public Balloon(int groundPosition, int maxGroundPosition, int maxAltitude, double tankCapacity) {
	this.groundPos = groundPosition;
	this.maxGroundPos = maxGroundPosition;
	this.maxAlti = maxAltitude; 
	this.tankCapacity = tankCapacity;
	this.fuel = 0;
	this.altitude = 0;
	this.verticalVel = 0;
	this.horizontalVel = 0;
	this.time = 0;
		
	}
	/**
	 * Returns the current vertical velocity of the balloon. 
	 * @return an int value for the vertical velocity of the balloon.
	 */
	public int getVerticalVelocity() {
		return verticalVel;
	}
	/**
	 * Returns the current horizontal velocity of the balloon.
	 * @return an int value for the horizontal velocity of the balloon.
	 */
	public int getHorizontalVelocity() {
		return horizontalVel;
	}
	/**
	 * Returns the current altitude of the balloon.
	 * @return an int value for the current altitude of the balloon.
	 */
	public int getAltitude() {
		return altitude;
	}
	/**
	 * Returns the maximum altitude value. the balloon can't fly above this value.
	 * @return an int value for the max altitude the balloon can travel.
	 */
	public int getMaxAltitude() {
		return maxAlti;
	}
	/**
	 * Returns the current value of the position with respect to the ground.
	 * @return an int value for the current ground position (respect to the ground.)
	 */
	public int getGroundPosition() {
		return groundPos;
	}
	/**
	 * Returns the max value the balloon can travel before wrapping around to 0.
	 * @return an int value for the max ground position (farthest balloon can go.)
	 */
	public int getMaxGroundPosition() {
		return maxGroundPos;
	}
	/**
	 * Return the amount of fuel in the tank.
	 * @return an double value for the fuel in the fuel tank.
	 */
	public double getFuel() {
		return fuel;
	}
	/**
	 * Returns the max capacity for the fuel tank.
	 * @return a double value for the fuel tank the fuel in the tank can not go above this number.
	 */
	public double getTankCapacity() {
		return tankCapacity;
	}
	/**
	 * Returns the maximum rate the fuel can burn.
	 * @return a double value for the max rate the fuel can burn assuming there is enough fuel in the tank.
	 */
	public double getMaxBurnRate() {
		return maxBurnRate;
	}
	/**
	 * Sets the tank capacity for the balloon.
	 * @param tankCapacity is the current tank capacity of the balloon.
	 */
	public void setTankCapacity(double tankCapacity) {
		this.tankCapacity = tankCapacity;
	}
	/**
	 *  Sets the max burn rate for the fuel in the balloon.
	 * @param maxBurnRate is the max burn rate that is set.
	 */
	public void setMaxBurnRate(double maxBurnRate) {
		this.maxBurnRate = maxBurnRate;
	}
	/**
	 * Sets the total time the simulation has run in seconds in respect to the given parameter. 
	 * @param time is the amount of time the simulation has ran in seconds.
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * Set the ground position back to where the constructor set it.
	 * initialize it to zero in this case.
	 */
	public void restoreInitialGroundPosition() {
		groundPos = 0;
	}
	/**
	 * Gets the number of seconds past in the current minute 
	 * @return the equation used to convert a number in seconds to the remainder of a number moded by 60
	 * to give the leftover seconds. (in respect to the given time.) 
	 */
	public int getSeconds() {
		return time % 60;
	}
	/**
	 * Gets the number of minutes passed in an hour.
	 * @return the equation used to get the current amount of minutes by first dividing the number by 60.
	 * then finding the remainder of minutes with mod 60.(in respect to the current time.)
	 */
	public int getMinutes() {
		return (time / 60) % 60;  
	}
	/**
	 * Gets the amount of hours passed.
	 * @return The equation used to find the amount of hours have passed by the current time value. 
	 */
	public int getHours() {
		return (time/60) /60;
	}
	/**
	 * Changes the vertical velocity by the given delta. 
	 * @param delta A value that changes the vertical velocity (by adding the values together.)
	 */
	public void adjustVerticalVelocity(int delta) {
		verticalVel += delta;
	}
	/**
	 * Changes the horizontal velocity by the given delta.
	 * @param delta A value that changes the horizontal velocity (by adding the values together.)
	 */
	public void adjustHorizontalVelocity(int delta) {
		horizontalVel += delta;
	}
	/**
	 * adds the given amount of fuel to the tank without going above the tank capacity
	 * uses a math min to take the minimum of the two given values so it doesn't go above the tank capacity.
	 * @param amount the amount of fuel added to the tank
	 * @return The current fuel (added with the add fuel) or the max tank capacity depending if the fuel went over the max
	 */
	public double addFuel(double amount) {
		double addFuel = Math.min(amount, tankCapacity - fuel);
		fuel += addFuel;
		return addFuel;
	}
	/**
	 * Simulates one second passing by also simulating the fuel burned by the maximum burn rate,
	 * The change in the current altitude by adding the vertical velocity (can not go above the max altitude),
	 * and the current ground position (adding the horizontal velocity and the ground position) (maxground is added so
	 * if the ground position is moded it is not modding a negative number.)
	 * @return The actions that happen within a second of the simulation (the fuel burning, altitude increasing or
	 * decreasing and the current ground position).
	 */
	public double oneSecondUpdate() {
		double fuelBurn = Math.min(fuel , maxBurnRate);
		fuel -= fuelBurn;
		altitude = Math.min(verticalVel + altitude, maxAlti);
		groundPos = (horizontalVel + groundPos + maxGroundPos)  % maxGroundPos;
		time += 1;
		return fuelBurn; 
	}
}
