package com.proyectosbeth;

import javax.swing.*;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {

        int opcion_menu = -1;

        String[] botones = {"1. Ver gatos","2. Ver favoritos","3. Salir"};

        CatService catService = new CatService();

        do {
            //menu principal
            String opcion = (String) JOptionPane.showInputDialog(null,"Gatitos java","Menu prnincipal", JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);

            //Se valida la opcion el usuario
            for (int i=0; i<botones.length; i++){
                if (opcion.equals(botones[i])){
                    opcion_menu=i;
                }
            }

            Cats cats = new Cats();
            switch (opcion_menu){
                case 0:
                    catService.viewCats();
                    break;
                case 1:
                    catService.viewCatsFavorite(cats.getApikey());
                    break;
                default:
                    break;
            }

        }while(opcion_menu !=1);

    }
}
