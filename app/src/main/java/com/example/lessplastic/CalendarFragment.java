package com.example.lessplastic;

import android.app.DatePickerDialog;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {
    private TextView Date;
    private Button Boton;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_calendar, container, false);
        Boton = root.findViewById(R.id.Boton);
        Date = root.findViewById(R.id.Date);

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, dateSetListener, year, month, day);
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d("Fecha" ,   "onDateSet: dd/mm/yy: " + day + "/" + month + "/" + year);
                String dat = day + "/" + month + "/" + year;
                Date.setText(dat);
            }
        };

     return root;
    }

}
   