package com.example.sat14.multithreaded;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = "numbers.txt";
        file = new File(this.getFilesDir(), fileName);
    }

    public void createFile(View v) {
        //File file = new File(context.getFilesDir(), numbers.txt);




        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
        {
            for (int i = 1; i < 11; i++)
            {
                String output = (String.valueOf(i) + "\n");
                bw.write(output);
            }

            Thread.sleep(250);
            bw.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFile(View v) {
        ListView myList = (ListView) findViewById(R.id.listView);

        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        myList.setAdapter(myAdapter);

        try (BufferedReader br = new BufferedReader(new FileReader(file)))  {

            String line;
            while ((line = br.readLine()) != null) {
                myAdapter.add(line);
            }

            br.close();

            Thread.sleep(250);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile(View v) {
        myAdapter.clear();
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String fileName;
    private File file;
    private ArrayAdapter<String> myAdapter;
}