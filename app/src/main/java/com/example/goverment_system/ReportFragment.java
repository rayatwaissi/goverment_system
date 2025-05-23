package com.example.goverment_system;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.Manifest;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReportFragment extends Fragment {
    final int CAMERA_REQUEST = 100, PERMISSION_CODE = 101;
    ImageButton imageButton;

    private MapView mapView;
    private GoogleMap googleMap;
    private LatLng selectedLocation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_issue, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // مهم لتشتغل الخريطة

        MapsInitializer.initialize(requireContext());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                googleMap = gMap;

                // حرك الكاميرا لموقع مبدأي (مثلاً عمان)
                LatLng jordan = new LatLng(31.9539, 35.9106);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jordan, 12));

                // عند الضغط على الخريطة
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear(); // احذف الماركر القديم
                        googleMap.addMarker(new MarkerOptions().position(latLng).title("موقع البلاغ"));
                        selectedLocation = latLng; // خزّن الإحداثيات
                    }
                });
            }
        });




        ImageButton arrow_back=view.findViewById(R.id.backArrow);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_main,new HomeFragment())
                        .commit();
            }
        });
//-------------------------------------------------------------------------------------------------------------

/*

simple_spinner_item`          | عرض العنصر المُختار حاليًا
`simple_spinner_dropdown_item` | عرض العناصر عند فتح القائمة

*/
        Spinner spinnerGovernorates =view.findViewById(R.id.spinner_governorates);
    /*    ينشئ Adapter (المسؤول عن ربط البيانات مع العناصر المعروضة).
 يستخدم مصفوفة القيم الموجودة في strings.xml باسم governorates_array.
 يستخدم الشكل الجاهز simple_spinner_item لعرض كل عنصر داخل الـ Spinner.
*/

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(requireContext(),
                R.array.governorates_array,
                android.R.layout.simple_spinner_item);
      //يحدد الشكل (Layout) الذي يستخدمه عندما تُفتح القائمة المنسدلة وتعرض العناصر
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // يربط Adapter بالـ Spinner ليتم عرض القائمة بداخله
        spinnerGovernorates.setAdapter(adapter);
       // Spinner ComptentAuthority =view.findViewById(R.id.spAuthority);


        Spinner spinner_authority=view.findViewById(R.id.spAuthority);
        Spinner issue_type=view.findViewById(R.id.sptype);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(requireContext()
        ,R.array.competent_authority
        , android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_authority.setAdapter(adapter1);


        spinner_authority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // String selected_type=spinner_authority.getSelectedItem().toString();
                String selectedAuthority = parent.getItemAtPosition(position).toString();


                if(selectedAuthority.equals("Ministry of Education")){
                ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(requireContext()
                ,R.array.IssueEducation
                ,android.R.layout.simple_spinner_item);

                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                issue_type.setAdapter(adapter2);}
                else

                if(selectedAuthority.equals("Ministry of Transportation")){
                    ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(requireContext()
                            ,R.array.IssueTransportation
                            ,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    issue_type.setAdapter(adapter3);
                }
               else
                   if(selectedAuthority.equals("Ministry of Health"))
                   {
                       ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource(requireContext()
                       ,R.array.IssueHealth
                       , android.R.layout.simple_spinner_item);
                       adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       issue_type.setAdapter(adapter4);
                   }
                   else
                       if(selectedAuthority.equals("Ministry of Tourism"))
                       {
                           ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(requireContext()
                           ,R.array.IssueTourist
                           , android.R.layout.simple_spinner_item);
                           adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           issue_type.setAdapter(adapter5);
                       }

                       else
                       if(selectedAuthority.equals("Ministry of Water and Irrigation"))
                       {
                           ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(requireContext()
                                   ,R.array.IssueWater
                                   , android.R.layout.simple_spinner_item);
                           adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           issue_type.setAdapter(adapter5);
                       }

                       else
                       if(selectedAuthority.equals("Ministry of Environment"))
                       {
                           ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(requireContext()
                                   ,R.array.IssueEnvironment
                                   , android.R.layout.simple_spinner_item);
                           adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           issue_type.setAdapter(adapter5);
                       }
                       else
                       if(selectedAuthority.equals("Ministry of Labor"))
                       {
                           ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(requireContext()
                                   ,R.array.IssueLabor
                                   , android.R.layout.simple_spinner_item);
                           adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           issue_type.setAdapter(adapter5);
                       }
                       else
                       if(selectedAuthority.equals("Ministry of Communications and Information Technology"))
                       {
                           ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(requireContext()
                                   ,R.array.IssueCommunication
                                   , android.R.layout.simple_spinner_item);
                           adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           issue_type.setAdapter(adapter5);
                       }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
//-----------------------------------------------------------------------------------------------------------------------------

        EditText editTextDate = view.findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(calendar.YEAR);
            int month = calendar.get(calendar.MONTH);
            int day = calendar.get(calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year1, month1, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (month1 + 1) + "-" + year1;
                        editTextDate.setText(selectedDate);
                    }, year, month, day);
            //نعرض نافذة اختيار التاريخ للمستخدم.
            datePickerDialog.show();
        });
