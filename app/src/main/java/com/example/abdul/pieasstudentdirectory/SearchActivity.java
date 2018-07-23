package com.example.abdul.pieasstudentdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    public static final int SEARCH_ACTIVITY = 3;

    private MainActivity mainActivity;
    private String[] labelStrings = {"Name : ", "Registration# : ", "Department : ", "Phone# : ", "Gender : "};
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private Button addButton, cancelButton;
    private ArrayList<String> searchTags = new ArrayList<>();

    private HashMap<String, ArrayList<Integer>> matchedIndex;
    private ArrayList<Student> studentList;
    private ArrayList<Student> searchedStudentList = new ArrayList<>();
    private ArrayList<Integer> finalSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(MainActivity.getContext(), "SearchActivity Started", Toast.LENGTH_SHORT).show();
        Log.i("SearchActivity", "onCreate");

        Intent intent = getIntent();
        studentList = (ArrayList<Student>) intent.getExtras().get("studentArrayList");
        mainActivity = MainActivity.getContext();

        inputEditTexts.add((EditText) findViewById(R.id.nameEditText));
        inputEditTexts.add((EditText) findViewById(R.id.regNoEditText));
        inputEditTexts.add((EditText) findViewById(R.id.departmentEditText));
        inputEditTexts.add((EditText) findViewById(R.id.phoneNoEditView));
        inputEditTexts.add((EditText) findViewById(R.id.genderEditText));
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Search")) {
            Toast.makeText(MainActivity.getContext(), "Search is Clicked", Toast.LENGTH_SHORT).show();

            matchedIndex = new HashMap<>();
            setSearchTags();

//            for (int i = 0; i < searchTags.size(); i++) {
//                Log.i("SearchActivity", "actionPerformed : " + i + " -> " + searchTags.get(i));
//            }

            matchedIndex.put(searchTags.get(0), searchByName());
            matchedIndex.put(searchTags.get(1), searchByRegNo());
            matchedIndex.put(searchTags.get(2), searchByDepartment());
            matchedIndex.put(searchTags.get(3), searchByPhoneNo());
            matchedIndex.put(searchTags.get(4), searchByGender());
//            showMap();

//            Log.i("SearchActivity", "actionPerformed : " + "Intersection of searchTags.get(0) and searchTags.get(1) -> ");
            finalSearch = intersect(matchedIndex.get(searchTags.get(0)), matchedIndex.get(searchTags.get(1)));
//            printList(finalSearch);

//            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(2) -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(2)));
//            printList(finalSearch);

//            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(3) -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(3)));
//            printList(finalSearch);

//            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(4) -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(4)));
//            printList(finalSearch);

            Toast.makeText(MainActivity.getContext(), finalSearch.size() + " Match Found", Toast.LENGTH_SHORT).show();
            if(finalSearch.size() != 0){
                setSearchedStudentList();
                mainActivity.setCustomAdapter(new CustomAdapter(mainActivity, searchedStudentList));
                finish();
            }
        } else if (clickedButton.getText().equals("Cancel")) {
            Toast.makeText(MainActivity.getContext(), "Cancel is Clicked", Toast.LENGTH_SHORT).show();
            Log.i("SearchActivity", "actionPerformed : " + "Cancel Button Clicked");
            setIntent(new Intent());
            finish();
        }
    }

    public void setSearchedStudentList() {
        for (Integer i : finalSearch) {
            searchedStudentList.add(studentList.get(i));
        }
    }

    public void setSearchTags() {
        searchTags.clear();
        for (int i = 0; i < inputEditTexts.size(); i++) {
            searchTags.add(inputEditTexts.get(i).getText().toString());
        }
    }

    public ArrayList<Integer> searchByName() {
        ArrayList<Integer> matchedStudents = new ArrayList<>();
        if (searchTags.get(0).length() != 0) {
            Log.i("SearchActivity", "searchByName : " + "searchTags.get(0) -> " + searchTags.get(0));
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getStudentName().toLowerCase().contains(searchTags.get(0).toLowerCase())) {
                    Log.i("SearchActivity", "searchByName : " + i + "th student's name contains " + searchTags.get(0));
                    matchedStudents.add(i);
                }
            }
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByRegNo() {
        ArrayList<Integer> matchedStudents = new ArrayList<>();
        if (searchTags.get(1).length() != 0) {
            Log.i("SearchActivity", "searchByRegNo : " + "searchTags.get(1) -> " + searchTags.get(1));
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getRegNo().toLowerCase().equals(searchTags.get(1).toLowerCase())) {
                    Log.i("SearchActivity", "searchByRegNo : " + i + "th student's regNo is " + searchTags.get(1));
                    matchedStudents.add(i);
                }
            }
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByDepartment() {
        ArrayList<Integer> matchedStudents = new ArrayList<>();
        if (searchTags.get(2).length() != 0) {
            Log.i("SearchActivity", "searchByDepartment : " + "searchTags.get(2) -> " + searchTags.get(2));
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getDepartment().toLowerCase().contains(searchTags.get(2).toLowerCase())) {
                    Log.i("SearchActivity", "searchByDepartment : " + i + "th student's department contains " + searchTags.get(2));
                    matchedStudents.add(i);
                }
            }
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByPhoneNo() {
        ArrayList<Integer> matchedStudents = new ArrayList<>();
        if (searchTags.get(3).length() != 0) {
            Log.i("SearchActivity", "searchByPhoneNo : " + "searchTags.get(3) -> " + searchTags.get(3));
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getPhoneNo().toLowerCase().equals(searchTags.get(3).toLowerCase())) {
                    Log.i("SearchActivity", "searchByPhoneNo : " + i + "th student's phoneNo is " + searchTags.get(3));
                    matchedStudents.add(i);
                }
            }
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByGender() {
        ArrayList<Integer> matchedStudents = new ArrayList<>();
        if (searchTags.get(4).length() != 0) {
            Log.i("SearchActivity", "searchByGender : " + "searchTags.get(4) -> " + searchTags.get(4));
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getGender().toLowerCase().charAt(0) == searchTags.get(4).toLowerCase().charAt(0)) {
                    Log.i("SearchActivity", "searchByName : " + i + "th student's gender contains " + searchTags.get(4));
                    matchedStudents.add(i);
                }
            }
        }
        return matchedStudents;
    }

    public ArrayList<Integer> intersect(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> intersection = new ArrayList<>();

        if (list1.size() != 0 && list2.size() == 0) {
            Log.i("SearchActivity", "intersect : " + "list1 is not empty and list2 is empty");
            return list1;
        } else if (list1.size() == 0 && list2.size() != 0) {
            Log.i("SearchActivity", "intersect : " + "list2 is not empty and list1 is empty");
            return list2;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i).equals(list2.get(j))) {
                        intersection.add(list1.get(i));
                        break;
                    }
                }
            }
            return intersection;
        }
    }

    public <T> void printList(ArrayList<T> list) {
        if (list != null) {
            Log.i("SearchActivity", "printList : " + "Start");
            Log.i("SearchActivity", "printList : " + "size() -> " + list.size());
            for (T t : list) {
                Log.i("SearchActivity", "printList : " + "t -> " + t);
            }
            Log.i("SearchActivity", "printList : " + "End");
        }
    }

    public void showMap() {
        Set<String> keys = matchedIndex.keySet();
        for (String k : keys) {
            Log.i("SearchActivity", "showMap : " + "key -> " + k);
            if (matchedIndex.get(k) != null) {
                for (int i : matchedIndex.get(k)) {
                    Log.i("SearchActivity", "showMap : " + "value -> " + i);
                }
            }
        }
    }

}
