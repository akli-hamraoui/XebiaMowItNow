package com.xebia.exercice.mower.services;

import com.xebia.exercice.mower.model.Mower;


/**
 * @author ahamraoui
 * Expose business interface of Mower
 *
 */
public interface IMowerService {

	/**
	 * get position of the mower according with this pattern, "x y orientation"
	 * @param mower
	 * @return
	 */
	public String getPosition(Mower mower);

	/**
	 * navigate the mower and control it
	 * @param mower
	 * @param instruction
	 */
	public void navigate(Mower mower, String instruction);
}
