package example.smarthome;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.gordonwong.materialsheetfab.AnimatedFab;
import com.gordonwong.materialsheetfab.MaterialSheetFab;

import java.util.ArrayList;
import java.util.List;

public class AddApplianceActivity extends AppCompatActivity  {
    View sheetView;
    ArrayList<String> appl = new ArrayList<String>();
    String[] appliances = {"TubeLight 1","TubeLight 2","Fan 1","Fan 2","Lamp 1","Lamp 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appliance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        sheetView = findViewById(R.id.fab_sheet);

        // Floating Action Button and sheet
        Fab fab = (Fab) findViewById(R.id.fab);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.colorPrimary1);
        int fabColor = getResources().getColor(R.color.colorPrimaryDark);

        // Initialize material sheet FAB
        MaterialSheetFab m = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        // Main list
        final ArrayAdapter adapter2 = new ArrayAdapter<String>(this,R.layout.listview_appliancemain,appl);
        ListView mainAppliance = (ListView)findViewById(R.id.listView);
        mainAppliance.setAdapter(adapter2);

        // Sheet list and dialog box
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,R.layout.listview_appliance,appliances);
        ListView appliance = (ListView)sheetView.findViewById(R.id.listView2);
        appliance.setAdapter(adapter1);

        appliance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Dialog dialog = new Dialog(AddApplianceActivity.this);
                dialog.setContentView(R.layout.dialog);

                Button set = (Button) dialog.findViewById(R.id.button10);
                final EditText tag = (EditText) dialog.findViewById(R.id.editText10);
                dialog.show();

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appl.add(appliances[position] + " " + tag.getText().toString());
                        adapter2.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

            }
        });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
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
                Intent intent = new Intent(AddApplianceActivity.this, SetupRooms.class);
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

