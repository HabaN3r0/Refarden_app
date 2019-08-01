package kenneth.com.refardenapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.view.Gravity;
import android.provider.CalendarContract;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by 1002215 on 19/7/19.
 */


public class HomeFragment extends Fragment {

    // Main layout elements
    public LinearLayout[] pot_wrapper = new LinearLayout[45];
    public LinearLayout[] pot_list = new LinearLayout[45];
    public LinearLayout[] pot_progress = new LinearLayout[45];
    public TextView[] pot_text = new TextView[45];
    public TextView[] percentage_text = new TextView[45];

    // Popup layout elements
    public LinearLayout[] big_pot_wrapper = new LinearLayout[45];
    public LinearLayout[] big_pot_list = new LinearLayout[45];
    public LinearLayout[] big_pot_progress = new LinearLayout[45];
    public TextView[] big_pot_text = new TextView[45];
    public TextView[] big_percentage_text = new TextView[45];
    public LinearLayout[] big_button_list = new LinearLayout[45];

    // All major layout elements
    public LinearLayout lay1;
    public LinearLayout lay2;
    public LinearLayout lay3;
    public LinearLayout lay_Lleft;
    public LinearLayout lay_Lmid;
    public LinearLayout lay_Lright;
    public LinearLayout lay_Mleft;
    public LinearLayout lay_Mmid;
    public LinearLayout lay_Mright;
    public LinearLayout lay_Rleft;
    public LinearLayout lay_Rmid;
    public LinearLayout lay_Rright;
    public LinearLayout mid_left_shift;
    public LinearLayout mid_right_shift;
    public LinearLayout right_mid_shift;
    public LinearLayout left_mid_shift;
    public LinearLayout overlay1;
    public LinearLayout overlay2;
    public LinearLayout overlay3;
    public LinearLayout lay_big1;
    public LinearLayout lay_big2;
    public LinearLayout lay_big3;
    public LinearLayout lay_big_Lleft;
    public LinearLayout lay_big_Lmid;
    public LinearLayout lay_big_Lright;
    public LinearLayout lay_big_Mleft;
    public LinearLayout lay_big_Mmid;
    public LinearLayout lay_big_Mright;
    public LinearLayout lay_big_Rleft;
    public LinearLayout lay_big_Rmid;
    public LinearLayout lay_big_Rright;

    public LinearLayout lay_big_name;
    public RelativeLayout lay_name;
    public EditText set_name_text;
    public Button set_name_button;

    public int button_counter;
    public int internal_button_counter;

    public List<Integer> pot_level = new ArrayList<>();
    public List<int[]> plant_location_list = new ArrayList<>();
    public List<String> pot_name = new ArrayList<>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    private static final String TAG = "Home Fragment";
    private TextView textViewTemperature;
    private TextView textViewSolution;
    private TextView textViewHumidity;
    private TextView textViewLight;
    private TextView textViewWater;
    private TextView textViewPh;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textViewTemperature = view.findViewById(R.id.temperature);
        textViewSolution = view.findViewById(R.id.solution);
        textViewHumidity = view.findViewById(R.id.humidity);
        textViewLight = view.findViewById(R.id.light);
        textViewWater = view.findViewById(R.id.water);
        textViewPh = view.findViewById(R.id.ph);
        mAuth = FirebaseAuth.getInstance();

        //Set Fragment Title
        getActivity().setTitle("Home");

