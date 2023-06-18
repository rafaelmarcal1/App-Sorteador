package br.edu.ifsp.dmo.appexe3sorteador.model;

import java.util.List;

public class Sorteio {

    private final int REGISTER_SIZE = 5;
    private SorteioStrategy mSorteioStrategy;
    private static int[] mRegister;
    private static int mLastRegister;

    public Sorteio() {
        mSorteioStrategy = DefaultLimit.getInstance();
        initRegister();
    }

    public Sorteio(int border) {
        mSorteioStrategy = new DefinedLimit(border);
        initRegister();
    }

    public int getNumber() {
        int value = mSorteioStrategy.nextNumber();
        enqueueRegister(value);
        return value;
    }

    public int getLowBorder() {
        return mSorteioStrategy.getLowBorder();
    }

    public int getHighBorder() {
        return mSorteioStrategy.getHighBorder();
    }

    public String getRegister() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= mLastRegister; i++) {
            sb.append(i + 1);
            sb.append("º sorteio: ");
            sb.append(mRegister[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void enqueueRegister(int value) {
        if (mLastRegister < REGISTER_SIZE - 1) {
            mLastRegister += 1;
            mRegister[mLastRegister] = value;
        } else {
            for (int i = 1; i < REGISTER_SIZE; i++) {
                mRegister[i - 1] = mRegister[i];
            }
            mRegister[mLastRegister] = value;
        }
    }

    private void initRegister() {
        if (mRegister == null) {
            mRegister = new int[5];
            mLastRegister = -1;
        }
    }
}