package com.em.sivicapp.sivic.controller;

import com.em.sivicapp.sivic.model.Game;
import com.em.sivicapp.sivic.model.Record;
import com.em.sivicapp.sivic.model.WishlistItem;
import com.em.sivicapp.sivic.service.GameService;
import com.em.sivicapp.sivic.service.RecordService;
import com.github.jsonldjava.core.DocumentLoader;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/wl")
public class WishlistController {

    @Autowired
    private GameService gameService;

    @Autowired
    private RecordService recordService;

//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewUser(@RequestBody String id) {

//    @GetMapping(path = "/add")
//    public @ResponseBody String addNewUser(@RequestParam String id) { // http://localhost:8080/wl/add?id=1

    @GetMapping("/add/{id}")
    public ResponseEntity<String> addNewWishListItem(@PathVariable int id) throws IOException {

        String url = "https://eshop-prices.com/games/" + id + "?currency=TRY";

        System.out.println(">>> " + id);
        WishlistItem grf = new WishlistItem();

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Document doc = Jsoup.parse(str);
        Elements scriptElements = doc.select("script[type=\"application/ld+json\"]");
        String scriptContent = scriptElements.first().html();

//        InputStream inputStream = new BufferedInputStream();// FileInputStream("input.json");

//        JsonUtils.fromString(scriptContent);
//
//        DocumentLoader dl = new DocumentLoader();
//        JsonLdOptions options = new JsonLdOptions();
//// ... the contents of "contexts/example.jsonld"
//        String jsonContext = "{ \"@context\": { https://schema.org} }";
//        dl.addInjectedDoc("http://www.example.com/context",  jsonContext);
//        options.setDocumentLoader(dl);

        LinkedHashMap jsonObject = (LinkedHashMap) JsonUtils.fromString(scriptContent);

        String name = jsonObject.get("name").toString();
        LinkedHashMap prive = (LinkedHashMap) jsonObject.get("offers");
        String price = prive.get("price").toString();

        Game g = new Game();
        g.setName(name);
        g.setZoid(id);
        gameService.save(g);

        //wishlist controller'a al knk burayi
        Record rg = new Record();
        Game game = null;
        //TODO: builder yap knk
        try {
            game = gameService.findByName(name);
            rg.setGame(game);
            rg.setTar(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        rg.setNewPrice(new BigDecimal(price));

        recordService.save(rg);
        //wishlist controller'a al knk burayi





        //        Map context = new HashMap();
//        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
//        System.out.println(JsonUtils.toPrettyString(compact));

        return ResponseEntity.ok("eklendi: " + id);
    }
}
