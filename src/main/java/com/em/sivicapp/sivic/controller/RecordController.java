package com.em.sivicapp.sivic.controller;

import com.em.sivicapp.sivic.model.Record;
import com.em.sivicapp.sivic.model.Game;
import com.em.sivicapp.sivic.repository.GameRepo;
import com.em.sivicapp.sivic.repository.RecordRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/demo")
public class RecordController {

    @Autowired
    private RecordRepo repo;

    @Autowired
    private GameRepo gameRepo;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Record g = new Record();

//        g.setName("deneme");
        g.setNewPrice(new BigDecimal(10));
        g.setOldPrice(new BigDecimal(11));
        g.setPercentage(new BigDecimal(10));
        g.setTar(LocalDateTime.now());

        repo.save(g);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Record> getAllUsers() {
        // This returns a JSON or XML with the users
        return repo.findAll();
    }

    @GetMapping("/deneme")
    public String deneme() {

        String url = "https://eshop-prices.com/games/on-sale?currency=TRY&page=1";

        List<Record> list = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            Elements nameList = doc.select(".games-list-item-title h5");
            Elements priceList = doc.select(".price .price-tag");

            for (int i = 0; i < nameList.size(); i++) {
                String name = nameList.get(i).childNodes().get(0).toString();
                BigDecimal tut = new BigDecimal(-11);
                try {
                    String price = priceList.get(i).childNodes().get(2).toString();
                    String priceFormatted = price.substring(2).replaceAll(",", ".").trim();
                     tut = new BigDecimal(priceFormatted);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                Record g = new Record();
                Game game = null;
                //TODO: builder yap knk
                try {
                     game = gameRepo.findByName(name);
                     g.setGame(game);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                g.setNewPrice(tut);

                repo.save(g);
            }
        }

        return "k";
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
}
