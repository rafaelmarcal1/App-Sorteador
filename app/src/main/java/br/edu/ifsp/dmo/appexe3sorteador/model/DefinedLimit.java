package br.edu.ifsp.dmo.appexe3sorteador.model;

public class DefinedLimit extends SorteioStrategy{

    private int mBorder;

    public DefinedLimit(int border) {
        this.mBorder = border;
    }

    @Override
    public int nextNumber() {
        return mRandom.nextInt(this.mBorder) + 1;
    }

    @Override
    public int getLowBorder() {
        return 1;
    }

    @Override
    public int getHighBorder() {
        return mBorder;
    }

}