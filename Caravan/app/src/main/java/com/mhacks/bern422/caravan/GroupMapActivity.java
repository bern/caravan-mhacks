package com.mhacks.bern422.caravan;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GroupMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private Firebase myFirebaseRef;
    private Firebase users;
    private Firebase trips;
    private GoogleMap groupMap;
    private HashMap<String, Marker> userToMarkerMap;
    private HashMap<String, DataSnapshot> idToUsersMap;
    private HashMap<String, DataSnapshot> idToUserListMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.map_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.groupMap = map;

        setFirebaseTrip();

        //LatLng sydney = new LatLng(-33.867, 151.206);

        //map.setMyLocationEnabled(true);
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        //map.addMarker(new MarkerOptions()
        //        .title("Sydney")
        //        .snippet("The most populous city in Australia.")
        //        .position(sydney));


    }

    public void setFirebaseTrip() {
        myFirebaseRef = new Firebase("https://fiery-fire-5591.firebaseio.com/");

        trips = myFirebaseRef.child("trips");

        users = myFirebaseRef.child("users");

        idToUsersMap = new HashMap<String, DataSnapshot>();
        idToUserListMap = new HashMap<String, DataSnapshot>();

        Query userQuery = users.orderByKey();

        userQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                idToUsersMap.put((String)(snapshot.getKey()), snapshot);
                Set<String> keySet = idToUsersMap.keySet();
                for(String key : keySet) {
                    Firebase tempUserListRef = users.child(key).child("userList");
                    Query userListQuery = tempUserListRef.orderByKey();

                    final String _key = key;

                    userListQuery.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot _snapshot, String _previousChild) {
                            idToUserListMap.put(_key, _snapshot);
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot _snapshot) {
                            // ???
                        }

                        @Override
                        public void onChildChanged(DataSnapshot _snapshot, String _previousChild) {
                            idToUserListMap.put(_key, _snapshot);
                        }

                        @Override
                        public void onChildMoved(DataSnapshot _snapshot, String _previousChild) {
                            // ???
                        }

                        @Override
                        public void onCancelled(FirebaseError _error) {
                            // ???
                        }
                    });
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                // ???
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChild) {
                idToUsersMap.put(snapshot.getKey(), snapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChild) {
                // ???
            }

            @Override
            public void onCancelled(FirebaseError error) {
                // ???
            }
        });

        User user = new User("1334", new Location(-40.000, 152.000));
        User user1 = new User("154", new Location(-30.000, 152.000));
        User user2 = new User("197", new Location(-20.000, 152.000));
        UserList userList = new UserList("1234", user, user1, user2);

        Trip trip =
                new Trip(
                        "1234",
                        new Location(-33.867, 151.206),
                        new Location(-50.867, 155.206)
                );

        trips.push().setValue(trip);

        users.push().setValue(userList);
    }
}