import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(Stock stock, double price);
}

class Stock {
    private String symbol;
    private double price;
    private List<Observer> observers;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    public void registerInvestor(Investor investor) {
        observers.add(investor);
    }

    public void unregisterInvestor(Investor investor) {
        observers.remove(investor);
    }

    public void updatePrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Observer observer : observers) {
            observer.update(this, price);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}

class Investor implements Observer {
    private String name;
    private List<Stock> stocks;

    public Investor(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public void investInStock(Stock stock) {
        stocks.add(stock);
        stock.registerInvestor(this);
    }

    public void divestFromStock(Stock stock) {
        stocks.remove(stock);
        stock.unregisterInvestor(this);
    }

    @Override
    public void update(Stock stock, double price) {
        System.out.println("Dear " + name + ", the price of stock " + stock.getSymbol() + " has been updated to: " + price);
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Stock appleStock = new Stock("TEST1", 150.0);
        Stock googleStock = new Stock("TEST2", 2500.0);

        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        investor1.investInStock(appleStock);
        investor2.investInStock(googleStock);

        appleStock.updatePrice(155.0);
        googleStock.updatePrice(2550.0);

        investor1.divestFromStock(appleStock);

        appleStock.updatePrice(160.0);
    }
}
