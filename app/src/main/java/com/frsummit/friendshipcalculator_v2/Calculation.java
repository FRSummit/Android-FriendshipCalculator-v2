package com.frsummit.friendshipcalculator_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;*/

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Calculation extends Activity {
    List<String> sex = new ArrayList<>();
    List<String> days = new ArrayList<>();
    List<String> months = new ArrayList<>();
    List<String> years = new ArrayList<>();
    int currentDay, currentMonth, currentYear;
    TextView yourName, friendName;
    Spinner spinner, spinner_f, date_spinner_day, date_spinner_day_f, date_spinner_month, date_spinner_month_f, date_spinner_year, date_spinner_year_f;
    NameParcentageByNumber nameParcentageByNumber = new NameParcentageByNumber();
    SexParcentageByGender sexParcentageByGender = new SexParcentageByGender();
    DOBParcentageByDate dobParcentageByDate = new DOBParcentageByDate();
    CheckDeveloper checkDeveloper = new CheckDeveloper();
    int nameCalculation = 0, sexCalculation = 0, dobCalculation = 0, totalParcentage = 0;

    private InterstitialAd mInterstitialAd; // AdMov

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        yourName = (TextView) findViewById(R.id.your_name);
        friendName = (TextView) findViewById(R.id.your_friend_name);
        spinner = (Spinner) findViewById(R.id.sex_spinner);
        spinner_f = (Spinner) findViewById(R.id.friend_sex_spinner);
        date_spinner_day = (Spinner) findViewById(R.id.date_spinner_day);
        date_spinner_day_f = (Spinner) findViewById(R.id.friend_date_spinner_day);
        date_spinner_month = (Spinner) findViewById(R.id.date_spinner_month);
        date_spinner_month_f = (Spinner) findViewById(R.id.friend_date_spinner_month);
        date_spinner_year = (Spinner) findViewById(R.id.date_spinner_year);
        date_spinner_year_f = (Spinner) findViewById(R.id.friend_date_spinner_year);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        currentDay = Integer.parseInt(dateFormat.format(date).split(" ")[0].split("/")[0]);
        currentMonth = Integer.parseInt(dateFormat.format(date).split(" ")[0].split("/")[0]);
        currentYear = Integer.parseInt(dateFormat.format(date).split(" ")[0].split("/")[0]);

        sexSpinner();
        createDays();
        createMonths();
        createYears();

        /************************** ADMOV **************************/
        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test unit id
        mInterstitialAd.setAdUnitId("ca-app-pub-4861848901455235/7087818880"); // Real unit id
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(request);
        adEvent();
        /************************** ADMOV **************************/
    }

    public void calculateBtnClick(View view) {
        if(validate()) {
            String name_1 = yourName.getText().toString();
            String name_2 = friendName.getText().toString();

            String sex_1 = spinner.getSelectedItem().toString();
            String sex_2 = spinner_f.getSelectedItem().toString();

            int dob_1_dd = Integer.parseInt(date_spinner_day.getSelectedItem().toString());
            int dob_2_dd = Integer.parseInt(date_spinner_day_f.getSelectedItem().toString());
            int dob_1_mm = Integer.parseInt(date_spinner_month.getSelectedItem().toString());
            int dob_2_mm = Integer.parseInt(date_spinner_month_f.getSelectedItem().toString());
            int dob_1_yy = Integer.parseInt(date_spinner_year.getSelectedItem().toString());
            int dob_2_yy = Integer.parseInt(date_spinner_year_f.getSelectedItem().toString());

            if(checkDeveloper.checkDeveloperAndHisWife(name_1, name_2)) {
                nameCalculation = nameParcentageByNumber.getNameParcentage(name_1, name_2);
                sexCalculation = sexParcentageByGender.getSexParcentage(sex_1, sex_2);
                dobCalculation = dobParcentageByDate.getBODParcentage(dob_1_dd, dob_1_mm, dob_1_yy, dob_2_dd, dob_2_mm, dob_2_yy);
                totalParcentage = nameCalculation * sexCalculation * dobCalculation;
                int digits = (int)(Math.log10(totalParcentage)+1);
                System.out.println(totalParcentage + " " + digits);
                if(digits == 3) totalParcentage /= 10;
                else if(digits ==4) totalParcentage /= 100;
                else if(digits ==5) totalParcentage /= 1000;
                else if(digits ==6) totalParcentage /= 10000;
                else if(digits == 7) totalParcentage /= 100000;
                else if(digits == 8) totalParcentage /= 1000000;
            } else {
                totalParcentage = 100;
            }
//            System.out.println(nameCalculation + "%" + " " + sexCalculation + "%" + " " + dobCalculation + "%" + " " + totalParcentage + "%");

            if(totalParcentage > 9 && totalParcentage < 20) totalParcentage += 51;
            else if(totalParcentage > 19 && totalParcentage < 30) totalParcentage += 42;
            else if(totalParcentage > 29 && totalParcentage < 40) totalParcentage += 35;
            else if(totalParcentage > 39 && totalParcentage < 50) totalParcentage += 31;
            else if(totalParcentage > 49 && totalParcentage < 60) totalParcentage += 15;
            else if(totalParcentage > 59 && totalParcentage < 70) totalParcentage += 9;
            else totalParcentage = totalParcentage;

            Intent intent = new Intent(this, CalculationResult.class);
            intent.putExtra("result", Integer.toString(totalParcentage));
            startActivity(intent);
        }
    }

    private boolean validate() {
        if((yourName.getText() == null || yourName.getText() == "" || yourName.getText().length() < 1) ||
                (friendName.getText() == null || friendName.getText() == "" || friendName.getText().length() < 1)) {
            Toast.makeText(this, "Name Should not be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    // SEX SPINNER
    public void sexSpinner() {
        sex.add("Male");
        sex.add("Female");
        sex.add("Other");
        spinnerArrayAdapter(spinner, sex);
        spinnerArrayAdapter(spinner_f, sex);
    }

    // Date Create
    public void createDays() {
        for(int day=1; day<32; day++) {
            String d = Integer.toString(day);
            if(day < 10) {
                d = "0" + d;
            }
            days.add(d);
        }
        spinnerArrayAdapter(date_spinner_day, days);
        spinnerArrayAdapter(date_spinner_day_f, days);
    }

    public void createMonths() {
        for(int month=1; month<13; month++) {
            String m = Integer.toString(month);
            if(month < 10) {
                m = "0" + m;
            }
            months.add(m);
        }
        spinnerArrayAdapter(date_spinner_month, months);
        spinnerArrayAdapter(date_spinner_month_f, months);
    }

    public void createYears() {
        for(int year=currentYear; year>1960; year--) {
            String y = Integer.toString(year);
            years.add(y);
        }
        spinnerArrayAdapter(date_spinner_year, years);
        spinnerArrayAdapter(date_spinner_year_f, years);
    }

    public void spinnerArrayAdapter(Spinner spinner, List<String> list) {
        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        yearSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(yearSpinnerAdapter);
    }




    /************************** ADMOV ************************** ADMOV ************************** ADMOV ************************** ADMOV **************************/
    /************************** ADMOV ************************** ADMOV ************************** ADMOV ************************** ADMOV **************************/

    public void interBtnClick(View view) {
        if(mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            Toast.makeText(this, "The interstitial wasn't loaded yet.", Toast.LENGTH_LONG).show();
        }
    }

    public void adEvent() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "Ad is loaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(getApplicationContext(), "Ad is clicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed", Toast.LENGTH_LONG).show();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }
    /************************** ADMOV ************************** ADMOV ************************** ADMOV ************************** ADMOV **************************/
    /************************** ADMOV ************************** ADMOV ************************** ADMOV ************************** ADMOV **************************/
}
