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
import com.example.ding.umutos.business.AccessShoppingCart;
import com.example.ding.umutos.business.OrderValidator;
import com.example.ding.umutos.objects.Item;
import com.example.ding.umutos.objects.OrderInfo;

import java.util.List;

public class AddressActivity extends AppCompatActivity {
    private EditText editFirstName, editLastName, editPhoneNum, editPostCode, editAddressInfo, editAdditionInfo;
    private int bookID;
    private String userName;
    private String firstName, lastName,phoneNum,postCode,addressInfo,additionInfo;
    private AccessShoppingCart accessShoppingCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        bookID = getIntent().getIntExtra("bookID",-1);
        userName = getIntent().getStringExtra("userName");
        accessShoppingCart=new AccessShoppingCart(  );
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
        OrderValidator orderValidator = new OrderValidator();
        if (orderValidator.validateAddress(addressInfo)&&orderValidator.validateFirstName(firstName)&&
                orderValidator.validateLastName(lastName)&&orderValidator.validatePhoneNumber(phoneNum)&&orderValidator.validatePostCode(postCode))
            showDialog(firstName);
        else
            showDialog();
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
                        clearShoppingCart();
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

    private void clearShoppingCart(){
        OrderInfo orderInfo = new OrderInfo(firstName,lastName,postCode,phoneNum,addressInfo);
        List<Item> newList=accessShoppingCart.clearShoppingCart( userName, orderInfo );
        if (newList==null){
            Intent intent = new Intent(AddressActivity.this,BookListActivity.class);
            intent.putExtra("bookID", bookID);
            intent.putExtra("userName", userName);
            intent.putExtra("userType", 1);
            AddressActivity.this.startActivity(intent);
        }else {
            String bookName="";
            for (int i=0;i<newList.size();i++){
                bookName+=newList.get( i ).getName()+"\n";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert:")
                    .setMessage("\n"+"Books below have been sold out.\n"+bookName)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {

                        }
                    })
                    .show();
        }

    }

    public void buttonBookCancel(View view) {
        finish();
    }

}
