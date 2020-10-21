package com.example.corotinestest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.corotinestest.R
import com.example.corotinestest.core.model.UserResponseModelItem
import com.example.corotinestest.ui.adapter.BaseAdapter
import com.example.corotinestest.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val getUserViewModel by viewModel<UserViewModel>()
    private var adapter: BaseAdapter<UserResponseModelItem>? = null
    private var userList: ArrayList<UserResponseModelItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //observerUserModel()
/*        GlobalScope.launch {
            getUserViewModel.getUser()
        }*/

        //setAdapter()
        //recycler.adapter = adapter
    }


/*    private fun setAdapter() {
        adapter = BaseAdapter(this, R.layout.row_item_main_recycler, userList)
        adapter!!.setItemView(object : ISetItemView<UserResponseModelItem> {
            override fun setItemView(v: View?, item: UserResponseModelItem, position: Int) {
                val tw1 = v!!.findViewById(R.id.tw1) as AppCompatTextView
                val tw2 = v.findViewById(R.id.tw2) as AppCompatTextView
                val tw3 = v.findViewById(R.id.tw3) as AppCompatTextView
                val detailAdressStreet =
                    v.findViewById(R.id.detailAdressStreet) as AppCompatTextView
                val detailAdressSuite =
                    v.findViewById(R.id.detailAdressSuite) as AppCompatTextView
                val detailAdressCity = v.findViewById(R.id.detailAdressCity) as AppCompatTextView
                val detailAdressZipCode =
                    v.findViewById(R.id.detailAdressZipCode) as AppCompatTextView
                val buttonAdress = v.findViewById(R.id.buttonAdress) as AppCompatButton
                val expandableLayout = v.findViewById(R.id.expandableLayout) as LinearLayout
                val card = v.findViewById(R.id.card) as CardView


                tw1.text = item.username
                tw2.text = item.name
                tw3.text = item.email
                detailAdressStreet.text = item.address.street
                detailAdressCity.text = item.address.city
                detailAdressSuite.text = item.address.suite
                detailAdressZipCode.text = item.address.zipcode


                if (item.expandable) {
                    expandableLayout.visibility = View.VISIBLE
                } else {
                    expandableLayout.visibility = View.GONE
                }
                buttonAdress.setOnClickListener {
                    if (item.expandable) {
                        item.expandable = false
                        adapter?.notifyDataSetChanged()
                        return@setOnClickListener
                    }
                    item.expandable = true
                    adapter?.notifyDataSetChanged()
                }
                card.setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("key", item)
                    startActivity(intent)
                }


            }

        })
    }


    private fun observerUserModel() {
        getUserViewModel.loading.observe(this, {
            if (it)
                Toast.makeText(this, "Veriler yukleniyor", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Veriler yuklendi", Toast.LENGTH_LONG).show()
        })
        getUserViewModel.responseErrorModel.observe(this, {
            //ERROR
        })
        getUserViewModel.resultResponse.observe(this, {
            //Succes
            adapter?.setList(it)
            println(it.toString())
        })
    }*/
}