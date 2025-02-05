package com.proyectosbeth;

import javax.swing.*;

public class Inicio {
    public static void main(String[] args) {
        int opcion_menu = -1;

        String[] botones = {"1. Ver gatos","2. Salir"};

        do {
            //menu principal
            String opcion = (String) JOptionPane.showInputDialog(null,"Gatitos java","Menu prnincipal", JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);

            //Se valida la opcion el usuario
            for (int i=0; i<botones.length; i++){
                if (opcion.equals(botones[i])){
                    opcion_menu=i;
                }
            }

            switch (opcion_menu){
                case 0:
                    break;
                default:
                    break;
            }

        }while(opcion_menu !=1);

    }
}
