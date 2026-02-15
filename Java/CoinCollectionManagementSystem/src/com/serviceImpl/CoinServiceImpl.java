package com.serviceImpl;

import com.DAO.CoinDAO;
import com.model.Coin;
import com.services.CoinService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

public class CoinServiceImpl implements CoinService {
    private CoinDAO coinDAO;

    public CoinServiceImpl(CoinDAO coinDAO) {
        this.coinDAO = coinDAO;
    }

    @Override
    public void addCoin(Coin coin) {
        coinDAO.addCoin(coin);
    }

    @Override
    public void deleteCoin(int id) {
        coinDAO.deleteCoin(id);
    }

    @Override
    public List<Coin> getAllCoins() {
        return coinDAO.getAllCoins();
    }

    @Override
    public List<Coin> searchByCountry(String country) {
        return coinDAO.searchByCountry(country);
    }

    @Override
    public List<Coin> searchByYear(int year) {
        return coinDAO.searchByYear(year);
    }

    @Override
    public List<Coin> searchByValueSorted() {
        return coinDAO.searchByValueSorted();
    }

    @Override
    public Coin searchByCountryAndDenomination(String country, String denomination) {
        return coinDAO.searchByCountryAndDenomination(country, denomination);
    }

    @Override
    public void loadFromDatabase() {
        coinDAO.loadAllFromDatabase();
    }

//    @Override
//    public void saveToDatabase() {
//        coinDAO.saveAllToDatabase(coinDAO.getAllCoins());
//    }

    @Override
    public void bulkUpload(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Coin coin = new Coin(0, parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]),
                        LocalDate.parse(parts[4]));
                addCoin(coin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
