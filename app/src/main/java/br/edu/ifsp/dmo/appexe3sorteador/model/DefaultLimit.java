package br.edu.ifsp.dmo.appexe3sorteador.model;

public class DefaultLimit extends SorteioStrategy{

    private static final int BORDER_DEFAULT = 1000;
    private static DefaultLimit instance;


    private DefaultLimit() {
    }

    public static DefaultLimit getInstance(){
        if(instance == null){
            instance = new DefaultLimit();
        }
        return instance;
    }

    @Override
    public int nextNumber() {
        return mRandom.nextInt(BORDER_DEFAULT) + 1;
    }

    @Override
    public int getLowBorder() {
        return 1;
    }

    @Override
    public int getHighBorder() {
        return BORDER_DEFAULT;
    }
}