package com.bookshop.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewsFactory {
    //Seller views
    //private AnchorPane sellerHomePage;
    private  FXMLLoader loginPage;
    private FXMLLoader signupPage;
    private FXMLLoader forgetPasswordPage;
    private FXMLLoader sendEmailPage;
    private FXMLLoader otpPage;
    private FXMLLoader createNewPasswordPage;

    private FXMLLoader homePage;

    private FXMLLoader bookshopPage;
    private FXMLLoader storeFrontPage;

    private FXMLLoader cartPage;
    private FXMLLoader ordersPage;
    private FXMLLoader fileSenderPage;
    private FXMLLoader fileReceiverPage;

    private FXMLLoader clientChat;
    private FXMLLoader serverChat;

    public ViewsFactory(){}

    /*
            Stage Manipulation
     */
    public void setStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("UIU Bookshop");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

    /*
        Login -- SignUp -- ForgotPassword loader

     **/

    public void showLoginWindow(){

        loginPage = new FXMLLoader(getClass().getResource("/Fxml/Login/login.fxml"));


        this.setStage(loginPage);
    }

    public void showSignUPWindow(){

        this.signupPage = new FXMLLoader(getClass().getResource("/Fxml/SignUp/signUp.fxml"));


        this.setStage(this.signupPage);
    }

    public void showForgetPasswordWindow(){

        this.forgetPasswordPage = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword/ForgotPassword.fxml"));


        this.setStage(forgetPasswordPage);
    }

    public void showSendEmailWindow(){

        this.sendEmailPage = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword/SendEmail.fxml"));


        this.setStage(sendEmailPage);
    }

    public void showOtpWindow(){

        this.otpPage = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword/Otp.fxml"));


        this.setStage(this.otpPage);
    }


    public void showCreateNewPasswordWindow(){
        this.createNewPasswordPage = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword/CreateNewPassword.fxml"));

        this.setStage(this.createNewPasswordPage);
    }

    /*
            Seller views
     */



    public void showHomePage(){
        this.homePage = new FXMLLoader(getClass().getResource("/Fxml/Home.fxml"));
        this.setStage(homePage);
    }

    public void showBookshopPage(){
        this.bookshopPage = new FXMLLoader(getClass().getResource("/Fxml/Bookshop/Bookshop.fxml"));
        this.setStage(bookshopPage);
    }

    public void showStoreFrontPage(){
        this.storeFrontPage = new FXMLLoader(getClass().getResource("/Fxml/storefront.fxml"));
        this.setStage(storeFrontPage);
    }


    public void showCartPage() {
        this.cartPage = new FXMLLoader(getClass().getResource("/Fxml/Cart.fxml"));
        this.setStage(cartPage);
    }

    public void showOrdersPage() {
        this.ordersPage = new FXMLLoader(getClass().getResource("/Fxml/Orders.fxml"));
        this.setStage(ordersPage);
    }

    public void showFileSenderPage(){
        this.fileSenderPage = new FXMLLoader(getClass().getResource("/Fxml/fileSender.fxml"));
        this.setStage(fileSenderPage);
    }

    public void showFileReceiverPage(){
        this.fileReceiverPage = new FXMLLoader(getClass().getResource("/Fxml/fileReceiver.fxml"));
        this.setStage(fileReceiverPage);
    }

    public void showClientChatPage(){
        this.clientChat = new FXMLLoader(getClass().getResource("/Fxml/Client.fxml"));
        this.setStage(clientChat);
    }

    public void showServerChatPage(){
        this.serverChat = new FXMLLoader(getClass().getResource("/Fxml/Server.fxml"));
        this.setStage(serverChat);
    }
}
