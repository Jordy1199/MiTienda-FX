package com.example.interfaz_con_crud_javafx.mitienda.controller;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private final Map<String, String[]> usuarios = new HashMap<>();

    public LoginController() {
        // usuario -> [contraseña, rol]
        usuarios.put("admin",     new String[]{"administrador1234", "Administrador"});
        usuarios.put("vendedor",  new String[]{"vendedor1234",      "Vendedor"});
        usuarios.put("cajero",    new String[]{"cajero1234",        "Cajero"});
    }

    public String validar(String usuario, String contrasena, String rol) {
        if (usuario.isEmpty() || contrasena.isEmpty() || rol == null) {
            return "Por favor complete todos los campos.";
        }

        if (!usuarios.containsKey(usuario)) {
            return "Usuario no encontrado.";
        }

        String[] datos = usuarios.get(usuario);

        if (!datos[0].equals(contrasena)) {
            return "Contraseña incorrecta.";
        }

        if (!datos[1].equals(rol)) {
            return "El rol seleccionado no corresponde al usuario.";
        }

        return null; // login exitoso
    }
}