package example.smarthome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.ArrayList;

public class SetupRooms extends AppCompatActivity {

    String a,b;
    ArrayList<String> roomList = new ArrayList<>();
    Spinner roomType, appliance;
    EditText name;
    Button setRoom;
    String[] rooms = {"Bedroom", "Kitchen", "Hall"};
    String[] appliances = {"TubeLight 1","TubeLight 2","Fan 1","Fan 2","Lamp 1","Lamp 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_rooms);
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

        roomType = (Spinner)sheetView.findViewById(R.id.room_spinner);
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, rooms);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomType.setAdapter(adapter1);
        roomType.setPrompt("Select Room");

        roomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        appliance = (Spinner)sheetView.findViewById(R.id.appliance_spinner);
        ArrayAdapter adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,appliances);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appliance.setAdapter(adapter2);
        appliance.setPrompt("Select Appliance");

        appliance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        name = (EditText)sheetView.findViewById(R.id.editText1);

        final ArrayAdapter adapter3 = new ArrayAdapter<>(SetupRooms.this,R.layout.listview_rooms,roomList);
        ListView listRoom = (ListView)findViewById(R.id.listView3);
        listRoom.setAdapter(adapter3);

        setRoom = (Button)sheetView.findViewById(R.id.button1);
        setRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomList.add(a + " " + b + " " + name.getText().toString());
                adapter3.notifyDataSetChanged();
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(setRoom.getWindowToken(), 0);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.next:
                Intent intent = new Intent(SetupRooms.this, Scenes.class);
                startActivity(intent);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
