package com.Hileb.teampotato.redirectionor;

import nilloader.api.lib.nanojson.JsonArray;
import nilloader.api.lib.nanojson.JsonWriter;
import nilloader.api.lib.nanojson.JsonObject;
import nilloader.api.lib.nanojson.JsonParser;
import nilloader.api.lib.nanojson.JsonParserException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/**
 * @Project Redirectionor
 * @Author Hileb
 * @Date 2023/9/9 18:14
 **/
public class RedirectionorConfig {

    public static File CONFIG_FILE = null;

    public static boolean decode(JsonObject jsonObject){
        boolean rewrite = false;
        try{

            if (jsonObject.isBoolean("printTransformedClasses")){
                Config.printTransformedClasses = jsonObject.getBoolean("printTransformedClasses");
            } else rewrite = true;

            if (jsonObject.getString("type") != null) Config.isBlock = Config.setBlocking(jsonObject.getString("type"));
            else rewrite = true;

            if (jsonObject.getArray("contains") != null){
                JsonArray contains = jsonObject.getArray("contains");
                Config.contains = new HashSet<>(contains.size());
                int size = contains.size();
                for (int i = 0; i < size ; i++) {
                    Config.contains.add(contains.getString(i));
                }
            } else rewrite = true;

            if (jsonObject.getArray("prefix") != null){
                JsonArray prefix = jsonObject.getArray("prefix");
                Config.prefix = new HashSet<>(prefix.size());
                int size = prefix.size();
                for (int i = 0; i < size ; i++) {
                    Config.contains.add(prefix.getString(i));
                }
            } else rewrite = true;

            if (jsonObject.isBoolean("generateConfigWhenCrash")){
                Config.generateConfigWhenCrash = jsonObject.getBoolean("generateConfigWhenCrash");
            } else rewrite = true;
        }catch (Throwable e){
            throw new RuntimeException("Could not read the config", e);
        }
        return rewrite;
    }

    public static JsonObject encode(){
        JsonObject json = new JsonObject();
        json.put("printTransformedClasses", Config.printTransformedClasses);
        json.put("type", Config.isBlock ? "block" : "allow");

        JsonArray contains = new JsonArray(Config.contains.size());
        contains.addAll(Config.contains);
        json.put("contains", contains);

        JsonArray prefix = new JsonArray(Config.prefix.size());
        prefix.addAll(Config.prefix);
        json.put("prefix", prefix);

        json.put("generateConfigWhenCrash", Config.generateConfigWhenCrash);
        return json;
    }

    public static void save(){
        try (PrintWriter pw = new PrintWriter(CONFIG_FILE, "UTF-8")){
            pw.println(JsonWriter.string(encode()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void initConfig(){
        if (CONFIG_FILE == null) {
            File gameRunRoot = RedirectionorPremain.tryGetMinecraftHome();
        File config = new File(gameRunRoot,"config");

        if (!config.exists()){
            config.mkdir();
        }

        File file = new File(config,"redirectionor_cfg.json");
        CONFIG_FILE = file;


        if (file.exists()){
            try {
                JsonObject jsonObject = JsonParser.object().from(new String(Files.readAllBytes(file.toPath())));
                if (decode(jsonObject)) save();
            } catch (IOException e) {
                throw new RuntimeException("Could not read the config", e);
            } catch (JsonParserException e){
                throw new RuntimeException("The Json is bad", e);
            }
        } else {
            try {
                file.createNewFile();
                save();
            } catch (IOException e) {
                throw new RuntimeException("Could not create config file", e);
            }
        }
        }
    }
    public static class Config{
        public static boolean generateConfigWhenCrash = true;
        public static boolean printTransformedClasses = false;
        public static boolean isBlock = true;
        public static HashSet<String> contains = new HashSet<>();
        public static HashSet<String> prefix = new HashSet<>();
        public static boolean setBlocking(String s){
            if ("block".equals(s.toLowerCase(Locale.ENGLISH))) return true;
            else if ("allow".equals(s.toLowerCase(Locale.ENGLISH))) return false;
            else throw new IllegalArgumentException("unknown type for config/redirectionor_cfg.json :" + s + " it should be \"block\" or \"allow\"");
        }

    }
}