        lay1 = (LinearLayout) view.findViewById(R.id.container_mid1);
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                big_layoutL_appear();
            }
        });

        lay2 = (LinearLayout) view.findViewById(R.id.container_mid2);
        ViewGroup.LayoutParams params = lay2.getLayoutParams();
        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                big_layoutM_appear();
            }
        });

        lay3 = (LinearLayout) view.findViewById(R.id.container_mid3);
        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                big_layoutR_appear();
            }
        });

        mid_left_shift = (LinearLayout) view.findViewById(R.id.container_mid_left);
        mid_left_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiftMidtoLeft();
            }
        });

        mid_right_shift = (LinearLayout) view.findViewById(R.id.container_mid_right);
        mid_right_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiftMidtoRight();
            }
        });

        left_mid_shift = (LinearLayout) view.findViewById(R.id.container_left_mid);
        left_mid_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiftLefttoMid();
            }
        });

        right_mid_shift = (LinearLayout) view.findViewById(R.id.container_right_mid);
        right_mid_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiftRighttoMid();
            }
        });

        overlay1 = (LinearLayout) view.findViewById(R.id.container_overlay1);
        overlay1.setVisibility(View.GONE);
        lay_big1 = (LinearLayout) view.findViewById(R.id.container_big1);
        overlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                big_layoutL_disappear();
            }
        });

        overlay2 = (LinearLayout) view.findViewById(R.id.container_overlay2);
        overlay2.setVisibility(View.GONE);
        lay_big2 = (LinearLayout) view.findViewById(R.id.container_big2);
        overlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                big_layoutM_disappear();
            }
        });

        overlay3 = (LinearLayout) view.findViewById(R.id.container_overlay3);
        overlay3.setVisibility(View.GONE);
        lay_big3 = (LinearLayout) view.findViewById(R.id.container_big3);
        overlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                big_layoutR_disappear();
            }
        });

        lay1.setVisibility(View.INVISIBLE);
        lay3.setVisibility(View.INVISIBLE);
        right_mid_shift.setVisibility(View.INVISIBLE);
        left_mid_shift.setVisibility(View.INVISIBLE);

        lay_big_name = (LinearLayout) view.findViewById(R.id.container_overlay_name);
        lay_big_name.setVisibility(View.INVISIBLE);
        lay_name = (RelativeLayout) view.findViewById(R.id.container_name_input);
        set_name_text = (EditText) view.findViewById(R.id.textplantname);
        set_name_button = (Button) view.findViewById(R.id.button_enter_name);
        lay_big_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_big_name.setVisibility(View.INVISIBLE);
            }
        });

        int wid = params.height;

        lay_Lleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Lleft = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        params_Lleft.setMargins(0,0,0,0);
        lay_Lleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Lleft.setOrientation(LinearLayout.VERTICAL);
        lay_Lleft.setLayoutParams(params_Lleft);
        lay_Lleft.setBackgroundResource(R.drawable.mini_small);

        lay_Lmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Lmid = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Lmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Lmid.setOrientation(LinearLayout.VERTICAL);
        lay_Lmid.setLayoutParams(params_Lmid);
        lay_Lmid.setBackgroundResource(R.drawable.mini_small);

        lay_Lright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Lright = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Lright.setOrientation(LinearLayout.VERTICAL);
        lay_Lright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Lright.setLayoutParams(params_Lright);
        lay_Lright.setBackgroundResource(R.drawable.mini_small);

        lay_Mleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Mleft = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        params_Mleft.setMargins(0,0,0,0);
        lay_Mleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Mleft.setOrientation(LinearLayout.VERTICAL);
        lay_Mleft.setLayoutParams(params_Mleft);
        lay_Mleft.setBackgroundResource(R.drawable.mini_small);

        lay_Mmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Mmid = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Mmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Mmid.setOrientation(LinearLayout.VERTICAL);
        lay_Mmid.setLayoutParams(params_Mmid);
        lay_Mmid.setBackgroundResource(R.drawable.mini_small);

        lay_Mright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Mright = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Mright.setOrientation(LinearLayout.VERTICAL);
        lay_Mright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Mright.setLayoutParams(params_Mright);
        lay_Mright.setBackgroundResource(R.drawable.mini_small);

        lay_Rleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Rleft = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        params_Rleft.setMargins(0,0,0,0);
        lay_Rleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Rleft.setOrientation(LinearLayout.VERTICAL);
        lay_Rleft.setLayoutParams(params_Rleft);
        lay_Rleft.setBackgroundResource(R.drawable.mini_small);

        lay_Rmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Rmid = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Rmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Rmid.setOrientation(LinearLayout.VERTICAL);
        lay_Rmid.setLayoutParams(params_Rmid);
        lay_Rmid.setBackgroundResource(R.drawable.mini_small);

        lay_Rright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_Rright = new LinearLayout.LayoutParams((int)(wid*2/9), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_Rright.setOrientation(LinearLayout.VERTICAL);
        lay_Rright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_Rright.setLayoutParams(params_Rright);
        lay_Rright.setBackgroundResource(R.drawable.mini_small);

        lay1.addView(lay_Lleft);
        lay1.addView(lay_Lmid);
        lay1.addView(lay_Lright);

        lay2.addView(lay_Mleft);
        lay2.addView(lay_Mmid);
        lay2.addView(lay_Mright);

        lay3.addView(lay_Rleft);
        lay3.addView(lay_Rmid);
        lay3.addView(lay_Rright);

        ViewGroup.LayoutParams bigLparams = lay_big1.getLayoutParams();
        int wid1 = bigLparams.width - 75;
//        Log.v(TAG, Integer.toString(wid));

        lay_big_Lleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Lleft = new LinearLayout.LayoutParams((int)(wid1/3), LinearLayout.LayoutParams.MATCH_PARENT);
        params_big_Lleft.setMargins(20,0,0,0);
        lay_big_Lleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Lleft.setOrientation(LinearLayout.VERTICAL);
        lay_big_Lleft.setLayoutParams(params_big_Lleft);
        lay_big_Lleft.setBackgroundResource(R.drawable.mini_small);

        lay_big_Lmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Lmid = new LinearLayout.LayoutParams((int)(wid1/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Lmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Lmid.setOrientation(LinearLayout.VERTICAL);
        lay_big_Lmid.setLayoutParams(params_big_Lmid);
        lay_big_Lmid.setBackgroundResource(R.drawable.mini_small);

        lay_big_Lright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Lright = new LinearLayout.LayoutParams((int)(wid1/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Lright.setOrientation(LinearLayout.VERTICAL);
        lay_big_Lright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Lright.setLayoutParams(params_big_Lright);
        lay_big_Lright.setBackgroundResource(R.drawable.mini_small);

        lay_big1.addView(lay_big_Lleft);
        lay_big1.addView(lay_big_Lmid);
        lay_big1.addView(lay_big_Lright);

        ViewGroup.LayoutParams bigMparams = lay_big2.getLayoutParams();
        int wid2 = bigMparams.width - 75;
//        Log.v(TAG, Integer.toString(wid2));

        lay_big_Mleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Mleft = new LinearLayout.LayoutParams((int)(wid2/3), LinearLayout.LayoutParams.MATCH_PARENT);
        params_big_Mleft.setMargins(20,0,0,0);
        lay_big_Mleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Mleft.setOrientation(LinearLayout.VERTICAL);
        lay_big_Mleft.setLayoutParams(params_big_Lleft);
        lay_big_Mleft.setBackgroundResource(R.drawable.mini_small);

        lay_big_Mmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Mmid = new LinearLayout.LayoutParams((int)(wid2/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Mmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Mmid.setOrientation(LinearLayout.VERTICAL);
        lay_big_Mmid.setLayoutParams(params_big_Lmid);
        lay_big_Mmid.setBackgroundResource(R.drawable.mini_small);

        lay_big_Mright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Mright = new LinearLayout.LayoutParams((int)(wid2/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Mright.setOrientation(LinearLayout.VERTICAL);
        lay_big_Mright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Mright.setLayoutParams(params_big_Lright);
        lay_big_Mright.setBackgroundResource(R.drawable.mini_small);

        lay_big2.addView(lay_big_Mleft);
        lay_big2.addView(lay_big_Mmid);
        lay_big2.addView(lay_big_Mright);

        ViewGroup.LayoutParams bigRparams = lay_big2.getLayoutParams();
        int wid3 = bigRparams.width - 75;
//        Log.v(TAG, Integer.toString(wid2));

        lay_big_Rleft = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Rleft = new LinearLayout.LayoutParams((int)(wid3/3), LinearLayout.LayoutParams.MATCH_PARENT);
        params_big_Rleft.setMargins(20,0,0,0);
        lay_big_Rleft.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Rleft.setOrientation(LinearLayout.VERTICAL);
        lay_big_Rleft.setLayoutParams(params_big_Lleft);
        lay_big_Rleft.setBackgroundResource(R.drawable.mini_small);

        lay_big_Rmid = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Rmid = new LinearLayout.LayoutParams((int)(wid3/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Rmid.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Rmid.setOrientation(LinearLayout.VERTICAL);
        lay_big_Rmid.setLayoutParams(params_big_Lmid);
        lay_big_Rmid.setBackgroundResource(R.drawable.mini_small);

        lay_big_Rright = new LinearLayout(this.getActivity());
        LinearLayout.LayoutParams params_big_Rright = new LinearLayout.LayoutParams((int)(wid3/3), LinearLayout.LayoutParams.MATCH_PARENT);
        lay_big_Rright.setOrientation(LinearLayout.VERTICAL);
        lay_big_Rright.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        lay_big_Rright.setLayoutParams(params_big_Lright);
        lay_big_Rright.setBackgroundResource(R.drawable.mini_small);

        lay_big3.addView(lay_big_Rleft);
        lay_big3.addView(lay_big_Rmid);
        lay_big3.addView(lay_big_Rright);


        // Creating database instance and reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User Accounts").child(mAuth.getCurrentUser().getUid());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Call another function to update the UI on the screen by passing in datasnapshot which is like the info
                updateUi(dataSnapshot.child("Growing Conditions"));

                pot_level.clear();
                plant_location_list.clear();
                pot_name.clear();

//                Log.v(TAG, pot_name.get(0));

                // for each pot in server
                for (DataSnapshot keyNode : dataSnapshot.child("Pots").getChildren()) {

                    // for each value in pot
                    for (DataSnapshot kNode : keyNode.getChildren()) {
                        if (kNode.getKey().equals("Name")) {
//                            Log.v(TAG, kNode.getValue().toString());
                            pot_name.add(kNode.getValue().toString());
                        } else if (kNode.getKey().equals("Level")) {
                            pot_level.add(Integer.parseInt(kNode.getValue().toString()));
                        } else if (kNode.getKey().equals("Location")) {
                            int[] loc_vals = new int[2];
                            for (DataSnapshot locs : kNode.getChildren()) {
                                if (locs.getKey().equals("0")) {
                                    loc_vals[0] = Integer.parseInt(locs.getValue().toString());
//                                    Log.v(TAG, locs.getValue().toString());

                                } else if (locs.getKey().equals("1")) {
                                    loc_vals[1] = Integer.parseInt(locs.getValue().toString());
//                                    Log.v(TAG, locs.getValue().toString());

                                }
                            }
                            plant_location_list.add(loc_vals);
                        }
                    }


                }

                assignPotBigL();
                assignPotBigM();
                assignPotBigR();
                assignPotL();
                assignPotM();
                assignPotR();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




//        return inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    private void updateUi(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d(TAG, "ds is:  " + ds.getKey());
            GrowingConditions growCond = new GrowingConditions();
            if (ds.getKey().equals("Temperature")) {
                textViewTemperature.setText(ds.getValue()+ "°C");
            } else if (ds.getKey().equals("Solution")) {
                textViewSolution.setText(ds.getValue() + "/1000 ml");
            } else if (ds.getKey().equals("Humidity")) {
                textViewHumidity.setText(ds.getValue() + "%");
            } else if (ds.getKey().equals("Light")) {
                textViewLight.setText(ds.getValue() + "lm");
            } else if (ds.getKey().equals("Water")) {
                textViewWater.setText(ds.getValue() + "/1000 ml");
            } else if (ds.getKey().equals("Ph")) {
                textViewPh.setText(ds.getValue() + "%");
            } else{

            }

        }
    }

    public void shiftMidtoLeft(){
        lay1.setVisibility(View.VISIBLE);
        lay2.setVisibility(View.INVISIBLE);
        lay3.setVisibility(View.INVISIBLE);
        mid_left_shift.setVisibility(View.INVISIBLE);
        mid_right_shift.setVisibility(View.INVISIBLE);
        left_mid_shift.setVisibility(View.VISIBLE);
        right_mid_shift.setVisibility(View.INVISIBLE);
//        assignPotL();
    }

    public void shiftMidtoRight(){
        lay1.setVisibility(View.INVISIBLE);
        lay2.setVisibility(View.INVISIBLE);
        lay3.setVisibility(View.VISIBLE);
        mid_left_shift.setVisibility(View.INVISIBLE);
        mid_right_shift.setVisibility(View.INVISIBLE);
        left_mid_shift.setVisibility(View.INVISIBLE);
        right_mid_shift.setVisibility(View.VISIBLE);
    }

    public void shiftRighttoMid(){
        lay1.setVisibility(View.INVISIBLE);
        lay2.setVisibility(View.VISIBLE);
        lay3.setVisibility(View.INVISIBLE);
        mid_left_shift.setVisibility(View.VISIBLE);
        mid_right_shift.setVisibility(View.VISIBLE);
        left_mid_shift.setVisibility(View.INVISIBLE);
        right_mid_shift.setVisibility(View.INVISIBLE);
    }

    public void shiftLefttoMid(){
        lay1.setVisibility(View.INVISIBLE);
        lay2.setVisibility(View.VISIBLE);
        lay3.setVisibility(View.INVISIBLE);
        mid_left_shift.setVisibility(View.VISIBLE);
        mid_right_shift.setVisibility(View.VISIBLE);
        left_mid_shift.setVisibility(View.INVISIBLE);
        right_mid_shift.setVisibility(View.INVISIBLE);
//        assignPotM();
    }

    public void assignPotBigL(){

        lay_big_Lleft.removeAllViews();
        lay_big_Lmid.removeAllViews();
        lay_big_Lright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 0 || plant_location_list.get(i)[0] == 1 || plant_location_list.get(i)[0] == 2) {

                //full pot + text
                big_pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(30, 100 + plant_location_list.get(i)[1], 0, 0);
                big_pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                big_pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                big_pot_wrapper[i].setLayoutParams(params_wrapper);

                // basic pot
                big_pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                big_pot_list[i].setOrientation(LinearLayout.VERTICAL);
                big_pot_list[i].setLayoutParams(pot_params);
                big_pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                big_pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                big_pot_progress[i] = new LinearLayout(this.getActivity());
                big_pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                big_pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                big_pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                big_pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                big_pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
                text_params.setMargins(0, 200, 0, 0);
                big_pot_text[i].setLayoutParams(text_params);
                big_pot_text[i].setText(pot_name.get(i));
                big_pot_text[i].setTextSize(10);
                big_pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                big_percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                big_percentage_text[i].setText(percentages + "%");
                big_percentage_text[i].setTextSize(8);
                big_percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_list[i].addView(big_percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_progress[i].addView(big_percentage_text[i]);
                }

                button_counter = i;
                big_button_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams big_button_params = new LinearLayout.LayoutParams(70, 70);
                big_button_list[i].setLayoutParams(big_button_params);
                big_button_list[i].setBackgroundResource(R.drawable.edit);
                switch(button_counter){
                    case 0:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName0();
                            }
                        });
                        break;
                    case 1:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName1();
                            }
                        });
                        break;
                    case 2:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName2();
                            }
                        });
                        break;
                    case 3:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName3();
                            }
                        });
                        break;
                    case 4:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName4();
                            }
                        });
                        break;
                    case 5:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName5();
                            }
                        });
                        break;
                    case 6:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName6();
                            }
                        });
                        break;
                    case 7:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName7();
                            }
                        });
                        break;
                    case 8:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName8();
                            }
                        });
                        break;
                    case 9:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName9();
                            }
                        });
                        break;
                    case 10:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName10();
                            }
                        });
                        break;
                    case 11:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName11();
                            }
                        });
                        break;
                    case 12:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName12();
                            }
                        });
                        break;
                    case 13:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName13();
                            }
                        });
                        break;
                    case 14:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName14();
                            }
                        });
                        break;
                    case 15:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName15();
                            }
                        });
                        break;
                    default:
                        Log.v(TAG, "check button counter");
                        break;


                }


                big_pot_list[i].addView(big_pot_progress[i]);


                big_pot_wrapper[i].addView(big_pot_list[i]);
                big_pot_wrapper[i].addView(big_pot_text[i]);

                if (plant_location_list.get(i)[0] == 0) {
                    lay_big_Lleft.addView(big_pot_wrapper[i]);
                    lay_big_Lleft.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 1) {
                    lay_big_Lmid.addView(big_pot_wrapper[i]);
                    lay_big_Lmid.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 2) {
                    lay_big_Lright.addView(big_pot_wrapper[i]);
                    lay_big_Lright.addView(big_button_list[i]);
                }

            }
        }

    }

    public void assignPotL(){

        lay_Lleft.removeAllViews();
        lay_Lmid.removeAllViews();
        lay_Lright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 0 || plant_location_list.get(i)[0] == 1 || plant_location_list.get(i)[0] == 2) {

                //full pot + text
                pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(25, 30 + plant_location_list.get(i)[1], 0, 0);
                pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                pot_wrapper[i].setLayoutParams(params_wrapper);
//            pot_wrapper[i].setBackgroundResource(R.drawable.background);

                // basic pot
                pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                pot_list[i].setOrientation(LinearLayout.VERTICAL);
                pot_list[i].setLayoutParams(pot_params);
                pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                pot_progress[i] = new LinearLayout(this.getActivity());
                pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
//            text_params.addRule(RelativeLayout.ABOVE, 808000 + i);
//            text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                text_params.setMargins(0, 200, 0, 0);
                pot_text[i].setLayoutParams(text_params);
                pot_text[i].setText(pot_name.get(i));
                pot_text[i].setTextSize(10);
                pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                percentage_text[i].setText(percentages + "%");
                percentage_text[i].setTextSize(8);
                percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_list[i].addView(percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_progress[i].addView(percentage_text[i]);
                }

                pot_list[i].addView(pot_progress[i]);


                pot_wrapper[i].addView(pot_list[i]);
                pot_wrapper[i].addView(pot_text[i]);

                if (plant_location_list.get(i)[0] == 0) {
                    lay_Lleft.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 1) {
                    lay_Lmid.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 2) {
                    lay_Lright.addView(pot_wrapper[i]);
                }

            }
        }


    }

    public void assignPotBigM(){

        lay_big_Mleft.removeAllViews();
        lay_big_Mmid.removeAllViews();
        lay_big_Mright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 3 || plant_location_list.get(i)[0] == 4 || plant_location_list.get(i)[0] == 5) {

                //full pot + text
                big_pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(30, 100 + plant_location_list.get(i)[1], 0, 0);
                big_pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                big_pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                big_pot_wrapper[i].setLayoutParams(params_wrapper);

                // basic pot
                big_pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                big_pot_list[i].setOrientation(LinearLayout.VERTICAL);
                big_pot_list[i].setLayoutParams(pot_params);
                big_pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                big_pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                big_pot_progress[i] = new LinearLayout(this.getActivity());
                big_pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                big_pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                big_pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                big_pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                big_pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
                text_params.setMargins(0, 200, 0, 0);
                big_pot_text[i].setLayoutParams(text_params);
                big_pot_text[i].setText(pot_name.get(i));
                big_pot_text[i].setTextSize(10);
                big_pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                big_percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                big_percentage_text[i].setText(percentages + "%");
                big_percentage_text[i].setTextSize(8);
                big_percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_list[i].addView(big_percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_progress[i].addView(big_percentage_text[i]);
                }

                button_counter = i;
                big_button_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams big_button_params = new LinearLayout.LayoutParams(70, 70);
                big_button_list[i].setLayoutParams(big_button_params);
                big_button_list[i].setBackgroundResource(R.drawable.edit);
                switch(button_counter){
                    case 0:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName0();
                            }
                        });
                        break;
                    case 1:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName1();
                            }
                        });
                        break;
                    case 2:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName2();
                            }
                        });
                        break;
                    case 3:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName3();
                            }
                        });
                        break;
                    case 4:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName4();
                            }
                        });
                        break;
                    case 5:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName5();
                            }
                        });
                        break;
                    case 6:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName6();
                            }
                        });
                        break;
                    case 7:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName7();
                            }
                        });
                        break;
                    case 8:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName8();
                            }
                        });
                        break;
                    case 9:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName9();
                            }
                        });
                        break;
                    case 10:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName10();
                            }
                        });
                        break;
                    case 11:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName11();
                            }
                        });
                        break;
                    case 12:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName12();
                            }
                        });
                        break;
                    case 13:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName13();
                            }
                        });
                        break;
                    case 14:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName14();
                            }
                        });
                        break;
                    case 15:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName15();
                            }
                        });
                        break;
                    case 16:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName16();
                            }
                        });
                        break;
                    case 17:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName17();
                            }
                        });
                        break;
                    case 18:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName18();
                            }
                        });
                        break;
                    case 19:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName19();
                            }
                        });
                        break;
                    case 20:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName20();
                            }
                        });
                        break;
                    case 21:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName21();
                            }
                        });
                        break;
                    case 22:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName22();
                            }
                        });
                        break;
                    case 23:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName23();
                            }
                        });
                        break;
                    case 24:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName24();
                            }
                        });
                        break;
                    case 25:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName25();
                            }
                        });
                        break;
                    case 26:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName26();
                            }
                        });
                        break;
                    case 27:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName27();
                            }
                        });
                        break;
                    case 28:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName28();
                            }
                        });
                        break;
                    case 29:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName29();
                            }
                        });
                        break;
                    case 30:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName30();
                            }
                        });
                        break;
                    default:
                        Log.v(TAG, "check button counter");
                        break;


                }


                big_pot_list[i].addView(big_pot_progress[i]);


                big_pot_wrapper[i].addView(big_pot_list[i]);
                big_pot_wrapper[i].addView(big_pot_text[i]);

                if (plant_location_list.get(i)[0] == 3) {
                    lay_big_Mleft.addView(big_pot_wrapper[i]);
                    lay_big_Mleft.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 4) {
                    lay_big_Mmid.addView(big_pot_wrapper[i]);
                    lay_big_Mmid.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 5) {
                    lay_big_Mright.addView(big_pot_wrapper[i]);
                    lay_big_Mright.addView(big_button_list[i]);
                }

            }
        }

    }


    public void assignPotM(){

        lay_Mleft.removeAllViews();
        lay_Mmid.removeAllViews();
        lay_Mright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 3 || plant_location_list.get(i)[0] == 4 || plant_location_list.get(i)[0] == 5) {

                //full pot + text
                pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(25, 30 + plant_location_list.get(i)[1], 0, 0);
                pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                pot_wrapper[i].setLayoutParams(params_wrapper);
//            pot_wrapper[i].setBackgroundResource(R.drawable.background);

                // basic pot
                pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                pot_list[i].setOrientation(LinearLayout.VERTICAL);
                pot_list[i].setLayoutParams(pot_params);
                pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                pot_progress[i] = new LinearLayout(this.getActivity());
                pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
//            text_params.addRule(RelativeLayout.ABOVE, 808000 + i);
//            text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                text_params.setMargins(0, 200, 0, 0);
                pot_text[i].setLayoutParams(text_params);
                pot_text[i].setText(pot_name.get(i));
                pot_text[i].setTextSize(10);
                pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                percentage_text[i].setText(percentages + "%");
                percentage_text[i].setTextSize(8);
                percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_list[i].addView(percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_progress[i].addView(percentage_text[i]);
                }

                pot_list[i].addView(pot_progress[i]);


                pot_wrapper[i].addView(pot_list[i]);
                pot_wrapper[i].addView(pot_text[i]);

                if (plant_location_list.get(i)[0] == 3) {
                    lay_Mleft.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 4) {
                    lay_Mmid.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 5) {
                    lay_Mright.addView(pot_wrapper[i]);
                }

            }
        }

    }

    public void assignPotBigR(){

        lay_big_Rleft.removeAllViews();
        lay_big_Rmid.removeAllViews();
        lay_big_Rright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 6 || plant_location_list.get(i)[0] == 7 || plant_location_list.get(i)[0] == 8) {

                //full pot + text
                big_pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(30, 100 + plant_location_list.get(i)[1], 0, 0);
                big_pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                big_pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                big_pot_wrapper[i].setLayoutParams(params_wrapper);

                // basic pot
                big_pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                big_pot_list[i].setOrientation(LinearLayout.VERTICAL);
                big_pot_list[i].setLayoutParams(pot_params);
                big_pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                big_pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                big_pot_progress[i] = new LinearLayout(this.getActivity());
                big_pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                big_pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                big_pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                big_pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                big_pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
                text_params.setMargins(0, 200, 0, 0);
                big_pot_text[i].setLayoutParams(text_params);
                big_pot_text[i].setText(pot_name.get(i));
                big_pot_text[i].setTextSize(10);
                big_pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                big_percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                big_percentage_text[i].setText(percentages + "%");
                big_percentage_text[i].setTextSize(8);
                big_percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_list[i].addView(big_percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    big_percentage_text[i].setLayoutParams(percentage_params);
                    big_pot_progress[i].addView(big_percentage_text[i]);
                }

                button_counter = i;
                big_button_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams big_button_params = new LinearLayout.LayoutParams(70, 70);
                big_button_list[i].setLayoutParams(big_button_params);
                big_button_list[i].setBackgroundResource(R.drawable.edit);
                switch(button_counter){
                    case 0:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName0();
                            }
                        });
                        break;
                    case 1:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName1();
                            }
                        });
                        break;
                    case 2:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName2();
                            }
                        });
                        break;
                    case 3:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName3();
                            }
                        });
                        break;
                    case 4:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName4();
                            }
                        });
                        break;
                    case 5:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName5();
                            }
                        });
                        break;
                    case 6:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName6();
                            }
                        });
                        break;
                    case 7:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName7();
                            }
                        });
                        break;
                    case 8:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName8();
                            }
                        });
                        break;
                    case 9:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName9();
                            }
                        });
                        break;
                    case 10:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName10();
                            }
                        });
                        break;
                    case 11:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName11();
                            }
                        });
                        break;
                    case 12:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName12();
                            }
                        });
                        break;
                    case 13:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName13();
                            }
                        });
                        break;
                    case 14:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName14();
                            }
                        });
                        break;
                    case 15:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName15();
                            }
                        });
                        break;
                    case 16:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName16();
                            }
                        });
                        break;
                    case 17:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName17();
                            }
                        });
                        break;
                    case 18:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName18();
                            }
                        });
                        break;
                    case 19:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName19();
                            }
                        });
                        break;
                    case 20:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName20();
                            }
                        });
                        break;
                    case 21:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName21();
                            }
                        });
                        break;
                    case 22:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName22();
                            }
                        });
                        break;
                    case 23:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName23();
                            }
                        });
                        break;
                    case 24:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName24();
                            }
                        });
                        break;
                    case 25:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName25();
                            }
                        });
                        break;
                    case 26:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName26();
                            }
                        });
                        break;
                    case 27:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName27();
                            }
                        });
                        break;
                    case 28:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName28();
                            }
                        });
                        break;
                    case 29:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName29();
                            }
                        });
                        break;
                    case 30:
                        big_button_list[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlantName30();
                            }
                        });
                        break;
                    default:
                        Log.v(TAG, "check button counter");
                        break;


                }


                big_pot_list[i].addView(big_pot_progress[i]);


                big_pot_wrapper[i].addView(big_pot_list[i]);
                big_pot_wrapper[i].addView(big_pot_text[i]);

                if (plant_location_list.get(i)[0] == 6) {
                    lay_big_Rleft.addView(big_pot_wrapper[i]);
                    lay_big_Rleft.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 7) {
                    lay_big_Rmid.addView(big_pot_wrapper[i]);
                    lay_big_Rmid.addView(big_button_list[i]);
                } else if (plant_location_list.get(i)[0] == 8) {
                    lay_big_Rright.addView(big_pot_wrapper[i]);
                    lay_big_Rright.addView(big_button_list[i]);
                }

            }
        }

    }


    public void assignPotR(){
        lay_Rleft.removeAllViews();
        lay_Rmid.removeAllViews();
        lay_Rright.removeAllViews();

        for(int i = 0; i < plant_location_list.size(); i++) {
            if (plant_location_list.get(i)[0] == 6 || plant_location_list.get(i)[0] == 7 || plant_location_list.get(i)[0] == 8) {

                //full pot + text
                pot_wrapper[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams params_wrapper = new LinearLayout.LayoutParams(120, 200);
                params_wrapper.setMargins(25, 30 + plant_location_list.get(i)[1], 0, 0);
                pot_wrapper[i].setOrientation(LinearLayout.HORIZONTAL);
                pot_wrapper[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                pot_wrapper[i].setLayoutParams(params_wrapper);
//            pot_wrapper[i].setBackgroundResource(R.drawable.background);

                // basic pot
                pot_list[i] = new LinearLayout(this.getActivity());
                LinearLayout.LayoutParams pot_params = new LinearLayout.LayoutParams(70, 200);
                pot_params.setMargins(100, 0, 0, 0);
                pot_list[i].setOrientation(LinearLayout.VERTICAL);
                pot_list[i].setLayoutParams(pot_params);
                pot_list[i].setHorizontalGravity(Gravity.BOTTOM);
                pot_list[i].setBackgroundResource(R.drawable.transparent_bg_pot);

                //pot level
                pot_progress[i] = new LinearLayout(this.getActivity());
                pot_progress[i].setId(808000 + i);
                int progress_height = pot_level.get(i) * 2;
                LinearLayout.LayoutParams pot_progress_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, progress_height);
                if (pot_level.get(i) < 20) {
                    pot_progress_params.setMargins(0, 0, 0, 0);
                } else {
                    pot_progress_params.setMargins(0, 200 - progress_height, 0, 0);
                }
                pot_progress[i].setLayoutParams(pot_progress_params);
                int draw_value = assignBg(pot_level.get(i));
                pot_progress[i].setBackgroundResource(draw_value);


                //pot text
                pot_text[i] = new TextView(this.getActivity());
//            pot_text[i].setId(707070 + i);
                //rotate text and shift back
                RotateAnimation rAnim = (RotateAnimation) AnimationUtils.loadAnimation(this.getActivity(), R.anim.myanim);
                rAnim.setFillAfter(true);
                pot_text[i].setAnimation(rAnim);
                RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams(150, 30);
//            text_params.addRule(RelativeLayout.ABOVE, 808000 + i);
//            text_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                text_params.setMargins(0, 200, 0, 0);
                pot_text[i].setLayoutParams(text_params);
                pot_text[i].setText(pot_name.get(i));
                pot_text[i].setTextSize(10);
                pot_text[i].setGravity(Gravity.CENTER);

                // percentage text
                percentage_text[i] = new TextView(this.getActivity());
                RelativeLayout.LayoutParams percentage_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 30);
                String percentages = Integer.toString(pot_level.get(i));
                percentage_text[i].setText(percentages + "%");
                percentage_text[i].setTextSize(8);
                percentage_text[i].setGravity(Gravity.BOTTOM);


                if (pot_level.get(i) < 20) {
                    percentage_params.setMargins(10, 170 - progress_height, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_list[i].addView(percentage_text[i]);
                } else {
                    percentage_params.setMargins(10, progress_height - 50, 0, 0);
                    percentage_text[i].setLayoutParams(percentage_params);
                    pot_progress[i].addView(percentage_text[i]);
                }

                pot_list[i].addView(pot_progress[i]);


                pot_wrapper[i].addView(pot_list[i]);
                pot_wrapper[i].addView(pot_text[i]);

                if (plant_location_list.get(i)[0] == 6) {
                    lay_Rleft.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 7) {
                    lay_Rmid.addView(pot_wrapper[i]);
                } else if (plant_location_list.get(i)[0] == 8) {
                    lay_Rright.addView(pot_wrapper[i]);
                }

            }
        }
    }

    public int assignBg(int pot) {
        int val;
        if (pot < 30) {
            val = R.drawable.transparent_bg_pot_lightgreen;
        } else if (pot < 50) {
            val = R.drawable.transparent_bg_pot_seagreen;
        } else if (pot < 80) {
            val = R.drawable.transparent_bg_pot_green;
        } else if (pot < 100) {
            val = R.drawable.transparent_bg_pot_darkgreen;
        } else {
            val = R.drawable.transparent_bg_pot_darkergreen;
        }
        return val;
    }

    public void big_layoutL_appear(){
        overlay1.setVisibility(View.VISIBLE);
        overlay1.bringToFront();
        left_mid_shift.setEnabled(false);
    }

    public void big_layoutM_appear(){
        overlay2.setVisibility(View.VISIBLE);
        overlay2.bringToFront();
        mid_left_shift.setEnabled(false);
        mid_right_shift.setEnabled(false);
    }

    public void big_layoutR_appear(){
        overlay3.setVisibility(View.VISIBLE);
        overlay3.bringToFront();
        right_mid_shift.setEnabled(false);
    }

    public void big_layoutL_disappear(){
        overlay1.setVisibility(View.GONE);
        left_mid_shift.setEnabled(true);
    }

    public void big_layoutM_disappear(){
        overlay2.setVisibility(View.GONE);
        mid_left_shift.setEnabled(true);
        mid_right_shift.setEnabled(true);
    }

    public void big_layoutR_disappear(){
        overlay3.setVisibility(View.GONE);
        right_mid_shift.setEnabled(true);
        overlay3.setVisibility(View.GONE);
    }

    public void setPlantName(int val){
        lay_big_name.setVisibility(View.VISIBLE);
        lay_big_name.bringToFront();

        internal_button_counter = val;

        set_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant_name = set_name_text.getText().toString();


                DatabaseReference mRef = database.getReference().child("User Accounts").child(mAuth.getCurrentUser().getUid()).child("Pots").child(Integer.toString(internal_button_counter));
                mRef.child("Name").setValue(plant_name);
                Log.v(TAG, "1");

                lay_big_name.setVisibility(View.INVISIBLE);

                set_name_text.getText().clear();
            }
        });
    }

    public void setPlantName0(){
        setPlantName(0);
    }

    public void setPlantName1(){
        setPlantName(1);
    }

    public void setPlantName2(){
        setPlantName(2);
    }

    public void setPlantName3(){
        setPlantName(3);
    }

    public void setPlantName4(){
        setPlantName(4);
    }

    public void setPlantName5(){
        setPlantName(5);
    }

    public void setPlantName6(){
        setPlantName(6);
    }

    public void setPlantName7(){
        setPlantName(7);
    }

    public void setPlantName8(){
        setPlantName(8);
    }

    public void setPlantName9(){
        setPlantName(9);
    }

    public void setPlantName10(){
        setPlantName(10);
    }

    public void setPlantName11(){
        setPlantName(11);
    }

    public void setPlantName12(){
        setPlantName(12);
    }

    public void setPlantName13(){
        setPlantName(13);
    }

    public void setPlantName14(){
        setPlantName(14);
    }

    public void setPlantName15(){
        setPlantName(15);
    }

    public void setPlantName16(){
        setPlantName(16);
    }

    public void setPlantName17(){
        setPlantName(17);
    }

    public void setPlantName18(){
        setPlantName(18);
    }

    public void setPlantName19(){
        setPlantName(19);
    }

    public void setPlantName20(){
        setPlantName(20);
    }

    public void setPlantName21(){
        setPlantName(21);
    }

    public void setPlantName22(){
        setPlantName(22);
    }

    public void setPlantName23(){
        setPlantName(23);
    }

    public void setPlantName24(){
        setPlantName(24);
    }

    public void setPlantName25(){
        setPlantName(25);
    }

    public void setPlantName26(){
        setPlantName(26);
    }

    public void setPlantName27(){
        setPlantName(27);
    }

    public void setPlantName28(){
        setPlantName(28);
    }

    public void setPlantName29(){
        setPlantName(29);
    }

    public void setPlantName30(){
        setPlantName(30);
    }

    public void setPlantName31(){
        setPlantName(31);
    }

    public void setPlantName32(){
        setPlantName(32);
    }

    public void setPlantName33(){
        setPlantName(33);
    }

    public void setPlantName34(){
        setPlantName(34);
    }

    public void setPlantName35(){
        setPlantName(35);
    }

    public void setPlantName36(){
        setPlantName(36);
    }

    public void setPlantName37(){
        setPlantName(37);
    }

    public void setPlantName38(){
        setPlantName(38);
    }

    public void setPlantName39(){
        setPlantName(39);
    }

    public void setPlantName40(){
        setPlantName(40);
    }

    public void setPlantName41(){
        setPlantName(41);
    }

    public void setPlantName42(){
        setPlantName(42);
    }

    public void setPlantName43(){
        setPlantName(43);
    }

    public void setPlantName44(){
        setPlantName(44);
    }

    public void setPlantName45(){
        setPlantName(45);
    }
}