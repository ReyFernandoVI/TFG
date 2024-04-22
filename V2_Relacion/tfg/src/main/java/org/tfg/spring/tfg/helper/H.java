package org.tfg.spring.tfg.helper;

import org.tfg.spring.tfg.domain.Usuario;

import jakarta.servlet.http.HttpSession;

public class H {

    private static int rolIndex(String rol) {
        int index = 0;
        switch (rol) {
            case "auth":
                index = 1;
                break;
            case "admin":
                index = 2;
                break;
        }
        return index;
    }

    /**
     * 
     * @param rol anon, auth, admin
     * @return
     */


     public static boolean isRolOK(String rolRequerido, HttpSession s) {
        boolean respuesta = false;
        String rolActual = "anon";

        if (s.getAttribute("usuario") != null) {
            Usuario usu = (Usuario) s.getAttribute("usuario");
            if (usu.getAdmin()) {
                rolActual = "admin";
            } else {
                rolActual = "auth";
            }
        }
        return (rolIndex(rolActual)>=rolIndex(rolRequerido));
    }
}