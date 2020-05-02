package in.irotech.databaseregistration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;

public class list_view extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference rootRef=db.getReference();
    DatabaseReference userRef=rootRef.child("Users");

    ArrayList<String> arrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView=findViewById(R.id.list);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(list_view.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                JSONObject jsonObject= null;
                String email=null;
                try {
                    jsonObject = new JSONObject(dataSnapshot.getValue().toString());
                    email=jsonObject.getString("Email");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                arrayList.add(email);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
