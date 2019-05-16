package com.example.quintessentiel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quintessentiel.Form.MgrForm;

import java.util.HashMap;


public class FormActivity extends BaseActivity {

    Integer idClient = 0;

    EditText inputAge;
    EditText inputQuantity;
    EditText inputMoreInfos;
    EditText inputWorkEnvironment;
    Spinner skintypeSpinner;
    Spinner productTypeSpinner;
    Spinner effectSpinner;
    Spinner fragranceSpinner;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_custom_recipe);
        super.onCreateDrawer(true);

        SharedPreferences prefs;
        prefs = this.getSharedPreferences("UserPref", 0);
        idClient = prefs.getInt("userId", 0);

        inputAge = (EditText) findViewById(R.id.txtAge);
        inputQuantity = (EditText) findViewById(R.id.txtQuantity);
        inputMoreInfos = (EditText) findViewById(R.id.txtMoreInfos);
        inputWorkEnvironment = (EditText) findViewById(R.id.txtWorkEnvironment);

        skintypeSpinner = (Spinner) findViewById(R.id.cbSkinType);
        ArrayAdapter<CharSequence> skintypeAdapter = ArrayAdapter.createFromResource(this, R.array.skintypes_array, android.R.layout.simple_spinner_item);
        skintypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skintypeSpinner.setAdapter(skintypeAdapter);

        productTypeSpinner = (Spinner) findViewById(R.id.cbProductType);
        ArrayAdapter<CharSequence> productTypeAdapter = ArrayAdapter.createFromResource(this, R.array.product_types_array, android.R.layout.simple_spinner_item);
        productTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productTypeSpinner.setAdapter(productTypeAdapter);

        effectSpinner = (Spinner) findViewById(R.id.cbEffect);
        ArrayAdapter<CharSequence> effectAdapter = ArrayAdapter.createFromResource(this, R.array.effects_array, android.R.layout.simple_spinner_item);
        effectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        effectSpinner.setAdapter(effectAdapter);

        fragranceSpinner = (Spinner) findViewById(R.id.cbFragrance);
        ArrayAdapter<CharSequence> fragranceAdapter = ArrayAdapter.createFromResource(this, R.array.fragrances_array, android.R.layout.simple_spinner_item);
        fragranceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragranceSpinner.setAdapter(fragranceAdapter);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFormView();
            }
        });
    }

    /**
     * Checks if data is correct
     */
    public void confirmFormView() {
        final String errorMessage = getString(R.string.error_empty_field);
        HashMap<String, String> formData = new HashMap<String, String>();

        final String age = inputAge.getText().toString();
        final String skintype = skintypeSpinner.getSelectedItem().toString();
        final String productType = productTypeSpinner.getSelectedItem().toString();
        final String effect = effectSpinner.getSelectedItem().toString();
        final String fragrance = fragranceSpinner.getSelectedItem().toString();
        final String quantity = inputQuantity.getText().toString();
        final String moreInfos = inputMoreInfos.getText().toString();
        final String workEnvironment = inputWorkEnvironment.getText().toString();

        if (age.length() == 0) {
            inputAge.requestFocus();
            inputAge.setError(errorMessage);
        } else if (Integer.parseInt(age) > 100) {
            inputAge.requestFocus();
            inputAge.setError(getString(R.string.error_age));
        } else if (quantity.length() == 0) {
            inputQuantity.requestFocus();
            inputQuantity.setError(errorMessage);
        } else if (Integer.parseInt(quantity) == 0) {
            inputQuantity.requestFocus();
            inputQuantity.setError(getString(R.string.error_quantity));
        } else {
            formData.put("idClient", idClient.toString());
            formData.put("age", age);
            formData.put("skintype", skintype);
            formData.put("productType", productType);
            formData.put("fragrance", fragrance);
            formData.put("effect", effect);
            formData.put("quantity", quantity);
            formData.put("moreInfos", moreInfos);
            formData.put("workEnvironment", workEnvironment);

            confirmFormData(formData);
        }
    }

    /**
     * Display the informations entered by the user to confirm
     * @param formData
     */
    public void confirmFormData(HashMap<String, String> formData) {
        setContentView(R.layout.form_confirmation_data);

        EditText txtAge = (EditText) findViewById(R.id.txtAgeFC);
        txtAge.setText(formData.get("age"));

        EditText txtSkinType = (EditText) findViewById(R.id.cbSkinTypeFC);
        txtSkinType.setText(formData.get("skintype"));

        EditText txtProduct = (EditText) findViewById(R.id.cbProductTypeFC);
        txtProduct.setText(formData.get("productType"));

        EditText txtFragrance = (EditText) findViewById(R.id.cbFragranceFC);
        txtFragrance.setText(formData.get("fragrance"));

        EditText txtEffect = (EditText) findViewById(R.id.cbEffectFC);
        txtEffect.setText(formData.get("effect"));

        EditText txtQuantity = (EditText) findViewById(R.id.txtQuantityFC);
        txtQuantity.setText(formData.get("quantity"));

        EditText txtMoreInfos = (EditText) findViewById(R.id.txtMoreInfosFC);
        txtMoreInfos.setText(formData.get("moreInfos"));

        EditText txtWork = (EditText) findViewById(R.id.txtWorkEnvironmentFC);
        txtWork.setText(formData.get("workEnvironment"));

        Button btnSend = (Button) findViewById(R.id.btnSendForm);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                MgrForm mgrForm = new MgrForm();
                mgrForm.insertForm(formData);
                Intent myIntent = new Intent(getApplicationContext(), CatalogActivity.class);
                startActivity(myIntent);
                Toast.makeText(getApplicationContext(),getString(R.string.confirm_form_sent),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
