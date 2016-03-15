package example.smarthome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.ArrayList;
import java.util.Calendar;

public class Scenes extends AppCompatActivity implements View.OnClickListener {

    Button btnCalendar, btnTimePicker, set;
    EditText txtDate, txtTime;
    Spinner sRoomType;
    String a;
    String[] rooms = {"Bedroom", "Kitchen", "Hall"};
    ArrayList<String> sceneList = new ArrayList<>();


    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        Fab fab = (Fab) findViewById(R.id.fab);
        View overlay = findViewById(R.id.overlay);
        View sheetView = findViewById(R.id.fab_sheet);
        int sheetColor = getResources().getColor(R.color.colorPrimary1);
        int fabColor = getResources().getColor(R.color.colorPrimaryDark);

        MaterialSheetFab m = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        btnCalendar = (Button) sheetView.findViewById(R.id.btnCalendar);
        btnTimePicker = (Button) sheetView.findViewById(R.id.btnTimePicker);
        set = (Button)sheetView.findViewById(R.id.button2);

        txtDate = (EditText) sheetView.findViewById(R.id.txtDate);
        txtTime = (EditText) sheetView.findViewById(R.id.txtTime);

        btnCalendar.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        final ArrayAdapter adapter = new ArrayAdapter<>(Scenes.this,R.layout.listview_scenes,sceneList);
        ListView listScene = (ListView)findViewById(R.id.listView4);
        listScene.setAdapter(adapter);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sceneList.add(a + " " + txtDate.getText().toString() + " " + txtTime.getText().toString());
                adapter.notifyDataSetChanged();
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(set.getWindowToken(), 0);
            }
        });

        sRoomType = (Spinner)sheetView.findViewById(R.id.selectRoom_spinner);
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,rooms);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sRoomType.setAdapter(adapter1);
        sRoomType.setPrompt("Select Room");

        sRoomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClick(View v) {

        if (v == btnCalendar) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            txtDate.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }

        if (v == btnTimePicker) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
    }
}
