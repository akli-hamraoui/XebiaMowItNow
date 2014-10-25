package com.xebia.exercice.mower.model;

/**
 * @author ahamraoui
 *  Lawn model
 */
public class Lawn {
	
	private int csdx;
	private int csdy;
	public  final int cigx = 0;
	public  final int cigy = 0;
	
	
	/**
	 * @return
	 */
	public int getCsdx() {
		return csdx;
	}
	/**
	 * @param csdx
	 */
	public void setCsdx(int csdx) {
		this.csdx = csdx;
	}
	/**
	 * @return
	 */
	public int getCsdy() {
		return csdy;
	}
	/**
	 * @param csdy
	 */
	public void setCsdy(int csdy) {
		this.csdy = csdy;
	}
	@Override
	public String toString() {
		return "x = "+csdx+", y = "+csdy;
	}
}
