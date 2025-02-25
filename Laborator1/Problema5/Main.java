public class Main {
    public static void main(String[] args) {
        Notifiable sms = new SMSNotification("You have a newSMS");
        Notifiable email = new EmailNotification("You have a new email");
        Notifiable push = new PushNotification("You have a new pushNotification");

        sms.sendNotification();
        email.sendNotification();
        push.sendNotification();
    }
}
