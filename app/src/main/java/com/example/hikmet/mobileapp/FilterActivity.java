package com.example.hikmet.mobileapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hukuk.mobileapp.entity.GetMattersResponse;
import com.hukuk.mobileapp.entity.GlobalVariables;
import com.hukuk.mobileapp.entity.IpModel;
import com.hukuk.mobileapp.entity.Matter;
import com.hukuk.mobileapp.webservice.WebService;

import java.util.Calendar;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    private ImageButton btnStartDate;
    private ImageButton btnEndDate;
    private Button btnfilter;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText startdate;
    private EditText enddate;
    private String flagclickdate="";
    private FilterActivity.FilterInitTask mAuthTask = null;
    private FilterActivity.SpinAdapter adapter;
    private EditText mDbName;
    private EditText mUserId;
    private EditText mPasswordView;
    private View mProgressView;
    private View mFilterFormView;
    private Spinner spinnerMatters;
    private Matter[] mattersList;
    private GetMattersResponse matterModel;
    int reporttpye=0;
    public void TestActivity() throws Exception {

        GlobalVariables.GetInstance().ipmodel= new IpModel("120.0.0.1","hs_hukuk","ABCHUKUK","success","GetIpContract","Giriş Başarılı");
        //WebService.getInstance().Test();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        btnStartDate = (ImageButton) findViewById(R.id.btnstartdate);
        btnfilter = (Button)findViewById(R.id.btnfilter);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        startdate = (EditText) findViewById(R.id.startdate);
        btnStartDate.setOnClickListener((View.OnClickListener) this);


        btnEndDate = (ImageButton) findViewById(R.id.btnenddate);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        enddate = (EditText) findViewById(R.id.enddate);
        btnEndDate.setOnClickListener((View.OnClickListener) this);

        mFilterFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mattersList= new Matter[0];
        adapter = new FilterActivity.SpinAdapter(FilterActivity.this, android.R.layout.simple_spinner_item,mattersList);

        spinnerMatters=(Spinner) findViewById(R.id.matters_spinner);

        spinnerMatters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                Matter matter = adapter.getItem(position);
                // Here you can do the action you want to...
                Toast.makeText(FilterActivity.this, "ID: " + matter.get_projeadi() + "\nName: " + matter.get_projekodu(),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int v_position=spinnerMatters.getSelectedItemPosition();
                String v_startdate=startdate.getText().toString();
                String v_enddate=enddate.getText().toString();


                GlobalVariables.GetInstance().ReportType=reporttpye;
                GlobalVariables.GetInstance().MatterId=mattersList[v_position].get_projekodu();
                GlobalVariables.GetInstance().StartDate=v_startdate;
                GlobalVariables.GetInstance().EndDate=v_enddate;

                Intent intent= new Intent(FilterActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        showProgress(true);
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.personal:
                if (checked)
                    reporttpye=1;
                break;
            case R.id.firstcntr:
                if (checked)
                    reporttpye=2;
                break;
            case R.id.finalcntr:
                if (checked)
                    reporttpye=3;
                break;
        }
        mAuthTask = new FilterActivity.FilterInitTask(reporttpye);
        mAuthTask.execute((Matter[]) null);
    }

    @Override
    public void onClick(View v) {
        flagclickdate=getResources().getResourceName(v.getId());

        showDialog(0);
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            if(flagclickdate.contains("btnstartdate"))
                startdate.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
            else
                enddate.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mFilterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mFilterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mFilterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mFilterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class FilterInitTask extends AsyncTask<Matter[], Void, Matter[]> {

        private final int gridtype;

        FilterInitTask(int radiobtnselected) {
            gridtype = radiobtnselected;
        }

        @Override
        protected Matter[]  doInBackground(Matter[]... params) {
            // TODO: attempt authentication against a network service.



            try {
                TestActivity();
                matterModel= WebService.getInstance().GetMatters(GlobalVariables.GetInstance().ipmodel.GetDbName() ,gridtype);
                List<Matter> list=matterModel.getMatters();
                mattersList=list.toArray(new Matter[list.size()]);

                final String Message=matterModel.getMessage();
                if(matterModel.getStatu()=="fail")
                {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FilterActivity.this, Message, Toast.LENGTH_LONG).show();
                        }
                    });
                    return null;
                }
            } catch (Exception e)
            {
                final String Message=e.getMessage();
                // TODO Auto-generated catch block
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FilterActivity.this, Message, Toast.LENGTH_LONG).show();
                    }
                });
                e.printStackTrace();
                return null;
            }




            return mattersList;
        }

        @Override
        protected void onPostExecute(final Matter[] list) {
            mAuthTask = null;
            showProgress(false);

            if (list!= null){
                adapter = new FilterActivity.SpinAdapter(FilterActivity.this, android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMatters.setAdapter(adapter);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
    public class SpinAdapter extends ArrayAdapter<Matter>
    {
        // Your sent context
        private Context context;
        // Your custom values for the spinner (User)
        private Matter[] values;

        public SpinAdapter(Context context, int textViewResourceId,
                           Matter[] values) {
            super(context, textViewResourceId, values);
            this.context = context;
            this.values = values;
        }

        public int getCount(){
            return values.length;
        }

        public Matter getItem(int position){
            return values[position];
        }

        public long getItemId(int position){
            return position;
        }


        // And the "magic" goes here
        // This is for the "passive" state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            // Then you can get the current item using the values array (Users array) and the current position
            // You can NOW reference each method you has created in your bean object (User class)
            label.setText(values[position].get_projeadi());

            // And finally return your dynamic (or custom) view for each spinner item
            return label;
        }

        // And here is when the "chooser" is popped up
        // Normally is the same view, but you can customize it if you want
        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(values[position].get_projekodu());

            return label;
        }
    }


}
