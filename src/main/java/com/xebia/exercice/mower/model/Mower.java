package com.xebia.exercice.mower.model;

import com.xebia.exercice.mower.enums.EOrientation;



/**
 * @author ahamraoui
 * Mower model
 *
 */
public class Mower {
	
	private int x;
	private int y;
	private EOrientation orientation;
	
	
	/**
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return
	 */
	public EOrientation getOrientation() {
		return orientation;
	}
	/**
	 * @param orientation
	 */
	public void setOrientation(EOrientation orientation) {
		this.orientation = orientation;
	}
	
	
}
