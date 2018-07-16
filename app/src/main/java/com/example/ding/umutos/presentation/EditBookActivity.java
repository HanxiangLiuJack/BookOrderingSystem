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

public class EditBookActivity extends AppCompatActivity {

    private EditText editBookTitle, editBookAuthor, editBookPrice, editBookDetail;
    private Spinner editBookCategory;

    private int bookID;
    private String userName;

    private ArrayAdapter<String> adapter;
    private String title, author, price, detail, category;

    private Book newBook;
    private AccessBooks accessBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbook);

        userName = getIntent().getStringExtra("userName");
        bookID = getIntent().getIntExtra("bookID",-1);
        System.out.println("BookID"+bookID);

        accessBookList=new AccessBooks();

        editBookCategory=(Spinner) findViewById(R.id.editBookCategory);

        Book aBook=new Book(  );

        String[] subArray = new String[aBook.getCategoryArr().length-1];
        System.arraycopy( aBook.getCategoryArr(), 1, subArray, 0, subArray.length );

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subArray);
        editBookCategory.setAdapter(adapter);
        editBookCategory.setOnItemSelectedListener(new SpinnerSelectedListener());

        if (bookID>0){
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
                        Book aBook= new Book(title,author,2131361792,detail,category,Double.parseDouble(price),userName);
                        accessBookList.insertBook(aBook);
                        int userType = 0;
                        Intent intent = new Intent(EditBookActivity.this, BookListActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userName", userName);

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
                        newBook.setAuthor( author );
                        newBook.setCategory( category );
                        newBook.setDescription( detail );
                        newBook.setName( title );
                        newBook.setPrice( Double.parseDouble( price ) );
                        accessBookList.updateBook(newBook);
                        int userType = 0;
                        Intent intent = new Intent(EditBookActivity.this, BookListActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userName", userName);
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

    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            Book aBook =new Book(  );
            category=aBook.getCategoryArr()[arg2+1];
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


}
