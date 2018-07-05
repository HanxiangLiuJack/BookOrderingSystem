package com.example.ding.umutos.presentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.business.AccessOrders;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Order;

import static java.lang.Thread.sleep;


public class AddressActivity extends AppCompatActivity {
    private EditText editFirstName, editLastName, editPhoneNum, editPostCode, editAddressInfo, editAdditionInfo;
    private int bookID;
    private AccessBooks accessBookList;
    private AccessOrders accessOrders;
    private int userID;
    private String firstName, lastName,phoneNum,postCode,addressInfo,additionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        bookID = getIntent().getIntExtra("bookID",-1);
        userID = getIntent().getIntExtra("userID",-1);
        accessOrders=new AccessOrders(  );
        accessBookList=new AccessBooks();



    }

    public void buttonAddSubmit(View view) {
        editFirstName=(EditText)findViewById(R.id.editFirstName);
        editLastName=(EditText)findViewById(R.id.editLastName);
        editPhoneNum=(EditText)findViewById(R.id.editPhoneNum);
        editPostCode=(EditText)findViewById(R.id.editPostCode);
        editAddressInfo=(EditText)findViewById(R.id.editAddressInfo);
        editAdditionInfo=(EditText)findViewById(R.id.editAdditionInfo);

        firstName=editFirstName.getText().toString();
        lastName=editLastName.getText().toString();
        phoneNum=editPhoneNum.getText().toString();
        postCode=editPostCode.getText().toString();
        addressInfo=editAddressInfo.getText().toString();
        additionInfo=editAdditionInfo.getText().toString();

        if (firstName.length()<1 || lastName.length()<1 || phoneNum.length()<1 || postCode.length()<1 || addressInfo.length()<1)
            showDialog();
        else
            showDialog(firstName);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lack of delivery information")
                .setMessage("\nPlease enter valid name, phone number, postal code and address.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }
    private void showDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n Click 'OK' to place your order.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Book aBook=accessBookList.searchBook( bookID );
                        accessBookList.deleteBook(bookID);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String[] address={firstName,lastName,postCode,phoneNum,addressInfo};
                        Order newOrder =  new Order(aBook.getName(),userID,aBook.getOwner(),aBook.getPrice(),address);
                        accessOrders.insertOrder( newOrder );
                        int userType=1;
                        Intent intent = new Intent(AddressActivity.this, BookListActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userID", userID);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }




    public void buttonBookCancel(View view) {
        finish();
    }


}
