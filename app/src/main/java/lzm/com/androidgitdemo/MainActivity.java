package lzm.com.androidgitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.lvTest);
        final String[] lists = getResources().getStringArray(R.array.listInfo);
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lists);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = lists[position];
                Toast.makeText(MainActivity.this,item,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
