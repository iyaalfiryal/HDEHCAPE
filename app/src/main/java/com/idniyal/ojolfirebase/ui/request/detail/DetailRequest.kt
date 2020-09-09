package com.idniyal.ojolfirebase.ui.request.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.idniyal.ojolfirebase.R
import com.idniyal.ojolfirebase.model.Booking
import com.idniyal.ojolfirebase.utils.Constan
import kotlinx.android.synthetic.main.activity_detail_request.*

class DetailRequest : AppCompatActivity(), OnMapReadyCallback {

    var status: Int? = null
    var booking: Booking? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_request)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapsdetail) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var auth = FirebaseAuth.getInstance()

        //menangkap objek yg dikiirm dari masing2 list
        booking = Booking()
        booking = intent.getSerializableExtra(Constan.booking) as Booking
        status = intent.getIntExtra(Constan.status, 0)

        //memindahkan object ke view
        detail_awal.text = booking?.lokasiAwal
        detail_tujuan.text = booking?.lokasiTujuan
        detail_tanggal.text = booking?.tanggal
        detail_price.text = booking?.harga

        detailBooking()

    }

    private fun detailBooking() {

        if (status == 2){
            detail_button.text = getString(R.string.complete)
        }
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(Constan.tb_booking)
        var key = ""

        val query = myRef.orderByChild("tanggal")
            .equalTo(booking?.tanggal)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                //untuk mengambil key masing2 item
                for (issue in snapshot.children){
                    key = issue.key.toString()
                }
            }

        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }
}