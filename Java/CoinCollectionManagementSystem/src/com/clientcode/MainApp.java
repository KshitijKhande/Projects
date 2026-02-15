package com.clientcode;
import com.DAOImpl.CoinDAOImpl;
import com.serviceImpl.CoinServiceImpl;
import com.controller.CoinController;

public class MainApp {
    public static void main(String[] args) {
        CoinDAOImpl dao = new CoinDAOImpl();
        CoinServiceImpl service = new CoinServiceImpl(dao);
        CoinController controller = new CoinController(service);
        
        service.loadFromDatabase();

        controller.start();
    }
}
