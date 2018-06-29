package com.example.ding.umutos.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Spinner;
import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.objects.Book;

import java.util.List;


public class EditBookActivity extends AppCompatActivity {

    private EditText editBookTitle, editBookAuthor, editBookPrice, editBookDetail;
    private Spinner editBookCategory;
    private String[] ctg = {"Agriculture","Architecture and design","Business","Divinity","Education","Engineering and technology","Environmental studies and forestry","Family and consumer science","Human physical performance and recreation", "Journalism, media studies and communication","Law","Library and museum studies","Medicine","Military sciences","Public administration","Public policy","Social work","Transportation"};

    private int bookID;
    private String bookTitle;

    private ArrayAdapter<String> adapter;
    private String title, author, price, detail, category;

    private Book newBook;
    private AccessBooks accessBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbook);

        editBookCategory=(Spinner) findViewById(R.id.editBookCategory);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctg);
        editBookCategory.setAdapter(adapter);
        editBookCategory.setOnItemSelectedListener(new SpinnerSelectedListener());

        accessBookList=new AccessBooks();

        bookID=-1;
        bookID = getIntent().getIntExtra("bookID",-1);

        if (bookID!=-1){
            newBook=accessBookList.searchBook(bookID);
            editBookTitle=(EditText)findViewById(R.id.editBookTitle);
            editBookAuthor=(EditText)findViewById(R.id.editBookAuthor);
            editBookPrice=(EditText)findViewById(R.id.editBookPrice);
            editBookDetail=(EditText)findViewById(R.id.editBookDetail);

            editBookTitle.setText(newBook.getName());
            editBookAuthor.setText(newBook.getAuthor());
            editBookPrice.setText(""+newBook.getPrice());
            editBookDetail.setText(newBook.getDescription());

        }
    }

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            category=ctg[arg2];
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    public void buttonBookSubmit(View view) {
        editBookTitle=(EditText)findViewById(R.id.editBookTitle);
        editBookAuthor=(EditText)findViewById(R.id.editBookAuthor);
        editBookPrice=(EditText)findViewById(R.id.editBookPrice);
        editBookDetail=(EditText)findViewById(R.id.editBookDetail);

        title=editBookTitle.getText().toString();
        author=editBookAuthor.getText().toString();
        price=editBookPrice.getText().toString();
        detail=editBookDetail.getText().toString();


        if (title.length()<1 || author.length()<1 || price.length()<1 )
            showDialog();
        else {
            if (bookID==-1)
                showNewDialog();
            else
                showEditDialog();
        }

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lack of book information")
                .setMessage("\nPlease enter valid book title, author and price.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {}
                })
                .show();
    }
    private void showNewDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to add "+title+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Book aBook= new Book(title,author,0,detail,category,Double.parseDouble(price),2);
                        accessBookList.insertBook(aBook);
                        Intent intent = new Intent(EditBookActivity.this, SellerBookListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                    }
                })
                .show();
    }

    private void showEditDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to update "+title+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Book aBook=accessBookList.searchBook(bookID);
                        aBook.setName(title);
                        aBook.setAuthor(author);
                        aBook.setDescription(detail);
                        aBook.setCategory(category);
                        aBook.setPrice(Double.parseDouble(price));
                        accessBookList.updateBook(aBook);
                        Intent intent = new Intent(EditBookActivity.this, SellerBookListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        finish();
                    }
                })
                .show();
    }

    public void buttonEditBookCancel(View view) {
        finish();
    }


}
