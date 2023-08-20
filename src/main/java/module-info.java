module com.tokai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires fontawesomefx;

    opens com.bookshop to javafx.fxml;
    exports com.bookshop;
    exports com.bookshop.Controllers;
    exports com.bookshop.Controllers.User;
    exports com.bookshop.Controllers.ForgotPassword;
    exports com.bookshop.Controllers.Trade;
    //exports com.bookshop.Controllers.Projects;
    exports com.bookshop.Controllers.Rewards;
    exports com.bookshop.Models;
    exports com.bookshop.Views;

}