package service;

import com.pl.firstclicker.PierogiClicker;

public class ShopService {

    static PierogiClicker game;

    public ShopService(PierogiClicker game) {
        ShopService.game = game;
    }


    public static void addPassiveIncomeFromShop(float toAdd, float price){
        game.getScoreService().addPassiveIncome(toAdd);
        game.getScoreService().addPoints(-price);
    }
    public static void multiplyPassiveIncomeFromShop(float toAdd, float price){
        game.getScoreService().multiplyPassiveIncome(toAdd);
        game.getScoreService().addPoints(-price);
    }
    public static void multiplyPointsFromShop(float toAdd, float price){
        game.getScoreService().multiplyClickvalue(toAdd);
        game.getScoreService().addPoints(-price);
    }


}
