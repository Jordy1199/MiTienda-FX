module com.example.interfaz_con_crud_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.interfaz_con_crud_javafx.mitienda to javafx.fxml;
    opens com.example.interfaz_con_crud_javafx.mitienda.controller to javafx.fxml;
    opens com.example.interfaz_con_crud_javafx.mitienda.model to javafx.fxml;
    opens com.example.interfaz_con_crud_javafx.mitienda.view to javafx.fxml;

    exports com.example.interfaz_con_crud_javafx.mitienda;
    exports com.example.interfaz_con_crud_javafx.mitienda.controller;
    exports com.example.interfaz_con_crud_javafx.mitienda.model;
    exports com.example.interfaz_con_crud_javafx.mitienda.view;
}