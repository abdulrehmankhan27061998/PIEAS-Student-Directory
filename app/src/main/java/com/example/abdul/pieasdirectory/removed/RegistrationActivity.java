//package com.example.abdul.pieasdirectory.removed;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.abdul.pieasdirectory.MainActivity;
//import com.example.abdul.pieasdirectory.Person;
//import com.example.abdul.pieasdirectory.R;
//
//import java.util.ArrayList;
//
//public class RegistrationActivity extends AppCompatActivity {
//
//    private static final String TAG = "RegistrationActivity";
//    public static final int REGISTRATION_ACTIVITY = 1;
//    private final int[] viewsID = {R.id.studentNameEditText, R.id.fatherNameEditText, R.id.cellNoEditText, R.id.extensionEditText, R.id.phoneNoEditText, R.id.emailEditText, R.id.departmentEditText, R.id.officeLocationEditText, R.id.emailEditText, R.id.addressEditText};
//    private MainActivity mainActivity;
//    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
//    private String[] inputData = new String[Person.PERSON_KEYS.length];
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//        setTitle(R.string.registration_form);
//
//        mainActivity = MainActivity.getContext();
//        initViews();
//    }
//
//    public void initViews() {
//        inputEditTexts.clear();
//        for (int aViewsID : viewsID) {
//            inputEditTexts.add((EditText) findViewById(aViewsID));
//        }
//    }
//
//    public void actionPerformed(View view) {
//        Button clickedButton = (Button) view;
//        if (clickedButton.getText().equals("Add")) {
//            setInputData();
//            if (allFieldsFilled()) {
////                if (isValidData()) {
////                    Person person = new Person(inputData);
////                    DatabaseHandler.insertPerson(this, person);
////                    mainActivity.addPerson(person);
////                    mainActivity.notifyDataSetChanged();
////                    mainActivity.loadNewPersonsData();
//                    finish();
////                } else {
////                    Toast.makeText(this, "Invalid Data Provided", Toast.LENGTH_SHORT).show();
////                }
//            } else {
//                Toast.makeText(this, "All Input Fields Not Provided", Toast.LENGTH_SHORT).show();
//            }
//        } else if (clickedButton.getText().equals("Cancel")) {
//            finish();
//        }
//    }
//
//    public void setInputData() {
//        for (int i = 0; i < inputData.length; i++) {
//            inputData[i] = inputEditTexts.get(i).getText().toString();
//        }
//    }
//
//    public boolean allFieldsFilled() {
//        for (String input : inputData) {
//            if (input.equals("")) {
//                return false;
//            } else {
//                int chars = 0;
//                for (int j = 0; j < input.length(); j++) {
//                    if (input.charAt(j) != ' ') {
//                        chars++;
//                        break;
//                    }
//                }
//                if (chars == 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
////    public boolean isValidData() {
////        return Person.isValidHostel(inputData[7].toUpperCase()) &&
////                Person.isValidPhoneNo(inputData[12]) &&
////                Person.isValidDepartment(inputData[4]) &&
////                Person.isValidRegNo(inputData[2]);
////    }
//
//}
