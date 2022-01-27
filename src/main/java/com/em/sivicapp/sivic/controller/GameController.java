package com.em.sivicapp.sivic.controller;

import com.em.sivicapp.sivic.model.Record;
import com.em.sivicapp.sivic.model.Game;
import com.em.sivicapp.sivic.repository.GameRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/demo/game")
public class GameController {

    @Autowired
    private GameRepo gameRepo;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Game g = new Game();

        g.setName("deneme");
        g.setTar(LocalDateTime.now());

        gameRepo.save(g);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Game> getAllUsers() {
        // This returns a JSON or XML with the users
        return gameRepo.findAll();
    }

    @GetMapping("/deneme")
    public String deneme() {

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
        return "k";
    }
}
//    {
//
//
////        String url = args[0];
//            String url = "https://eshop-prices.com/games/on-sale?currency=TRY";
//            url ="https://eshop-prices.com/games/on-sale?currency=TRY&page=1";
//
//            Map<String, BigDecimal> test = new HashMap<>();
//            List<String> names = new ArrayList<>();
//            List<BigDecimal> prices = new ArrayList<>();
//
//
//            Document doc = null;
//            try {
//                doc = Jsoup.connect(url).get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (doc != null) {
//                Elements nameList = doc.select(".games-list-item-title h5");
//                Elements priceList = doc.select(".price .price-tag");
//
////            for (Element name : nameList) {
////                String gameName = name.childNodes().get(0).toString();
////                names.add(gameName);
////            }
////
////            for(Element name : priceList) {
////                String price = name.childNodes().get(2).toString();
////                String priceFormatted = price.substring(2).replaceAll(",", ".").trim();
////                prices.add(new BigDecimal(priceFormatted));
////                System.out.println();
////            }
////
////            for(int i=0; i<names.size(); i++) {
////                test.put(names.get(i), prices.get(i) );
////                System.out.println(names.get(i) + " " + prices.get(i));
////            }
//
//                for(int i=0; i<nameList.size(); i++) {
//                    String name = nameList.get(i).childNodes().get(0).toString();
//                    String price = priceList.get(i).childNodes().get(2).toString();
//                    String priceFormatted = price.substring(2).replaceAll(",", ".").trim();
//                    test.put(name, new BigDecimal(priceFormatted));
//                }
//
//
//
//                System.out.println(test);
//            }
//        }
//    }