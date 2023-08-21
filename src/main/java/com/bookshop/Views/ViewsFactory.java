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
    private FXMLLoader userHomePage;
    private FXMLLoader tradePage;
    private FXMLLoader pickUpPage;
    private FXMLLoader pickUpConfirmationPage;
    private FXMLLoader rewardsPage;
    private FXMLLoader pointsRedeemedPage;
    private FXMLLoader redeemErrorPage;

    private FXMLLoader homePage;

    private FXMLLoader bookshopPage;
    private FXMLLoader storeFrontPage;

    private FXMLLoader cartPage;
    private FXMLLoader ordersPage;


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

    public void showRewardsPage(){
        this.rewardsPage = new FXMLLoader(getClass().getResource("/Fxml/Rewards/Rewards.fxml"));
        this.setStage(rewardsPage);
    }

    public void showCreateNewPasswordWindow(){
        this.createNewPasswordPage = new FXMLLoader(getClass().getResource("/Fxml/ForgotPassword/CreateNewPassword.fxml"));

        this.setStage(this.createNewPasswordPage);
    }

    /*
            Seller views
     */

    public void showUserHomeWindow(){

        this.userHomePage = new FXMLLoader(getClass().getResource("/Fxml/User/UserHomePage.fxml"));

        this.setStage(this.userHomePage);
    }



    public FXMLLoader getTradePage() {
        return tradePage;
    }

    public void showTradeWindow(){

        this.tradePage = new FXMLLoader(getClass().getResource("/Fxml/Trade/Trade.fxml"));
        signupPage = tradePage.getController();
        this.setStage(tradePage);
    }

    public void showPickUpWindow(){

        this.pickUpPage = new FXMLLoader(getClass().getResource("/Fxml/Trade/Appointment.fxml"));

        this.setStage(pickUpPage);
    }

    public void showPickUpConfirmationPage(){

        this.pickUpConfirmationPage = new FXMLLoader(getClass().getResource("/Fxml/Trade/ConfirmPickUp.fxml"));
        this.setStage(pickUpConfirmationPage);

    }

    public void showPointsRedeemedPage(){

        this.pointsRedeemedPage = new FXMLLoader(getClass().getResource("/Fxml/Rewards/pointsRedeemedPage.fxml"));
        this.setStage(pointsRedeemedPage);

    }

    public void showRedeemErrorPage(){

        this.redeemErrorPage = new FXMLLoader(getClass().getResource("/Fxml/Rewards/redeemErrorPage.fxml"));
        this.setStage(redeemErrorPage);

    }

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
}
