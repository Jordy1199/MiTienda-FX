package com.example.interfaz_con_crud_javafx.mitienda.view;

import com.example.interfaz_con_crud_javafx.mitienda.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginView {

    public void show(Stage stage) {

        // ---- LOGO + TÍTULO ----
        Label logoIcon = new Label("🛒");
        logoIcon.setStyle("-fx-font-size: 28px; -fx-background-color: #2563EB; -fx-text-fill: white; -fx-padding: 8 10 8 10; -fx-background-radius: 10;");

        Label titulo = new Label("MiTienda");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titulo.setTextFill(Color.web("#1e293b"));

        Label subtitulo = new Label("Sistema de Ventas");
        subtitulo.setFont(Font.font("Arial", 12));
        subtitulo.setTextFill(Color.web("#64748b"));

        VBox tituloBox = new VBox(2, titulo, subtitulo);
        tituloBox.setAlignment(Pos.CENTER_LEFT);

        HBox header = new HBox(10, logoIcon, tituloBox);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 0, 20, 0));

        // ---- FORMULARIO ----
        Label lblLogin = new Label("Iniciar sesión");
        lblLogin.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblLogin.setTextFill(Color.web("#1e293b"));

        Label lblUsuario = new Label("Usuario");
        lblUsuario.setFont(Font.font("Arial", 13));
        lblUsuario.setTextFill(Color.web("#374151"));

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Ej: admin");
        txtUsuario.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 8; -fx-font-size: 13px;");
        txtUsuario.setMaxWidth(Double.MAX_VALUE);

        Label lblContrasena = new Label("Contraseña");
        lblContrasena.setFont(Font.font("Arial", 13));
        lblContrasena.setTextFill(Color.web("#374151"));

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 8; -fx-font-size: 13px;");
        txtContrasena.setMaxWidth(Double.MAX_VALUE);

        Label lblRol = new Label("Rol");
        lblRol.setFont(Font.font("Arial", 13));
        lblRol.setTextFill(Color.web("#374151"));

        ComboBox<String> cmbRol = new ComboBox<>();
        cmbRol.getItems().addAll("Administrador", "Vendedor", "Cajero");
        cmbRol.setPromptText("— Seleccionar —");
        cmbRol.setMaxWidth(Double.MAX_VALUE);
        cmbRol.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-font-size: 13px;");

        Button btnIngresar = new Button("⬡  Ingresar");
        btnIngresar.setMaxWidth(Double.MAX_VALUE);
        btnIngresar.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 6; -fx-cursor: hand;");

        Label lblHint = new Label("admin / administrador1234 / Administrador");
        lblHint.setFont(Font.font("Arial", 11));
        lblHint.setTextFill(Color.web("#94a3b8"));
        lblHint.setAlignment(Pos.CENTER);

        Label lblError = new Label("");
        lblError.setFont(Font.font("Arial", 12));
        lblError.setTextFill(Color.web("#ef4444"));
        lblError.setAlignment(Pos.CENTER);

        VBox form = new VBox(10,
                header,
                lblLogin,
                lblUsuario, txtUsuario,
                lblContrasena, txtContrasena,
                lblRol, cmbRol,
                btnIngresar,
                lblHint,
                lblError
        );
        form.setPadding(new Insets(30));
        form.setMaxWidth(360);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 4);");

        // ---- FONDO ----
        StackPane root = new StackPane(form);
        root.setStyle("-fx-background-color: #f1f5f9;");
        root.setPadding(new Insets(40));

        // ---- ACCIÓN BOTÓN ----
        LoginController controller = new LoginController();
        btnIngresar.setOnAction(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            String rol = cmbRol.getValue();

            String error = controller.validar(usuario, contrasena, rol);
            if (error == null) {
                DashboardView dashboard = new DashboardView();
                dashboard.show(stage, usuario, rol);
            } else {
                lblError.setText(error);
            }
        });

        Scene scene = new Scene(root, 480, 580);
        stage.setTitle("MiTienda - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}