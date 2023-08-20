module com.tokai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires fontawesomefx;

    opens com.tokai to javafx.fxml;
    exports com.tokai;
    exports com.tokai.Controllers;
    exports com.tokai.Controllers.User;
    exports com.tokai.Controllers.ForgotPassword;
    exports com.tokai.Controllers.Trade;
    exports com.tokai.Controllers.Projects;
    exports com.tokai.Controllers.Rewards;
    exports com.tokai.Models;
    exports com.tokai.Views;

}