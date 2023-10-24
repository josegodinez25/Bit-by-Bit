package application;

public class Customer {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

    String paymentFirstName;
    String paymentLastName;
    int cardNumber;
    int expDate;
    int zipCode;
    String country;

    String ID;

    String roomNumber;
    String roomPrice;
    

    Customer(String firstName,String lastName, String email, String phoneNumber,String paymentFirstName, 
            String paymentLastName, int cardNumber, int expDate, int zipCode,String country){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.paymentFirstName = paymentFirstName;
        this.paymentLastName = paymentLastName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.zipCode = zipCode;
        this.country = country;

        ID = "";
    }

    public void inputUserDetail() {

        ReadWriteExcel obj = new ReadWriteExcel();
        int CustomerCount = 1;
        //checks if the cell is empty
        while (obj.isCustomerNull(CustomerCount, 1) == false) {
            CustomerCount++;
        }

        String sCardNumber = Integer.toString(cardNumber);
        String sExpDate = Integer.toString(expDate);
        String sZipCode = Integer.toString(zipCode);


        //if the cell is empty then write the info in excel
        obj.WriteExcel("Customers", CustomerCount, 1, firstName);
        obj.WriteExcel("Customers", CustomerCount, 2, lastName);
        obj.WriteExcel("Customers", CustomerCount, 3, email);
        obj.WriteExcel("Customers", CustomerCount, 4, phoneNumber);
        obj.WriteExcel("Customers", CustomerCount, 5, paymentFirstName);
        obj.WriteExcel("Customers", CustomerCount, 6, paymentLastName);
        obj.WriteExcel("Customers", CustomerCount, 7, sCardNumber);
        obj.WriteExcel("Customers", CustomerCount, 8, sExpDate);
        obj.WriteExcel("Customers", CustomerCount, 9, country);
        obj.WriteExcel("Customers", CustomerCount, 10, sZipCode);
        obj.WriteExcel("Customers", CustomerCount, 11, ID);
        obj.WriteExcel("Customers", CustomerCount, 12, roomNumber);
        obj.WriteExcel("Customers", CustomerCount, 13, roomPrice);
   
        
        
        

    }
}