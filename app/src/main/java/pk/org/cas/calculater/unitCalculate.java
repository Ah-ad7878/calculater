package pk.org.cas.calculater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;
import java.util.Map;

public class unitCalculate extends AppCompatActivity {

    TextView unit_measure, To_unit;
    AppCompatSpinner spinner_one, spinner_2;
    EditText from_unit;
    ChipGroup chipGroup;
    Map<String, String[]> unitMap;
    UnitCalculation unitCalculation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_calculate);

        unitCalculation = new UnitCalculation();
        unit_measure = findViewById(R.id.unit_measure);
        spinner_one = findViewById(R.id.spinner_one);
        spinner_2 = findViewById(R.id.spinner_2);
        chipGroup = findViewById(R.id.cip_group);
        from_unit = findViewById(R.id.from_unit);
        To_unit = findViewById(R.id.To_unit);

        unit_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(unitCalculate.this, "previous page", Toast.LENGTH_SHORT).show();
            }
        });

        initUnitData();


        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, java.util.List<Integer> checkedIds) {
                if (!checkedIds.isEmpty()) {
                    Chip chip = findViewById(checkedIds.get(0));
                    if (chip != null) {
                        String category = chip.getText().toString();
                        updateSpinnerData(category);
                        performCalculation();
                    }
                }
            }
        });


        from_unit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performCalculation();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                performCalculation();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        spinner_one.setOnItemSelectedListener(spinnerListener);
        spinner_2.setOnItemSelectedListener(spinnerListener);


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

    private void performCalculation() {
        String input = from_unit.getText().toString();
        if (input.isEmpty()) {
            To_unit.setText("");
            return;
        }

        try {
            double value = Double.parseDouble(input);
            if (spinner_one.getSelectedItem() == null || spinner_2.getSelectedItem() == null) return;

            String fromUnit = spinner_one.getSelectedItem().toString();
            String toUnit = spinner_2.getSelectedItem().toString();
            
            int checkedId = chipGroup.getCheckedChipId();
            if (checkedId == View.NO_ID) return;
            
            Chip checkedChip = findViewById(checkedId);
            String category = checkedChip.getText().toString();
            double result = 0;

            switch (category) {
                case "Area":
                    result = unitCalculation.convertArea(fromUnit, toUnit, value);
                    break;
                case "Length":
                    result = unitCalculation.convertLength(fromUnit, toUnit, value);
                    break;
                case "Temperature":
                    result = unitCalculation.convertTemperature(fromUnit, toUnit, value);
                    break;
                case "Data":
                    result = unitCalculation.convertData(fromUnit, toUnit, value);
                    break;
                case "Tax":
                    result = unitCalculation.taxCalculate(fromUnit, toUnit, value);
                    break;
                case "Mass":
                    result = unitCalculation.massCalculate(fromUnit, toUnit, value);
                    break;
            }

            if (result == (long) result) {
                To_unit.setText(String.valueOf((long) result));
            } else {
                To_unit.setText(String.format("%.4f", result));
            }
        } catch (NumberFormatException e) {
            To_unit.setText("");
        }
    }
}
