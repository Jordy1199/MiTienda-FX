package com.example.interfaz_con_crud_javafx.mitienda.view;

import com.example.interfaz_con_crud_javafx.mitienda.controller.DashboardController;
import com.example.interfaz_con_crud_javafx.mitienda.model.Producto;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DashboardView {

    private DashboardController controller;
    private TextField txtCodigo, txtNombre, txtPrecio, txtStock;
    private ComboBox<String> cmbCategoria, cmbEstado;
    private TableView<Producto> tabla;
    private Label lblBreadcrumb;

    public void show(Stage stage, String usuario, String rol) {
        controller = new DashboardController();

        VBox menu = crearMenu(stage);
        menu.setPrefWidth(220);

        BorderPane contenido = new BorderPane();
        contenido.setTop(crearTopBar(usuario, rol));
        contenido.setCenter(crearPanelProductos());
        contenido.setStyle("-fx-background-color: #f1f5f9;");

        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(contenido);

        Scene scene = new Scene(root, 1100, 680);
        stage.setTitle("MiTienda - Dashboard");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

        cargarTabla();
    }

    private VBox crearMenu(Stage stage) {
        Label logoIcon = new Label("🛒");
        logoIcon.setStyle("-fx-font-size: 22px; -fx-background-color: #2563EB; -fx-text-fill: white; -fx-padding: 6 8 6 8; -fx-background-radius: 8;");

        Label lblTitulo = new Label("MiTienda");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lblTitulo.setTextFill(Color.WHITE);

        Label lblSub = new Label("Sistema de Ventas");
        lblSub.setFont(Font.font("Arial", 10));
        lblSub.setTextFill(Color.web("#94a3b8"));

        VBox tituloBox = new VBox(1, lblTitulo, lblSub);
        HBox headerMenu = new HBox(10, logoIcon, tituloBox);
        headerMenu.setAlignment(Pos.CENTER_LEFT);
        headerMenu.setPadding(new Insets(20, 15, 20, 15));

        Label iconUser = new Label("👤");
        iconUser.setStyle("-fx-font-size: 28px;");

        Label lblUser = new Label("admin");
        lblUser.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        lblUser.setTextFill(Color.WHITE);

        Label lblOnline = new Label("● En línea");
        lblOnline.setFont(Font.font("Arial", 11));
        lblOnline.setTextFill(Color.web("#22c55e"));

        Label lblRolBadge = new Label("Administrador");
        lblRolBadge.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 2 8 2 8; -fx-background-radius: 4;");

        VBox userInfo = new VBox(2, lblUser, lblOnline, lblRolBadge);
        VBox userBox = new VBox(6, iconUser, userInfo);
        userBox.setPadding(new Insets(0, 15, 15, 15));

        Separator sep1 = new Separator();
        sep1.setStyle("-fx-background-color: #334155;");

        Label lblMenuPrincipal = new Label("MENÚ PRINCIPAL");
        lblMenuPrincipal.setFont(Font.font("Arial", 10));
        lblMenuPrincipal.setTextFill(Color.web("#64748b"));
        lblMenuPrincipal.setPadding(new Insets(15, 15, 5, 15));

        Button[] menuItems = {
                crearItemMenu("🏠", "Inicio", false),
                crearItemMenu("📦", "Productos", true),
                crearItemMenu("🏷", "Categorías", false),
                crearItemMenu("👥", "Clientes", false),
                crearItemMenu("🛒", "Ventas", false),
                crearItemMenu("📊", "Reportes", false),
                crearItemMenu("👤", "Usuarios", false)
        };

        VBox itemsMenu = new VBox(2);
        itemsMenu.setPadding(new Insets(0, 10, 0, 10));
        for (Button b : menuItems) {
            itemsMenu.getChildren().add(b);
        }

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Separator sep2 = new Separator();
        sep2.setStyle("-fx-background-color: #334155;");

        Button btnCerrar = crearItemMenu("⏻", "Cerrar sesión", false);
        btnCerrar.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.show(stage);
        });
        VBox cerrarBox = new VBox(btnCerrar);
        cerrarBox.setPadding(new Insets(0, 10, 15, 10));

        VBox menu = new VBox(headerMenu, userBox, sep1, lblMenuPrincipal, itemsMenu, spacer, sep2, cerrarBox);
        menu.setStyle("-fx-background-color: #1e293b;");
        return menu;
    }

    private Button crearItemMenu(String icono, String texto, boolean activo) {
        Button btn = new Button(icono + "  " + texto);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPadding(new Insets(10, 15, 10, 15));
        btn.setFont(Font.font("Arial", 13));
        if (activo) {
            btn.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-background-radius: 8; -fx-cursor: hand;");
        } else {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-background-radius: 8; -fx-cursor: hand;");
        }
        return btn;
    }

    private HBox crearTopBar(String usuario, String rol) {
        Label lblMenu = new Label("☰");
        lblMenu.setFont(Font.font("Arial", 20));
        lblMenu.setTextFill(Color.web("#1e293b"));

        Label lblTitulo = new Label("Productos");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblTitulo.setTextFill(Color.web("#1e293b"));

        HBox izquierda = new HBox(12, lblMenu, lblTitulo);
        izquierda.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label iconUser = new Label("👤");
        iconUser.setStyle("-fx-font-size: 22px;");

        Label lblUsuario = new Label(usuario);
        lblUsuario.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        lblUsuario.setTextFill(Color.web("#1e293b"));

        Label lblRol = new Label(rol);
        lblRol.setFont(Font.font("Arial", 11));
        lblRol.setTextFill(Color.web("#64748b"));

        VBox userInfo = new VBox(1, lblUsuario, lblRol);
        HBox derecha = new HBox(8, iconUser, userInfo);
        derecha.setAlignment(Pos.CENTER_RIGHT);

        HBox topBar = new HBox(izquierda, spacer, derecha);
        topBar.setPadding(new Insets(15, 20, 15, 20));
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");

        return topBar;
    }

    private VBox crearPanelProductos() {

        lblBreadcrumb = new Label("Inicio  /  Productos");
        lblBreadcrumb.setFont(Font.font("Arial", 12));
        lblBreadcrumb.setTextFill(Color.web("#64748b"));
        lblBreadcrumb.setPadding(new Insets(10, 20, 0, 20));

        Label lblFormTitulo = new Label("Registro / Edición de Producto");
        lblFormTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblFormTitulo.setTextFill(Color.web("#1e293b"));
        lblFormTitulo.setStyle("-fx-border-color: #2563EB; -fx-border-width: 0 0 0 4; -fx-padding: 0 0 0 10;");

        Label lblCodigo = new Label("Código del producto");
        lblCodigo.setFont(Font.font("Arial", 12));
        txtCodigo = new TextField();
        txtCodigo.setPromptText("Ej: P001");
        txtCodigo.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 7;");

        Label lblNombre = new Label("Nombre del producto");
        lblNombre.setFont(Font.font("Arial", 12));
        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre");
        txtNombre.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 7;");

        Label lblCategoria = new Label("Categoría");
        lblCategoria.setFont(Font.font("Arial", 12));
        cmbCategoria = new ComboBox<>();
        cmbCategoria.getItems().addAll("Calzado", "Ropa", "Accesorios", "Electrónica", "Hogar");
        cmbCategoria.setPromptText("Seleccione una categoría");
        cmbCategoria.setMaxWidth(Double.MAX_VALUE);
        cmbCategoria.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1;");

        Label lblPrecio = new Label("Precio");
        lblPrecio.setFont(Font.font("Arial", 12));
        txtPrecio = new TextField("0.00");
        txtPrecio.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 7;");

        Label lblStock = new Label("Stock");
        lblStock.setFont(Font.font("Arial", 12));
        txtStock = new TextField("0");
        txtStock.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 7;");

        Label lblEstado = new Label("Estado");
        lblEstado.setFont(Font.font("Arial", 12));
        cmbEstado = new ComboBox<>();
        cmbEstado.getItems().addAll("Activo", "Inactivo");
        cmbEstado.setPromptText("Seleccione estado");
        cmbEstado.setMaxWidth(Double.MAX_VALUE);
        cmbEstado.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1;");

        GridPane gridForm = new GridPane();
        gridForm.setHgap(15);
        gridForm.setVgap(8);
        gridForm.setPadding(new Insets(15, 0, 10, 0));

        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPercentWidth(33.3);
        gridForm.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint);

        gridForm.add(lblCodigo,    0, 0); gridForm.add(txtCodigo,    0, 1);
        gridForm.add(lblNombre,    1, 0); gridForm.add(txtNombre,    1, 1);
        gridForm.add(lblCategoria, 2, 0); gridForm.add(cmbCategoria, 2, 1);
        gridForm.add(lblPrecio,    0, 2); gridForm.add(txtPrecio,    0, 3);
        gridForm.add(lblStock,     1, 2); gridForm.add(txtStock,     1, 3);
        gridForm.add(lblEstado,    2, 2); gridForm.add(cmbEstado,    2, 3);

        Button btnNuevo      = new Button("+ Nuevo");
        Button btnGuardar    = new Button("💾 Guardar");
        Button btnActualizar = new Button("✏ Actualizar");
        Button btnEliminar   = new Button("🗑 Eliminar");
        Button btnLimpiar    = new Button("↺ Limpiar");

        btnNuevo.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-background-radius: 6; -fx-padding: 8 14 8 14; -fx-cursor: hand;");
        btnGuardar.setStyle("-fx-background-color: #16a34a; -fx-text-fill: white; -fx-background-radius: 6; -fx-padding: 8 14 8 14; -fx-cursor: hand;");
        btnActualizar.setStyle("-fx-background-color: #d97706; -fx-text-fill: white; -fx-background-radius: 6; -fx-padding: 8 14 8 14; -fx-cursor: hand;");
        btnEliminar.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-background-radius: 6; -fx-padding: 8 14 8 14; -fx-cursor: hand;");
        btnLimpiar.setStyle("-fx-background-color: #6b7280; -fx-text-fill: white; -fx-background-radius: 6; -fx-padding: 8 14 8 14; -fx-cursor: hand;");

        HBox botonesBox = new HBox(10, btnNuevo, btnGuardar, btnActualizar, btnEliminar, btnLimpiar);
        botonesBox.setPadding(new Insets(5, 0, 0, 0));

        VBox formBox = new VBox(10, lblFormTitulo, gridForm, botonesBox);
        formBox.setPadding(new Insets(20));
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 8, 0, 0, 2);");

        Label lblListaTitulo = new Label("≡  Lista de Productos");
        lblListaTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblListaTitulo.setTextFill(Color.web("#1e293b"));

        TextField txtBuscar = new TextField();
        txtBuscar.setPromptText("🔍 Buscar producto...");
        txtBuscar.setPrefWidth(220);
        txtBuscar.setStyle("-fx-background-radius: 6; -fx-border-radius: 6; -fx-border-color: #cbd5e1; -fx-padding: 7;");

        Region spacerTabla = new Region();
        HBox.setHgrow(spacerTabla, Priority.ALWAYS);

        HBox tablaHeader = new HBox(lblListaTitulo, spacerTabla, txtBuscar);
        tablaHeader.setAlignment(Pos.CENTER_LEFT);

        tabla = new TableView<>();
        tabla.setStyle("-fx-background-radius: 8;");
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Producto, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, String> colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format("$%.2f", item));
            }
        });

        TableColumn<Producto, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null); setGraphic(null);
                } else {
                    Label badge = new Label(item);
                    badge.setFont(Font.font("Arial", FontWeight.BOLD, 11));
                    if (item.equals("Activo")) {
                        badge.setStyle("-fx-background-color: #dcfce7; -fx-text-fill: #16a34a; -fx-background-radius: 4; -fx-padding: 2 8 2 8;");
                    } else {
                        badge.setStyle("-fx-background-color: #fee2e2; -fx-text-fill: #dc2626; -fx-background-radius: 4; -fx-padding: 2 8 2 8;");
                    }
                    setGraphic(badge); setText(null);
                }
            }
        });

        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Producto, Void> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setCellFactory(tc -> new TableCell<>() {
            private final Button btnEditar = new Button("✏");
            private final Button btnElim   = new Button("🗑");
            {
                btnEditar.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white; -fx-background-radius: 4; -fx-cursor: hand;");
                btnElim.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-background-radius: 4; -fx-cursor: hand;");

                btnEditar.setOnAction(e -> {
                    Producto p = getTableView().getItems().get(getIndex());
                    cargarEnFormulario(p);
                });
                btnElim.setOnAction(e -> {
                    Producto p = getTableView().getItems().get(getIndex());
                    controller.eliminar(p);
                    cargarTabla();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) { setGraphic(null); }
                else {
                    HBox box = new HBox(5, btnEditar, btnElim);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        tabla.getColumns().addAll(colCodigo, colNombre, colCategoria, colPrecio, colStock, colEstado, colAcciones);

        txtBuscar.textProperty().addListener((obs, old, nuevo) -> {
            FilteredList<Producto> filtrada = new FilteredList<>(controller.getProductos(), p ->
                    nuevo == null || nuevo.isEmpty() ||
                            p.getNombre().toLowerCase().contains(nuevo.toLowerCase()) ||
                            p.getCodigo().toLowerCase().contains(nuevo.toLowerCase())
            );
            tabla.setItems(filtrada);
        });

        VBox tablaBox = new VBox(12, tablaHeader, tabla);
        tablaBox.setPadding(new Insets(20));
        tablaBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 8, 0, 0, 2);");
        VBox.setVgrow(tabla, Priority.ALWAYS);

        Label footer = new Label("© 2024 MiTienda - Sistema de Ventas. Todos los derechos reservados.");
        footer.setFont(Font.font("Arial", 11));
        footer.setTextFill(Color.web("#94a3b8"));
        footer.setAlignment(Pos.CENTER);
        footer.setMaxWidth(Double.MAX_VALUE);
        footer.setPadding(new Insets(8, 0, 8, 0));

        VBox panel = new VBox(10, lblBreadcrumb, formBox, tablaBox, footer);
        panel.setPadding(new Insets(10, 20, 10, 20));
        VBox.setVgrow(tablaBox, Priority.ALWAYS);

        btnNuevo.setOnAction(e -> limpiarFormulario());

        btnGuardar.setOnAction(e -> {
            Producto p = leerFormulario();
            if (p != null) {
                String msg = controller.guardar(p);
                mostrarAlerta(msg);
                cargarTabla();
            }
        });

        btnActualizar.setOnAction(e -> {
            Producto p = leerFormulario();
            if (p != null) {
                String msg = controller.actualizar(p);
                mostrarAlerta(msg);
                cargarTabla();
            }
        });

        btnEliminar.setOnAction(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                mostrarAlerta("Seleccione un producto para eliminar.");
                return;
            }
            Producto p = controller.buscarPorCodigo(codigo);
            if (p != null) {
                controller.eliminar(p);
                mostrarAlerta("Producto eliminado.");
                limpiarFormulario();
                cargarTabla();
            }
        });

        btnLimpiar.setOnAction(e -> limpiarFormulario());

        return panel;
    }

    private void cargarTabla() {
        tabla.setItems(controller.getProductos());
    }

    private void cargarEnFormulario(Producto p) {
        txtCodigo.setText(p.getCodigo());
        txtNombre.setText(p.getNombre());
        cmbCategoria.setValue(p.getCategoria());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cmbEstado.setValue(p.getEstado());
    }

    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        cmbCategoria.setValue(null);
        txtPrecio.setText("0.00");
        txtStock.setText("0");
        cmbEstado.setValue(null);
    }

    private Producto leerFormulario() {
        String codigo    = txtCodigo.getText().trim();
        String nombre    = txtNombre.getText().trim();
        String categoria = cmbCategoria.getValue();
        String precioTxt = txtPrecio.getText().trim();
        String stockTxt  = txtStock.getText().trim();
        String estado    = cmbEstado.getValue();

        if (codigo.isEmpty() || nombre.isEmpty() || categoria == null || precioTxt.isEmpty() || stockTxt.isEmpty() || estado == null) {
            mostrarAlerta("Por favor complete todos los campos.");
            return null;
        }
        try {
            double precio = Double.parseDouble(precioTxt);
            int stock = Integer.parseInt(stockTxt);
            return new Producto(codigo, nombre, categoria, precio, stock, estado);
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio y Stock deben ser numéricos.");
            return null;
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MiTienda");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}