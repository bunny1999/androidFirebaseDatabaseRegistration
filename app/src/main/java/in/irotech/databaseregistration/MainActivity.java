package in.irotech.databaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText1;
    EditText editText2;
    Button button;
    Button button2;

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference rootRef=db.getReference();
    DatabaseReference usersRef=rootRef.child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText2);
        editText2=findViewById(R.id.editText3);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("Username",editText.getText().toString());
                hashMap.put("Name",editText1.getText().toString());
                hashMap.put("Email",editText2.getText().toString());

                usersRef.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,list_view.class);
                startActivity(intent);
            }
        });
    }
}
