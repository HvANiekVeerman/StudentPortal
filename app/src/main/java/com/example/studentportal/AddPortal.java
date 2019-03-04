package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        Button addPortal = findViewById(R.id.addPortal);
        final EditText url = findViewById(R.id.dynamicUrl);
        final EditText title = findViewById(R.id.dynamicTitle);

        // Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.addPortalToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Add portal button functionality
        addPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUrl = url.getText().toString();
                String newTitle = title.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("newUrl", newUrl);
                intent.putExtra("newTitle", newTitle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
