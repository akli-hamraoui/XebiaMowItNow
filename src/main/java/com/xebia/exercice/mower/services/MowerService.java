package com.xebia.exercice.mower.services;

import com.xebia.exercice.mower.enums.EInstruction;
import com.xebia.exercice.mower.enums.EOrientation;
import com.xebia.exercice.mower.model.Lawn;
import com.xebia.exercice.mower.model.Mower;

/**
 * @author ahamraoui implements business interface of Mower
 * 
 */
public class MowerService implements IMowerService {

	private Lawn lawn;

	/**
	 * @param lawn
	 */
	public MowerService(Lawn lawn) {
		super();
		this.lawn = lawn;
	}

	@Override
	public String getPosition(Mower mower) {
		return mower.getX() + " " + mower.getY() + " "
				+ mower.getOrientation().toString();
	}

	@Override
	public void navigate(Mower mower, String instruction) {
		char[] arrayInst = instruction.toCharArray();
		for (int i = 0; i < arrayInst.length; i++) {
			String Inst = String.valueOf(arrayInst[i]);
			EInstruction enumInst = EInstruction.valueOf(Inst);
			if (enumInst == null) {
				throw new IllegalArgumentException("Incorrect Instruction : "
						+ Inst);
			}
			switch (enumInst) {
			// A A D A A D A D D A 3 3 E
			case A:
				EOrientation or = mower.getOrientation();
				switch (or) {
				case N:
					int yn = mower.getY();
					int newYn = yn + 1;
					// do nothing if the new y is outside the lawn
					if (newYn <= lawn.getCsdy()) {
						mower.setY(newYn);
					}
					break;
				case S:
					int ys = mower.getY();
					int newYs = ys - 1;
					// do nothing if the new y is outside the lawn
					if (newYs >= lawn.cigy) {
						mower.setY(newYs);
					}
					break;
				case E:
					int xe = mower.getX();
					int newXe = xe + 1;
					// do nothing if the new x is outside the lawn
					if (newXe <= lawn.getCsdx()) {
						mower.setX(newXe);
					}
					break;

				case W:
					int xw = mower.getX();
					int newXw = xw - 1;
					// do nothing if the new x is outside the lawn
					if (newXw >= lawn.cigx) {
						mower.setX(newXw);
					}
					break;
				default:
					break;
				}
				break;
				
			case D:
				//don't use the switch under the parent switch because it's create nested cases  
				EOrientation mod = mower.getOrientation();
				if (mod.equals(EOrientation.N)) {
					mower.setOrientation(EOrientation.E);
				} else if (mod.equals(EOrientation.S)) {
					mower.setOrientation(EOrientation.W);
				} else if (mod.equals(EOrientation.E)) {
					mower.setOrientation(EOrientation.S);
				} else if (mod.equals(EOrientation.W)) {
					mower.setOrientation(EOrientation.N);
				}
				break;
			case G:
				EOrientation mog = mower.getOrientation();
				if (mog.equals(EOrientation.N)) {
					mower.setOrientation(EOrientation.W);
				} else if (mog.equals(EOrientation.S)) {
					mower.setOrientation(EOrientation.E);
				} else if (mog.equals(EOrientation.E)) {
					mower.setOrientation(EOrientation.N);
				} else if (mog.equals(EOrientation.W)) {
					mower.setOrientation(EOrientation.S);
				}
				break;
			default:
				break;
			}
		}
		
		System.out.println("Instruction : "+instruction);
		System.out.println("New Position : "+getPosition(mower));
		System.out.println("==================================================");
	}

}
