package com.tttuan.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {


    private EditText mTenEdit, mSDTEdit;
//    private DanhBaDBHelper dbHelper;
    private DanhBaEntry mDanhBaEntry;
    private DanhBaEntry dbObj;
    private Button mXoaButton, mCapNhatButton;

    //Danh bạ
    private final LinkedList<String> mWordList = new LinkedList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTenEdit = findViewById(R.id.ten_text);
        mSDTEdit = findViewById(R.id.sdt_text);

        mXoaButton = findViewById(R.id.xoa_button);
        mCapNhatButton = findViewById(R.id.capnhat_button);

//       dbHelper = new DanhBaEntry(this);
        mDanhBaEntry = new DanhBaEntry(this);

        //tạo tập dữ liệu
        for(int i = 0;i<20; i++){
            mWordList.addLast("DanhBa"+i);
        }
    }

    public void xoaForm(View view) {
        //Xóa nội dung 2 EditText
        mTenEdit.setText("");
        mSDTEdit.setText("");

        //Chuyển focus vào EditText Tên
        mTenEdit.requestFocus();
    }

    public void themMoi(View view) {
        String ten = mTenEdit.getText().toString();
        String sdt = mSDTEdit.getText().toString();
        mDanhBaEntry = new DanhBaEntry(this,ten,sdt);
        long id = mDanhBaEntry.themDanhBa();
        if(id > 0){
            Toast.makeText(this,"Thêm thông tin liên lạc thành công",Toast.LENGTH_SHORT).show();
            xoaForm(view);
        }else {
            Toast.makeText(this,"Thêm thông tin liên lạc thất bại",Toast.LENGTH_SHORT).show();
        }
    }

    public void timTheoSDT(View view) {
        mDanhBaEntry = mDanhBaEntry.timTheoSDT(mSDTEdit.getText().toString());
        boolean isExist = (mDanhBaEntry != null);

        mXoaButton.setEnabled(isExist);
        mCapNhatButton.setEnabled(isExist);

        if(isExist){
            Toast.makeText(this,"tim thay so dien thoai",Toast.LENGTH_SHORT).show();
            mTenEdit.setText(mDanhBaEntry.getTen());
        }
        else{
            Toast.makeText(this,"Khong tim thay so dien thoai", Toast.LENGTH_SHORT).show();
        }
    }

    public void xoaDanhBa(View view) {
        int count = mDanhBaEntry.xoaDanhBa(mDanhBaEntry);
        if(count > 0){
            Toast.makeText(this,"Da xoa thanh cong ", Toast.LENGTH_SHORT).show();
            xoaForm(view);
        }
        else{
            Toast.makeText(this,"Khong tim thay so dien thoai", Toast.LENGTH_SHORT).show();
        }
    }

    public void capNhatDanhBa(View view) {
        mDanhBaEntry.setSdt(mSDTEdit.getText().toString());
        mDanhBaEntry.setTen(mTenEdit.getText().toString());

        int count = mDanhBaEntry.capNhatDanhBa(mDanhBaEntry);
        if(count > 0){
            Toast.makeText(this,"cap nhat thanh cong ", Toast.LENGTH_SHORT).show();
            xoaForm(view);
        }
        else{
            Toast.makeText(this,"Khong tim thay so dien thoai", Toast.LENGTH_SHORT).show();
        }
    }
}
