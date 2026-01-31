package pk.org.cas.calculater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;
import java.util.Map;

public class unitCalculate extends AppCompatActivity {

    TextView unit_measure;
    AppCompatSpinner spinner_one, spinner_2;
    ChipGroup chipGroup;
    Map<String, String[]> unitMap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_calculate);

        unit_measure = findViewById(R.id.unit_measure);
        unit_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(unitCalculate.this, "previous page", Toast.LENGTH_SHORT).show();
            }
        });

        spinner_one = findViewById(R.id.spinner_one);
        spinner_2 = findViewById(R.id.spinner_2);
        chipGroup = findViewById(R.id.cip_group);

        initUnitData();

        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, java.util.List<Integer> checkedIds) {
                if (!checkedIds.isEmpty()) {
                    Chip chip = findViewById(checkedIds.get(0));
                    if (chip != null) {
                        String category = chip.getText().toString();
                        updateSpinnerData(category);
                    }
                }
            }
        });

        if (chipGroup.getChildCount() > 0) {
            Chip firstChip = (Chip) chipGroup.getChildAt(0);
            firstChip.setChecked(true);
            updateSpinnerData(firstChip.getText().toString());
        }
    }

    private void initUnitData() {
        unitMap = new HashMap<>();
        unitMap.put("Area", new String[]{"Acres(ac)", "Square Foot", "Square Inch", "Square Meter"});
        unitMap.put("Length", new String[]{"Millimeter", "Centimeter", "Meter", "Kilometer"});
        unitMap.put("Temperature", new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        unitMap.put("Data", new String[]{"Byte", "Kilobyte", "Megabyte", "Gigabyte"});
        unitMap.put("Tax", new String[]{"Percentage", "Decimal"});
        unitMap.put("Mass", new String[]{"Milligram", "Gram", "Kilogram"});
    }

    private void updateSpinnerData(String category) {
        String[] data = unitMap.get(category);
        if (data != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, data);
            adapter.setDropDownViewResource(R.layout.spinner_item);

            spinner_one.setAdapter(adapter);
            spinner_2.setAdapter(adapter);
        }
    }
}