//-----------------------------------------------------------------------------------------------------------------------------


        imageButton = view.findViewById(R.id.camera);

        imageButton.setOnClickListener(v -> {
            // هل تطبيقك يملك إذن استخدام الكاميرا؟،   إذا لم يكن لديه الإذن → ننتقل للسطر التالي

            //طلب من المستخدم السماح باستخدام الكاميرا (تظهر له نافذة نظام تسأله).
            //PERMISSION_CODE هو رقم (مثلاً 100) نستخدمه لاحقًا لنعرف ما هو الطلب
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE);
            } else {
               openCamera();
            }
        });






//--------------------------------------------------------------------------------------------------------------------------
    Button submitBtn=view.findViewById(R.id.submit_report);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGovernorate = spinnerGovernorates.getSelectedItemPosition();
                int selectedauthority = spinner_authority.getSelectedItemPosition();
                int selected_issue_type = issue_type.getSelectedItemPosition();

                EditText EditDATE=view.findViewById(R.id.editTextDate);
                String date=EditDATE.getText().toString().trim();

                EditText title=view.findViewById(R.id.Title_txt);
                String titleSt=title.getText().toString().trim();

                EditText Edit=view.findViewById(R.id.DisTxt);
                String discription=Edit.getText().toString().trim() ;



                if (selectedGovernorate == 0) {
                    Toast.makeText(getContext(), "Please select a governorate", Toast.LENGTH_LONG).show();
                    return;
                }

                if (selectedauthority == 0) {
                    Toast.makeText(getContext(), "Please select competent authority", Toast.LENGTH_LONG).show();
                    return;
                }

                if (selected_issue_type == 0) {
                    Toast.makeText(getContext(), "Please select issue type", Toast.LENGTH_LONG).show();
                    return;
                }
                if (date.isEmpty()) {
                    Toast.makeText(getContext(), "Please select date", Toast.LENGTH_LONG).show();
                    return;
                }

                if (titleSt.isEmpty()) {
                    Toast.makeText(getContext(), "Please write a title for the issue ", Toast.LENGTH_LONG).show();
                    return;
                }

                if (discription.isEmpty()) {
                    Toast.makeText(getContext(), "Please describe the issue", Toast.LENGTH_LONG).show();
                    return;
                }

// داخل onClick زر الإرسال بعد التحقق من صحة البيانات:

// 1. جلب البيانات من الفورم:
                String governorateStr = spinnerGovernorates.getSelectedItem().toString();
                String authorityStr = spinner_authority.getSelectedItem().toString();
                String issueTypeStr = issue_type.getSelectedItem().toString();
                String dateStr = date;
                String titleStr = titleSt;
                String descriptionStr = discription;

                double lat = 0.0, lng = 0.0;
                if (selectedLocation != null) {
                    lat = selectedLocation.latitude;
                    lng = selectedLocation.longitude;
                } else {
                    Toast.makeText(getContext(), "Please select a location on the map", Toast.LENGTH_LONG).show();
                    return;
                }

// 2. إذا عندك صورة، لازم ترفعيها أولاً على Firebase Storage لتحصلي على رابط، لكن لو بدون صورة، يمكن تترك imageUrl فارغ أو null
                String imageUrl = null; // هنا خليها null أو رابط الصورة بعد رفعها (سنشرح لاحقاًد
// 3. إنشاء كائن البلاغ:

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userEmail = currentUser != null ? currentUser.getEmail() : "unknown_user";


                ReportFirebase report = new ReportFirebase(
                        governorateStr,
                        authorityStr,
                        issueTypeStr,
                        dateStr,
                        titleStr,
                        descriptionStr,
                        lat,
                        lng,
                        imageUrl,
                        userEmail

                );

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reports");
                String reportId = databaseReference.push().getKey();

                if (reportId != null) {
                    databaseReference.child(reportId).setValue(report).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Report submitted successfully!", Toast.LENGTH_LONG).show();
                            // هنا ممكن تمسح الحقول أو تنقل المستخدم لشاشة أخرى
                        } else {
                            Toast.makeText(getContext(), "Failed to submit report: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });


        return view;
        }


        /*
        openCamera() تُستخدم لفتح تطبيق الكاميرا.
        Intent يخبر النظام بأننا نريد التقاط صورة بالكاميرا.
        startActivityForResult() يعني: "افتح الكاميرا وارجع لي النتيجة لاحقًا".
         CAMERA_REQUEST هو رقم تعريف للطلب (مثل 101).*/
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }

  @Override
  //إذا وافق يفتح الكاميرا→ onRequestPermissionsResult .
    public void onRequestPermissionsResult(int code, String[] perms, int[] results) {
        if (code == PERMISSION_CODE && results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        }
    }

    @Override
    //اذا التقط صورة بالكاميرا  يستقبل الصورة ويعرضها → onActivityResult.



    public void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);

        if (reqCode == CAMERA_REQUEST && resCode == Activity.RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(image);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null) mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }


}
