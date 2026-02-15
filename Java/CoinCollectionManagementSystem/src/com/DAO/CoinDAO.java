package com.DAO;

import com.model.Coin;
import java.util.List;

public interface CoinDAO {
    void addCoin(Coin coin);
    void deleteCoin(int id);
    List<Coin> getAllCoins();
    List<Coin> searchByCountry(String country);
    List<Coin> searchByYear(int year);
    List<Coin> searchByValueSorted();
    Coin searchByCountryAndDenomination(String country, String denomination);

    List<Coin> loadAllFromDatabase();

}
