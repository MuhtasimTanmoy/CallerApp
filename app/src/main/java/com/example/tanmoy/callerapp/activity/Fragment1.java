package com.example.tanmoy.callerapp.activity;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanmoy.callerapp.R;


/**
 * Created by Tanmoy on 10/27/2016.
 */

public class Fragment1 extends Fragment {


    EditText num;
    EditText content;
    Button b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        num= (EditText) view.findViewById(R.id.number);
        content= (EditText) view.findViewById(R.id.content);
        b= (Button) view.findViewById(R.id.smsButton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=num.getText().toString();
                String body=content.getText().toString();

                //This one for just redirecting it to sms app


//                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",number,null));
//                intent.putExtra("sms_body",body);
//                startActivity(intent);


                //This one directly sends sms :)

                    performSMS(number,body);


            }
        });


    }


    void performSMS(String number,String body){


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SEND_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null,body, null, null);
                Toast.makeText(getActivity(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getActivity(),ex.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    performSMS(num.getText().toString(),content.getText().toString());

                } else {

                    Toast.makeText(getActivity(), "Not granted", Toast.LENGTH_SHORT).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
