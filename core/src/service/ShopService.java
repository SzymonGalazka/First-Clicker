package service;

import com.pl.firstclicker.PierogiClicker;

public class ShopService {

    static PierogiClicker game;

    public ShopService(PierogiClicker game) {
        this.game = game;
    }

    public void openShop(){
        System.out.println("Dummy ShopService method");
    }

    public static void addPassiveIncomeFromShop(float toAdd, float price){
        game.getScoreService().addPassiveIncome(toAdd);
        game.getScoreService().addPoints(-price);
    }

    public static void addPointsFromShop(float toAdd,float price){
        game.getScoreService().addPoints(toAdd);
        game.getScoreService().addPoints(-price);
    }


}
