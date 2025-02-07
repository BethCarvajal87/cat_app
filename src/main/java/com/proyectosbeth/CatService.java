package com.proyectosbeth;

import com.google.gson.Gson;
import okhttp3.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CatService {

    public void viewCats() throws IOException {

        //traer los datos de la api
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        //cortar los corchetes
        responseJson = responseJson.substring(1, responseJson.length()-1);

        //crear un objeto de la clase Gson

        Gson gson = new Gson();
        Cats cats = gson.fromJson(responseJson, Cats.class);

        //menuImage(cats);

        //Redimensionar en caso de necesitar
        Image image = null;
        try{
            URL url = new URL(cats.getUrl());
            image = ImageIO.read(url);

            ImageIcon backgroudCat =  new ImageIcon(image);

            if (backgroudCat.getIconWidth() >800){
                //redimensionamos
                Image background = backgroudCat.getImage();
                Image imageModification = background.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                backgroudCat = new ImageIcon(imageModification);
            }

            String menu = "Opciones: \n"
                    + "1. Ver otra imagen \n "
                    + "2. Favorito  \n"
                    + "3. Volver  \n";

            String[] botones = {"ver otra imagen","favorito","volver"};

            String id_cat = cats.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, backgroudCat, botones, botones[0]);

            int seleccion = -1;

            for (int i=0; i<botones.length; i++){
                if (opcion.equals(botones[i])){
                    seleccion=i;
                }
            }

            switch (seleccion){
                case 0:
                    viewCats();
                    break;
                case 1:
                    favoriteCats(cats);
                    break;
                default:
                    break;

            }

        }catch (IOException e){
            System.out.println(e);

        }

    }
    public static void favoriteCats(Cats cats){

        try{

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+cats.getId()+"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("x-api-key", cats.getApikey())
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();

        }catch (IOException e){
            System.out.println(e);
        }

    }
    public void viewCatsFavorite(String apiKey) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("x-api-key", apiKey)
                .build();
        Response response = client.newCall(request).execute();


        //guardamos el string con la respuesta
        String responseJson = response.body().string();

        //creamos el objeto gson
        Gson gson = new Gson();

        FavoriteCats[]  arrayCats = gson.fromJson(responseJson, FavoriteCats[].class);

        if(arrayCats.length >0){{
            int min = 1;
            int max = arrayCats.length;
            int aleatorio = (int) (Math.random() * ((max-min)-1))+ min;
            int indice = aleatorio-1;

            FavoriteCats favoriteCats =  arrayCats[indice];

            //menuImage(Cats cats);

            //Redimensionar en caso de necesitar
            Image image = null;
            try{
                if (favoriteCats.imageX == null) {
                    System.out.println("Error: imageX es null en el objeto FavoriteCats con ID: " + favoriteCats.getId());
                    return; // Evita continuar si imageX es null
                }

                URL url = new URL(favoriteCats.imageX.getUrl());
                image = ImageIO.read(url);

                ImageIcon backgroudCat =  new ImageIcon(image);

                if (backgroudCat.getIconWidth() >800){
                    //redimensionamos
                    Image background = backgroudCat.getImage();
                    Image imageModification = background.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                    backgroudCat = new ImageIcon(imageModification);
                }

                String menu = "Opciones: \n"
                        + "1. Ver otra imagen \n "
                        + "2. Eliminar Favorito  \n"
                        + "3. Volver  \n";

                String[] botones = {"ver otra imagen","Eliminar favorito","volver"};

                String id_cat = favoriteCats.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, backgroudCat, botones, botones[0]);

                int seleccion = -1;

                for (int i=0; i<botones.length; i++){
                    if (opcion.equals(botones[i])){
                        seleccion=i;
                    }
                }

                switch (seleccion){
                    case 0:
                        viewCatsFavorite(apiKey);
                        break;
                    case 1:
                        deleteFavorite(favoriteCats);
                        break;
                    default:
                        break;

                }

            }catch (IOException e){
                System.out.println(e);

            }

        }

        }

    }

    public void deleteFavorite(FavoriteCats favoriteCats){

    }

    private void menuImage(Cats cats) {

        //Redimensionar en caso de necesitar
        Image image = null;
        try{
            URL url = new URL(cats.getUrl());
            image = ImageIO.read(url);

            ImageIcon backgroudCat =  new ImageIcon(image);

            if (backgroudCat.getIconWidth() >800){
                //redimensionamos
                Image background = backgroudCat.getImage();
                Image imageModification = background.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                backgroudCat = new ImageIcon(imageModification);
            }

            String menu = "Opciones: \n"
                    + "1. Ver otra imagen \n "
                    + "2. Favorito  \n"
                    + "3. Volver  \n";

            String[] botones = {"ver otra imagen","favorito","volver"};

            String id_cat = cats.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, backgroudCat, botones, botones[0]);

            int seleccion = -1;

            for (int i=0; i<botones.length; i++){
                if (opcion.equals(botones[i])){
                    seleccion=i;
                }
            }

            switch (seleccion){
                case 0:
                    viewCats();
                    break;
                case 1:
                    favoriteCats(cats);
                    break;
                default:
                    break;

            }

        }catch (IOException e){
            System.out.println(e);

        }


    }


}
