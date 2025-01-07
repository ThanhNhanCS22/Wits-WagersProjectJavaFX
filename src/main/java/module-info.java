module com.nhan.witsandwagers {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.nhan.witsandwagers to javafx.fxml;
    exports com.nhan.witsandwagers;
}
