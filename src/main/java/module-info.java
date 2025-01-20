module com.nhan.witsandwagers {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.nhan.witsandwagers to javafx.fxml;

    exports com.nhan.witsandwagers.Elements;
    opens com.nhan.witsandwagers.Elements to javafx.fxml;
    exports com.nhan.witsandwagers.Logic;
    opens com.nhan.witsandwagers.Logic to javafx.fxml;
    exports com.nhan.witsandwagers.Gui;
    opens com.nhan.witsandwagers.Gui to javafx.fxml;
}
