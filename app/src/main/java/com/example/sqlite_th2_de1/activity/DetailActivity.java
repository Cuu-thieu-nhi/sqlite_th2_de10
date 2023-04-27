package com.example.sqlite_th2_de1.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.adapter.SpinnerAdapter;
import com.example.sqlite_th2_de1.database.DAO;
import com.example.sqlite_th2_de1.model.Tour;
import com.example.sqlite_th2_de1.model.TourAndID;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    int id;
    private Spinner spinner;

    TextView mID;
    private EditText editText1, editText2, editText3;
    private CheckBox checkBox1, checkBox2, checkBox3;
    String field1, field2, field3, field4;
    Long field5;
    private Button buttonBack;
    private Button buttonUpdate;
    private Button buttonDelete;
    private DAO mDAO;


    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        TourAndID tours = (TourAndID) intent.getSerializableExtra("tour");
        id = tours.getId();
        Tour tour = tours.getTour();
        field1 = tour.getField1();
        field2 = tour.getField2();
        field3 = tour.getField3();
        field4 = tour.getField4();
        field5 = tour.getField5();

        mID = findViewById(R.id.tvID);
        spinner = findViewById(R.id.add_spinner);
        editText1 = findViewById(R.id.add_edt_1);
        editText2 = findViewById(R.id.add_edt_2);
        editText3 = findViewById(R.id.add_edt_3);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        buttonBack = findViewById(R.id.buttonBack);

        mDAO = new DAO(this);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.buttonBack);
        mDAO = new DAO(this);

        mID.setText(Integer.toString(id));
        editText1.setText(field2);
        editText2.setText(field4);
        editText3.setText(String.valueOf(field5));
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);

        if (field3.contains("Xe máy")) checkBox1.setChecked(true);
        if (field3.contains("Ô tô")) checkBox2.setChecked(true);
        if (field3.contains("Máy bay")) checkBox3.setChecked(true);

        List<String> publishers = Arrays.asList(getResources().getStringArray(R.array.spinner_array));
        SpinnerAdapter adapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, publishers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        selectSpinnerItemByValue(spinner, field1);

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Tạo hộp thoại chọn ngày
                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Định dạng ngày tháng đầu ra
                        String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                        // Hiển thị ngày đã chọn trong EditText
                        editText2.setText(date);
                    }
                }, year, month, day);

                // Hiển thị hộp thoại chọn ngày
                datePickerDialog.show();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f1 = spinner.getSelectedItem().toString();
                String f2 = editText1.getText().toString();

                String f3 = "";
                if (checkBox1.isChecked()) f3 += checkBox1.getText() + "  ";
                if (checkBox2.isChecked()) f3 += checkBox2.getText() + "  ";
                if (checkBox3.isChecked()) f3 += checkBox3.getText() + "  ";

                String f4 = editText2.getText().toString();
                Long f5 = Long.parseLong(editText3.getText().toString());

                Tour tour = new Tour(f1, f2, f3, f4, f5);
                mDAO.update(tour, id);
                Toast.makeText(DetailActivity.this, "Bản ghi số " + id + " đã được cập nhật", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDAO.delete(id);
                Toast.makeText(DetailActivity.this, "Bản ghi số " + id + " đã bị xoá", Toast.LENGTH_SHORT).show();
                onBackPressed();
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