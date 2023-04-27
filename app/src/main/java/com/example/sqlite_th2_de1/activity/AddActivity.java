package com.example.sqlite_th2_de1.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.adapter.SpinnerAdapter;
import com.example.sqlite_th2_de1.database.DAO;
import com.example.sqlite_th2_de1.model.Tour;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText1, editText2, editText3;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private Button buttonSave;
    private Button buttonBack;
    private DAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinner = findViewById(R.id.add_spinner);
        editText1 = findViewById(R.id.add_edt_1);
        editText2 = findViewById(R.id.add_edt_2);
        editText3 = findViewById(R.id.add_edt_3);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);

        mDAO = new DAO(this);

        List<String> status = Arrays.asList(getResources().getStringArray(R.array.spinner_array));
        SpinnerAdapter adapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                    editText2.setText(selectedDate);
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f1 = spinner.getSelectedItem().toString();
                String f2 = editText1.getText().toString();

                String f3 = "";
                if (checkBox1.isChecked()) f3 += checkBox1.getText() + "  ";
                if (checkBox2.isChecked()) f3 += checkBox2.getText() + "  ";
                if (checkBox3.isChecked()) f3 += checkBox3.getText();

                String f4 = editText2.getText().toString();
                Long f5 = Long.parseLong(editText3.getText().toString());

                if (TextUtils.isEmpty(f1) || TextUtils.isEmpty(f2) || TextUtils.isEmpty(f3) || TextUtils.isEmpty(f4) || TextUtils.isEmpty(editText3.getText().toString()))
                    Toast.makeText(AddActivity.this, "Hãy điền đầy đủ các mục", Toast.LENGTH_SHORT).show();
                else {
                    Tour tour = new Tour(f1, f2, f3, f4, f5);
                    Log.d("Tuan", "onClick: " + tour.toString());
                    mDAO.add(tour);
                    Toast.makeText(AddActivity.this, "Thêm công việc thành công ", Toast.LENGTH_SHORT).show();
                    editText1.setText(null);
                    editText2.setText(null);
                    editText3.setText(null);
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
