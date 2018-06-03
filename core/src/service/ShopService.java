package service;

import com.pl.firstclicker.FirstClickerGame;

public class ShopService {

    static FirstClickerGame game;

    public ShopService(FirstClickerGame game) {
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
