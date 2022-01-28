package com.em.sivicapp.sivic.service;

import com.em.sivicapp.sivic.model.Game;
import com.em.sivicapp.sivic.model.Record;
import com.em.sivicapp.sivic.repository.GameRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepo;

    public String deneme(String id){
        for (int ii = 0; ii < 400; ii++) {
            System.out.println("selam " + ii + "as" + LocalDateTime.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("selam " + ii + "as" + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("selam " + ii + "as" + LocalDateTime.now());
            String url = "https://eshop-prices.com/games/on-sale?currency=TRY&page=" + ii;
            url = "https://eshop-prices.com/games?currency=TRY&page=" + ii;
            List<Record> list = new ArrayList<>();

            Document doc = null;
            try {
                System.out.println(">");
                doc = Jsoup.connect(url).get();
                System.out.println("+");
            } catch (IOException e) {
                System.out.println("- " + e.getMessage());
                e.printStackTrace();
            }
            if (doc != null) {
                Elements nameList = doc.select(".games-list-item-title h5");
                Elements priceList = doc.select(".price .price-tag");

                for (int i = 0; i < nameList.size(); i++) {
                    String name = nameList.get(i).childNodes().get(0).toString();
//                    String price = priceList.get(i).childNodes().get(0).toString().trim();

//                    System.out.println(">>>price: " + price);

//                    BigDecimal tut = null;

//                    if(price!=null && !price.isBlank() && !price.isBlank()) {
//                        String priceFormatted = price.substring(1).replaceAll(",", ".").trim();
//                        tut = new BigDecimal(priceFormatted);
//                    }
                    Game g = new Game();
                    //TODO: builder yap knk
                    g.setName(name);
                    //g.setNewPrice(tut);
                    g.setTar(LocalDateTime.now());

                    Game gg = gameRepo.findByName(name);


                    System.out.println(gg);
                    if (gg == null) {
                        System.out.println("katdediyom knk bunu: " + g.getName());
                        gameRepo.save(g);
                    } else {
                        System.out.println("aynisi varmis knk" + g.getName());
                    }
                }
            } else {
                System.out.println("dohuman yoh kanha");
            }
        }

        System.out.println("kibcuseeyoubyes");
        return "kdak";
    }

    public Iterable<Game> findAll() {
        return gameRepo.findAll();
    }

    public void save(Game g) {
        Game existingGame = gameRepo.findByName(g.getName());
        if(existingGame==null) {
            gameRepo.save(g);
        }
    }

    public Game findByName(String name) {
        return gameRepo.findByName(name);
    }
}
