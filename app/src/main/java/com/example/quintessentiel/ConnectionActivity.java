package com.example.quintessentiel;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.quintessentiel.R;
import com.example.quintessentiel.User.CtrlUser;

public class ConnectionActivity extends AppCompatActivity {

    private CtrlUser ctrlUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        this.ctrlUser = new CtrlUser(this);

        if(this.ctrlUser.checkIfConnected()){   //If user is already connected
            loadCatalogPage();
        }
        else{   //If user is not connected yet

            //On connection button click
            findViewById(R.id.btnConnection).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    EditText usernameField = findViewById(R.id.txtUsername);
                    EditText passwordField = findViewById(R.id.txtPassword);

                    String usernameVal = usernameField.getText().toString();
                    String passwordVal = passwordField.getText().toString();


                    if(usernameVal.equals("") || passwordVal.equals("")){   //If fields are empty
                        System.out.println("please enter something");
                    }
                    else{

                        if(ctrlUser.checkCredentials(usernameVal,passwordVal)){
                            System.out.println("CONNECTED");
                        }
                        else{
                            System.out.println("ERROR, CAN'T CONNECT");
                        }

                        System.out.println("Done");
                    }

                }
            });
        }
    }

    /**
     * Brings the user to the catalog page once he's connected
     */
    public void loadCatalogPage(){
        System.out.println("HERERERERRE");
    }

    public CtrlUser getCtrlUser(){
        return this.ctrlUser;
    }


}
