package com.example.mubeen.bloodbank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BloodBanks extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_layout);
        RecyclerView program = (RecyclerView) findViewById(R.id.my_recycler_view);
        program.setLayoutManager(new LinearLayoutManager(this));
        bank b[] = new bank[10];
        b[0] = new bank("Fatimid Foundation Lahore","04235210834","72-A, Block# D-II, Johar Town, Lahore., Block D 2 Phase 1 Johar Town, Lahore, Punjab 54000");
        b[1] = new bank("Online Blood Donors Organization","03084402414","790-A, Q block, Model Town.، Block R Model Town, Lahore, Punjab 54040");
        b[2] = new bank("Fatmid Foundation (bloodbank& haematogical)","02132225284","Garden East, Karachi, Karachi City, Sindh");
        b[3] = new bank("AKU Aga Khan University Hospital Blood Bank","04235210834","72-A, Block# D-II, Johar Town, Lahore., Block D 2 Phase 1 Johar Town, Lahore, Punjab 54000");
        b[4] = new bank("Blood Bank, PIMS","03005573087","G-8/3 G 8/3 G-8, Islamabad, Islamabad Capital Territory");
        b[5] = new bank("Al Aqsa Welfare Trust Volunteer Blood Donations Org","03335738615","G-8/3 G 8/3 G-8, Islamabad, Islamabad Capital Territory");
        b[6] = new bank("Ali Zaib Blood Bank","0412409754","Blood Bank, Faisalabad, Punjab");
        b[7] = new bank("Ali Zaib Blood Transfusion Services","0418715997","University Road, Faisalabad, Punjab");
        b[8] = new bank("Amina Blood Bank","03017542456","Lalazar Colony Rd، Multan");
        b[9] = new bank("Nishtar Blood Bank","03006516316","Gillani Colony, Multan, Punjab");

        program.setAdapter(new Adapter(b));
    }

}
