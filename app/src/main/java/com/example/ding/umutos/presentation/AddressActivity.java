package com.example.ding.umutos.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.ding.umutos.R;


public class AddressActivity extends AppCompatActivity {
    private EditText editFirstName, editLastName, editPhoneNum, editPostCode, editAddressInfo, editAdditionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
    }

    public void buttonAddSubmit(View view) {
        editFirstName=(EditText)findViewById(R.id.editFirstName);
        editLastName=(EditText)findViewById(R.id.editLastName);
        editPhoneNum=(EditText)findViewById(R.id.editPhoneNum);
        editPostCode=(EditText)findViewById(R.id.editPostCode);
        editAddressInfo=(EditText)findViewById(R.id.editAddressInfo);
        editAdditionInfo=(EditText)findViewById(R.id.editAdditionInfo);

        String firstName=editFirstName.getText().toString();
        String lastName=editLastName.getText().toString();
        String phoneNum=editPhoneNum.getText().toString();
        String postCode=editPostCode.getText().toString();
        String addressInfo=editAddressInfo.getText().toString();
        String additionInfo=editAdditionInfo.getText().toString();

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
                .setMessage("\n"+title+" has been added.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
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

    }


}
