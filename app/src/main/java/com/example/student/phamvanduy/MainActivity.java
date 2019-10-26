package com.example.student.phamvanduy;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et_ma, et_ten, et_nd;
    Button btnXemDS, btn_Chon, btnLuu, btn_Xoa, btn_Capnhat;
    GridView gr_display;
    DBBook dbBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbBook = new DBBook(this);
        btnXemDS = (Button) findViewById(R.id.btn_XemTTSach);
        btnXemDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goiDialog();
            }
        });

    }
    private void goiDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        btnLuu = dialog.findViewById(R.id.btn_Save);
        et_ma = dialog.findViewById(R.id.edt_masach);
        et_ten = dialog.findViewById(R.id.edt_tensach);
        et_nd = dialog.findViewById(R.id.edt_noidung);

        btn_Chon = dialog.findViewById(R.id.btn_Select);
        gr_display =  dialog.findViewById(R.id.grid);

        btn_Xoa = dialog.findViewById(R.id.btn_Delete);
        btn_Capnhat = dialog.findViewById(R.id.btn_Update);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book book = new Book();
                book.setId(Integer.parseInt(et_ma.getText().toString()));
                book.setTenSach(et_ten.getText().toString());
                book.setNoidung(et_nd.getText().toString());

                if (dbBook.intsertBook(book))

                    Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Khong Thanh cong", Toast.LENGTH_SHORT).show();

            }
        });


        btn_Chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<Book> bookArrayList;
                final String id = et_ma.getText().toString();
                if (id == null) {
                    bookArrayList = dbBook.getAllBook();
                } else {
                    bookArrayList = dbBook.getBook(Integer.parseInt(id));
                }

                if (bookArrayList != null) {
                    for (Book book : bookArrayList) {
                        arrayList.add(book.getId() + "");
                        arrayList.add(book.getTenSach());
                        arrayList.add(book.getNoidung());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
                    gr_display.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Khong co du lieu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = et_ma.getText().toString();
                if (dbBook.deleteBook(Integer.parseInt(id)))

                    Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Khong Thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_Capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = et_ma.getText().toString();
                Book book = new Book();
                book.setId(Integer.parseInt(et_ma.getText().toString()));
                book.setTenSach(et_ten.getText().toString());
                book.setNoidung(et_nd.getText().toString());
                if (dbBook.updateBook(book))
                    Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Khong Thanh cong", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
